package logic.models.bots.botutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.*;

import java.util.ArrayList;

public class PossibleActionsUtils {
    public static ArrayList<Player> getListOfChallengeableActionsPlayers(ActionsStack stack) {
        ArrayList<Player> challengeableActionsPlayersList = new ArrayList<>();

        for (Action action : stack.getStackOfActions()) {
            boolean isCounterAction = action.isCounterAction();
            boolean isNormalAction = !(isCounterAction || action.isChallenge());
            boolean isAnUnchallengeableAction = action.getActionIdentifier() == ActionIdentifier.INCOME_ACQUISITION ||
                    action.getActionIdentifier() == ActionIdentifier.COUP ||
                    action.getActionIdentifier() == ActionIdentifier.CARD_SWAP;


            if ((isNormalAction || isCounterAction) && !isAnUnchallengeableAction) {
                if (!actionHasBeenChallengedBefore(action, stack)) {
                    Player targetPlayer = action.getActionPlayer();
                    challengeableActionsPlayersList.add(targetPlayer);
                }
            }
        }

        return challengeableActionsPlayersList;
    }

    public static ArrayList<Action> getListOfChallengeableActions(ActionsStack stack, Player currentPlayer) {
        ArrayList<Action> challengeableActionsList = new ArrayList<>();

        for (Action action : stack.getStackOfActions()) {
            boolean isCounterAction = action.isCounterAction();
            boolean isNormalAction = !(isCounterAction || action.isChallenge());
            boolean isAnUnchallengeableAction = action.getActionIdentifier() == ActionIdentifier.INCOME_ACQUISITION ||
                    action.getActionIdentifier() == ActionIdentifier.COUP ||
                    action.getActionIdentifier() == ActionIdentifier.CARD_SWAP;


            if ((isNormalAction || isCounterAction) && !isAnUnchallengeableAction) {
                if (action.getActionPlayer().getPlayerIdentifier() != currentPlayer.getPlayerIdentifier()) {
                    if (!actionHasBeenChallengedBefore(action, stack)) {
                        challengeableActionsList.add(action);
                    }
                }
            }
        }

        return challengeableActionsList;
    }

    private static boolean actionHasBeenChallengedBefore(Action targetAction, ActionsStack stack) {
        for (Action action : stack.getStackOfActions()) {
            if (action.getActionIdentifier() == ActionIdentifier.CHALLENGE) {
                Challenge challenge = (Challenge) action;

                if (challenge.getChallengedAction() == targetAction) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean thereHaveAlreadyBeenTooManyChallenges(ActionsStack stack) {
        int numberOfChallenges = 0;
        for (Action action : stack.getStackOfActions()) {
            if (action.isChallenge()) {
                numberOfChallenges++;
            }
        }

        // in case this is true, it would be implied that the capacity for challenges per rounds has been filled:
        return numberOfChallenges >= 1;
    }

    public static Action getAttackActionOnPlayer(Player targetPlayer, ActionsStack stack) {
        // by "attacked" I mean has been target of an opponent's action
        for (Action action : stack.getStackOfActions()) {
            if (action.getTargetPlayer() == targetPlayer) {
                return action;
            }
        }

        return null;
    }

    public static CounterAction getAppropriateCounterAction(Player targetPlayer, Action attackActionOnPlayer,
                                                            ActionsStack stack) {
        Player attackActionPlayer = attackActionOnPlayer.getActionPlayer();
        ActionIdentifier attackActionIdentifier = attackActionOnPlayer.getActionIdentifier();

        CounterAction counterAction;
        switch (attackActionIdentifier) {
            case ASSASSINATION:
                counterAction = new CounterAction(ActionIdentifier.ASSASSINATION_COUNTER, CardIdentifier.CONTESSA,
                        targetPlayer, attackActionPlayer, stack);
                break;
            case EXTORTION:
                counterAction = new CounterAction(ActionIdentifier.EXTORTION_COUNTER, CardIdentifier.CAPTAIN,
                        targetPlayer, attackActionPlayer, stack);
                break;
            default:
                counterAction = null;
        }

        return counterAction;
    }
}