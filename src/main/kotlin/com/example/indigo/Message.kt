package com.example.indigo

enum class Message(val text: String) {
    GAME_NAME("Indigo Card Game"),
    PLAY_FIRST("Play first?"),
    INIT_TABLE_CARDS("Initial cards on the table: %s\n"),
    TABLE_RENDER("%d cards on the table, and the top card is %s"),
    HAND("Cards in hand:%s"),
    CHOOSE("Choose a card to play %s"),
    WIN("You win!"),
    LOSE("You lose."),
    GAME_OVER("Game Over");

    override fun toString() = text
}