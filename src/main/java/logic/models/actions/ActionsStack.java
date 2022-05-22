package logic.models.actions;

import gui.game.GamePanel;
import logic.models.actions.specialactions.AssassinationAction;
import logic.models.actions.specialactions.CoupAction;
import logic.models.actions.specialactions.ExchangeAction;
import logic.models.actions.specialactions.ExtortionAction;
import utils.logging.LogHistory;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionsStack {
    private Deque<Action> stackOfActions;

    public ActionsStack() {
        stackOfActions = new ArrayDeque<>();
    }

    public void resolveStack(GamePanel gamePanel) {
        for (Action action : stackOfActions) {
            if (!action.shouldBeSkipped()) { // used for filtering out challenged actions that have lost
                if (action.getActionIdentifier() == ActionIdentifier.ASSASSINATION) {
                    ((AssassinationAction) action).resolveAction();
                    continue;
                } else if (action.getActionIdentifier() == ActionIdentifier.COUP) {
                    ((CoupAction) action).resolveAction();
                    continue;
                } else if (action.getActionIdentifier() == ActionIdentifier.EXCHANGE) {
                    ((ExchangeAction) action).resolveAction();
                    continue;
                } else if (action.getActionIdentifier() == ActionIdentifier.EXTORTION) {
                    ((ExtortionAction) action).resolveAction();
                    continue;
                }

                action.resolveAction();
            }
        }

        LogHistory.addSeparatorToLogs();
    }

    public void addToStack(Action action) {
        stackOfActions.addFirst(action);
    }

    public Deque<Action> getStackOfActions() {
        return stackOfActions;
    }
}