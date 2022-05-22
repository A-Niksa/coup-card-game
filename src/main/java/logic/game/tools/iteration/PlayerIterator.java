package logic.game.tools.iteration;

import logic.models.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerIterator implements Iterator<Player> {
    public enum IterationType {
        ALL_PLAYERS,
        ALL_PLAYERS_EXCEPT_CURRENT_PLAYER
    }

    private int currentTurnIndex;
    private ArrayList<Player> playersList;
    private IterationType type;

    private int numberOfIteratedPlayers;

    public PlayerIterator(int currentTurnIndex, ArrayList<Player> playersList, IterationType type) {
        if (type == IterationType.ALL_PLAYERS) {
            this.currentTurnIndex = currentTurnIndex; // there's no need for %4 since it's already done by turnKeeper
        } else { // type == IterationType.ALL_PLAYERS_EXCEPT_CURRENT_PLAYER
            this.currentTurnIndex = (currentTurnIndex + 1) % 4; // +1 so that the turn starts from the next player
        }
        this.playersList = playersList;
        this.type = type;

        numberOfIteratedPlayers = 0;
    }

    @Override
    public boolean hasNext() {
        if (type == IterationType.ALL_PLAYERS) {
            return ++numberOfIteratedPlayers <= 4;
        }

        return ++numberOfIteratedPlayers <= 3;
    }

    @Override
    public Player next() {
        Player nextPlayer = playersList.get(currentTurnIndex);
        currentTurnIndex = (currentTurnIndex + 1) % 4;

        return nextPlayer;
    }
}