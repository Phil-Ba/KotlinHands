package at.bayava.kotlinhand.model

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.ShouldSpec

class HandTest : ShouldSpec() {

    init {

        val suiteGen = Gen.enum<Suite>()
        val suites = suiteGen.random().iterator()
        val straightStartGen = Gen.choose(2, 9)
        val straightStarts = straightStartGen.random().iterator()

        val straightsGen = object : Gen<List<Card>> {
            override fun constants(): Iterable<List<Card>> {
                return listOf(
                    listOf(
                        Card(suites.next(), Rank.ACE),
                        Card(suites.next(), Rank.TWO),
                        Card(suites.next(), Rank.THREE),
                        Card(suites.next(), Rank.FOUR),
                        Card(suites.next(), Rank.FIVE)
                    ).shuffled(),
                    listOf(
                        Card(suites.next(), Rank.ACE),
                        Card(suites.next(), Rank.KING),
                        Card(suites.next(), Rank.QUEEN),
                        Card(suites.next(), Rank.JACK),
                        Card(suites.next(), Rank.TEN)
                    ).shuffled()
                )
            }

            override fun random(): Sequence<List<Card>> {
                return generateSequence {
                    val start = straightStarts.next()
                    (0..4).map {
                        Card(suites.next(), Rank.fromValue(start + it))
                    }.shuffled()
                }
            }

        }

        should("Straights") {
            forAll(straightsGen) { straight ->
                Hand.isStraight(straight) == true
            }
        }
    }
}