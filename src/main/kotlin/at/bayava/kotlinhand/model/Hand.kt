package at.bayava.kotlinhand.model

enum class Hand(private val value: Int) {

    StraightFlush(9),
    FourOfAKind(8),
    FullHouse(7),
    FLush(6),
    Straight(5),
    ThreeOfAKind(4),
    TwoPair(3),
    Pair(2),
    HighCard(1);

    interface SameHandComperator<H : Hand> {

        fun compare(l1: List<Card>, l2: List<Card>, handType: H): Int

    }

    companion object {

        fun matches(cards: List<Card>) {
//            when (cards.) {
//            }
        }

        internal fun isStraight(cards: List<Card>): Boolean {
            if (cards.count { it.rank == Rank.ACE } > 1) {
                return false
            }

            val sortedCardsWithoutAces = cards.asSequence()
                .map { it.rank }
                .filter { it != Rank.ACE }
                .sortedBy { o -> o.value }
                .toList()

            val isStraight = sortedCardsWithoutAces
                .windowed(2)
                .all { list: List<Rank> -> list[0].value + 1 == list[1].value }
            return if (isStraight && cards.any { it.rank == Rank.ACE }) {
                (sortedCardsWithoutAces.first() == Rank.TWO || sortedCardsWithoutAces.last() == Rank.KING)
            } else isStraight
        }

        internal fun isFlush(cards: List<Card>): Boolean = cards.groupBy { it.suite }
            .keys
            .size == 1

        internal fun isStraightFlush(cards: List<Card>) = isFlush(cards) && isStraight(cards)

        internal fun isFourOfAKind(cards: List<Card>) = groupCountByRank(cards)
            .containsValue(4)

        internal fun isFullHouse(cards: List<Card>): Boolean {
            val cardsByRank = groupCountByRank(cards)
            return cardsByRank.containsValue(3) && cardsByRank.containsValue(2)
        }

        internal fun isThreeOfAKind(cards: List<Card>) = groupCountByRank(cards).containsValue(3)

        internal fun isTwoPair(cards: List<Card>) = groupCountByRank(cards).filterValues { it == 2 }
            .size == 2

        internal fun isPair(cards: List<Card>) = groupCountByRank(cards).containsValue(2)

        private fun groupCountByRank(cards: List<Card>) =
            cards.groupBy { it.rank }
                .mapValues { it.value.size }

    }

}
