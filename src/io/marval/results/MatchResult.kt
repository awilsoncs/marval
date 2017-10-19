package io.marval.results

import io.marval.selections.Selection
import io.marval.selections.ShortCircuitSelection

data class MatchResult(val selection: Selection): Result {
    override fun iff(rule: (x: Any?) -> Boolean): Result = this.implies(rule)
    override fun implies(rule: (x: Any?) -> Boolean): Result = if (rule(selection)) this else NoMatchResult(selection)
    override fun where(rule: (x: Any?) -> Boolean): Result = this
    override fun or(): Selection = ShortCircuitSelection(this)
    override fun and(): Selection = selection
    operator override fun invoke(x: Any?): Boolean = true
}