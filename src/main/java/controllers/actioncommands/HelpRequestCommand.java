package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class HelpRequestCommand extends Command {
    public HelpRequestCommand() {
        super(ActionIdentifier.EXTERNAL_HELP_REQUEST);
    }
}
