package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;

public class NormalAction extends Action {
    public NormalAction(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer) {
        super(actionIdentifier, cardIdentifier, actionPlayer);
    }

    @Override
    protected void resolveAction() {
        Player affectedPlayer = counteredAction.getActionPlayer();
        ActionToCardMapper.mapActionToCard(actionIdentifier, cardIdentifier, actionPlayer, affectedPlayer);
    }
}
