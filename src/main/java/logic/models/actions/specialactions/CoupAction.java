package logic.models.actions.specialactions;

import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.GeneralActions;

public class CoupAction extends NormalAction {
    public CoupAction(Player actionPlayer, Player targetPlayer) {
        super(ActionIdentifier.COUP, null, actionPlayer, targetPlayer);
    }

    @Override
    public void resolveAction() {
        GeneralActions.attemptCoup(actionPlayer, targetPlayer);
    }
}