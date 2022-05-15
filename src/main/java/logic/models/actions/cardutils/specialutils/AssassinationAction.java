package logic.models.actions.cardutils.specialutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.AmbassadorActions;
import logic.models.actions.cardutils.AssassinActions;

public class AssassinationAction extends NormalAction {
    private CardIdentifier targetCardIdentifier;

    public AssassinationAction(Player actionPlayer, Player targetPlayer, CardIdentifier targetCardIdentifier) {
        super(ActionIdentifier.ASSASSINATION, CardIdentifier.ASSASSIN, actionPlayer, targetPlayer);
        this.targetCardIdentifier = targetCardIdentifier;
    }

    @Override
    protected void resolveAction() {
        AssassinActions.assassinate(targetPlayer, targetCardIdentifier);
    }
}
