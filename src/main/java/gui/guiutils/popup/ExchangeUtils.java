package gui.guiutils.popup;

import controllers.ConditionCheckingUtils;
import controllers.actioncommands.AssassinationCommand;
import controllers.actioncommands.ExchangeCommand;
import gui.popup.specialactionpopups.SpecialActionPopupTemplate;
import logic.game.GameState;
import logic.models.Card;

import java.util.ArrayList;

public class ExchangeUtils {
    public static void exchange(SpecialActionPopupTemplate popupPanel, ArrayList<Card> selectedCardsList) {
        ExchangeCommand command = new ExchangeCommand(selectedCardsList);
        GameState.setCurrentCommandInController(command);

        disposePopup(popupPanel);
    }

    public static Card getCardFromListByName(ArrayList<Card> exchangeCardsList, String cardName,
                                             ArrayList<Boolean> cardHasBeenSelectedList) {
        String exchangeCardName;
        Card exchangeCard;
        for (int i = 0; i < exchangeCardsList.size(); i++) {
            exchangeCard = exchangeCardsList.get(i);
            exchangeCardName = exchangeCard.getIdentifier().toString();

            if (exchangeCardName.equals(cardName) && !cardHasBeenSelectedList.get(i)) {
                cardHasBeenSelectedList.set(i, Boolean.TRUE);
                return exchangeCard;
            }
        }

        return null;
    }

    private static void disposePopup(SpecialActionPopupTemplate popupPanel) {
        popupPanel.getFrame().dispose();
    }
}
