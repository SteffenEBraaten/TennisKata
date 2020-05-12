#Tennis
____
The tennis kata implemented in Kotlin with Junit.
You can find the original description of the tennis kata here: http://codingdojo.org/kata/Tennis/

### Description of the tennis kata
This Kata is about implementing a tennis game against one opponent.

#### Scoring system
- Each player can have either of these points in one game: "0, 15, 30, 40". 

#### Win
The player who has 40 and wins the ball is declared the winner, but there are special rules.
- If both have 40 the players are deuce. 
- If the game is in deuce, the winner of a ball will have advantage and game ball. 
- If the player with advantage wins the ball he wins the game 
- If the player without advantage wins they are back at deuce.

## Running the tests
To run the tests simply write the following
```bash 
gradle test
```