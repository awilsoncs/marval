package io.marval.selections

import io.marval.core.Rules
import io.marval.results.MatchResult
import io.marval.results.NoMatchResult
import io.marval.results.Result

/**
 * Supports universal quantifier.
 *
 * Note that the selection can be empty but not null.
 */
data class EachOfSelection(private val set: Set<Any>): QuantifiedSelection {

    private fun eval(result: Boolean): Result = if(result) MatchResult(this) else NoMatchResult(this)

    /**
     * Filters the selection.
     *
     * @see QuantifiedSelection#suchThat
     */
    override fun suchThat(criteria: (x: Any) -> Boolean): QuantifiedSelection {
        return EachOfSelection(set.filter(criteria).toSet())
    }


    /**
     * Each member of the set is equal to a given object.
     * 
     * Note that unless the set is of size one, this will always return NoMatchResult.
     */
    override fun isEqualTo(obj: Any): Result {
        return eval(set.all { Rules.isEqualTo(obj)(it) })
    }

    /**
     * Each member of the set is alphabetical.
     */
    override fun isAlpha(): Result {
        return eval(set.all { Rules.isAlpha(it) })
    }

    /**
     * Each member of the set is among the given collection.
     */
    override fun isAmong(options: Collection<Any>): Result {
        return eval(set.all { Rules.isAmong(options)(it) })
    }

    /**
     * Each member of the set is null. Always returns NoMatchResult.
     */
    override fun isNull(): Result {
        return eval(set.all { Rules.isNull(it) })
    }

    /**
     * Each member of the set is numeric.
     */
    override fun isNumeric(): Result {
        return eval(set.all { Rules.isNumeric(it) })
    }

    /**
     * Each member of the set matches a given regex pattern.
     */
    override fun isLike(pattern: String): Result {
        return eval(set.all { Rules.isLike(pattern)(it) })
    }

    /**
     * Each member of the set contains a given object.
     */
    override fun contains(obj: Any): Result {
        return eval(set.all { Rules.contains(obj)(it) })
    }

    /**
     * Each member of the set matches a given rule.
     */
    override fun matches(rule: (x: Any?) -> Boolean): Result {
        return eval(set.all { Rules.matches(rule)(it) })
    }

    /**
     * Each member of the set does not match a given rule.
     */
    override fun not(rule: (x: Any?) -> Boolean): Result {
        return eval(set.all { Rules.not(rule)(it) })
    }

    /**
     * Each member of the set matches all of the given rules.
     */
    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.all { Rules.matchesAll(*rules)(it) })
    }

    /**
     * Each member of the set matches any of the given rules.
     */
    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.all { Rules.matchesAny(*rules)(it) })
    }
}
