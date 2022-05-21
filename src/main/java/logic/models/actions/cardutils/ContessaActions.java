package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import utils.logging.ActionState;
import utils.logging.LogHistory;

public class ContessaActions {
    static void blockAssassination(Player actionPlayer, Player targetPlayer) {
        // punishing the assassin:
        int punishmentCoins = targetPlayer.reduceCoinsFromPlayer(3);
        GameState.requestCoinsFromTreasury(punishmentCoins);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(),
                ActionIdentifier.ASSASSINATION_COUNTER, ActionState.SUCCESSFUL);
    }
}
