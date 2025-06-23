package com.connect4;

public class Connect4 {
    private final int MAX_C = 6;
    private final int MAX_R = 7;
    private final int PLAYER_1 = 1;
    private final int PLAYER_2 = 2;
    private final int NO_WINNER = 0;
    private final int POS_TO_ANALYZE = 3;
    private final int WINNER_COUNT = 4;

    private int[][] board;
    private int[] lastMove;
    private int currentPlayer;

    public Connect4(){
        this.board = new int[MAX_R][MAX_C];
        this.currentPlayer = PLAYER_1;
    }

    public void initialize(int[] game){
        for (int i = 0; i < game.length; i++) {
            int col = game[i];
            int row = getNextFreeRow(col);
            this.board[row][col] = this.currentPlayer;

            if(i == game.length - 1){
                this.lastMove = new int[]{row, col};
            } else {
                switchPlayer();
            }
        }
    }

    private int getNextFreeRow(int col) {
        for (int row = MAX_R - 1; row >= 0; row--) {
            if (this.board[row][col] == NO_WINNER) {
                return row;
            }
        }

        return -1;
    }

    private void switchPlayer() {
        if (this.currentPlayer == PLAYER_1) {
            this.currentPlayer = PLAYER_2;
        } else {
            this.currentPlayer = PLAYER_1;
        }
    }

    public int winner() {
        if (wonVertically()
            || wonHorizontally()
            || wonDiagonally())
            return currentPlayer;
        return NO_WINNER;
    }

    private boolean wonVertically() {
        int col = lastMove[1];
        int fil = lastMove[0];
        int start = getStart(fil);
        int end = getEnd(fil, MAX_R);

        int count = 0;
        for (int i = start; i <= end && count < WINNER_COUNT; i++){
            if(this.board[i][col] != this.currentPlayer)
                count = 0;
            else
                count++;
        }

        return count == WINNER_COUNT;
    }

    private boolean wonHorizontally() {
        int col = lastMove[1];
        int fil = lastMove[0];
        int start = getStart(col);
        int end = getEnd(col, MAX_C);

        int count = 0;
        for (int i = start; i <= end && count < WINNER_COUNT; i++){
            if(this.board[fil][i] != this.currentPlayer)
                count = 0;
            else
                count++;
        }

        return count == WINNER_COUNT;
    }

    private boolean wonDiagonally() {
        int col = lastMove[1];
        int fil = lastMove[0];
        int startRow = getStart(fil);
        int endRow = getEnd(fil, MAX_R);
        int startCol = getStart(col);
        int endCol = getEnd(col, MAX_C);

        int count = 0;
        for (int i = startRow; i <= endRow && count < WINNER_COUNT; i++){
            for (int j = startCol; j <= endCol && count < WINNER_COUNT; j++) {
                if (this.board[i][j] != this.currentPlayer)
                    count = 0;
                else
                    count++;
            }
        }

        return count == WINNER_COUNT;
    }

    private int getEnd(int coord, int max) {
        return Math.min(coord + POS_TO_ANALYZE, max - 1);
    }

    private int getStart(int coord) {
        return Math.max(coord - POS_TO_ANALYZE, 0);
    }
}
