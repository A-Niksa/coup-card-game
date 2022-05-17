package logic.game.tools;

import logic.game.GameState;
import logic.models.Hand;
import logic.models.Player;

import java.util.ArrayList;

public class EndgameChecker {
    // TODO: coordinating EndgameChecker with TurnKeeper for removing losers

    public boolean gameHasEnded() {
        ArrayList<Player> playersList = GameState.getPlayersList();

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