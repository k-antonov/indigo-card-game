package com.example.indigo

fun main() {
    Game.play()
}

object Game {
    private var isFinished: Boolean = false
    // todo add winner

    fun play() {
        when (determineTheWinner()) {
            null -> println(Message.GAME_OVER)
            true -> println(Message.WIN)
            false -> println(Message.LOSE)
        }
    }

    private fun determineTheWinner(): Boolean? {
        var humanTurn = playFirst() ?: return null
        Table.printInitialCards()
        while (!isFinished) {
            Table.render() // вызывается три раза, потому что игроки берут карты, а это не выводится на консоль
            humanTurn = if (humanTurn) {
                if (!HumanPlayer.takeTurn()) return null
                false
            } else {
                AIPlayer.takeTurn()
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
}

object Table {
    private const val initCardsAmount = 4

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


