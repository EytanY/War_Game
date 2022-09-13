package com.example.wargame;

import java.io.Serializable;

public class Card implements Serializable {
    private final int power;
    private final String src;

    public Card(int power, String src) {
        this.power = power;
        this.src = src;
    }

    public int getPower() {
        return power;
    }

    public String getSrc() {
        return src;
    }


}
