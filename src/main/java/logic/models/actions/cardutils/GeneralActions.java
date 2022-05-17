package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;

public class GeneralActions {
    static void acquireIncome(Player actionPlayer) {
        int income = GameState.requestCoinsFromTreasury(1);
        actionPlayer.addCoinsToPlayer(income);
    }

    static void requestExternalHelp(Player actionPlayer) {
        int externalHelp = GameState.requestCoinsFromTreasury(2);
        actionPlayer.addCoinsToPlayer(externalHelp);
    }

    public static void attemptCoup(Player targetPlayer, CardIdentifier targetCardIdentifier) {
        // public, so that it can be accessed from CoupAction
        Hand handOfTargetPlayer = targetPlayer.getHand();
        handOfTargetPlayer.removeCard(targetCardIdentifier);
    }

    static void swapPlayerCardRandomly(Player actionPlayer) {
        int costOfAction = actionPlayer.reduceCoinsFromPlayer(1);
        GameState.returnCoinToTreasury(costOfAction);

        Hand handOfActionPlayer = actionPlayer.getHand();
        handOfActionPlayer.swapRandomCard();
    }
}
