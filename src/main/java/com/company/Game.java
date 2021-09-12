package com.company;

import java.util.Arrays;
import java.util.List;

public class Game {
    public String[][] gameScheme;

    public void buildGameScheme(String[] moves) {
        String[][] gameMatrix = new String[moves.length][moves.length];
        int numOfMiddleElement = moves.length / 2;
        int winsAmount = 0;
        String win = "You win!", draw = "Draw!", lose = "You lose!";
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves.length; j++) {
                if (i == j) {
                    gameMatrix[i][j] = draw;
                    if (i < numOfMiddleElement)
                        winsAmount = numOfMiddleElement;
                    else
                        winsAmount = numOfMiddleElement + 1;
                } else if (winsAmount != 0) {
                    gameMatrix[i][j] = lose;
                    winsAmount--;
                } else
                    gameMatrix[i][j] = win;
            }
        }
        this.gameScheme = gameMatrix;
    }

    public String defineWinner(String[] moves, String userMove, String computerMove) {
        List<String> movesList = Arrays.asList(moves);
        return this.gameScheme[movesList.indexOf(userMove)][movesList.indexOf(computerMove)];
    }
}
