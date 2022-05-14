package logic.game.tools;

import logic.models.Player;

import java.util.ArrayList;

public class TurnKeeper {
    private ArrayList<Player> playersList;
    private int indexOfCurrentPlayer;

    public TurnKeeper(ArrayList<Player> players) {
        this.playersList = players;
        indexOfCurrentPlayer = 0;
    }

    public void advanceTurn() {
        indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % 4;
    }

    public Player getCurrentPlayer() {
        return playersList.get(indexOfCurrentPlayer);
    }
}
