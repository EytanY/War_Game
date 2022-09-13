package com.example.wargame;

import java.util.ArrayList;

public class GameManager {
    public static final String[] types = {"a", "b", "c", "d"};
    public static final int WHITE_PLAYER_WIN = 1;
    public static final int BLACK_PLAYER_WIN = 2;
    public static final int TIE = 0;
    public static ArrayList<Card> getCards(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0; i<4; i++){
            for (int j=2; j<=14; j ++){
                Card card = new Card(j, "@drawable/poker_card_" + j + "" + types[i]);
                cards.add(card);
            }
        }
        return cards;
    }

    public static Card randCard(ArrayList<Card> cards){
        if(cards.size() == 0){
            return null;
        }
        Card randC = cards.get(getRandomNumber(cards));
        cards.remove(randC);
        return randC;
    }

    public static int getRandomNumber(ArrayList<Card> cards) {
        return (int) ((Math.random() * cards.size()));
    }

    public static int checkWinner(Card whiteCard, Card blackCard){
        if(whiteCard.getPower() > blackCard.getPower())
            return WHITE_PLAYER_WIN;
        else if(blackCard.getPower() > whiteCard.getPower())
            return BLACK_PLAYER_WIN;
        else
            return TIE;

    }

}
