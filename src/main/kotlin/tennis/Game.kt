package tennis

import kotlin.RuntimeException

class Game (private val playerOne : Player, private val playerTwo : Player) {

    fun increaseScore(player : Player) {
        player.score++;
    }

    fun hasAdvantage(scorePlayerOne: Int, scorePlayerTwo: Int) : Boolean {
        return (scorePlayerOne >= 4 && scorePlayerTwo >= 3 && (scorePlayerOne - scorePlayerTwo) == 1)
            || (scorePlayerTwo >= 4 && scorePlayerOne >= 3 && (scorePlayerTwo - scorePlayerOne) == 1)
    }

    fun hasWinner(scorePlayerOne: Int, scorePlayerTwo: Int) : Boolean {
        return (scorePlayerOne == 4 && scorePlayerTwo < 3)
                || (scorePlayerTwo == 4 && scorePlayerOne < 3)
                || (scorePlayerOne > 4 && scorePlayerOne - scorePlayerTwo == 2)
                || (scorePlayerTwo > 4 && scorePlayerTwo - scorePlayerOne == 2)
    }

    fun isDeuce(scorePlayerOne : Int, scorePlayerTwo: Int): Boolean {
        return (scorePlayerOne > 3 && scorePlayerOne == scorePlayerTwo)
    }

    fun getPlayerHighestScore() : Player {
        if (playerOne.score > playerTwo.score) {
            return playerOne
        }
        return playerTwo
    }

    fun convertScore(score: Int) : String {
        return when (score) {
            0 -> "0"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> throw RuntimeException("An issue occurred")
        }
    }

    fun checkGameStatus(): GameStatus {
        val scorePlayerOne = playerOne.score
        val scorePlayerTwo = playerTwo.score

        return when {
            hasWinner(scorePlayerOne, scorePlayerTwo) -> GameStatus.WIN
            hasAdvantage(scorePlayerOne, scorePlayerTwo) -> GameStatus.ADVANTAGE
            isDeuce(scorePlayerOne, scorePlayerTwo) -> GameStatus.DEUCE
            else -> GameStatus.SCORE
        }
    }

    fun getScore(): String {
        return when (checkGameStatus()) {
            GameStatus.WIN -> "${getPlayerHighestScore().playerName} won the game!"
            GameStatus.ADVANTAGE -> "${getPlayerHighestScore().playerName} has the advantage."
            GameStatus.DEUCE -> "Deuce"
            else -> "${convertScore(playerOne.score)} | ${convertScore(playerTwo.score)}"
        }
    }

    fun resetPlayersScore() {
        playerOne.score = 0
        playerTwo.score = 0
    }
}