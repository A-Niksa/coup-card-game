package logic.models.actions.cardutils;

import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import utils.logging.ActionState;
import utils.logging.LogHistory;

public class CaptainActions {
    public static void extort(Player actionPlayer, Player targetPlayer) {
        // public, so that it can be accessed by ExtortionAction
        int extortedCoins = targetPlayer.extortCoinsFromPlayer(2);
        actionPlayer.addCoinsToPlayer(extortedCoins);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(),
                ActionIdentifier.EXTORTION, ActionState.SUCCESSFUL);
    }

    static void blockExtortion(Player actionPlayer) {
        // empty logic body since skipping the countered action in the stack of actions will work like blocking

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.EXTORTION_COUNTER,
                ActionState.SUCCESSFUL);
    }
}
