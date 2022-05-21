package controllers;

import logic.game.GameState;
import logic.models.Human;
import logic.models.Player;
import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;

public class ConditionCheckingUtils {
    public static boolean humanHasEnoughCoins(int numberOfRequiredCoins) {
        Player humanPlayer = getHumanPlayer();

        return numberOfRequiredCoins <= humanPlayer.getNumberOfCoins();
    }

    public static boolean thereHasBeenExternalHelpRequest(ActionsStack stack) {
        for (Action action : stack.getStackOfActions()) {
            if (action.getActionIdentifier() == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                return true;
            }
        }

        return false;
    }

    public static boolean humanIsTargetOfAction(ActionIdentifier actionIdentifier, ActionsStack stack) {
        Player humanPlayer = getHumanPlayer();

        for (Action action : stack.getStackOfActions()) {
            if (action.getActionIdentifier() == actionIdentifier) {
                if (action.getTargetPlayer() == humanPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Player getHumanPlayer() {
        return GameState.getPlayersList().get(0);
    }
}
