package com.example.indigo

interface Player {
    val hand: Hand
    companion object {
        val cardsToDraw = 6
    }

    interface Hand : CardCollection
}

object HumanPlayer : Player {
    override val hand: Player.Hand
        get() = TODO("Not yet implemented")

}

object AIPlayer : Player {
    override val hand: Player.Hand
        get() = TODO("Not yet implemented")
}