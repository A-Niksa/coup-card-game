package logic.game.tools.iteration;

import logic.models.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerIterable implements Iterable<Player> {
    private int currentTurnIndex;
    private ArrayList<Player> playersList;
    private PlayerIterator.IterationType iterationType;

    public PlayerIterable(int currentTurnIndex, ArrayList<Player> playersList,
                          PlayerIterator.IterationType iterationType) {
        this.currentTurnIndex = currentTurnIndex;
        this.playersList = playersList;
        this.iterationType = iterationType;
    }

    @Override
    public Iterator<Player> iterator() {
        return new PlayerIterator(currentTurnIndex, playersList, iterationType);
    }
}
