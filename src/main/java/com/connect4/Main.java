package com.connect4;

import java.util.*;
import java.io.*;

class Main {

    public static int Connect4(int[] arr) {
        Connect4 game = new Connect4();
        game.init(arr);
        return game.winner();
    }

    public static void main (String[] args) {
        // keep this function call here
        System.out.print("Sample test passing: " + (Connect4(new int[] {3, 4, 3, 4, 3, 4, 3}) == 1 ) + "\r\n");
        System.out.print("Sample test passing: " + (Connect4(new int[] {3, 4, 3, 4, 3, 4, 2, 4}) == 2 ) + "\r\n");
        System.out.print("Sample test passing: " + (Connect4(new int[] {3, 4, 3, 4, 3, 4, 3}) == 1 ) + "\r\n");
        System.out.print("Sample test passing: " + (Connect4(new int[] {3, 4, 3, 4, 3, 4, 2, 4}) == 2) + "\r\n");
        System.out.print("Sample test passing: " + (Connect4(new int[] {3, 3, 4, 4, 5, 5, 6}) == 1) + "\r\n");
        System.out.print("Sample test passing: " + (Connect4(new int[] {0, 1, 1, 2, 2, 4, 2, 3, 3, 3, 3}) == 1) + "\r\n");
        /* do not remove this line: __internalTestCases__ */
    }
}

class Connect4 {
    private final int MAX_R = 6;
    private final int MAX_C = 7;
    private final int P1 = 1;
    private final int P2 = 2;
    private final int NO_WINNER = 0;
    private final int WINNER_LINE = 4;
    private final int RANGE = 3;

    private int[][] board;
    private int player;
    private int lastRow;
    private int lastCol;

    public Connect4() {
        this.board = new int[MAX_R][MAX_C];
        this.player = P1;
    }

    private void switchPlayer(){
        if(this.player == P1)
            this.player = P2;
        else
            this.player = P1;
    }

    private int getNextRow(int col){
        for(int i = MAX_R - 1; i >= 0; i--){
            if(this.board[i][col] == NO_WINNER)
                return i;
        }

        return -1;
    }

    public void init(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int col = arr[i];
            int row = getNextRow(col);

            this.board[row][col] = this.player;

            if(i == arr.length - 1){
                this.lastCol = col;
                this.lastRow = row;
            }else{
                switchPlayer();
            }
        }
    }

    private boolean wonVertically(){
        int start = this.getStart(this.lastCol);
        int end = this.getEnd(this.lastCol, MAX_R);

        int count = 0;

        for(int i = start; i <= end && count < WINNER_LINE; i++){
            if(this.player != this.board[i][this.lastCol])
                count = 0;
            else
                count++;
        }

        return count == WINNER_LINE;
    }

    private boolean wonHorizontally(){
        int start = this.getStart(this.lastRow);
        int end = this.getEnd(this.lastRow, MAX_C);

        int count = 0;
        for(int i = start; i <= end && count < WINNER_LINE; i++){
            if(this.player != this.board[this.lastRow][i])
                count = 0;
            else
                count++;
        }

        return count == WINNER_LINE;
    }

    private boolean wonDiagonally(int dRow, int dCol) {
        int count = 1;

        int i = lastRow + dRow;
        int j = lastCol + dCol;
        while (i >= 0 && i < MAX_R && j >= 0 && j < MAX_C && board[i][j] == player) {
            count++;
            i += dRow;
            j += dCol;
        }

        i = lastRow - dRow;
        j = lastCol - dCol;
        while (i >= 0 && i < MAX_R && j >= 0 && j < MAX_C && board[i][j] == player) {
            count++;
            i -= dRow;
            j -= dCol;
        }

        return count >= WINNER_LINE;
    }

    private int getStart(int coord){
        return Math.max(0, coord - RANGE);
    }

    private int getEnd(int coord, int max){
        return Math.min(max - 1, coord + RANGE);
    }

    public int winner(){
        if(wonVertically() || wonHorizontally() || wonDiagonally(1,1) || wonDiagonally(1,-1))
            return this.player;

        return NO_WINNER;
    }
}