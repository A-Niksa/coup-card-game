package logic.models.actions;

import logic.models.CardIdentifier;
import logic.models.Player;
import utils.logging.ActionState;
import utils.logging.LogHistory;

public class Challenge extends Action {
    private Action challengedAction;

    public Challenge(Player actionPlayer, Player targetPlayer, Action challengedAction) {
        super(ActionIdentifier.CHALLENGE, null, actionPlayer, targetPlayer, true,
                false);
        this.challengedAction = challengedAction;
    }

    @Override
    protected void resolveAction() {
        if (challengeIsCorrect()) {
            targetPlayer.punishPlayer();

            challengedAction.setShouldBeSkipped(true);

            LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(),
                    ActionIdentifier.CHALLENGE, ActionState.SUCCESSFUL);
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