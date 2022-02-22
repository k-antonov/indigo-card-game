package com.example.indigo

fun main() {
    Game.play()
}

object Game {
    private var isFinished: Boolean = false
//    val table = Table()
    private val players = listOf(HumanPlayer, AIPlayer)
    // todo add winner

    fun play() {
        when (determineTheWinner()) {
            null -> println(Message.GAME_OVER)
            true -> println("You win!")
            false -> println("You lose.")
        }
    }

    private fun determineTheWinner(): Boolean? {
        val humanFirst = Game.playFirst() ?: return null
        while (!Game.isFinished) {
            // render the table and player hand
            // input
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

    fun render() {

    }
}


