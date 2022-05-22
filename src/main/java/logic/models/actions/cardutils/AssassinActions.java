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

public class AssassinActions {
    public static void assassinate(Player actionPlayer, Player targetPlayer) {
        // public, so that it can be accessed from AssassinationAction
        int costOfAction = actionPlayer.reduceCoinsFromPlayer(3);

        if (costOfAction == 3) { // player had sufficient coins
            GameState.returnCoinsToTreasury(costOfAction);

            Card randomCard = RandomActionsUtils.getRandomCardOfPlayer(new Random(), targetPlayer);
            if (randomCard == null) {
                return;
            }

            CardIdentifier targetCardIdentifier = randomCard.getIdentifier();

            Hand handOfTargetPlayer = targetPlayer.getHand();
            handOfTargetPlayer.removeCard(targetCardIdentifier);

            LogHistory.log(actionPlayer.getPlayerIdentifier(), targetPlayer.getPlayerIdentifier(),
                    ActionIdentifier.ASSASSINATION, ActionState.SUCCESSFUL);
        }
    }
}
