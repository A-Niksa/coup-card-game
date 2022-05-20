package logic.models.actions.cardutils.specialutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.AssassinActions;
import logic.models.actions.cardutils.CaptainActions;

public class ExtortionAction extends NormalAction {
    public ExtortionAction(Player actionPlayer, Player targetPlayer) {
        super(ActionIdentifier.EXTORTION, CardIdentifier.CAPTAIN, actionPlayer, targetPlayer);
    }

    @Override
    protected void resolveAction() {
        CaptainActions.extort(actionPlayer, targetPlayer);
    }
}
