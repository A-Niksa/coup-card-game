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
        String logEntryString = actionPlayerIdentifier + " -> " + affectedPlayerIdentifier + ": " +
                actionIdentifier;

        return logEntryString;
    }
}