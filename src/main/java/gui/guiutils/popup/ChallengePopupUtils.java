package gui.guiutils.popup;

import controllers.actioncommands.ChallengeCommand;
import gui.popup.ChallengePopup;
import logic.game.GameState;
import logic.models.Player;
import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class ChallengePopupUtils {
    public static ArrayList<Action> getListOfChallengeableActions(ActionsStack stack, Player currentPlayer) {
        ArrayList<Action> challengeableActionsList = new ArrayList<>();

        for (Action action : stack.getStackOfActions()) {
            boolean isCounterAction = action.isCounterAction();
            boolean isNormalAction = !(isCounterAction || action.isChallenge());
            boolean isAnUnchallengeableAction = action.getActionIdentifier() == ActionIdentifier.INCOME_ACQUISITION ||
                    action.getActionIdentifier() == ActionIdentifier.COUP ||
                    action.getActionIdentifier() == ActionIdentifier.CARD_SWAP;


            if ((isNormalAction || isCounterAction) && !isAnUnchallengeableAction) {
                if (action.getActionPlayer() != currentPlayer) {
                    challengeableActionsList.add(action);
                }
            }
        }

        return challengeableActionsList;
    }

    public static void challenge(ChallengePopup popupPanel, Action challengedAction) {
        PlayerIdentifier targetIdentifier = challengedAction.getActionPlayer().getPlayerIdentifier();

        ChallengeCommand command = new ChallengeCommand(targetIdentifier, challengedAction);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    private static void disposePopup(ChallengePopup popupPanel) {
        popupPanel.getFrame().dispose();
    }
}
