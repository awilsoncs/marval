package io.marval.core

import io.marval.selections.NullSelection
import io.marval.selections.Selection
import io.marval.selections.SimpleSelection

/**
 * Selectors operate on a single element of data, or an ordered sequence like a CharSequence or List.
 *
 * All methods return a Selection representing the result of a selection operation on the passed data.
 */
class Selectors {
    companion object {

        /**
         * Selects the entire object.
         */
        fun the(target: Any?): Selection {
            when (target) {
                null -> return NullSelection()
                else -> return SimpleSelection(target)
            }
        }

        /**
         * Selects a subsequence of a CharSequence
         */
        fun subSeqOf(target: CharSequence, start: Int, end: Int): Selection {
            return SimpleSelection(target.subSequence(start, end))
        }

        /**
         * Selects a subsequence of a List.
         */
        fun subSeqOf(target: List<Any>, start: Int, end: Int): Selection {
            if (start < end && end < target.size) {
                return SimpleSelection(target.subList(start, end))
            }
            return NullSelection()
        }

        /**
         * Selects the first character of a String.
         */
        fun firstOf(target: CharSequence): Selection {
            return if (target.isNotEmpty()) SimpleSelection(target.first()) else NullSelection()
        }

        /**
         * Selects the first element of a List.
         */
        fun firstOf(target: List<Any>): Selection {
            return if (target.isNotEmpty()) SimpleSelection(target.first()) else NullSelection()
        }

        /**
         * Selects the last character of a String.
         */
        fun lastOf(target: CharSequence): Selection {
            return if (target.isNotEmpty()) SimpleSelection(target.last()) else NullSelection()
        }

        /**
         * Selects the last element of a List.
         */
        fun lastOf(target: List<Any>): Selection {
            return if (target.isNotEmpty()) SimpleSelection(target.last()) else NullSelection()
        }
    }
}
