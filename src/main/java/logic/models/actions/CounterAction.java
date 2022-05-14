package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;

public class CounterAction extends Action {

    public CounterAction(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer,
                         Action counteredAction, boolean isCounterAction) {
        super(actionIdentifier, cardIdentifier, actionPlayer, counteredAction, isCounterAction);
    }

    @Override
    protected void resolveAction() {
        if (counteredAction.shouldBeSkipped()) {
            // in this case, the counterAction was challenged and nullified as a result
            return;
        }

        Player affectedPlayer = counteredAction.getActionPlayer();
        ActionToCardMapper.mapActionToCard(actionIdentifier, cardIdentifier, actionPlayer, affectedPlayer);

        counteredAction.setShouldBeSkipped(true);
    }
}
