package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class TaxationCommand extends Command {
    public TaxationCommand() {
        super(ActionIdentifier.TAXATION);
    }
}
