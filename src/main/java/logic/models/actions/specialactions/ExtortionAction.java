package logic.models.actions.specialactions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.CaptainActions;

public class ExtortionAction extends NormalAction {
    public ExtortionAction(Player actionPlayer, Player targetPlayer) {
        super(ActionIdentifier.EXTORTION, CardIdentifier.CAPTAIN, actionPlayer, targetPlayer);
    }

    @Override
    public void resolveAction() {
        CaptainActions.extort(actionPlayer, targetPlayer);
    }
}
