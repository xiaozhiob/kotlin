/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.serialization.compiler.resolve

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.lazy.IrMaybeDeserializedClass
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.classOrNull
import org.jetbrains.kotlin.ir.types.isAny
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtDeclarationWithInitializer
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperClassNotAny
import org.jetbrains.kotlin.resolve.hasBackingField
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter
import org.jetbrains.kotlin.resolve.source.getPsi
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor
import org.jetbrains.kotlin.serialization.deserialization.getName
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.checker.SimpleClassicTypeSystemContext.isInterface
import org.jetbrains.kotlinx.serialization.compiler.backend.common.isInitializePropertyFromParameter
import org.jetbrains.kotlinx.serialization.compiler.backend.common.isInternalSerializable
import org.jetbrains.kotlinx.serialization.compiler.backend.common.isInternallySerializableEnum
import org.jetbrains.kotlinx.serialization.compiler.diagnostic.SERIALIZABLE_PROPERTIES
import org.jetbrains.kotlinx.serialization.compiler.extensions.SerializationDescriptorSerializerPlugin
import org.jetbrains.kotlinx.serialization.compiler.extensions.SerializationPluginMetadataExtensions

interface ISerializableProperties<D, T, S : ISerializableProperty<D, T>> {
    val serializableProperties: List<S>
    val isExternallySerializable: Boolean
    val serializableConstructorProperties: List<S>
    val serializableStandaloneProperties: List<S>
}

class SerializableProperties(private val serializableClass: ClassDescriptor, val bindingContext: BindingContext?) :
    ISerializableProperties<PropertyDescriptor, KotlinType, SerializableProperty> {
    private val primaryConstructorParameters: List<ValueParameterDescriptor> =
        serializableClass.unsubstitutedPrimaryConstructor?.valueParameters ?: emptyList()

    override val serializableProperties: List<SerializableProperty>
    override val isExternallySerializable: Boolean
    private val primaryConstructorProperties: Map<PropertyDescriptor, Boolean>

    init {
        val descriptorsSequence = serializableClass.unsubstitutedMemberScope.getContributedDescriptors(DescriptorKindFilter.VARIABLES)
            .asSequence()
        // call to any BindingContext.get should be only AFTER MemberScope.getContributedDescriptors
        // TODO: fix binding context shit
        primaryConstructorProperties =
            primaryConstructorParameters.asSequence()
                .map { parameter ->
                    bindingContext?.get(
                        BindingContext.VALUE_PARAMETER_AS_PROPERTY,
                        parameter
                    ) to parameter.declaresDefaultValue()
                }
                .mapNotNull { (a, b) -> if (a == null) null else a to b }
                .toMap()

        fun isPropSerializable(it: PropertyDescriptor) =
            if (serializableClass.isInternalSerializable) !it.annotations.serialTransient
            else !DescriptorVisibilities.isPrivate(it.visibility) && ((it.isVar && !it.annotations.serialTransient) || primaryConstructorProperties.contains(
                it
            ))

        serializableProperties = descriptorsSequence.filterIsInstance<PropertyDescriptor>()
            .filter { it.kind == CallableMemberDescriptor.Kind.DECLARATION }
            .filter(::isPropSerializable)
            .map { prop ->
                val declaresDefaultValue = prop.declaresDefaultValue()
                SerializableProperty(
                    prop,
                    primaryConstructorProperties[prop] ?: false,
                    prop.hasBackingField(bindingContext) || (prop is DeserializedPropertyDescriptor && prop.backingField != null) // workaround for TODO in .hasBackingField
                            // workaround for overridden getter (val) and getter+setter (var) - in this case hasBackingField returning false
                            // but initializer presents only for property with backing field
                            || declaresDefaultValue
                            || prop.backingField != null, // todo: find out what happens next
                    declaresDefaultValue
                )
            }
            .filterNot { it.transient }
            .partition { primaryConstructorProperties.contains(it.descriptor) }
            .run {
                val supers = serializableClass.getSuperClassNotAny()
                if (supers == null || !supers.isInternalSerializable)
                    first + second
                else
                    SerializableProperties(supers, bindingContext).serializableProperties + first + second
            }
            .let { unsort(serializableClass, it) }

        isExternallySerializable =
            serializableClass.isInternallySerializableEnum() || primaryConstructorParameters.size == primaryConstructorProperties.size

    }

    override val serializableConstructorProperties: List<SerializableProperty> =
        serializableProperties.asSequence()
            .filter { primaryConstructorProperties.contains(it.descriptor) }
            .toList()

    override val serializableStandaloneProperties: List<SerializableProperty> =
        serializableProperties.minus(serializableConstructorProperties)

    val size = serializableProperties.size
    operator fun get(index: Int) = serializableProperties[index]
    operator fun iterator() = serializableProperties.iterator()

    val primaryConstructorWithDefaults = serializableClass.unsubstitutedPrimaryConstructor
        ?.original?.valueParameters?.any { it.declaresDefaultValue() } ?: false
}

fun PropertyDescriptor.declaresDefaultValue(): Boolean {
    when (val declaration = this.source.getPsi()) {
        is KtDeclarationWithInitializer -> return declaration.initializer != null
        is KtParameter -> return declaration.defaultValue != null
        is Any -> return false // Not-null check
    }
    // PSI is null, property is from another module
    if (this !is DeserializedPropertyDescriptor) return false
    val myClassCtor = (this.containingDeclaration as? ClassDescriptor)?.unsubstitutedPrimaryConstructor ?: return false
    // If property is a constructor parameter, check parameter default value
    // (serializable classes always have parameters-as-properties, so no name clash here)
    if (myClassCtor.valueParameters.find { it.name == this.name }?.declaresDefaultValue() == true) return true
    // If it is a body property, then it is likely to have initializer when getter is not specified
    // note this approach is not working well if we have smth like `get() = field`, but such cases on cross-module boundaries
    // should be very marginal. If we want to solve them, we need to add protobuf metadata extension.
    if (getter?.isDefault == true) return true
    return false
}


