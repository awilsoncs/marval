package io.marval.selections

import io.marval.core.Rules
import io.marval.results.MatchResult
import io.marval.results.NoMatchResult
import io.marval.results.Result

/**
 * Supports a n-of quantifier
 *
 * Note that the selection can be empty but not null.
 */
data class NOfSelection(private val n: Int, private val set: Set<Any>): QuantifiedSelection {

    private fun eval(list: List<Any>): Result {
        return if (list.size == n) MatchResult(this) else NoMatchResult(this)
    }

    /**
     * Filters the selection.
     *
     * @see QuantifiedSelection#suchThat
     */
    override fun suchThat(criteria: (x: Any) -> Boolean): QuantifiedSelection {
        return NOfSelection(n, set.filter(criteria).toSet())
    }

    override fun isEqualTo(obj: Any): Result {
        return eval(set.filter { Rules.isEqualTo(obj)(it) })
    }

    override fun isAlpha(): Result {
        return eval(set.filter { Rules.isAlpha(it) })
    }

    override fun isAmong(options: Collection<Any>): Result {
        return eval(set.filter { Rules.isAmong(options)(it) })
    }

    override fun isNull(): Result {
        return eval(set.filter { Rules.isNull(it) })
    }

    override fun isNumeric(): Result {
        return eval(set.filter { Rules.isNumeric(it) })
    }

    override fun isLike(pattern: String): Result {
        return eval(set.filter { Rules.isLike(pattern)(it) })
    }

    override fun contains(obj: Any): Result {
        return eval(set.filter { Rules.contains(obj)(it) })
    }

    override fun matches(rule: (x: Any?) -> Boolean): Result {
        return eval(set.filter { Rules.matches(rule)(it) })
    }

    override fun not(rule: (x: Any?) -> Boolean): Result {
        return eval(set.filter { Rules.not(rule)(it) })
    }

    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.filter { Rules.matchesAll(*rules)(it) })
    }

    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result {
        return eval(set.filter { Rules.matchesAny(*rules)(it) })
    }
}