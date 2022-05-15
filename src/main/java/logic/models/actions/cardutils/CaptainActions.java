package logic.models.actions.cardutils;

import logic.models.Player;

public class CaptainActions {
    static void extort(Player actionPlayer, Player targetPlayer) {
        int extortedCoins = targetPlayer.extortCoinsFromPlayer(2);
        actionPlayer.addCoinsToPlayer(extortedCoins);
    }

    static void blockExtortion() {
        // empty body since skipping the countered action in the stack of actions will work like blocking
    }
}
