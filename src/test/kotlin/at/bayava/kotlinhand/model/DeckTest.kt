package at.bayava.kotlinhand.model

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec

class DeckTest : ShouldSpec() {

    init {
        "list"{
            should("contain 52 cards") {
                Deck().list.size shouldBe 52
            }

        }
        should("draw") { }
    }

}