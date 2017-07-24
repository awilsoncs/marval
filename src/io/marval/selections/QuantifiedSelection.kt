package io.marval.selections

/**
 * Provides an extension for filtering selections.
 *
 * Care should be taken when applying boolean operators to QuantifiedSelections. Specifically, when applying AND and OR,
 * the quantifier is effectively distributed across all operations. For instance:
 *
 * ```
 * allOf(X).isNumeric().or().isAlpha()
 * ```
 *
 * asserts that every element of X is numeric, or every element of X is alphabetical. To assert whether each element
 * is either numeric or alphabetical, you should use matchesAny():
 *
 * ```
 * allOf(X).matchesAny(isNumeric(), isAlpha())
 * ```
 */
interface QuantifiedSelection: Selection {

    /**
     * Filters the QuantifiedSelection. To apply more than one filter, the client may string suchThat() calls together
     * or use @Rules#matchesAll and @Rules#matchesAny.
     */
    fun suchThat(criteria: (x: Any) -> Boolean): QuantifiedSelection
}