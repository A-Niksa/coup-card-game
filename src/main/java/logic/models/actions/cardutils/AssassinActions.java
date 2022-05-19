package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;

public class AssassinActions {
    public static void assassinate(Player actionPlayer, Player targetPlayer, CardIdentifier targetCardIdentifier) {
        // public, so that it can be accessed from AssassinationAction
        int costOfAction = actionPlayer.reduceCoinsFromPlayer(3);

        if (costOfAction == 3) { // player had sufficient coins
            GameState.returnCoinToTreasury(costOfAction);

            Hand handOfTargetPlayer = targetPlayer.getHand();
            handOfTargetPlayer.removeCard(targetCardIdentifier);
        }
    }
}
