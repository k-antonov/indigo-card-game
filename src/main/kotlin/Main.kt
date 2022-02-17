// package indigo
class Game {
    private val initTableCards = 4
    private val cardsToDraw = 6
    private var deck: MutableList<String> =
        ("A♠ 2♠ 3♠ 4♠ 5♠ 6♠ 7♠ 8♠ 9♠ 10♠ J♠ Q♠ K♠ " +
                "A♥ 2♥ 3♥ 4♥ 5♥ 6♥ 7♥ 8♥ 9♥ 10♥ J♥ Q♥ K♥ " +
                "A♦ 2♦ 3♦ 4♦ 5♦ 6♦ 7♦ 8♦ 9♦ 10♦ J♦ Q♦ K♦ " +
                "A♣ 2♣ 3♣ 4♣ 5♣ 6♣ 7♣ 8♣ 9♣ 10♣ J♣ Q♣ K♣").split(" ") as MutableList<String>

    private var table = mutableListOf<String>()
    private val userPlayer = UserPlayer()
    private val aiPlayer = AIPlayer()

    private fun moveCards(from: MutableList<String>, to: MutableList<String>, amount: Int) {
        to += from.takeLast(amount)
        from.removeIf { from.indexOf(it) > from.lastIndex - amount }
    }

    private fun startTheGame() {
        fun loopGame(player1: Player, player2: Player) {
            fun printInfoAndTakeTurn(player: Player): Boolean {
                println("${table.size} cards on the table, and the top card is ${table[table.lastIndex]}")
                return player.takeTurn()
            }

            while (true) {
                if (!printInfoAndTakeTurn(player1)) return
                if (!printInfoAndTakeTurn(player2)) return
            }
        }

        fun printInfoAndLoopGame(player1: Player, player2: Player) {
            println("Initial cards on the table: ${table.joinToString(" ")}\n")
            loopGame(player1, player2)
        }

        println("Indigo Card Game")
        do {
            println("Play first?")
            val answer = readLine()?.lowercase()
            when (answer) {
                "yes" -> printInfoAndLoopGame(userPlayer, aiPlayer)
                "no" -> printInfoAndLoopGame(aiPlayer, userPlayer)
            }
        } while (answer !in listOf<String>("yes", "no", "exit"))
        return
    }

    init {
        deck.shuffle()
        moveCards(deck, table, initTableCards)
        startTheGame()
        println("Game Over")
    }

    abstract inner class Player {
        var hand = mutableListOf<String>()

        init {
            moveCards(deck, hand, cardsToDraw)
        }

        protected fun putCardOnTable(num: Int): Boolean {
            return if (num >= 0) {
                table.add(hand[num])
                hand.removeAt(num)
                true
            } else false
        }

        fun drawnSuccessfulIfHadTo(): Boolean {
            if (hand.isEmpty()) {
                if (deck.isEmpty()) return false
                moveCards(deck, hand, cardsToDraw)
            }
            return true
        }

        abstract fun takeTurn(): Boolean
    }

    private inner class UserPlayer: Player() {
        override fun takeTurn(): Boolean {
            fun makeChoice(): Int {
                var choice: String
                do {
                    println("Choose a card to play (1-${hand.size}):")
                    choice = readLine().toString()
                    if (choice == "exit") return -1
                } while (choice.toIntOrNull() !in 1..hand.size)
                return choice.toInt()
            }

            fun printHand() {
                print("Cards in hand:")
                for ((index, card) in hand.withIndex()) {
                    print(" ${index + 1})$card")
                }
                println()
            }

            if (!drawnSuccessfulIfHadTo()) return false

            printHand()
            return putCardOnTable(makeChoice() - 1)
        }
    }

    private inner class AIPlayer: Player() {
        override fun takeTurn(): Boolean {
            if (!drawnSuccessfulIfHadTo()) return false
            println("Computer plays ${hand[hand.lastIndex]}\n")
            return putCardOnTable(hand.lastIndex)
        }
    }
}

fun main() {
    Game()
}