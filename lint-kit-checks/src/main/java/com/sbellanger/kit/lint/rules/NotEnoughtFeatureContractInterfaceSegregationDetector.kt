@file:Suppress("UnstableApiUsage")

package com.sbellanger.kit.lint.rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.sbellanger.kit.lint.helper.isFeatureContract
import org.jetbrains.uast.UClass
import java.util.EnumSet

class NotEnoughtFeatureContractInterfaceSegregationDetector : Detector(), SourceCodeScanner {

    ///////////////////////////////////////////////////////////////////////////
    // CONST
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        val ISSUE = Issue.create(
            "FeatureContractInterfaceSegregation",
            "Feature Contract Interface should be refined enougth",
            "Interface should be refined with ViewCapabilities, ViewModel, ViewNavigation, ViewTag",
            Category.COMPLIANCE,
            5,
            Severity.ERROR,
            Implementation(
                NotEnoughtFeatureContractInterfaceSegregationDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
            )
        )
    }

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun createUastHandler(context: JavaContext) = RuleHandler(context)

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    class RuleHandler(private val context: JavaContext) : UElementHandler() {
        override fun visitClass(node: UClass) {
            if (node.isFeatureContract()) {
                node.innerClasses.map { it.name }.filterNotNull().let {
                    if (it.contains("ViewModel").not()) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ViewModel should be defined in Feature Contract Interface, even void"
                        )
                    }
                    if (it.contains("ViewEvent").not()) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ViewEvent should be defined in Feature Contract Interface, even void"
                        )
                    }
                    if (it.contains("ViewCapabilities").not()) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ViewCapabilities should be defined in Feature Contract Interface, even void"
                        )
                    }
                    if (it.contains("ViewTag").not()) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ViewTag should be defined in Feature Contract Interface, even void"
                        )
                    }
                    if (it.contains("ViewNavigation").not()) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ViewNavigation should be defined in Feature Contract Interface, even void"
                        )
                    }
                }
            }
        }
    }
}
