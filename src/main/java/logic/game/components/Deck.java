package logic.game.components;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private static final int NUMBER_OF_CARDS_PER_ROLE = 3;

    private Random randomGenerator;
    private ArrayList<Card> cardsList;

    public Deck() {
        randomGenerator = new Random();
        cardsList = new ArrayList<>();

        createCards();
        shuffleCards();
    }

    private void createCards() {
        for (int i = 0; i < NUMBER_OF_CARDS_PER_ROLE; i++) {
            cardsList.add(new Card(CardIdentifier.AMBASSADOR));
            cardsList.add(new Card(CardIdentifier.ASSASSIN));
            cardsList.add(new Card(CardIdentifier.CAPTAIN));
            cardsList.add(new Card(CardIdentifier.CONTESSA));
            cardsList.add(new Card(CardIdentifier.DUKE));
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cardsList);
    }

    public void addCardToDeck(Card card) {
        cardsList.add(card);
    }

    public Hand getRandomHand() {
        Card firstCard = getRandomCard();
        Card secondCard = getRandomCard();

        Hand hand = new Hand();
        hand.addCardsToHand(firstCard, secondCard);

        return hand;
    }

    public void chooseCardsFromExchangeCards(Card firstSelectedCard, Card secondSelectedCard,
                                             ArrayList<Card> exchangeCardsList, Player player) {
        Card card;
        for (int i = 0; i < exchangeCardsList.size(); i++) {
            card = exchangeCardsList.get(i);
            if (card == firstSelectedCard || card == secondSelectedCard) {

            }
        }
    }

    private void returnCardsToDeck(ArrayList<Card> cardsToReturnList) {
        for (Card card : cardsToReturnList) {
            addCardToDeck(card);
        }
    }

    public ArrayList<Card> getExchangeCards(Player player) {
        ArrayList<Card> exchangeCardsList = new ArrayList<>();

        exchangeCardsList.add(getRandomCard());
        exchangeCardsList.add(getRandomCard());

        ArrayList<Card> playerCardsList = player.getHand().getCardsList();
        exchangeCardsList.addAll(playerCardsList);

        return exchangeCardsList;
    }

    public Card getRandomCard() { // will also remove card from cardsList
        shuffleCards();

        int randomIndex = randomGenerator.nextInt(cardsList.size());
        Card randomCard = cardsList.get(randomIndex);
        cardsList.remove(randomIndex);

        return randomCard;
    }
}