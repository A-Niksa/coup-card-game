package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;

public class CounterAction extends Action {
    private Action counteredAction;

    public CounterAction(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer,
                         Player targetPlayer) {
        super(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer, false, true);
    }

    @Override
    protected void resolveAction() {
        if (counteredAction.shouldBeSkipped()) {
            // in this case, the counterAction was challenged and nullified as a result
            return;
        }

        ActionToCardMapper.mapActionToCard(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer);

        counteredAction.setShouldBeSkipped(true);
    }
}
