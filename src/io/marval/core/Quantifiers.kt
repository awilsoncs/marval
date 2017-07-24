package io.marval.core

import io.marval.selections.AnyOfSelection
import io.marval.selections.EachOfSelection
import io.marval.selections.NOfSelection
import io.marval.selections.QuantifiedSelection

/**
 * Quantifiers for first-order logic. Note that Quantifiers may have unexpected interactions with boolean operations.
 *
 * By composing quantified selection can yield powerful first-order logical expressions.
 *
 * ```
 * allOf(X).matches(x -> thereExists(Y).suchThat(y -> y > x))
 * ```
 *
 * expresses the condition that for every x, there is a y that is greater.
 *
 * @see QuantifiedSelection
 */
class Quantifiers {
    companion object {

        /**
         * Utility method for the set of all combinations from the given sets.
         */
        private fun crossOf(vararg sets: Set<Any>): Set<Any> = crossOfRecursive(HashSet(), *sets)

        /**
         * Utility method for crossOf()
         */
        private fun crossOfRecursive(acc: Set<List<Any>>, vararg sets: Set<Any>): Set<Any> {
            if(sets.isEmpty()) {
                return acc
            } else {
                var out: Set<List<Any>> = HashSet()
                acc.forEach({ x -> sets[0].forEach({ y -> out.plus(x.plus(y)) }) })
                return crossOfRecursive(out, *(sets.drop(0).toTypedArray()))
            }
        }

        /**
         * Selects all of a Set. Matches only if each member matches the rule.
         *
         * In first-order logic, supports the existential quantifier.
         */
        fun eachOf(set: Set<Any>): QuantifiedSelection = EachOfSelection(set)

        fun eachOf(vararg sets: Set<Any>): QuantifiedSelection = EachOfSelection(crossOf(*sets))

        /**
         * Selects all of a Set. Matches if at least one member matches the rule.
         *
         * In first-order logic, supports the universal quantifier.
         */
        fun anyOf(set: Set<Any>): QuantifiedSelection = AnyOfSelection(set)

        fun anyOf(vararg sets: Set<Any>): QuantifiedSelection = AnyOfSelection(crossOf(*sets))

        /**
         * Selects all of a Set. Matches if exactly one member matches the rule.
         *
         * In first-order logic, supports the uniqueness quantifier.
         */
        fun oneOf(set: Set<Any>): QuantifiedSelection = NOfSelection(1, set)

        fun oneOf(vararg sets: Set<Any>): QuantifiedSelection = NOfSelection(1, crossOf(*sets))

        /**
         * Selects all of a Set. Matches if no members match the rule.
         *
         * In first-order logic, supports the non-existence quantifier.
         */
        fun noneOf(set: Set<Any>): QuantifiedSelection = NOfSelection(0, set)

        fun noneOf(vararg sets: Set<Any>): QuantifiedSelection = NOfSelection(0, crossOf(*sets))
    }
}