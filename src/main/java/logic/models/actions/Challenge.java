package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;

public class Challenge extends Action {
    private Action challengedAction;

    public Challenge(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer,
                     Player targetPlayer) {
        super(actionIdentifier, cardIdentifier, actionPlayer, targetPlayer, true, false);
    }

    @Override
    protected void resolveAction() {
        if (challengeIsCorrect()) {
            targetPlayer.punishPlayer();

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

    public void setChallengedAction(Action challengedAction) {
        this.challengedAction = challengedAction;
        isChallenge = true;
    }
}