package logic.game;

import controllers.HumanPlayerController;
import controllers.actioncommands.Command;
import logic.game.tools.EndgameChecker;
import logic.game.tools.TurnKeeper;
import logic.game.components.Deck;
import logic.models.Card;
import logic.models.Hand;
import logic.models.Player;
import logic.game.components.Treasury;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private static GameState state;

    private ArrayList<Player> playersList;
    private Deck deck;
    private Treasury treasury;
    private TurnKeeper turnKeeper;
    private EndgameChecker endgameChecker;
    private HumanPlayerController controller;

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

    public static void returnCoinsToTreasury(int numberOfCoinsToReturn) {
        Treasury treasury = getTreasury();
        treasury.returnCoins(numberOfCoinsToReturn);
    }

    public static Hand getHandFromDeck(Player player) {
        Deck deck = getDeck();
        return deck.getHand(player);
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

    public static HumanPlayerController getController() {
        return getInstance().controller;
    }

    public static int getIndexOfCurrentPlayer() {
        return getInstance().getIndexOfCurrentPlayerByInstance();
    }

    private int getIndexOfCurrentPlayerByInstance() {
        Player currentPlayer = playersList.get(indexOfCurrentPlayer);

        while (currentPlayer.hasLost()) { // finding the first player who hasn't lost yet
            indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % 4;
            currentPlayer = playersList.get(indexOfCurrentPlayer);
        }

        return indexOfCurrentPlayer;
    }

    public static boolean humanHasChosenAction() {
        HumanPlayerController controller = getController();
        return controller.humanHasChosenAction();
    }

    public static void setHumanHasChosenAction(boolean humanHasChosenAction) {
        HumanPlayerController controller = getController();
        controller.setHumanHasChosenAction(humanHasChosenAction);
    }

    public static void setCurrentCommandInController(Command command) {
        HumanPlayerController controller = getController();
        controller.setCurrentCommand(command);
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

    public static void setController(HumanPlayerController controller) {
        getInstance().controller = controller;
    }

    public static void setEndgameChecker(EndgameChecker endgameChecker) {
        getInstance().endgameChecker = endgameChecker;
    }

    public static void setIndexOfCurrentPlayer(int indexOfCurrentPlayer) {
        getInstance().indexOfCurrentPlayer = indexOfCurrentPlayer;
    }
}