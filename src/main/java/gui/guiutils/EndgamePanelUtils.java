package gui.guiutils;

import logic.game.GameState;
import logic.models.Player;

import java.util.ArrayList;

public class EndgamePanelUtils {
    public static String getNameOfWinner() {
        ArrayList<Player> playersList = GameState.getPlayersList();
        String winnerName = "Winner: ";

        for (Player player : playersList) {
            if (!player.hasLost()) {
                return winnerName + player.getPlayerName();
            }
        }

        return winnerName + "None of the players won this game";
    }
}
