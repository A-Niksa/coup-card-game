package utils.logging;

import logic.models.actions.ActionIdentifier;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class LogHistory {
    private static LogHistory history;

    private ConsoleLogger consoleLogger;
    private ArrayList<LogEntry> logEntriesList;

    private LogHistory() {
        consoleLogger = new ConsoleLogger();
        logEntriesList = new ArrayList<>();
    }

    private static LogHistory getInstance() {
        if (history == null) {
            history = new LogHistory();
        }

        return history;
    }

    public static void log(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                           ActionIdentifier actionIdentifier) {
        getInstance().logByInstance(actionPlayerIdentifier, affectedPlayerIdentifier, actionIdentifier);
    }

    private void logByInstance(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                               ActionIdentifier actionIdentifier) {
        LogEntry entry = new LogEntry(actionPlayerIdentifier, affectedPlayerIdentifier, actionIdentifier);
        logEntriesList.add(entry);

        consoleLogger.printLogToConsole(entry);
    }
}
