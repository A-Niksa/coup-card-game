package logic.models;

import logic.game.GameState;
import logic.models.actions.ActionsStack;
import utils.config.PlayerIdentifier;

import java.util.Random;

public abstract class Player {
    protected PlayerIdentifier playerIdentifier;
    protected int playerIndex;
    protected boolean isHuman;
    protected String playerName;
    protected int numberOfCoins;
    protected Hand hand;

    public Player(PlayerIdentifier playerIdentifier, int playerIndex, boolean isHuman, String playerName) {
        this.playerIdentifier = playerIdentifier;
        this.playerIndex = playerIndex;
        this.isHuman = isHuman;
        this.playerName = playerName;

        numberOfCoins = GameState.requestCoinsFromTreasury(2); // setting initial number of coins
        hand = GameState.getHandFromDeck(this);
    }

    public abstract void playNormalAction(ActionsStack stack);

    public abstract void playCounterAction(ActionsStack stack);

    public abstract void challenge(ActionsStack stack);

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

    public int extortCoinsFromPlayer(int numberOfExtortionCoins) {
        if (numberOfCoins - numberOfExtortionCoins >= 0) {
            numberOfCoins -= numberOfExtortionCoins;
            return numberOfExtortionCoins;
        }

        int numberOfRemainingCoins = numberOfCoins;
        numberOfCoins = 0;
        return numberOfRemainingCoins; // will return all coins of the extorted player if there aren't sufficient coins
    }

    public int reduceCoinsFromPlayer(int numberOfCoinsToReduce) {
        if (numberOfCoins - numberOfCoinsToReduce >= 0) {
            numberOfCoins -= numberOfCoinsToReduce;
            return numberOfCoinsToReduce;
        }

        return 0;
    }

    public PlayerIdentifier getPlayerIdentifier() {
        return playerIdentifier;
    }

    public Hand getHand() {
        return hand;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}