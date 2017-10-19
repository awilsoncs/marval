package io.marval.results

import io.marval.selections.Selection

/**
 * A seed is the result of an operation on a Selection. Use them to operate on the results, or to string together
 * multiple calls.
 */
interface Result {
    /**
     * Evaluate the result to a boolean.
     */
    operator fun invoke(x: Any? = null): Boolean
    fun or(): Selection
    fun and(): Selection

    /**
     * Evaluate a => b where a is all syntax up to the conditional, and b is the operand.
     */
    fun implies(rule: (x:Any?) -> Boolean): Result

    /**
     * Evaluate a => b where a is the operand and b is all syntax up to the conditional.
     */
    fun where(rule: (x:Any?) -> Boolean): Result

    /**
     * Biconditional a <=> b where a is all syntax up to the biconditional and b is the operand.
     */
    fun iff(rule: (x:Any?) -> Boolean): Result
    fun orDo(listener: () -> Unit) = if (this()) Unit else listener()
    fun thenDo(listener: () -> Unit) = if (this()) listener() else Unit
}
