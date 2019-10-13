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
            when (cards) {
            }
        }

        private fun isFlush(cards: List<Card>) = cards.groupBy { it.suite }
            .keys
            .size == 1

    }

}
