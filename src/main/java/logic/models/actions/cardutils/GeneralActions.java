package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Player;

public class GeneralActions {
    public static void acquireIncome(Player actionPlayer) {
        int income = GameState.requestCoinsFromTreasury(1);
        actionPlayer.addCoinsToPlayer(income);
    }

    public static void requestExternalHelp(Player actionPlayer) {
        int externalHelp = GameState.requestCoinsFromTreasury(2);
        actionPlayer.addCoinsToPlayer(externalHelp);
    }

    public static void attemptCoup(Player actionPlayer) {
        // TODO
    }
}
