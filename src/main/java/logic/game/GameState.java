package logic.game;

import logic.game.tools.EndgameChecker;
import logic.game.tools.TurnKeeper;
import logic.game.components.Deck;
import logic.models.Card;
import logic.models.Hand;
import logic.models.Human;
import logic.models.Player;
import logic.game.components.Treasury;
import logic.models.bots.*;
import utils.config.ConfigProcessor;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private static GameState state;

    private ArrayList<Player> playersList;
    private Deck deck;
    private Treasury treasury;
    private TurnKeeper turnKeeper;
    private EndgameChecker endgameChecker;

    private Random randomGenerator;
    private int indexOfCurrentPlayer;

    private GameState() {
        randomGenerator = new Random();
        indexOfCurrentPlayer = randomGenerator.nextInt(4); // allowed indices: 0, 1, 2, and 3
    }

    private static GameState getInstance() {
        if (state == null) {
            state = new GameState();
        }
        return state;
    }

    public static int requestCoinsFromTreasury(int numberOfRequestedCoins) {
        Treasury treasury = getTreasury();
        return treasury.requestCoins(numberOfRequestedCoins);
    }

    public static void returnCoinToTreasury(int numberOfCoinsToReturn) {
        Treasury treasury = getTreasury();
        treasury.returnCoins(numberOfCoinsToReturn);
    }

    public static Hand getRandomHandFromDeck() {
        Deck deck = getDeck();
        return deck.getRandomHand();
    }

    public static Card getRandomCardFromDeck() {
        Deck deck = getDeck();
        return deck.getRandomCard();
    }

    public static void returnCardToDeck(Card card) {
        Deck deck = getDeck();
        deck.addCardToDeck(card);
    }

    public static ArrayList<Card> getExchangeCardsFromDeck(Player player) {
        Deck deck = getDeck();
        return deck.getExchangeCards(player);
    }

    public static boolean gameHasEnded() {
        EndgameChecker endgameChecker = getEndgameChecker();
        return endgameChecker.gameHasEnded();
    }

    public static void advanceTurnInTurnKeeper() {
        TurnKeeper turnKeeper = getTurnKeeper();
        turnKeeper.advanceTurn();
    }

    public static Player getCurrentPlayerFromTurnKeeper() {
        TurnKeeper turnKeeper = getTurnKeeper();
        return turnKeeper.getCurrentPlayer();
    }

    public static ArrayList<Player> getPlayersList() {
        return getInstance().playersList;
    }

    public static Deck getDeck() {
        return getInstance().deck;
    }

    public static Treasury getTreasury() {
        return getInstance().treasury;
    }

    public static EndgameChecker getEndgameChecker() {
        return getInstance().endgameChecker;
    }

    public static TurnKeeper getTurnKeeper() {
        return getInstance().turnKeeper;
    }

    public static int getIndexOfCurrentPlayer() {
        return getInstance().indexOfCurrentPlayer;
    }

    public static void setPlayersList(ArrayList<Player> playersList) {
        getInstance().playersList = playersList;
    }

    public static void setDeck(Deck deck) {
        getInstance().deck = deck;
    }

    public static void setTreasury(Treasury treasury) {
        getInstance().treasury = treasury;
    }

    public static void setTurnKeeper(TurnKeeper turnKeeper) {
        getInstance().turnKeeper = turnKeeper;
    }

    public static void setEndgameChecker(EndgameChecker endgameChecker) {
        getInstance().endgameChecker = endgameChecker;
    }

    public static void setIndexOfCurrentPlayer(int indexOfCurrentPlayer) {
        getInstance().indexOfCurrentPlayer = indexOfCurrentPlayer;
    }
}