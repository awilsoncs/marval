package io.marval.results

import io.marval.selections.Selection
import io.marval.selections.ShortCircuitSelection

data class NoMatchResult(val selection: Selection): Result {
    override fun or(): Selection = selection
    override fun and(): Selection = ShortCircuitSelection(this)
    override fun implies(): Selection = ShortCircuitSelection(MatchResult(selection))
    override fun invoke(x: Any?): Boolean = false
}
