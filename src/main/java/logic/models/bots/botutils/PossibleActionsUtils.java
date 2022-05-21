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
                Player targetPlayer = action.getActionPlayer();
                challengeableActionsPlayersList.add(targetPlayer);
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
                if (action.getActionPlayer() != currentPlayer) {
                    challengeableActionsList.add(action);
                }
            }
        }

        return challengeableActionsList;
    }

    public static boolean thereHaveAlreadyBeenTooManyChallenges(ActionsStack stack) {
        int numberOfNormalOrCounterActions = getListOfChallengeableActionsPlayers(stack).size();

        int numberOfChallenges = 0;
        for (Action action : stack.getStackOfActions()) {
            if (action.isChallenge()) {
                numberOfChallenges++;
            }
        }

        // in case this is true, it would be implied that the capacity for challenges per rounds has been filled:
        return numberOfChallenges >= numberOfNormalOrCounterActions;
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

    public static CounterAction getAppropriateCounterAction(Player targetPlayer, Action attackActionOnPlayer) {
        Player attackActionPlayer = attackActionOnPlayer.getActionPlayer();
        ActionIdentifier attackActionIdentifier = attackActionOnPlayer.getActionIdentifier();

        CounterAction counterAction;
        switch (attackActionIdentifier) {
            case ASSASSINATION:
                counterAction = new CounterAction(ActionIdentifier.ASSASSINATION_COUNTER, CardIdentifier.CONTESSA,
                        targetPlayer, attackActionPlayer);
                break;
            case EXTORTION:
                counterAction = new CounterAction(ActionIdentifier.EXTORTION_COUNTER, CardIdentifier.CAPTAIN,
                        targetPlayer, attackActionPlayer);
                break;
            default:
                counterAction = null;
        }

        return counterAction;
    }
}