package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Player;

public class ContessaActions {
    static void blockAssassination(Player targetPlayer) {
        // punishing the assassin:
        int punishmentCoins = targetPlayer.reduceCoinsFromPlayer(3);
        GameState.requestCoinsFromTreasury(punishmentCoins);
    }
}
