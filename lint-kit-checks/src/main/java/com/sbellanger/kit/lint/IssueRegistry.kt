@file:Suppress("UnstableAPIUSage") // We know that Lint API's aren't final.

package com.sbellanger.kit.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.sbellanger.kit.lint.rules.NotEnoughtFeatureContractInterfaceSegregationDetector
import com.sbellanger.kit.lint.rules.WellSeparatorDetector

class IssueRegistry : IssueRegistry() {

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val api: Int = CURRENT_API

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override val issues: List<Issue>
        get() = listOf(
            WellSeparatorDetector.ISSUE_SBC_NAMING,
            NotEnoughtFeatureContractInterfaceSegregationDetector.ISSUE
        )
}
