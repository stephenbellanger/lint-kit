package com.sbellanger.kit.lint.rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

class WellSeparatorDetectorTest {

    @Test
    fun testSuccessSbc() {
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                LintDetectorTest.kotlin(
                    """
                |package foo
                |
                |class Toto {
                |
                |///////////////////////////////////////////////////////////////////////////
                |// PUBLIC METHOD
                |///////////////////////////////////////////////////////////////////////////
                |
                |fun tutu() {}
                |
                |}""".trimMargin()
                )
            )
            .issues(WellSeparatorDetector.ISSUE_SBC_NAMING)
            .run()
            .expectClean()
    }

    @Test
    fun testSuccessMultipleSbc() {
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                LintDetectorTest.kotlin(
                    """
                |package foo
                |
                |class Toto {
                |
                |///////////////////////////////////////////////////////////////////////////
                |// PUBLIC METHOD
                |///////////////////////////////////////////////////////////////////////////
                |
                |fun tutu() {}
                |
                |///////////////////////////////////////////////////////////////////////////
                |// PRIVATE METHOD
                |///////////////////////////////////////////////////////////////////////////
                |
                |private fun tata() {}
                |
                |}""".trimMargin()
                )
            )
            .issues(WellSeparatorDetector.ISSUE_SBC_NAMING)
            .run()
            .expectClean()
    }

    @Test
    fun testFailSbc() {
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                LintDetectorTest.kotlin(
                    """
                |package foo
                |
                |class Toto {
                |
                |///////////////////////////////////////////////////////////////////////////
                |// ALL METHODS
                |///////////////////////////////////////////////////////////////////////////
                |
                |fun tutu() {}
                |
                |}""".trimMargin()
                )
            )
            .issues(WellSeparatorDetector.ISSUE_SBC_NAMING)
            .run()
            .expectErrorCount(1)
    }

    @Test
    fun testFailSbcWithPlural() {
        TestLintTask.lint()
            .allowMissingSdk()
            .files(
                LintDetectorTest.kotlin(
                    """
                |package foo
                |
                |class Toto {
                |
                |///////////////////////////////////////////////////////////////////////////
                |// PUBLIC METHODS
                |///////////////////////////////////////////////////////////////////////////
                |
                |fun tutu() {}
                |
                |}""".trimMargin()
                )
            )
            .issues(WellSeparatorDetector.ISSUE_SBC_NAMING)
            .run()
            .expectErrorCount(1)
    }
}