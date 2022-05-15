package logic.models;

import logic.game.GameState;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    private ArrayList<Card> cardsList;

    public Hand() {
        cardsList = new ArrayList<>();
    }

    public void addCardsToHand(Card... cards) {
        ArrayList<Card> cardsToAdd = new ArrayList<>(Arrays.asList(cards));

        if (cardsToAdd.size() > 2) {
            return;
        }

        for (Card card : cardsToAdd) {
            addCard(card);
        }
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

    public void clearHand() {
        cardsList.clear();
    }

    public int getNumberOfCards() {
        return cardsList.size();
    }

    public ArrayList<Card> getCardsList() {
        return cardsList;
    }
}
