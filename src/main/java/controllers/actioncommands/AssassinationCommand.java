package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class AssassinationCommand extends Command {
    private PlayerIdentifier targetPlayerIdentifier;

    public AssassinationCommand(PlayerIdentifier targetPlayerIdentifier) {
        super(ActionIdentifier.ASSASSINATION);
        this.targetPlayerIdentifier = targetPlayerIdentifier;
    }

    public PlayerIdentifier getTargetPlayerIdentifier() {
        return targetPlayerIdentifier;
    }
}
