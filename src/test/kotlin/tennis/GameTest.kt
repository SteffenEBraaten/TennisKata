package tennis

import org.junit.Before
import org.junit.Test
import org.junit.Assert

class GameTest {

    lateinit var player1: Player
    lateinit var player2: Player
    lateinit var game: Game

    @Before
    fun init() {
        player1 = Player("Rafael Nadal", 0)
        player2 = Player("Roger Federer", 0)
        game = Game(player1, player2)
    }

    // increaseScore()
    @Test
    fun `It should increase the players score`() {
        game.increaseScore(player1)

        Assert.assertEquals(1, player1.score)
        Assert.assertEquals(0, player2.score)
    }

    // isDeuce
    @Test
    fun `It should result in a valid deuce`() {
        for(i in 0..3) {
            game.increaseScore(player1)
            game.increaseScore(player2)
        }
        Assert.assertEquals(true, game.isDeuce(player1.score, player2.score))
    }

    @Test
    fun `It should result in a invalid deuce`() {
        Assert.assertNotEquals(true, game.isDeuce(player1.score, player2.score))

        createScores(2, 2)

        Assert.assertNotEquals(true, game.isDeuce(player1.score, player2.score))

        game.increaseScore(player1)
        Assert.assertNotEquals(true, game.isDeuce(player1.score, player2.score))
    }

    // hasAdvantage()
    @Test
    fun `It should result in a advantage`() {
        createScores(4, 3)

        Assert.assertEquals(true, game.hasAdvantage(player1.score, player2.score))
    }

    // getPlayerHighetsScore()
    @Test
    fun `Highest score testing`() {
        createScores(3, 2)

        Assert.assertEquals(player1, game.getPlayerHighestScore())
    }

    // hasWinner()
    @Test
    fun `It should be a winner when the player1 wins at advantage`() {
        createScores(5, 3)

        Assert.assertEquals(true, game.hasWinner(player1.score, player2.score))
    }

    // convertScore()
    @Test
    fun `It should convert the score into "0" when given a score of 0`() {
        Assert.assertEquals("0", game.convertScore(player1.score))
    }

    @Test
    fun `It should convert the score into "15" when given a score of 1`() {
        game.increaseScore(player1)
        Assert.assertEquals("15", game.convertScore(player1.score))
    }

    @Test
    fun `It should convert the score into "30" when given a score of 2`() {
        createScores(1, 0)
        Assert.assertEquals("30", game.convertScore(player1.score))
    }

    @Test
    fun `It should convert the score into "40" when given a score of 3`() {
        createScores(2, 0)
        Assert.assertEquals("40", game.convertScore(player1.score))
    }

    @Test (expected = RuntimeException::class)
    fun `It should result in a RuntimeException when trying to get convert a score over 3`() {
        createScores(3, 0)

        game.convertScore(player1.score)
    }

    // getScore()
    @Test
    fun `It should result in a win for Player1`() {
        createScores(3, 1)

        Assert.assertEquals("${player1.playerName} won the game!", game.getScore())
    }

    @Test
    fun `It should result in a advantage for Player1`() {
        createScores(5, 4)

        Assert.assertEquals("${player1.playerName} has the advantage.", game.getScore())
    }

    @Test
    fun `It should be a deuce`() {
        createScores(3, 3)

        Assert.assertEquals("Deuce", game.getScore())
    }

    @Test
    fun `It should return the score 15 | 0`() {
        game.increaseScore(player1)
        Assert.assertEquals("15 | 0", game.getScore())
    }

    @Test
    fun `It should return the score 0 | 15`() {
        game.increaseScore(player2)
        Assert.assertEquals("0 | 15", game.getScore())
    }

    @Test
    fun `It should return the score 15 | 15`() {
        createScores(0, 0)
        Assert.assertEquals("15 | 15", game.getScore())
    }

    @Test
    fun `It should return the score 30 | 15`() {
        createScores(1, 0)
        Assert.assertEquals("30 | 15", game.getScore())
    }

    @Test
    fun `It should return the score 15 | 30`() {
        createScores(0, 1)
        Assert.assertEquals("15 | 30", game.getScore())
    }

    @Test
    fun `It should return the score 30 | 30`() {
        createScores(1, 1)
        Assert.assertEquals("30 | 30", game.getScore())
    }

    @Test
    fun `It should return the score 40 | 30`() {
        createScores(2, 1)
        Assert.assertEquals("40 | 30", game.getScore())
    }

    @Test
    fun `It should return the score 30 | 40`() {
        createScores(1, 2)
        Assert.assertEquals("30 | 40", game.getScore())
    }

    // checkGameStatus()
    @Test
    fun `It should return WIN`() {
        createScores(3, 1)
        Assert.assertEquals(GameStatus.WIN, game.checkGameStatus())
    }

    @Test
    fun `It should return ADVANTAGE`() {
        createScores(4, 3)
        Assert.assertEquals(GameStatus.ADVANTAGE, game.checkGameStatus())
    }

    @Test
    fun `It should return DEUCE`() {
        createScores(3, 3)
        Assert.assertEquals(GameStatus.DEUCE, game.checkGameStatus())
    }

    @Test
    fun `It should return SCORE`() {
        createScores(1, 2)
        Assert.assertEquals(GameStatus.SCORE, game.checkGameStatus())
    }

    // resetScore()
    @Test
    fun `It should reset the players scores to 0`() {
        createScores(5, 5)
        game.resetPlayersScore()

        Assert.assertEquals(0, player1.score)
        Assert.assertEquals(0, player2.score)
    }

    // Helper function to create scores for tests
    private fun createScores(scorePlayer1 : Int, scorePlayer2: Int) {
        for (i in 0..scorePlayer1) {
            game.increaseScore(player1)
        }
        for (i in 0..scorePlayer2) {
            game.increaseScore(player2)
        }
    }
}
