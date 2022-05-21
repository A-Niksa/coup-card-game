package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Card;
import logic.models.Hand;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import utils.logging.ActionState;
import utils.logging.LogHistory;

import java.util.ArrayList;

public class AmbassadorActions {
    public static void exchangeCards(Player actionPlayer, ArrayList<Card> selectedCardsList) {
        // public, so that it can be accessible from ExchangeAction
        Card firstCard = selectedCardsList.get(0);
        Card secondCard = selectedCardsList.get(1);

        Hand handOfPlayer = actionPlayer.getHand();
        handOfPlayer.clearHand();
        handOfPlayer.addCardsToHand(firstCard, secondCard);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.EXCHANGE,
                ActionState.SUCCESSFUL);
    }

    static void blockExtortion() {
        // empty body since skipping the countered action in the stack of actions will work like blocking
    }
}
