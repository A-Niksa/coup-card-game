package logic.models.bots;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.*;
import logic.models.actions.cardutils.specialutils.CoupAction;
import logic.models.bots.botutils.PossibleActionsUtils;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class RandomBot extends Bot {
    public RandomBot(int playerIndex, String name) {
        super(PlayerIdentifier.RANDOM, playerIndex, name);
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        if (numberOfCoins >= 10) {
            Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

            Card targetCard = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
            CardIdentifier targetCardIdentifier = targetCard.getIdentifier();

            CoupAction coup = new CoupAction(this, targetPlayer, targetCardIdentifier);

            return;
        }

        NormalAction normalAction = RandomActionsUtils.getRandomSimpleAction(randomGenerator, this);
        stack.addToStack(normalAction);
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        if (randomBoolean()) {
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
        if (randomBoolean()) {
            boolean thereHaveBeenTooManyChallenges = PossibleActionsUtils.thereHaveAlreadyBeenTooManyChallenges(stack);

            if (!thereHaveBeenTooManyChallenges) {
                ArrayList<Action> challengableActionsList =
                        PossibleActionsUtils.getListOfChallengeableActions(stack, this);

                if (challengableActionsList.isEmpty()) {
                    return;
                }

                int randomIndex = randomGenerator.nextInt(challengableActionsList.size());
                Action actionToChallenge = challengableActionsList.get(randomIndex);

                Challenge challenge = new Challenge(this, actionToChallenge.getTargetPlayer(),
                        actionToChallenge);
                stack.addToStack(challenge);
            }
        }
    }

    private boolean randomBoolean() {
        return RandomActionsUtils.getRandomBoolean(randomGenerator);
    }
}
