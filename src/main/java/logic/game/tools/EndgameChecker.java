package logic.game.tools;

import logic.models.Hand;
import logic.models.Player;

import java.util.ArrayList;

public class EndgameChecker {
    // TODO: coordinating EndgameChecker with TurnKeeper for removing losers
    private ArrayList<Player> playersList;

    public EndgameChecker(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public boolean gameHasEnded() {
        int numberOfPlayersWithoutCards = 0;

        Hand handOfPlayer;
        for (Player player : playersList) {
            handOfPlayer = player.getHand();
            if (handOfPlayer.getNumberOfCards() == 0) {
                numberOfPlayersWithoutCards++;

                if (numberOfPlayersWithoutCards >= 3) {
                    return true;
                }
            }
        }

        return false;
    }
}