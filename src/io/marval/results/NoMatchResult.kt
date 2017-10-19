package io.marval.results

import io.marval.selections.Selection
import io.marval.selections.ShortCircuitSelection

data class NoMatchResult(val selection: Selection): Result {
    override fun iff(rule: (x: Any?) -> Boolean): Result = if (!rule(selection)) this else MatchResult(selection)
    override fun implies(rule: (x: Any?) -> Boolean): Result = MatchResult(selection)
    override fun where(rule: (x: Any?) -> Boolean): Result = if (rule(selection)) MatchResult(selection) else this
    override fun or(): Selection = selection
    override fun and(): Selection = ShortCircuitSelection(this)
    override fun invoke(x: Any?): Boolean = false
}
