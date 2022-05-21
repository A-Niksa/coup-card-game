package gui.guiutils.popup;

import controllers.ConditionCheckingUtils;
import controllers.actioncommands.*;
import gui.popup.ActionsPopup;
import gui.popup.PopupFrame;
import logic.game.GameState;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import utils.config.PlayerIdentifier;
import utils.logging.GameStateEntry;
import utils.logging.GameStateHistory;
import utils.logging.GameStateIdentifier;

import javax.swing.*;
import java.util.ArrayList;

public class ActionsPopupUtils {
    public static void swapCardRandomly(PopupFrame frame, ActionsPopup popupPanel) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        if (!ConditionCheckingUtils.humanHasEnoughCoins(1)) {
            JOptionPane.showMessageDialog(frame, "You do not have a sufficient number of coins.");
            return;
        }

        CardSwapCommand command = new CardSwapCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void acquireIncome(PopupFrame frame, ActionsPopup popupPanel) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        IncomeCommand command = new IncomeCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void requestExternalHelp(PopupFrame frame, ActionsPopup popupPanel) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        HelpRequestCommand command = new HelpRequestCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void acquireTax(PopupFrame frame, ActionsPopup popupPanel) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        TaxationCommand command = new TaxationCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void blockAssassination(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        if (!ConditionCheckingUtils.humanIsTargetOfAction(ActionIdentifier.ASSASSINATION, stack)) {
            JOptionPane.showMessageDialog(frame, "There are no assassination attempts on you to block.");
            return;
        }

        AssassinationCounterCommand command = new AssassinationCounterCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void blockExternalHelp(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        if (!ConditionCheckingUtils.thereHasBeenExternalHelpRequest(stack)) {
            JOptionPane.showMessageDialog(frame, "There are no external help requests to block.");
            return;
        }

        // won't be null since we checked in the if block above:
        PlayerIdentifier targetIdentifier = getIdentifierOfHelpRequester(stack);

        HelpRequestCounterCommand command = new HelpRequestCounterCommand(targetIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void blockExtortion(PopupFrame frame, ActionsPopup popupPanel, CardIdentifier cardIdentifier,
                                      ActionsStack stack) {
        if (checkIfShouldAttemptCoup(frame)) {
            return;
        }

        if (!ConditionCheckingUtils.humanIsTargetOfAction(ActionIdentifier.EXTORTION, stack)) {
            JOptionPane.showMessageDialog(frame, "There are no extortion attempts on you to block.");
            return;
        }

        ExtortionCounterCommand command = new ExtortionCounterCommand(cardIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static PlayerIdentifier getIdentifierOfTarget(String targetName) {
        ArrayList<Player> playersList = GameState.getPlayersList();
        for (Player player : playersList) {
            if (player.getPlayerName().equals(targetName)) {
                return player.getPlayerIdentifier();
            }
        }

        return null;
    }

    private static PlayerIdentifier getIdentifierOfHelpRequester(ActionsStack stack) {
        for (logic.models.actions.Action action : stack.getStackOfActions()) {
            if (action.getActionIdentifier() == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                return action.getActionPlayer().getPlayerIdentifier();
            }
        }

        return null;
    }

    public static boolean checkIfShouldAttemptCoup(PopupFrame frame) {
        if (ConditionCheckingUtils.humanHasEnoughCoins(10)) {
            JOptionPane.showMessageDialog(frame, "You have 10 or more coins. You have to attempt coup.");
            return true;
        }

        return false;
    }

    public static boolean checkIfShouldMakeCounterAction(PopupFrame frame) {
        GameStateEntry latestEntry = GameStateHistory.getLatestGameStateEntry();

        if (latestEntry.getGameState() == GameStateIdentifier.COUNTER_ACTIONS) {
            JOptionPane.showMessageDialog(frame, "You should play a counter action.");
            return true;
        }

        return false;
    }

    public static boolean checkIfShouldMakeNormalAction(PopupFrame frame) {
        GameStateEntry latestEntry = GameStateHistory.getLatestGameStateEntry();

        if (latestEntry.getGameState() == GameStateIdentifier.NORMAL_ACTIONS) {
            JOptionPane.showMessageDialog(frame, "You should play a normal action.");
            return true;
        }

        return false;
    }

    private static void disposePopup(ActionsPopup popupPanel) {
        popupPanel.getFrame().dispose();
    }
}
