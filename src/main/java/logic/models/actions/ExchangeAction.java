package logic.models.actions;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;
import logic.models.actions.cardutils.AmbassadorActions;

import java.util.ArrayList;
import java.util.Arrays;

public class ExchangeAction extends NormalAction {
    private ArrayList<Card> selectedCardsList;

    public ExchangeAction(Player actionPlayer, Player targetPlayer, Card firstSelectedCard, Card secondSelectedCard) {
        super(ActionIdentifier.EXCHANGE, CardIdentifier.AMBASSADOR, actionPlayer, targetPlayer);
        selectedCardsList = new ArrayList<>(Arrays.asList(firstSelectedCard, secondSelectedCard));
    }

    @Override
    protected void resolveAction() {
        AmbassadorActions.exchangeCards(actionPlayer, selectedCardsList);
    }

    public ArrayList<Card> getSelectedCardsList() {
        return selectedCardsList;
    }
}
