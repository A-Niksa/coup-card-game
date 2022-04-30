package logic.models;

import java.util.ArrayList;

public class Player {
    private int numberOfCoins;
    private ArrayList<Card> cardsList;

    public int getNumberOfCards() {
        return cardsList.size();
    }
}