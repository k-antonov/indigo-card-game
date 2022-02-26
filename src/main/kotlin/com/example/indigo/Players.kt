package com.example.indigo

abstract class Player {
    protected val hand = Hand()
    protected val wonDeck = WonDeck()
    protected var points = 0
    companion object {
        const val cardsToDraw = 6
    }

    init {
        DrawDeck.putCards(hand, cardsToDraw)
    }

    protected abstract fun displayHand(): Unit

    protected abstract fun chooseCard(): Int?

    // todo изменение тут
    fun takeTurn(): Card? {
        if (hand.isEmpty()) {
            if (DrawDeck.isEmpty()) {
                return null
            }
            draw()
        }
        displayHand()
        val chosenCardNum = chooseCard() ?: return null
        val currentCard = getCardByNumber(chosenCardNum)
        putChosenCard(chosenCardNum)
        return currentCard
    }

    private fun getCardByNumber(num: Int) = hand.collection[num - 1]

    private fun putChosenCard(cardNum: Int) = hand.putCards(PutDeck, 1, cardNum)

    private fun draw() = DrawDeck.putCards(hand, cardsToDraw)

    protected class Hand : CardCollection {
        override var collection = ArrayDeque<Card>()

        override fun putCards(to: CardCollection, amount: Int, pos: Int): Boolean {
            return super.putCards(to, amount, pos - 1)
        }
    }
}

class HumanPlayer : Player() {
    override fun displayHand() {
        var indexesAndCards = ""
        hand.collection.forEachIndexed { index, card ->
            indexesAndCards += " ${index + 1})$card"
        }
        println(Message.HAND.text.format(indexesAndCards))
    }

    override fun chooseCard(): Int? {
        var input = ""
        val size = hand.collection.size
        val range = "(1-$size):"
        do {
            println(Message.CHOOSE.text.format(range))
            input = readLine().toString()
            if (input == "exit") return null
        } while (input.toIntOrNull() !in 1..size)
        val chosenCardNum = input.toInt()
        println()
        return chosenCardNum
    }
}

class AIPlayer : Player() {
    private val chosenCardNum = 1

    override fun displayHand() {}

    override fun chooseCard(): Int {
        println(Message.AI_TURN.text.format(hand.collection[0]))
        return chosenCardNum // todo убрать затычку
    }
}