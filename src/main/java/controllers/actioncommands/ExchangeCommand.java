package controllers.actioncommands;

import logic.models.Card;
import logic.models.actions.ActionIdentifier;

import java.util.ArrayList;

public class ExchangeCommand extends Command {
    private ArrayList<Card> cardsList;

    public ExchangeCommand(ArrayList<Card> cardsList) {
        super(ActionIdentifier.EXCHANGE);
        this.cardsList = cardsList;
    }

    public ArrayList<Card> getCardsList() {
        return cardsList;
    }
}
