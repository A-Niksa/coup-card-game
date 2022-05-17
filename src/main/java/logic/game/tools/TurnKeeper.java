package logic.game.tools;

import logic.game.GameState;
import logic.models.Player;

import java.util.ArrayList;

public class TurnKeeper {
    public void advanceTurn() {
        int indexOfCurrentPlayer = GameState.getIndexOfCurrentPlayer();
        indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % 4;

        GameState.setIndexOfCurrentPlayer(indexOfCurrentPlayer);
    }

    public Player getCurrentPlayer() {
        ArrayList<Player> playersList = GameState.getPlayersList();
        int indexOfCurrentPlayer = GameState.getIndexOfCurrentPlayer();

        return playersList.get(indexOfCurrentPlayer);
    }
}
