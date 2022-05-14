package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Player;

public class DukeActions {
    static void getTax(Player activePlayer) {
        int taxCoins = GameState.requestCoinsFromTreasury(3);
        activePlayer.addCoinsToPlayer(taxCoins);
    }

    static void blockExternalHelp() {
        // empty body since skipping the countered action in the stack of actions will work like blocking
    }
}
