package logic.game;

import logic.game.tools.EndgameChecker;
import logic.game.tools.TurnKeeper;
import logic.game.components.Deck;
import logic.models.Card;
import logic.models.Hand;
import logic.models.Player;
import logic.game.components.Treasury;

import java.util.ArrayList;

public class GameState {
    private static GameState state;

    private ArrayList<Player> playersList;
    private Deck deck;
    private Treasury treasury;
    private TurnKeeper turnKeeper;
    private EndgameChecker endgameChecker;

    private GameState() {
    }

    private static GameState getInstance() {
        if (state == null) {
            state = new GameState();
        }
        return state;
    }

    public static int requestCoinsFromTreasury(int numberOfRequestedCoins) {
        Treasury treasury = getInstance().getTreasury();
        return treasury.requestCoins(numberOfRequestedCoins);
    }

    public static Hand getRandomHandFromDeck() {
        Deck deck = getInstance().getDeck();
        return deck.getRandomHand();
    }

    public static Card getRandomCardFromDeck() {
        Deck deck = getInstance().getDeck();
        return deck.getRandomCard();
    }

    public static void returnCardToDeck(Card card) {
        Deck deck = getInstance().getDeck();
        deck.addCardToDeck(card);
    }

    public Deck getDeck() {
        return deck;
    }

    public Treasury getTreasury() {
        return treasury;
    }
}