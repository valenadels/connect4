
# Connect4



Connect4 is a game where two players take turns dropping pieces into a 7x6 grid. The first player to place 4 tokens in a row horizontally, vertically or diagonally wins. The players always insert a token in a column, and the token drops to the lowest available space.





The input is a sequence of numbers representing the column where the alternating active player places a token.



The output is the winner of the game, represented by the numbers "1" for player 1, "2" for player 2, and "0" for no winner.



For example:

[3, 4, 3, 4, 3, 4, 3] // both players play the same column over and over. Player 1 wins 


Examples
Input: new int[] {3, 4, 3, 4, 3, 4, 3}
Output: 1
Input: new int[] {3, 4, 3, 4, 3, 4, 2, 4}
Output: 2

# Run
`javac src/main/java/com/connect4/*.java`

`java -cp src/main/java com.connect4.Main`
