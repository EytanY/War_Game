package com.example.wargame.model;

import java.io.Serializable;

public class Winner implements Serializable {
    private String winnerName;
    private int score;

    public Winner(String winnerName, int score) {
        this.winnerName = winnerName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
