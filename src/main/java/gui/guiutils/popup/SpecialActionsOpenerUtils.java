package gui.guiutils.popup;

import gui.popup.ActionsPopup;
import gui.popup.PopupFrame;
import gui.popup.specialactionpopups.AssassinationPopup;
import gui.popup.specialactionpopups.CoupPopup;
import gui.popup.specialactionpopups.ExchangePopup;
import gui.popup.specialactionpopups.ExtortionPopup;
import logic.models.actions.ActionsStack;

public class SpecialActionsOpenerUtils {
    public static void goToExchangePopup(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (ActionsPopupUtils.checkIfShouldAttemptCoup(frame)) {
            return;
        }

        ExchangePopup popup = new ExchangePopup(frame, popupPanel, stack);
        frame.setCurrentPanel(popup);
    }

    public static void goToAssassinationPopup(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (ActionsPopupUtils.checkIfShouldAttemptCoup(frame)) {
            return;
        }

        AssassinationPopup popup = new AssassinationPopup(frame, popupPanel, stack);
        frame.setCurrentPanel(popup);
    }

    public static void goToCoupPopup(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (ActionsPopupUtils.checkIfShouldAttemptCoup(frame)) {
            return;
        }

        CoupPopup popup = new CoupPopup(frame, popupPanel, stack);
        frame.setCurrentPanel(popup);
    }

    public static void goToExtortionPopup(PopupFrame frame, ActionsPopup popupPanel, ActionsStack stack) {
        if (ActionsPopupUtils.checkIfShouldAttemptCoup(frame)) {
            return;
        }

        ExtortionPopup popup = new ExtortionPopup(frame, popupPanel, stack);
        frame.setCurrentPanel(popup);
    }
}
