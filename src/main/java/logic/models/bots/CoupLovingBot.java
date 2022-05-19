package logic.models.bots;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.specialutils.CoupAction;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

public class CoupLovingBot extends Bot {
    public CoupLovingBot(int playerIndex, String name) {
        super(PlayerIdentifier.COUP_LOVING, playerIndex, name);
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        if (numberOfCoins < 7) {
            NormalAction taxation = new NormalAction(ActionIdentifier.TAXATION, CardIdentifier.DUKE,this,
                    null);
            stack.addToStack(taxation);
        } else {
            Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator);

            Card targetCard = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
            CardIdentifier targetCardIdentifier = targetCard.getIdentifier();

            CoupAction coup = new CoupAction(this, targetPlayer, targetCardIdentifier);
            stack.addToStack(coup);
        }
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        // will do nothing
    }

    @Override
    public void challenge(ActionsStack stack) {
        // will do nothing
    }
}
