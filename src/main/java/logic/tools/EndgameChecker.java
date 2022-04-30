package logic.tools;

import logic.models.Player;

import java.util.ArrayList;

public class EndgameChecker {
    private ArrayList<Player> playersList;

    public EndgameChecker(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public boolean gameHasEnded() {
        int numberOfPlayersWithoutCards = 0;

        for (Player player : playersList) {
            if (player.getNumberOfCards() == 0) {
                numberOfPlayersWithoutCards++;

                if (numberOfPlayersWithoutCards >= 3) {
                    return true;
                }
            }
        }

        return false;
    }
}