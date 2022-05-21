package gui.guiutils.popup;

import controllers.ConditionCheckingUtils;
import controllers.actioncommands.AssassinationCommand;
import controllers.actioncommands.CoupCommand;
import controllers.actioncommands.ExtortionCommand;
import gui.popup.ActionsPopup;
import gui.popup.PopupFrame;
import gui.popup.PopupTemplate;
import gui.popup.specialactionpopups.SpecialActionPopupTemplate;
import logic.game.GameState;
import utils.config.PlayerIdentifier;

import javax.swing.*;

public class SpecialActionsUtils {
    public static void assassinate(PopupFrame frame, SpecialActionPopupTemplate popupPanel, String targetName) {
        if (!ConditionCheckingUtils.humanHasEnoughCoins(3)) {
            JOptionPane.showMessageDialog(frame, "You do not have a sufficient number of coins.");
            return;
        }

        PlayerIdentifier targetIdentifier = ActionsPopupUtils.getIdentifierOfTarget(targetName);
        AssassinationCommand command = new AssassinationCommand(targetIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void attemptCoup(PopupFrame frame, SpecialActionPopupTemplate popupPanel, String targetName) {
        if (!ConditionCheckingUtils.humanHasEnoughCoins(7)) {
            JOptionPane.showMessageDialog(frame, "You do not have a sufficient number of coins.");
            return;
        }

        PlayerIdentifier targetIdentifier = ActionsPopupUtils.getIdentifierOfTarget(targetName);
        CoupCommand command = new CoupCommand(targetIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static void extort(SpecialActionPopupTemplate popupPanel, String targetName) {
        PlayerIdentifier targetIdentifier = ActionsPopupUtils.getIdentifierOfTarget(targetName);
        ExtortionCommand command = new ExtortionCommand(targetIdentifier);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    private static void disposePopup(SpecialActionPopupTemplate popupPanel) {
        popupPanel.getFrame().dispose();
    }
}
