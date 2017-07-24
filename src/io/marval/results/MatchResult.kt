package io.marval.results

import io.marval.selections.Selection
import io.marval.selections.ShortCircuitSelection

data class MatchResult(val selection: Selection): Result {
    override fun or(): Selection = ShortCircuitSelection(this)
    override fun and(): Selection = selection
    override fun implies(): Selection = selection
    operator override fun invoke(x: Any?): Boolean = true
}