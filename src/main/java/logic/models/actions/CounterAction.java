package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.cardutils.ActionToCardMapper;

public class CounterAction extends Action {
    private Action counteredAction;

    public CounterAction(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer,
                         Player targetPlayer, ActionsStack stack) {
        super(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer, false, true);

        setCounteredAction(stack);
    }

    private void setCounteredAction(ActionsStack stack) {
        for (Action action : stack.getStackOfActions()) {
            if (action.getActionIdentifier() != ActionIdentifier.CHALLENGE) {
                if (action.getActionPlayer().getPlayerIdentifier() == targetPlayer.getPlayerIdentifier()) {
                    counteredAction = action;
                    return;
                }
            }
        }
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
