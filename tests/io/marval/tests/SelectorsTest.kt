package io.marval.tests

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.StringSpec
import io.marval.core.Selectors

class SelectorsTest : StringSpec() {
    init {
        "the() selector should select the whole object" {
            forAll { a: String ->
                Selectors.the(a).isEqualTo(a)()
            }
        }

        "subSeq() selector should select part of an object" {
            val testTable = table(
                    headers("target", "i", "j", "result"),
                    row("abc", 0, 1, "a"),
                    row("abc", 1, 2, "b"),
                    row("abc", 2, 3, "c"),
                    row("abc", 0, 2, "ab"),
                    row("abc", 0, 3, "abc"),
                    row("abc", 0, 0, "")
            )

            forAll(testTable) { target, i, j, result ->
                Selectors.subSeqOf(target, i, j).isEqualTo(result)() shouldBe true
            }
        }

        "firstOf(String) selector should select the first character of a String" {
            forAll {a: String ->
                a.isEmpty() || Selectors.firstOf(a).isEqualTo(a.first())()
            }
        }

        "lastOf(String) selector should select the last character of a String" {
            forAll {a: String ->
                a.isEmpty() || Selectors.lastOf(a).isEqualTo(a.last())()
            }
        }
    }
}