package at.bayava.kotlinhand.model

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.ShouldSpec

class CardTest : ShouldSpec() {

    init {
        "Cards"{
            should("never be equal if they don't share a suit.") {
                forAll(
                    Gen.pair(
                        Gen.enum<Suite>(),
                        Gen.enum<Suite>()
                    ).filterNot { suites -> suites.first == suites.second },
                    Gen.pair(
                        Gen.enum<Rank>(),
                        Gen.enum<Rank>()
                    )
                ) { suites: Pair<Suite, Suite>, ranks: Pair<Rank, Rank> ->
                    Card(suites.first, ranks.first) != Card(suites.second, ranks.second)
                }
            }

            should("never be equal if they don't share a rank.") {
                forAll(
                    Gen.pair(
                        Gen.enum<Suite>(),
                        Gen.enum<Suite>()
                    ),
                    Gen.pair(
                        Gen.enum<Rank>(),
                        Gen.enum<Rank>()
                    ).filterNot { ranks -> ranks.first == ranks.second }
                ) { suites: Pair<Suite, Suite>, ranks: Pair<Rank, Rank> ->
                    Card(suites.first, ranks.first) != Card(suites.second, ranks.second)
                }
            }

            should("always be equal if they share rank and suite.") {
                forAll(
                    Gen.enum<Suite>(),
                    Gen.enum<Rank>()
                )
                { suite: Suite, rank: Rank ->
                    Card(suite, rank) == Card(suite, rank)
                }
            }
        }
    }

}
