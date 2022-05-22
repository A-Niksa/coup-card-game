package logic.models.actions;

import logic.game.GameState;
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
    public void resolveAction() {
        if (challengeIsCorrect()) {
            targetPlayer.punishPlayer();

            if (challengedAction.getActionIdentifier() == ActionIdentifier.ASSASSINATION) { // deduct 3 coins in this case
                int punishmentCoins = targetPlayer.reduceCoinsFromPlayer(3);
                GameState.requestCoinsFromTreasury(punishmentCoins);
            }

            challengedAction.setShouldBeSkipped(true);

            LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(),
                    ActionIdentifier.CHALLENGE, ActionState.SUCCESSFUL);
        } else {
            actionPlayer.punishPlayer();

            CardIdentifier cardIdentifierOfChallengedAction = challengedAction.getCardIdentifier();
            targetPlayer.swapRevealedCard(cardIdentifierOfChallengedAction);

            LogHistory.logRevealedCard(targetPlayer.getPlayerIdentifier(), cardIdentifierOfChallengedAction);
        }
    }

    private boolean challengeIsCorrect() {
        return challengedAction.isBluff();
    }

    public Action getChallengedAction() {
        return challengedAction;
    }

    public void setChallengedAction(Action challengedAction) {
        this.challengedAction = challengedAction;
        isChallenge = true;
    }
}