internal val ISerializableProperties<*, *, *>.goldenMask: Int
    get() {
        var goldenMask = 0
        var requiredBit = 1
        for (property in serializableProperties) {
            if (!property.optional) {
                goldenMask = goldenMask or requiredBit
            }
            requiredBit = requiredBit shl 1
        }
        return goldenMask
    }

internal val ISerializableProperties<*, *, *>.goldenMaskList: List<Int>
    get() {
        val maskSlotCount = serializableProperties.bitMaskSlotCount()
        val goldenMaskList = MutableList(maskSlotCount) { 0 }

        for (i in serializableProperties.indices) {
            if (!serializableProperties[i].optional) {
                val slotNumber = i / 32
                val bitInSlot = i % 32
                goldenMaskList[slotNumber] = goldenMaskList[slotNumber] or (1 shl bitInSlot)
            }
        }
        return goldenMaskList
    }

internal fun List<ISerializableProperty<*, *>>.bitMaskSlotCount() = size / 32 + 1
internal fun bitMaskSlotAt(propertyIndex: Int) = propertyIndex / 32

internal fun BindingContext?.serializablePropertiesFor(
    classDescriptor: ClassDescriptor,
    serializationDescriptorSerializer: SerializationDescriptorSerializerPlugin? = null
): SerializableProperties {
    val props = this?.get(SERIALIZABLE_PROPERTIES, classDescriptor) ?: SerializableProperties(classDescriptor, this)
    serializationDescriptorSerializer?.putIfNeeded(classDescriptor, props)
    return props
}

private fun unsort(descriptor: ClassDescriptor, props: List<SerializableProperty>): List<SerializableProperty> {
    if (descriptor !is DeserializedClassDescriptor) return props
    val correctOrder: List<Name> = descriptor.classProto.getExtension(SerializationPluginMetadataExtensions.propertiesNamesInProgramOrder)
        .map { descriptor.c.nameResolver.getName(it) }
    val propsMap = props.associateBy { it.descriptor.name }
    return correctOrder.map { propsMap.getValue(it) }
}

class IrSerializableProperties(
    override val serializableProperties: List<IrSerializableProperty>,
    override val isExternallySerializable: Boolean,
    override val serializableConstructorProperties: List<IrSerializableProperty>,
    override val serializableStandaloneProperties: List<IrSerializableProperty>
) : ISerializableProperties<IrProperty, IrSimpleType, IrSerializableProperty> {
}

internal fun BindingContext?.serializablePropertiesForIrBackend(
    classDescriptor: IrClass,
    serializationDescriptorSerializer: SerializationDescriptorSerializerPlugin? = null
): IrSerializableProperties {
    if (this != null) {
        // can work with old FE
        TODO("Support this for old FE")
    } else {
        val properties = classDescriptor.properties.toList()
        val primaryConstructorParams = classDescriptor.primaryConstructor?.valueParameters.orEmpty()
        val primaryParamsAsProps = properties.associateBy { it.name }.let { namesMap ->
            primaryConstructorParams.mapNotNull {
                if (it.name !in namesMap) null else namesMap.getValue(it.name) to it.hasDefaultValue()
            }.toMap()
        }

        fun isPropSerializable(it: IrProperty) =
            if (classDescriptor.isInternalSerializable) !it.annotations.hasAnnotation(SerializationAnnotations.serialTransientFqName)
            else !DescriptorVisibilities.isPrivate(it.visibility) && ((it.isVar && !it.annotations.hasAnnotation(SerializationAnnotations.serialTransientFqName)) || primaryParamsAsProps.contains(
                it
            ))

        val (primaryCtorSerializableProps, bodySerializableProps) = properties
            .filter { !it.isFakeOverride && !it.isDelegated }
            .filter(::isPropSerializable)
            .map {
                val isConstructorParameterWithDefault = primaryParamsAsProps[it] ?: false
                IrSerializableProperty(
                    it,
                    isConstructorParameterWithDefault,
                    it.backingField != null,
                    it.backingField?.initializer.let { init -> init != null && !init.expression.isInitializePropertyFromParameter() } || isConstructorParameterWithDefault
                )
            }
            .filterNot { it.transient }
            .partition { primaryParamsAsProps.contains(it.descriptor) }

        val serializableProps = run {
            val supers = classDescriptor.getParentClassNotAny()
            if (supers == null || !supers.isInternalSerializable)
                primaryCtorSerializableProps + bodySerializableProps
            else
                serializablePropertiesForIrBackend(
                    supers,
                    serializationDescriptorSerializer
                ).serializableProperties + primaryCtorSerializableProps + bodySerializableProps
        } // todo: implement unsorting

        val isExternallySerializable =
            classDescriptor.isInternallySerializableEnum() || primaryConstructorParams.size == primaryParamsAsProps.size

        return IrSerializableProperties(serializableProps, isExternallySerializable, primaryCtorSerializableProps, bodySerializableProps)
    }
}

fun IrClass.getParentClassNotAny(): IrClass? {
    val parentClass =
        superTypes
            .mapNotNull { it.classOrNull?.owner }
            .singleOrNull { it.kind == ClassKind.CLASS || it.kind == ClassKind.ENUM_CLASS } ?: return null
    return if (parentClass.defaultType.isAny()) null else parentClass
}