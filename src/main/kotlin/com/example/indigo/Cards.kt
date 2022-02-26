package com.example.indigo

class WonDeck : Deck {
    override var collection = ArrayDeque<Card>()
}

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

interface Deck : CardCollection

interface CardCollection {
    var collection: ArrayDeque<Card>

    fun putCards(to: CardCollection,
                 amount: Int,
                 pos: Int = this.collection.size - amount): Boolean { // todo подумать почему замена на this.collection.size ничего не сломала
        if (this.collection.size < amount) return false
        val elementsToMove = this.collection.subList(pos, pos + amount)
        to.collection.addAll(elementsToMove)
        this.collection.removeAll(elementsToMove)
        return true
    }

    fun isEmpty() = collection.isEmpty()
}

class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return "${rank.printableName}${suit.printableName}"
    }

    enum class Rank(val printableName: String, val value: Int) {
        ACE("A", 1),
        KING("K", 1),
        QUEEN("Q", 1),
        JACK("J", 1),
        TEN("10", 1),
        NINE("9", 0),
        EIGHT("8", 0),
        SEVEN("7", 0),
        SIX("6", 0),
        FIVE("5", 0),
        FOUR("4", 0),
        THREE("3", 0),
        TWO("2", 0)
    }

    enum class Suit(val printableName: Char) {
        SPADES('♠'),
        HEARTS('♥'),
        DIAMONDS('♦'),
        CLUBS('♣')
    }
}