package logic.game.components;

import gui.guiconfig.menus.BotSelectionConfig;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;
import utils.config.ConfigProcessor;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Hand getHand(Player player) {
        if (BotSelectionConfig.shouldUseDefaultHands) {
            return getDefaultHand(player);
        } else { // should not use default hands === should use random hands
            return getRandomHand();
        }
    }

    private Hand getDefaultHand(Player player) { // gets default hands from game metadata config
        ArrayList<CardIdentifier> cardIdentifiersOfPlayer = ConfigProcessor.getCardIdentifiersOfPlayer(player);

        Card firstCard = getCardByIdentifier(cardIdentifiersOfPlayer.get(0));
        Card secondCard = getCardByIdentifier(cardIdentifiersOfPlayer.get(1));

        Hand hand = new Hand();
        ArrayList<Card> cardsList = new ArrayList<>(Arrays.asList(firstCard, secondCard));
        hand.addCardsToHand(cardsList);

        return hand;
    }

    private Card getCardByIdentifier(CardIdentifier identifier) {
        for (Card card : cardsList) {
            if (card.getIdentifier() == identifier) {
                return card;
            }
        }

        return getRandomCard(); // returns random card if card cannot be drawn from the deck
    }

    private Hand getRandomHand() {
        Card firstCard = getRandomCard();
        Card secondCard = getRandomCard();

        Hand hand = new Hand();
        ArrayList<Card> cardsList = new ArrayList<>(Arrays.asList(firstCard, secondCard));
        hand.addCardsToHand(cardsList);

        return hand;
    }

//    public void chooseCardsFromExchangeCards(Card firstSelectedCard, Card secondSelectedCard,
//                                             ArrayList<Card> exchangeCardsList, Player player) {
//        Card card;
//        for (int i = 0; i < exchangeCardsList.size(); i++) {
//            card = exchangeCardsList.get(i);
//            if (card == firstSelectedCard || card == secondSelectedCard) {
//
//            }
//        }
//    }

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