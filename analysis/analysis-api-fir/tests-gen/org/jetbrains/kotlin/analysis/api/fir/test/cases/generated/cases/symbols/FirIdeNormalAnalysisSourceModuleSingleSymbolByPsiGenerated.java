/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.fir.test.cases.generated.cases.symbols;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.analysis.api.fir.test.configurators.AnalysisApiFirTestConfiguratorFactory;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfiguratorFactoryData;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfigurator;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.TestModuleKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.FrontendKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisSessionMode;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiMode;
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.symbols.AbstractSingleSymbolByPsi;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi")
@TestDataPath("$PROJECT_ROOT")
public class FirIdeNormalAnalysisSourceModuleSingleSymbolByPsiGenerated extends AbstractSingleSymbolByPsi {
    @NotNull
    @Override
    public AnalysisApiTestConfigurator getConfigurator() {
        return AnalysisApiFirTestConfiguratorFactory.INSTANCE.createConfigurator(
            new AnalysisApiTestConfiguratorFactoryData(
                FrontendKind.Fir,
                TestModuleKind.Source,
                AnalysisSessionMode.Normal,
                AnalysisApiMode.Ide
            )
        );
    }

    @Test
    public void testAllFilesPresentInSingleSymbolByPsi() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("ExpandedParameterType.kt")
    public void testExpandedParameterType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/ExpandedParameterType.kt");
    }

    @Test
    @TestMetadata("ExpandedReturnType.kt")
    public void testExpandedReturnType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/ExpandedReturnType.kt");
    }

    @Test
    @TestMetadata("functionWithReceiverAnnotation.kt")
    public void testFunctionWithReceiverAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/functionWithReceiverAnnotation.kt");
    }

    @Test
    @TestMetadata("getterWithAnnotations.kt")
    public void testGetterWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/getterWithAnnotations.kt");
    }

    @Test
    @TestMetadata("getterWithReceiverAndAnnotations.kt")
    public void testGetterWithReceiverAndAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/getterWithReceiverAndAnnotations.kt");
    }

    @Test
    @TestMetadata("nestedTypeAnnotationWithTypeAlias.kt")
    public void testNestedTypeAnnotationWithTypeAlias() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/nestedTypeAnnotationWithTypeAlias.kt");
    }

    @Test
    @TestMetadata("nestedTypeAnnotationWithTypeAliasAsAnnotation.kt")
    public void testNestedTypeAnnotationWithTypeAliasAsAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/nestedTypeAnnotationWithTypeAliasAsAnnotation.kt");
    }

    @Test
    @TestMetadata("propertyWithAnnotations.kt")
    public void testPropertyWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithAnnotations.kt");
    }

    @Test
    @TestMetadata("propertyWithAnnotationsAndAccessors.kt")
    public void testPropertyWithAnnotationsAndAccessors() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithAnnotationsAndAccessors.kt");
    }

    @Test
    @TestMetadata("propertyWithDelegateAndAnnotations.kt")
    public void testPropertyWithDelegateAndAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithDelegateAndAnnotations.kt");
    }

    @Test
    @TestMetadata("propertyWithReceiverAnnotation.kt")
    public void testPropertyWithReceiverAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithReceiverAnnotation.kt");
    }

    @Test
    @TestMetadata("setterWithAnnotations.kt")
    public void testSetterWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/setterWithAnnotations.kt");
    }

    @Test
    @TestMetadata("typeAnnotationWithArgument.kt")
    public void testTypeAnnotationWithArgument() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationWithArgument.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnFunctionParameterType.kt")
    public void testTypeAnnotationsOnFunctionParameterType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnFunctionParameterType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnFunctionReceiverType.kt")
    public void testTypeAnnotationsOnFunctionReceiverType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnFunctionReceiverType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnFunctionalTypeWithTypeAlias.kt")
    public void testTypeAnnotationsOnFunctionalTypeWithTypeAlias() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnFunctionalTypeWithTypeAlias.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnPropertyGetterReturnType.kt")
    public void testTypeAnnotationsOnPropertyGetterReturnType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnPropertyGetterReturnType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnPropertyReceiverType.kt")
    public void testTypeAnnotationsOnPropertyReceiverType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnPropertyReceiverType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnPropertyReturnType.kt")
    public void testTypeAnnotationsOnPropertyReturnType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnPropertyReturnType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnPropertySetterParameterType.kt")
    public void testTypeAnnotationsOnPropertySetterParameterType() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnPropertySetterParameterType.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnPropertySetterParameterTypeWithAnotherAnnotation.kt")
    public void testTypeAnnotationsOnPropertySetterParameterTypeWithAnotherAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnPropertySetterParameterTypeWithAnotherAnnotation.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnSuperClassCall.kt")
    public void testTypeAnnotationsOnSuperClassCall() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnSuperClassCall.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnSuperClassCallOnAnonymousObject.kt")
    public void testTypeAnnotationsOnSuperClassCallOnAnonymousObject() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnSuperClassCallOnAnonymousObject.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnSuperInterface.kt")
    public void testTypeAnnotationsOnSuperInterface() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnSuperInterface.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsOnSuperInterfaceOnAnonymousObject.kt")
    public void testTypeAnnotationsOnSuperInterfaceOnAnonymousObject() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsOnSuperInterfaceOnAnonymousObject.kt");
    }

    @Test
    @TestMetadata("typeAnnotationsWithTypeAlias.kt")
    public void testTypeAnnotationsWithTypeAlias() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/typeAnnotationsWithTypeAlias.kt");
    }

    @Nested
    @TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts")
    @TestDataPath("$PROJECT_ROOT")
    public class Contracts {
        @Test
        public void testAllFilesPresentInContracts() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts"), Pattern.compile("^(.+)\\.kt$"), null, true);
        }

        @Test
        @TestMetadata("booleanConstReferenceInImplies.kt")
        public void testBooleanConstReferenceInImplies() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/booleanConstReferenceInImplies.kt");
        }

        @Test
        @TestMetadata("booleanExprContract.kt")
        public void testBooleanExprContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/booleanExprContract.kt");
        }

        @Test
        @TestMetadata("callsInPlaceAtLeastOnceContract.kt")
        public void testCallsInPlaceAtLeastOnceContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/callsInPlaceAtLeastOnceContract.kt");
        }

        @Test
        @TestMetadata("callsInPlaceAtMostOnceContract.kt")
        public void testCallsInPlaceAtMostOnceContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/callsInPlaceAtMostOnceContract.kt");
        }

        @Test
        @TestMetadata("callsInPlaceExactlyOnceContract.kt")
        public void testCallsInPlaceExactlyOnceContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/callsInPlaceExactlyOnceContract.kt");
        }

        @Test
        @TestMetadata("callsInPlaceUnknownContract.kt")
        public void testCallsInPlaceUnknownContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/callsInPlaceUnknownContract.kt");
        }

        @Test
        @TestMetadata("invalidContractParameterPassedToReturns.kt")
        public void testInvalidContractParameterPassedToReturns() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/invalidContractParameterPassedToReturns.kt");
        }

        @Test
        @TestMetadata("isInstancePredicateContract.kt")
        public void testIsInstancePredicateContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/isInstancePredicateContract.kt");
        }

        @Test
        @TestMetadata("logicalNotContract.kt")
        public void testLogicalNotContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/logicalNotContract.kt");
        }

        @Test
        @TestMetadata("referenceBooleanReceiverInContract.kt")
        public void testReferenceBooleanReceiverInContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/referenceBooleanReceiverInContract.kt");
        }

        @Test
        @TestMetadata("referenceReceiverInContract.kt")
        public void testReferenceReceiverInContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/referenceReceiverInContract.kt");
        }

        @Test
        @TestMetadata("returnsContract.kt")
        public void testReturnsContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/returnsContract.kt");
        }

        @Test
        @TestMetadata("returnsFalseContract.kt")
        public void testReturnsFalseContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/returnsFalseContract.kt");
        }

        @Test
        @TestMetadata("returnsNotNullContract.kt")
        public void testReturnsNotNullContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/returnsNotNullContract.kt");
        }

        @Test
        @TestMetadata("returnsNullContract.kt")
        public void testReturnsNullContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/returnsNullContract.kt");
        }

        @Test
        @TestMetadata("returnsTrueContract.kt")
        public void testReturnsTrueContract() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/returnsTrueContract.kt");
        }

        @Test
        @TestMetadata("twoContracts.kt")
        public void testTwoContracts() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/contracts/twoContracts.kt");
        }
    }

    @Nested
    @TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring")
    @TestDataPath("$PROJECT_ROOT")
    public class Destructuring {
        @Test
        public void testAllFilesPresentInDestructuring() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring"), Pattern.compile("^(.+)\\.kt$"), null, true);
        }

        @Test
        @TestMetadata("destructuringDeclaration.kt")
        public void testDestructuringDeclaration() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/destructuringDeclaration.kt");
        }

        @Test
        @TestMetadata("destructuringDeclarationInLambda.kt")
        public void testDestructuringDeclarationInLambda() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/destructuringDeclarationInLambda.kt");
        }

        @Test
        @TestMetadata("destructuringDeclarationParameterInLambda.kt")
        public void testDestructuringDeclarationParameterInLambda() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/destructuringDeclarationParameterInLambda.kt");
        }

        @Test
        @TestMetadata("entryInDestructuringDeclaration.kt")
        public void testEntryInDestructuringDeclaration() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/entryInDestructuringDeclaration.kt");
        }

        @Test
        @TestMetadata("entryInDestructuringDeclarationParameterInLambda.kt")
        public void testEntryInDestructuringDeclarationParameterInLambda() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/entryInDestructuringDeclarationParameterInLambda.kt");
        }

        @Test
        @TestMetadata("entryUnderscoreInDestructuringDeclaration.kt")
        public void testEntryUnderscoreInDestructuringDeclaration() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/entryUnderscoreInDestructuringDeclaration.kt");
        }

        @Test
        @TestMetadata("entryUnderscoreInDestructuringDeclarationParameterInLambda.kt")
        public void testEntryUnderscoreInDestructuringDeclarationParameterInLambda() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/destructuring/entryUnderscoreInDestructuringDeclarationParameterInLambda.kt");
        }
    }

    @Nested
    @TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors")
    @TestDataPath("$PROJECT_ROOT")
    public class Errors {
        @Test
        public void testAllFilesPresentInErrors() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors"), Pattern.compile("^(.+)\\.kt$"), null, true);
        }

        @Test
        @TestMetadata("anonymousObjectInInvalidPosition.kt")
        public void testAnonymousObjectInInvalidPosition() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors/anonymousObjectInInvalidPosition.kt");
        }

        @Test
        @TestMetadata("objectWithTypeArgsAsExpression.kt")
        public void testObjectWithTypeArgsAsExpression() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors/objectWithTypeArgsAsExpression.kt");
        }
    }
}
