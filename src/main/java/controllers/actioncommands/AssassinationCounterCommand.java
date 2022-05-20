package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class AssassinationCounterCommand extends Command {
    public AssassinationCounterCommand() {
        super(ActionIdentifier.ASSASSINATION_COUNTER);
    }
}
