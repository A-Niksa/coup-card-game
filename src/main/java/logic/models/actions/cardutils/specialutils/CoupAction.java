package logic.models.actions.cardutils.specialutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.AssassinActions;
import logic.models.actions.cardutils.GeneralActions;

public class CoupAction extends NormalAction {
    private CardIdentifier targetCardIdentifier;

    public CoupAction(CardIdentifier cardIdentifier, Player actionPlayer, Player targetPlayer,
                      CardIdentifier targetCardIdentifier) {
        super(ActionIdentifier.COUP, cardIdentifier, actionPlayer, targetPlayer);
        this.targetCardIdentifier = targetCardIdentifier;
    }

    @Override
    protected void resolveAction() {
        GeneralActions.attemptCoup(targetPlayer, targetCardIdentifier);
    }
}