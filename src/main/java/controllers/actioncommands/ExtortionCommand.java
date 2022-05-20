package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class ExtortionCommand extends Command {
    private PlayerIdentifier targetPlayerIdentifier;

    public ExtortionCommand(PlayerIdentifier targetPlayerIdentifier) {
        super(ActionIdentifier.EXTORTION);
        this.targetPlayerIdentifier = targetPlayerIdentifier;
    }

    public PlayerIdentifier getTargetPlayerIdentifier() {
        return targetPlayerIdentifier;
    }
}
