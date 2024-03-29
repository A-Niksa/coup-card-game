package logic.models;

import logic.game.GameState;

import java.util.ArrayList;
import java.util.Random;

public class Hand {
    private Random randomGenerator;
    private ArrayList<Card> cardsList;

    public Hand() {
        randomGenerator = new Random();
        cardsList = new ArrayList<>();
    }

    public void addCardsToHand(ArrayList<Card> selectedCardsList) {
        cardsList.addAll(selectedCardsList);
    }

    private void addCard(Card card) {
        if (cardsList.size() >= 2) {
            return;
        }

        cardsList.add(card);
    }

    public void removeCard(int cardIndex) {
        Card cardToRemove = cardsList.get(cardIndex);
        GameState.returnCardToDeck(cardToRemove);

        cardsList.remove(cardIndex);
    }

    public void removeCard(CardIdentifier identifier) {
        Card card;

        for (int i = 0; i < cardsList.size(); i++) {
            card = cardsList.get(i);
            if (card.getIdentifier() == identifier) {
                removeCard(i);

                return;
            }
        }
    }

    public void swapCard(CardIdentifier identifier) {
        Card card;

        for (int i = 0; i < cardsList.size(); i++) {
            card = cardsList.get(i);
            if (card.getIdentifier() == identifier) {
                cardsList.remove(i);
                GameState.returnCardToDeck(card);

                cardsList.add(GameState.getRandomCardFromDeck());

                return;
            }
        }
    }

    public void swapRandomCard() {
        int randomIndex = randomGenerator.nextInt(cardsList.size());
        Card randomCardToSwap = cardsList.get(randomIndex);

        swapCard(randomCardToSwap.getIdentifier());
    }

    public void clearHand() {
        cardsList.clear();
    }

    public int getNumberOfCards() {
        return cardsList.size();
    }

    public ArrayList<Card> getCardsList() {
        return cardsList;
    }

    public ArrayList<CardIdentifier> getCardIdentifiersList() {
        ArrayList<CardIdentifier> cardIdentifiersList = new ArrayList<>();

        for (Card card : cardsList) {
            cardIdentifiersList.add(card.getIdentifier());
        }

        return cardIdentifiersList;
    }
}