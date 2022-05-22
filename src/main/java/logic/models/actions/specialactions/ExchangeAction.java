package logic.models.actions.specialactions;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.AmbassadorActions;

import java.util.ArrayList;

public class ExchangeAction extends NormalAction {
    private ArrayList<Card> selectedCardsList;

    public ExchangeAction(Player actionPlayer, ArrayList<Card> selectedCardsList) {
        super(ActionIdentifier.EXCHANGE, CardIdentifier.AMBASSADOR, actionPlayer, null);
        this.selectedCardsList = selectedCardsList;
    }

    @Override
    public void resolveAction() {
        AmbassadorActions.exchangeCards(actionPlayer, selectedCardsList);
    }

    public ArrayList<Card> getSelectedCardsList() {
        return selectedCardsList;
    }
}
