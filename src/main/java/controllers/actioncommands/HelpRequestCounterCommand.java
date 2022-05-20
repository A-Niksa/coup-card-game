package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class HelpRequestCounterCommand extends Command {
    PlayerIdentifier targetPlayerIdentifier;

    public HelpRequestCounterCommand(PlayerIdentifier targetPlayerIdentifier) {
        super(ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER);
        this.targetPlayerIdentifier = targetPlayerIdentifier;
    }

    public PlayerIdentifier getTargetPlayerIdentifier() {
        return targetPlayerIdentifier;
    }
}
