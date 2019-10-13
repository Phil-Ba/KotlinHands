package at.bayava.kotlinhand.model

enum class Rank(val value: Int) {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    companion object {
        fun fromString(s: String): Rank = when (s) {
            "2" -> TWO
            "3" -> THREE
            "4" -> FOUR
            "5" -> FIVE
            "6" -> SIX
            "7" -> SEVEN
            "8" -> EIGHT
            "9" -> NINE
            "10" -> TEN
            "J" -> JACK
            "Q" -> QUEEN
            "K" -> KING
            "A" -> ACE
            else -> throw IllegalArgumentException("Unknown value $s!")
        }

        fun fromValue(value: Int): Rank = when (value) {
            2 -> TWO
            3 -> THREE
            4 -> FOUR
            5 -> FIVE
            6 -> SIX
            7 -> SEVEN
            8 -> EIGHT
            9 -> NINE
            10 -> TEN
            11 -> JACK
            12 -> QUEEN
            13 -> KING
            14 -> ACE
            else -> throw IllegalArgumentException("Unknown value $value!")
        }
    }

}