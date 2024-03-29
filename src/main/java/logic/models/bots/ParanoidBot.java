package logic.models.bots;

import logic.models.Player;
import logic.models.actions.*;
import logic.models.actions.specialactions.CoupAction;
import logic.models.bots.botutils.PossibleActionsUtils;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class ParanoidBot extends Bot {
    private boolean challengedInPreviousRound;

    public ParanoidBot(int playerIndex, String name) {
        super(PlayerIdentifier.PARANOID, playerIndex, name);

        challengedInPreviousRound = false;
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        if (numberOfCoins >= 10) {
            Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

            CoupAction coup = new CoupAction(this, targetPlayer);

            return;
        }

        // will acquire 1 coin from the treasury
        NormalAction incomeAcquisition = new NormalAction(ActionIdentifier.INCOME_ACQUISITION, null,
                this, null);
        stack.addToStack(incomeAcquisition);
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        // will do nothing
    }

    @Override
    public void challenge(ActionsStack stack) {
        boolean thereHaveBeenTooManyChallenges = PossibleActionsUtils.thereHaveAlreadyBeenTooManyChallenges(stack);

        if (!thereHaveBeenTooManyChallenges) {
            ArrayList<Action> challengableActionsList =
                    PossibleActionsUtils.getListOfChallengeableActions(stack, this);

            if (challengableActionsList.isEmpty()) {
                return;
            }

            if (!challengedInPreviousRound) {

                int randomIndex = randomGenerator.nextInt(challengableActionsList.size());
                Action actionToChallenge = challengableActionsList.get(randomIndex);

                Challenge challenge = new Challenge(this, actionToChallenge.getActionPlayer(),
                        actionToChallenge);
                stack.addToStack(challenge);

                challengedInPreviousRound = true;
            } else {
                challengedInPreviousRound = false;
            }
        }
    }
}