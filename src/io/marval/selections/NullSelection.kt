package io.marval.selections

import io.marval.core.Rules
import io.marval.results.MatchResult
import io.marval.results.NoMatchResult
import io.marval.results.Result

/**
 * Represent a null selection.
 */
open class NullSelection : Selection {
    private fun eval(boolean: Boolean): Result = if(boolean) NoMatchResult(this) else MatchResult(this)
    /**
     * Since the selection is null, always returns no match. Use isNull to check for null.
     *
     * @see Selection#isEqualTo
     */
    override fun isEqualTo(obj: Any): Result = NoMatchResult(this)
    /**
     *
     */
    override fun isAlpha(): Result = NoMatchResult(this)
    override fun isAmong(options: Collection<Any>): Result = MatchResult(this)
    override fun isNull(): Result = MatchResult(this)
    override fun isNumeric(): Result = NoMatchResult(this)
    override fun isLike(pattern: String): Result = NoMatchResult(this)
    override fun contains(obj: Any): Result = NoMatchResult(this)
    override fun matches(rule: (x: Any?) -> Boolean): Result = eval(Rules.matches(rule)(null))
    override fun not(rule: (x: Any?) -> Boolean): Result = eval(Rules.not(rule)(null))
    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result = eval(Rules.matchesAll(*rules)(null))
    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result = eval(Rules.matchesAny(*rules)(null))
}