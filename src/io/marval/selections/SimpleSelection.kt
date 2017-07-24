package io.marval.selections

import io.marval.core.Rules
import io.marval.results.MatchResult
import io.marval.results.NoMatchResult
import io.marval.results.Result

data class SimpleSelection(private val selection: Any): Selection {
    private fun eval(result: Boolean): Result = if(result) MatchResult(this) else NoMatchResult(this)
    override fun matches(rule: (x: Any?) -> Boolean): Result = this.eval(Rules.matches(rule)(selection))
    override fun not(rule: (x: Any?) -> Boolean): Result = this.eval(Rules.not(rule)(selection))
    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result = this.eval(Rules.matchesAll(*rules)(selection))
    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result = this.eval(Rules.matchesAll(*rules)(selection))
    override fun isEqualTo(obj: Any): Result = this.eval(Rules.isEqualTo(obj)(selection))
    override fun isAlpha(): Result = this.eval(Rules.isAlpha(selection))
    override fun isAmong(options: Collection<Any>): Result = this.eval(Rules.isAmong(options)(selection))
    override fun isNull(): Result = NoMatchResult(this)
    override fun isNumeric(): Result = this.eval(Rules.isNumeric(selection))
    override fun isLike(pattern: String): Result = this.eval(Rules.isLike(pattern)(selection))
    override fun contains(obj: Any): Result = this.eval(Rules.contains(obj)(selection))
}
