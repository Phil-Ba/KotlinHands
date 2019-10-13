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
            return if (isStraight && sortedCardsWithoutAces.any { it == Rank.ACE }) {
                (sortedCardsWithoutAces.first() == Rank.TWO || sortedCardsWithoutAces.last() == Rank.KING)
            } else isStraight
        }

        internal fun isFlush(cards: List<Card>): Boolean = cards.groupBy { it.suite }
            .keys
            .size == 1
    }

}
