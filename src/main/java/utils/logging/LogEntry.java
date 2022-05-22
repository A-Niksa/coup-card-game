package utils.logging;

import logic.models.CardIdentifier;
import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class LogEntry {
    private PlayerIdentifier actionPlayerIdentifier;
    private PlayerIdentifier affectedPlayerIdentifier;
    private ActionIdentifier actionIdentifier;
    private ActionState actionState;
    private CardIdentifier revealedCard;

    public LogEntry(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                    ActionIdentifier actionIdentifier) {
        this.actionPlayerIdentifier = actionPlayerIdentifier;
        this.affectedPlayerIdentifier = affectedPlayerIdentifier;
        this.actionIdentifier = actionIdentifier;
    }

    public LogEntry(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                    ActionIdentifier actionIdentifier, ActionState actionState) {
        this.actionPlayerIdentifier = actionPlayerIdentifier;
        this.affectedPlayerIdentifier = affectedPlayerIdentifier;
        this.actionIdentifier = actionIdentifier;
        this.actionState = actionState;
    }

    public LogEntry(PlayerIdentifier actionPlayerIdentifier, CardIdentifier revealedCard) {
        this.actionPlayerIdentifier = actionPlayerIdentifier;
        this.revealedCard = revealedCard;
    }

    public String getLogEntryString() {
        if (revealedCard != null) {
            String playerNameLowerCase = actionPlayerIdentifier.toString().toLowerCase();
            return "Revealed card of " + playerNameLowerCase + ": " + revealedCard;
        }

        String actionPlayerString, affectedPlayerString, actionString, stateString, arrowString;

        actionPlayerString = actionPlayerIdentifier == null ? "" : actionPlayerIdentifier.toString();
        affectedPlayerString = affectedPlayerIdentifier == null ? "" : affectedPlayerIdentifier.toString();
        actionString = actionIdentifier == null ? "Passed Round" : actionIdentifier.toString();
        stateString = actionState == null ? "" : actionState.toString();

        if (actionPlayerIdentifier == null || affectedPlayerIdentifier == null) {
            arrowString = "";
        } else {
            arrowString = " -> ";
        }

        String logEntryString = actionPlayerString + arrowString + affectedPlayerString + ": " +
                actionString + " " + stateString;

        return logEntryString;
    }
}