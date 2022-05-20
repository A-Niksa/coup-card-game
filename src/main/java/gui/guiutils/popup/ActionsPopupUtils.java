package gui.guiutils.popup;

import controllers.ConditionCheckingUtils;
import controllers.actioncommands.*;
import gui.MainFrame;
import gui.popup.ActionsPopup;
import logic.game.GameState;
import logic.models.CardIdentifier;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;

import javax.swing.*;

public class ActionsPopupUtils {
    public static void swapCardRandomly(MainFrame mainFrame, ActionsPopup popupPanel) {
        if (!ConditionCheckingUtils.humanHasEnoughCoins(1)) {
            JOptionPane.showMessageDialog(mainFrame, "You do not have a sufficient number of coins.");
            return;
        }

        CardSwapCommand command = new CardSwapCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void acquireIncome(ActionsPopup popupPanel) {
        IncomeCommand command = new IncomeCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void requestExternalHelp(ActionsPopup popupPanel) {
        HelpRequestCommand command = new HelpRequestCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void acquireTax(ActionsPopup popupPanel) {
        TaxationCommand command = new TaxationCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void counterAssassination(MainFrame mainFrame, ActionsPopup popupPanel, ActionsStack stack) {
        if (!ConditionCheckingUtils.humanIsTargetOfAction(ActionIdentifier.ASSASSINATION, stack)) {
            JOptionPane.showMessageDialog(mainFrame, "There are no assassination attempts on you to block.");
            return;
        }

        AssassinationCounterCommand command = new AssassinationCounterCommand();
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void blockExtortion(MainFrame mainFrame, ActionsPopup popupPanel, CardIdentifier cardIdentifier,
                                      ActionsStack stack) {
        if (!ConditionCheckingUtils.humanIsTargetOfAction(ActionIdentifier.EXTORTION, stack)) {
            JOptionPane.showMessageDialog(mainFrame, "There are no extortion attempts on you to block.");
            return;
        }

        ExtortionCounterCommand command = new ExtortionCounterCommand(cardIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    private static void disposePopup(ActionsPopup popupPanel) {
        popupPanel.getFrame().dispose();
    }
}
