package logic.models.actions;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionsStack {
    private Deque<Action> stackOfActions;

    public ActionsStack() {
        stackOfActions = new ArrayDeque<>();
    }

    public void resolveStack() {
        for (Action action : stackOfActions) {
            if (!action.shouldBeSkipped()) { // used for filtering out challenged actions that have lost
                action.resolveAction();
            }
        }
    }

    public void addToStack(Action action) {
        stackOfActions.addFirst(action);
    }

    public Deque<Action> getStackOfActions() {
        return stackOfActions;
    }
}