package io.marval.selections

import io.marval.results.Result

data class ShortCircuitSelection(val value: Result): Selection {
    override fun isEqualTo(obj: Any): Result = value
    override fun isAlpha(): Result = value
    override fun isAmong(options: Collection<Any>): Result = value
    override fun isNull(): Result = value
    override fun isNumeric(): Result = value
    override fun isLike(pattern: String): Result = value
    override fun contains(obj: Any): Result = value
    override fun matches(rule: (x: Any?) -> Boolean): Result = value
    override fun not(rule: (x: Any?) -> Boolean): Result = value
    override fun matchesAll(vararg rules: (x: Any?) -> Boolean): Result = value
    override fun matchesAny(vararg rules: (x: Any?) -> Boolean): Result = value
}
