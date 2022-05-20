package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public abstract class Command { // commands are to be issued by humans only
    private ActionIdentifier actionIdentifier;

    public Command(ActionIdentifier actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }
}
