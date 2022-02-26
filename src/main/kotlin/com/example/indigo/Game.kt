package com.example.indigo

fun main() {
    Game().play()
}

private class Game {
    private var isFinished: Boolean = false
    private val table = Table()
    private val humanPlayer = HumanPlayer()
    private val aiPlayer = AIPlayer()
    fun play() {
        when (determineTheWinner()) {
            null -> println(Message.GAME_OVER)
            true -> println(Message.WIN)
            false -> println(Message.LOSE)
        }
    }

    private fun determineTheWinner(): Boolean? {
        val humanFirst = playFirst() ?: return null
        var humanTurn = humanFirst
        var currentCard: Card
        var topCard: Card
        table.printInitialCards()
        while (!isFinished) {
            table.render()
            topCard = PutDeck.collection.last() // top card before player's turn -> currentCard comes on top after
            // todo вынести DRY в отдельную функцию
            humanTurn = if (humanTurn) {
                currentCard = humanPlayer.takeTurn() ?: return null
                if (areCardsSimilar(topCard, currentCard)) {
                    println("Player Similar!")
                }
                false
            } else {
                currentCard = aiPlayer.takeTurn() ?: return null
                if (areCardsSimilar(topCard, currentCard)) {
                    println("AI Similar!")
                }
                true
            }
        }
        return true
    }

    private fun playFirst(): Boolean? {
        println(Message.GAME_NAME.text)
        var answer: String?
        do {
            println(Message.PLAY_FIRST.text)
            answer = readLine()?.lowercase()
        } while (answer !in listOf("yes", "no", "exit"))
        if (answer == "exit") return null
        return answer == "yes"
    }

    private fun areCardsSimilar(card1: Card, card2: Card): Boolean {
        return card1.rank == card2.rank || card1.suit == card2.suit
    }
}

private class Table {
    private val initCardsAmount = 4

    init {
        DrawDeck.putCards(PutDeck, initCardsAmount)
    }

    fun printInitialCards() {
        val firstCards = PutDeck.collection.joinToString(" ")
        println(Message.INIT_TABLE_CARDS.text.format(firstCards))
    }

    fun render() {
        val size = PutDeck.collection.size
        val topCard = PutDeck.collection.last()
        println(Message.TABLE_RENDER.text.format(size, topCard))
    }
}


