package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class PassingTurnCommand extends Command {
    public PassingTurnCommand() {
        super(ActionIdentifier.NOTHING);
    }
}
