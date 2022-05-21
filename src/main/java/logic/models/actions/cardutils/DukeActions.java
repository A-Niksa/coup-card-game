package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import utils.logging.ActionState;
import utils.logging.LogHistory;

public class DukeActions {
    static void getTax(Player actionPlayer) {
        int taxCoins = GameState.requestCoinsFromTreasury(3);
        actionPlayer.addCoinsToPlayer(taxCoins);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.TAXATION,
                ActionState.SUCCESSFUL);
    }

    static void blockExternalHelp(Player actionPlayer) {
        // empty logic body since skipping the countered action in the stack of actions will work like blocking

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null,
                ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER, ActionState.SUCCESSFUL);
    }
}
