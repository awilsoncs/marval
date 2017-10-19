package io.marval.core

/**
 * Provides static rules for validating data.
 */
class Rules {
    companion object {

        /**
         * Assert that the object is equal to a given object.
         */
        fun isEqualTo(x: Any): (y: Any?) -> Boolean = { it?.equals(x) ?: false }

        /**
         * Assert that the object is an alphabetical String.
         */
        fun isAlpha(x: Any?): Boolean {
            return when (x) {
                is String -> x.matches("[A-Za-z]*".toRegex())
                else -> false
            }
        }

        /**
         * Assert that the object is null.
         */
        fun isNull(x: Any?): Boolean = x == null

        /**
         * Assert that the object is either a numeric String or a Number.
         *
         * For numeric Strings, this rule only matches against the regex "-?[0-9]*\\.?[0-9".
         */
        fun isNumeric(x: Any?): Boolean {
            return when(x) {
                is String -> x.matches("-?[0-9]*\\.?[0-9]+".toRegex())
                is Number -> true
                else -> false
            }
        }

        /**
         * Asserts that the object is alphanumeric.
         */
        fun isAlphaNumeric(x: Any?): Boolean = isAlpha(x) || isNumeric(x)

        /**
         * Asserts that the object is among a given list of objects.
         */
        fun isAmong(options: Collection<Any>): (x: Any?) -> Boolean = { options.contains(it) }

        /**
         * Assert that the object matches a regex string, if possible.
         */
        fun isLike(pattern: String): (x: Any?) -> Boolean = {
            x ->
            when (x) {
                null -> false
                is String -> x.matches(pattern.toRegex())
                else -> x.toString().matches(pattern.toRegex())
            }
        }

        /**
         * Given a comparator, assert that the object is greater than a given value.
         */
        fun <T> isLessThan(value: T, comparator: Comparator<T>): (x: Any?) -> Boolean {
            TODO("Not implemented yet")
        }

        /**
         * Given a comparator, assert that the object is less than a
         */
        fun <T> isGreaterThan(value: T, comparator: Comparator<T>): (x: Any?) -> Boolean {
            TODO("Not implemented yet")
        }

        /**
         * Assert that the object matches the given rule.
         */
        fun matches(rule: (x: Any?) -> Boolean): (x: Any?) -> Boolean = { rule(it) }

        /**
         * Assert that the object does not match the given rule.
         */
        fun not(rule: (x: Any?) -> Boolean): (x: Any?) -> Boolean = { !rule(it) }

        /**
         * Assert that the object contains the given object, if this makes sense.
         */
        fun contains(obj: String): (x: Any?) -> Boolean = {
            x ->
            when (x) {
                is CharSequence -> x.contains(obj.toRegex())
                else -> false
            }
        }

        /**
         * Assert that the object is a superset of the given Iterable.
         */
        fun contains(obj: Iterable<Any>): (x: Any?) -> Boolean = {
            x ->
            when (x) {
                is Iterable<*> -> obj.all { x.contains(it) }
                else -> false
            }
        }

        /**
         * Asserts that the object contains the given object.
         *
         * If the deferred operand is an Iterable, will test for direct membership.
         *
         * If the deferred operand is a CharSequence, will check that the object contains a string representation.
         */
        fun contains(obj: Any?): (x: Any?) -> Boolean = {
            x ->
            when (x) {
                null -> true
                is Iterable<*> -> x.contains(obj)
                is CharSequence -> x.contains(obj.toString())
                else -> false
            }
        }

        /**
         * Asserts that the operand matches all given rules.
         */
        fun matchesAll(vararg rules: (x: Any?) -> Boolean): (x: Any?) -> Boolean = { x -> rules.all { it(x) } }

        /**
         * Asserts that the operand matches any of the given rules.
         */
        fun matchesAny(vararg rules: (x: Any?) -> Boolean): (x: Any?) -> Boolean = { x -> rules.any { it(x) } }
    }
}
