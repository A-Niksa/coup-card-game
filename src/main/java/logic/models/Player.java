package logic.models;

import logic.game.GameState;
import logic.models.actions.ActionIdentifier;

import java.util.Random;

public abstract class Player {
    private int playerIndex;
    private boolean isHuman;
    private String playerName;
    private int numberOfCoins;
    private Hand hand;

    public Player(int playerIndex, boolean isHuman, String playerName) {
        this.playerIndex = playerIndex;
        this.isHuman = isHuman;
        this.playerName = playerName;

        numberOfCoins = GameState.requestCoinsFromTreasury(2); // setting initial number of coins
        hand = GameState.getRandomHandFromDeck();
    }

    public void swapRevealedCard(CardIdentifier identifier) { // swaps card with another card in the deck
        hand.swapCard(identifier);
    }

    public void punishPlayer() {
        Random randomGenerator = new Random();
        int randomNumberBetweenZeroAndOne = randomGenerator.nextInt(2);

        hand.removeCard(randomNumberBetweenZeroAndOne);
    }

    public void addCoinsToPlayer(int numberOfCoinsToAdd) {
        numberOfCoins += numberOfCoinsToAdd;
    }

    public Hand getHand() {
        return hand;
    }
}