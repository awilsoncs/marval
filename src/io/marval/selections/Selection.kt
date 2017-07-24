package io.marval.selections

import io.marval.results.Result

/**
 * Represents the result of applying a Selector
 *
 * @see io.marval.Selectors
 */
interface Selection {
    /**
     * Assert equality
     */
    fun isEqualTo(obj: Any): Result

    /**
     * Assert that the selection is alphabetical (regex [A-Za-z]*)
     */
    fun isAlpha(): Result

    /**
     * Assert that the selection is a member of a given list.
     */
    fun isAmong(options: Collection<Any>): Result

    /**
     * Assert that the selection is null.
     */
    fun isNull(): Result

    /**
     * Assert that the selection is numeric.
     */
    fun isNumeric(): Result

    /**
     * Assert that the selection matches a regex string.
     */
    fun isLike(pattern: String): Result

    /**
     * Assert that the selection contains a given object.
     */
    fun contains(obj: Any): Result

    /**
     * Assert that the selection matches a given rule.
     */
    fun matches(rule: (x:Any?) -> Boolean): Result

    /**
     * Assert that the selection does not match a rule.
     */
    fun not(rule: (x:Any?) -> Boolean): Result

    /** 
     * Assert that the selection matches all given rules.
     */
    fun matchesAll(vararg rules: (x:Any?) -> Boolean): Result

    /**
     * Assert that the selection matches an of the given rules.
     */
    fun matchesAny(vararg rules: (x:Any?) -> Boolean): Result
}
