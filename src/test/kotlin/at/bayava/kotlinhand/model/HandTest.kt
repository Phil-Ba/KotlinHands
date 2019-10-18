package at.bayava.kotlinhand.model

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.ShouldSpec

class HandTest : ShouldSpec() {

    init {

        val suites = suiteGen.random().iterator()
        val straightStartGen = Gen.choose(2, 9)
        val straightStarts = straightStartGen.random().iterator()
        val nonStraightsGen = Gen.create {
            cardGen.random().take(4).toList().run {
                val maxCard = maxBy { it.rank.value }!!
                val nonStraightRank = if (maxCard.rank.value <= Rank.QUEEN.value) maxCard.rank.value + 2
                else maxCard.rank.value - 5
                this + Card(suites.next(), Rank.fromValue(nonStraightRank))
            }
        }

        val flushesGen = Gen.bind(suiteGen) { suite ->
            rankGen.random()
                .take(5)
                .map { Card(suite, it) }
                .toList()
        }

        val nonFlushesGen = Gen.create {
            cardGen.random()
                .take(5)
                .toList()
        }.filterNot { nonFlush ->
            Suite.values().any({ suite ->
                nonFlush.all { it.suite == suite }
            })
        }

        val straightsGen = object : Gen<List<Card>> {
            override fun constants(): Iterable<List<Card>> {
                return listOf(
                    listOf(
                        Card(suites.next(), Rank.ACE),
                        Card(suites.next(), Rank.TWO),
                        Card(suites.next(), Rank.THREE),
                        Card(suites.next(), Rank.FOUR),
                        Card(suites.next(), Rank.FIVE)
                    ).shuffled(), listOf(
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
        "Straights"{
            should("be detected for hands with a straight") {
                forAll(straightsGen) { straight ->
                    Hand.isStraight(straight) == true
                }
            }
            should("be not detected for hands without a straight") {
                forAll(nonStraightsGen) { straight ->
                    Hand.isStraight(straight) == false
                }
            }
        }

        "Fulshes"{
            should("be detected for all hands with a flush") {
                forAll(flushesGen) { flush ->
                    Hand.isFlush(flush) == true
                }
            }
            should("be detected for all hands without a flush") {
                forAll(nonFlushesGen) { flush ->
                    Hand.isFlush(flush) == false
                }
            }
        }
    }
}
