package at.bayava.kotlinhand.model

enum class Suite {
    SPADE, HEARTH, DIAMOND, CLUB;

    companion object {
        fun fromString(s: String): Suite = when (s) {
            "S" -> SPADE
            "H" -> HEARTH
            "D" -> DIAMOND
            "C" -> CLUB
            else -> throw IllegalArgumentException("Unknown value $s!")
        }
    }
}