package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class CardSwapCommand extends Command {
    public CardSwapCommand() {
        super(ActionIdentifier.CARD_SWAP);
    }
}
