package com.example.indigo

enum class Message(val text: String) {
    GAME_NAME("Indigo Card Game"),
    PLAY_FIRST("Play first?"),
    INIT_TABLE_CARDS("Initial cards on the table: %d"),
    GAME_OVER("Game Over");

    override fun toString() = text
}