package io.marval.results

import io.marval.selections.Selection

/**
 * A seed is the result of an operation on a Selection. Use them to operate on the results, or to string together
 * multiple calls.
 */
interface Result {
    operator fun invoke(x: Any? = null): Boolean
    fun or(): Selection
    fun and(): Selection
    fun implies(): Selection
    fun orDo(listener: () -> Unit) = if (this()) Unit else listener()
    fun thenDo(listener: () -> Unit) = if (this()) listener() else Unit
}
