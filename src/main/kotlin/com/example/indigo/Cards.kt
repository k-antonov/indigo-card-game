package com.example.indigo

object DrawDeck : Deck {
    override var collection = ArrayDeque<Card>()

    init {
        Card.Rank.values().forEach { rank ->
            Card.Suit.values().forEach { suit ->
                collection.add(Card(rank, suit))
            }
        }
        collection.shuffle()
    }
}

object PutDeck : Deck {
    override var collection = ArrayDeque<Card>()
}

interface Deck : CardCollection {
    override fun putCards(to: CardCollection, amount: Int): Boolean {
        if (this.collection.size < amount) return false
        to.collection.addAll(this.collection.takeLast(amount))
        repeat(amount) { this.collection.removeLast() }
        return true
    }
}

interface CardCollection {
    var collection: ArrayDeque<Card>

    fun putCards(to: CardCollection, amount: Int): Boolean
}

class Card(private val rank: Rank, private val suit: Suit) {
    override fun toString(): String {
        return "${rank.printableName}${suit.printableName}"
    }

    enum class Rank(val printableName: String) {
        ACE("A"),
        KING("K"),
        QUEEN("Q"),
        JACK("J"),
        TEN("10"),
        NINE("9"),
        EIGHT("8"),
        SEVEN("7"),
        SIX("6"),
        FIVE("5"),
        FOUR("4"),
        THREE("3"),
        TWO("2")
    }

    enum class Suit(val printableName: Char) {
        SPADES('♠'),
        HEARTS('♥'),
        DIAMONDS('♦'),
        CLUBS('♣')
    }
}