package io.marval.selections

import io.marval.core.Rules
import io.marval.results.MatchResult
import io.marval.results.NoMatchResult
import io.marval.results.Result

/**
 * Supports existential quantifier.
 *
 * Note that the selection can be empty but not null.
 */
data class AnyOfSelection(private val set: Set<Any>): QuantifiedSelection {

    private fun eval(result: Boolean): Result = if(result) MatchResult(this) else NoMatchResult(this)

    /**
     * Filters the selection.
     *
     * @see QuantifiedSelection#suchThat
     */
    override fun suchThat(criteria: (x: Any) -> Boolean): QuantifiedSelection {
        return AnyOfSelection(set.filter(criteria).toSet())
    }

    /**
     * Asserts that any of the set is equal to the given value.
     */
    override fun isEqualTo(obj: Any): Result {
        return eval(set.any { Rules.isEqualTo(obj)(it) })
    }

    /**
     * Asserts that any of the set is alphabetical.
     */
    override fun isAlpha(): Result {
        return eval(set.any { Rules.isAlpha(it) })
    }

    /**
     * Asserts that any of the set is among a given collection.
     */
    override fun isAmong(options: Collection<Any>): Result {
        return eval(set.any { Rules.isAmong(options)(it) })
    }

    /**
     * Asserts that any of the set is Null. Always returns NoMatchResult.
     */
    override fun isNull(): Result {
        return eval(set.any { Rules.isNull(it) })
    }

    /**
     * Asserts that any of the set is numeric.
     */
    override fun isNumeric(): Result {
        return eval(set.any { Rules.isNumeric(it) })
    }

    /**
     * Asserts that any of the set is like a given regex pattern.
     */
    override fun isLike(pattern: String): Result {
        return eval(set.any { Rules.isLike(pattern)(it) })
    }

    /**
     * Asserts that any of the set contains a given object.
     */
    override fun contains(obj: Any): Result {
        return eval(set.any { Rules.contains(obj)(it) })
    }

    /**
     * Asserts that any of the set matches a given rule.
     */
    override fun matches(rule: (x: Any?) -> Boolean): Result {
        return eval(set.any { Rules.matches(rule)(it) })
    }

    /**
     * Asserts that any of the set does not match the given rule.
     */
    override fun not(rule: (x: Any?) -> Boolean): Result {
        return eval(set.any { Rules.not(rule)(it) })
    }

    /**
     * Asserts that any member of the set matches all of the given rules.
     */
    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.any { Rules.matchesAll(*rules)(it) })
    }

    /**
     * Asserts that any member of the set matches any of the given rules.
     */
    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.any { Rules.matchesAny(*rules)(it) })
    }
}