package logic.models.bots;

import logic.models.Player;
import logic.models.actions.*;
import logic.models.bots.botutils.PossibleActionsUtils;
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
        if (!challengedInPreviousRound) {
            boolean thereHaveBeenTooManyChallenges = PossibleActionsUtils.thereHaveAlreadyBeenTooManyChallenges(stack);

            if (thereHaveBeenTooManyChallenges) {
                ArrayList<Player> challengableActionsPlayersList =
                        PossibleActionsUtils.getListOfChallengeableActionsPlayers(stack);

                if (challengableActionsPlayersList.isEmpty()) {
                    return;
                }

                int randomIndex = randomGenerator.nextInt(challengableActionsPlayersList.size());
                Player targetPlayer = challengableActionsPlayersList.get(randomIndex);

                Challenge challenge = new Challenge(this, targetPlayer);
                stack.addToStack(challenge);
            }
        }
    }
}