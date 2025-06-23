package com.connect4;

public class Main {
    public static void main(String[] args) {
        int[] game = new int[]{3,4,3,4,3,4,3};

        Connect4 connect4 = new Connect4();
        connect4.initialize(game);
        System.out.println(connect4.winner());
    }
}