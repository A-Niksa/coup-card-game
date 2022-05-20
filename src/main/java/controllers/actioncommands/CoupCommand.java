package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class CoupCommand extends Command {
    private PlayerIdentifier targetPlayerIdentifier;

    public CoupCommand(PlayerIdentifier targetPlayerIdentifier) {
        super(ActionIdentifier.COUP);
        this.targetPlayerIdentifier = targetPlayerIdentifier;
    }

    public PlayerIdentifier getTargetPlayerIdentifier() {
        return targetPlayerIdentifier;
    }
}
