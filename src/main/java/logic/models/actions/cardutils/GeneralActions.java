package logic.models.actions.cardutils;

import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.logging.ActionState;
import utils.logging.LogHistory;

import java.util.Random;

public class GeneralActions {
    static void acquireIncome(Player actionPlayer) {
        int income = GameState.requestCoinsFromTreasury(1);
        actionPlayer.addCoinsToPlayer(income);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.INCOME_ACQUISITION,
                ActionState.SUCCESSFUL);
    }

    static void requestExternalHelp(Player actionPlayer) {
        int externalHelp = GameState.requestCoinsFromTreasury(2);
        actionPlayer.addCoinsToPlayer(externalHelp);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.EXTERNAL_HELP_REQUEST,
                ActionState.SUCCESSFUL);
    }

    public static void attemptCoup(Player actionPlayer, Player targetPlayer) {
        // public, so that it can be accessed from CoupAction
        Card randomCard = RandomActionsUtils.getRandomCardOfPlayer(new Random(), targetPlayer);
        CardIdentifier targetCardIdentifier = randomCard.getIdentifier();

        Hand handOfTargetPlayer = targetPlayer.getHand();
        handOfTargetPlayer.removeCard(targetCardIdentifier);

        LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(), ActionIdentifier.COUP,
                ActionState.SUCCESSFUL);
    }

    static void swapPlayerCardRandomly(Player actionPlayer) {
        int costOfAction = actionPlayer.reduceCoinsFromPlayer(1);

        if (costOfAction == 1) { // player had sufficient coins
            GameState.returnCoinsToTreasury(costOfAction);

            Hand handOfActionPlayer = actionPlayer.getHand();
            handOfActionPlayer.swapRandomCard();

            LogHistory.log(actionPlayer.getPlayerIdentifier(), null, ActionIdentifier.CARD_SWAP,
                    ActionState.SUCCESSFUL);
        }
    }
}
