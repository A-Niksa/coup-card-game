package logic.game.tools;

import logic.game.GameState;
import logic.models.Hand;
import logic.models.Player;

import java.util.ArrayList;

public class EndgameChecker {
    // TODO: coordinating EndgameChecker with TurnKeeper for removing losers

    public boolean gameHasEnded() { // this method flags losers as well
        ArrayList<Player> playersList = GameState.getPlayersList();

        int numberOfPlayersWithoutCards = 0;
        Hand handOfPlayer;
        for (Player player : playersList) {
            handOfPlayer = player.getHand();
            if (handOfPlayer.getNumberOfCards() == 0) {
                declarePlayerAsLoser(player);

                numberOfPlayersWithoutCards++;

                if (numberOfPlayersWithoutCards >= 3) {
                    return true;
                }
            }
        }

        return false;
    }

    private void declarePlayerAsLoser(Player player) { // flags player as loser and returns its coins to the treasury
        player.setHasLost(true);

        int coinsOfPlayer = player.getNumberOfCoins();
        GameState.returnCoinsToTreasury(coinsOfPlayer);
    }
}