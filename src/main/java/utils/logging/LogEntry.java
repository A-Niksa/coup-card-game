package utils.logging;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

public class LogEntry {
    private PlayerIdentifier actionPlayerIdentifier;
    private PlayerIdentifier affectedPlayerIdentifier;
    private ActionIdentifier actionIdentifier;

    public LogEntry(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                    ActionIdentifier actionIdentifier) {
        this.actionPlayerIdentifier = actionPlayerIdentifier;
        this.affectedPlayerIdentifier = affectedPlayerIdentifier;
        this.actionIdentifier = actionIdentifier;
    }

    public String getLogEntryString() {
        String actionPlayerString, affectedPlayerString, actionString;

        actionPlayerString = actionPlayerIdentifier == null ? "-" : actionPlayerIdentifier.toString();
        affectedPlayerString = affectedPlayerIdentifier == null ? "-" : affectedPlayerIdentifier.toString();
        actionString = actionIdentifier == null ? "Passed Round" : actionIdentifier.toString();

        String logEntryString = actionPlayerString + " -> " + affectedPlayerString + ": " +
                actionString;

        return logEntryString;
    }
}