package controllers.actioncommands;

import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class ChallengeCommand extends Command {
    private PlayerIdentifier targetPlayerIdentifier;
    private Action challengedAction;

    public ChallengeCommand(PlayerIdentifier targetPlayerIdentifier, Action challengedAction) {
        super(ActionIdentifier.CHALLENGE);
        this.targetPlayerIdentifier = targetPlayerIdentifier;
        this.challengedAction = challengedAction;
    }

    public PlayerIdentifier getTargetPlayerIdentifier() {
        return targetPlayerIdentifier;
    }

    public Action getChallengedAction() {
        return challengedAction;
    }
}
