package logic.models.bots;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.*;
import logic.models.actions.specialactions.CoupAction;
import logic.models.bots.botutils.PossibleActionsUtils;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

public class CoupLovingBot extends Bot {
    public CoupLovingBot(int playerIndex, String name) {
        super(PlayerIdentifier.COUP_LOVING, playerIndex, name);
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        // this bot will attempt coup anyway. so there's no need for adding mandatory coups at 10 coins

        if (numberOfCoins < 7) {
            NormalAction taxation = new NormalAction(ActionIdentifier.TAXATION, CardIdentifier.DUKE, this,
                    null);
            stack.addToStack(taxation);
        } else {
            Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

            CoupAction coup = new CoupAction(this, targetPlayer);
            stack.addToStack(coup);
        }
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        if (RandomActionsUtils.getRandomBoolean(randomGenerator)) {
            Action attackAction = PossibleActionsUtils.getAttackActionOnPlayer(this, stack);

            if (attackAction != null) {
                CounterAction counterAction = PossibleActionsUtils.getAppropriateCounterAction(this,
                        attackAction, stack);

                if (counterAction != null) {
                    stack.addToStack(counterAction);
                }
            }
        }
    }

    @Override
    public void challenge(ActionsStack stack) {
        // will do nothing
    }
}
