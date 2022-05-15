package logic.models.actions.cardutils;

import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;

public class AssassinActions {
    public static void assassinate(Player targetPlayer, CardIdentifier targetCardIdentifier) {
        // public, so that it can be accessed from AssassinationAction
        Hand handOfTargetPlayer = targetPlayer.getHand();
        handOfTargetPlayer.removeCard(targetCardIdentifier);
    }
}
