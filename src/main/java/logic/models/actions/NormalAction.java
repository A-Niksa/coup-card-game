package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;

public class NormalAction extends Action {
    public NormalAction(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer,
                        Player targetPlayer) {
        super(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer, false, false);
    }

    @Override
    public void resolveAction() {
        ActionToCardMapper.mapActionToCard(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer);
    }
}
