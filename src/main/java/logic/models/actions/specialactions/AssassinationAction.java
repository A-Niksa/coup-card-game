package logic.models.actions.specialactions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.AssassinActions;

public class AssassinationAction extends NormalAction {
    public AssassinationAction(Player actionPlayer, Player targetPlayer) {
        super(ActionIdentifier.ASSASSINATION, CardIdentifier.ASSASSIN, actionPlayer, targetPlayer);
    }

    @Override
    public void resolveAction() {
        AssassinActions.assassinate(actionPlayer, targetPlayer);
    }
}
