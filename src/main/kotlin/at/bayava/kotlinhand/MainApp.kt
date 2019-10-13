package at.bayava.kotlinhand

import at.bayava.kotlinhand.model.Card
import at.bayava.kotlinhand.model.Rank
import at.bayava.kotlinhand.model.Suite
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    scanner.useDelimiter(" ")
    scanner.findAll("([2-9JQKA]|(10))([HSDC])")
        .map { match ->
            val rank = match.group(1)
            val suite = match.group(3)
            Card(Suite.fromString(suite), Rank.fromString(rank))
        }
        .forEach { t -> println(t) }
}