package utils.config;

import logic.models.CardIdentifier;

import java.util.ArrayList;
import java.util.Arrays;

public class HandMetaData {
    PlayerIdentifier playerIdentifier;
    ArrayList<CardIdentifier> cardsList;

    HandMetaData(PlayerIdentifier playerIdentifier, CardIdentifier firstCard, CardIdentifier secondCard) {
        this.playerIdentifier = playerIdentifier;
        cardsList = new ArrayList<>(Arrays.asList(firstCard, secondCard));
    }

    public PlayerIdentifier getPlayerIdentifier() {
        return playerIdentifier;
    }

    public ArrayList<CardIdentifier> getCardsList() {
        return cardsList;
    }
}
