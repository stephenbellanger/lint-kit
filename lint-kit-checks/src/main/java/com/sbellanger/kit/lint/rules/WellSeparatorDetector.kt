@file:Suppress("UnstableApiUsage")

package com.sbellanger.kit.lint.rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UFile
import java.util.EnumSet

class WellSeparatorDetector : Detector(), Detector.UastScanner {

    ///////////////////////////////////////////////////////////////////////////
    // COMPANION
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        private const val SBC_LINE =
            "///////////////////////////////////////////////////////////////////////////"

        private val SBC_AUTHORIZED = listOf(
            "// TEST",
            "// ATTRIBUTE",
            "// DEPENDENCY",
            "// PUBLIC METHOD",
            "// PRIVATE METHOD",
            "// STATIC METHOD",
            "// OVERRIDE METHOD",
            "// COMPANION",
            "// TAG",
            "// LIFECYCLE METHOD",
            "// HELPER",
            "// DATA",
            "// LIVEDATA",
            "// INIT",
            "// CONST",
        )

        val ISSUE_SBC_NAMING = Issue.create(
            "NotAuthorizedSbcNaming",
            "SBC name must be contain in $SBC_AUTHORIZED.",
            "Because there are too many SBCs that contain the same thing with different names.",
            Category.CORRECTNESS,
            9,
            Severity.ERROR,
            Implementation(
                WellSeparatorDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
            )
        )
    }

    ///////////////////////////////////////////////////////////////////////////
    // OVERRIDE METHODS
    ///////////////////////////////////////////////////////////////////////////

    override fun getApplicableUastTypes() = listOf(UFile::class.java)

    override fun createUastHandler(context: JavaContext) = RuleHandler(context)

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("TooGenericExceptionCaught")
    class RuleHandler(private val context: JavaContext) : UElementHandler() {
        override fun visitFile(node: UFile) {
            node.allCommentsInFile.forEachIndexed { index, comment ->
                try {
                    if (comment.text == SBC_LINE && node.allCommentsInFile[index + 2].text == SBC_LINE) {
                        val nextLine = node.allCommentsInFile[index + 1]
                        if (!SBC_AUTHORIZED.contains(nextLine.text)) {
                            context.report(
                                ISSUE_SBC_NAMING,
                                node,
                                context.getNameLocation(node),
                                "SBC name not respected"
                            )
                        }
                    }
                } catch (e: IndexOutOfBoundsException) {
                    // Do nothing
                }
            }
        }
    }
}