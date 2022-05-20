package logic.models.actions.cardutils;

import logic.models.Player;

public class CaptainActions {
    public static void extort(Player actionPlayer, Player targetPlayer) {
        // public, so that it can be accessed by ExtortionAction
        int extortedCoins = targetPlayer.extortCoinsFromPlayer(2);
        actionPlayer.addCoinsToPlayer(extortedCoins);
    }

    static void blockExtortion() {
        // empty body since skipping the countered action in the stack of actions will work like blocking
    }
}
