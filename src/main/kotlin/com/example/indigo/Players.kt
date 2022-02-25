package com.example.indigo

abstract class Player {
    protected val hand = Hand()
    companion object {
        const val cardsToDraw = 6
    }

    init {
        DrawDeck.putCards(hand, cardsToDraw)
    }

    protected abstract fun displayHand(): Unit

    protected abstract fun chooseCard(): Boolean

    fun takeTurn(): Boolean {
        if (hand.isEmpty()) {
            if (DrawDeck.isEmpty()) {
                println("All empty")
                return false
            }
            draw()
        }
        displayHand()
        if (!chooseCard()) return false
        return true
    }

    private fun draw() = DrawDeck.putCards(hand, cardsToDraw)

    protected class Hand : CardCollection {
        override var collection = ArrayDeque<Card>()

        override fun putCards(to: CardCollection, amount: Int, pos: Int): Boolean {
            return super.putCards(to, amount, pos - 1)
        }
    }
}

object HumanPlayer : Player() {
    override fun displayHand() {
        var indexesAndCards = ""
        hand.collection.forEachIndexed { index, card ->
            indexesAndCards += " ${index + 1})$card"
        }
        println(Message.HAND.text.format(indexesAndCards))
    }

    override fun chooseCard(): Boolean {
        var choice = ""
        val size = hand.collection.size
        val range = "(1-$size):"
        do {
            println(Message.CHOOSE.text.format(range))
            choice = readLine().toString()
            if (choice == "exit") return false
        } while (choice.toIntOrNull() !in 1..size)
        hand.putCards(PutDeck, 1, choice.toInt())
        println()
        return true
    }
}

object AIPlayer : Player() {
    override fun displayHand() {}

    override fun chooseCard(): Boolean {
        println(Message.AI_TURN.text.format(hand.collection[0]))
        hand.putCards(PutDeck, 1, 1)
        return true
    }
}