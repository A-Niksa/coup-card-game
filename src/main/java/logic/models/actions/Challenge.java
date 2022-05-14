package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;

public class Challenge extends Action {
    public Challenge(ActionIdentifier identifier, Player actionPlayer, Action challengedAction) {
        super(identifier, actionPlayer, challengedAction);
    }

    @Override
    protected void resolveAction() {
        if (challengeIsCorrect()) {
            Player challengedPlayer = challengedAction.getActionPlayer();
            challengedPlayer.punishPlayer();

            challengedAction.setShouldBeSkipped(true);
        } else {
            actionPlayer.punishPlayer();

            CardIdentifier cardIdentifierOfChallengedAction = challengedAction.getCardIdentifier();
            actionPlayer.swapRevealedCard(cardIdentifierOfChallengedAction);
        }
    }

    private boolean challengeIsCorrect() {
        return challengedAction.isBluff();
    }
}