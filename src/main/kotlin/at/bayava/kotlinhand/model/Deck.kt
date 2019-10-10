package at.bayava.kotlinhand.model

class Deck {

    val list: List<Card>
    private val iter: Iterator<Card>

    init {
        list = Suite.values()
            .flatMap { suite -> Rank.values().map { rank -> Card(suite, rank) } }
            .shuffled()
        iter = list.iterator()
    }

    fun draw(): Card = iter.next()
}
