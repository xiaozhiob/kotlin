/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.project.structure.impl

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.analysis.project.structure.KtLibraryModule
import org.jetbrains.kotlin.analysis.project.structure.KtLibrarySourceModule
import org.jetbrains.kotlin.analysis.project.structure.KtModule
import org.jetbrains.kotlin.analysis.project.structure.computeTransitiveDependsOnDependencies
import org.jetbrains.kotlin.platform.TargetPlatform

internal class KtLibrarySourceModuleImpl(
    override val directRegularDependencies: List<KtModule>,
    override val directDependsOnDependencies: List<KtModule>,
    override val directFriendDependencies: List<KtModule>,
    override val contentScope: GlobalSearchScope,
    override val platform: TargetPlatform,
    override val project: Project,
    override val libraryName: String,
    override val binaryLibrary: KtLibraryModule,
) : KtLibrarySourceModule, KtModuleWithPlatform {
    override val transitiveDependsOnDependencies: List<KtModule> by lazy {
        computeTransitiveDependsOnDependencies(directDependsOnDependencies)
    }
}
