package at.bayava.kotlinhand.model

import io.kotlintest.properties.Gen

val suiteGen = Gen.enum<Suite>()
val rankGen = Gen.enum<Rank>()
val cardGen = Gen.bind(suiteGen, rankGen) { suite, rank -> Card(suite, rank) }
