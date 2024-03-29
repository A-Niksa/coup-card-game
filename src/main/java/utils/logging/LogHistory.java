package utils.logging;

import logic.models.CardIdentifier;
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

    public static void log(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                           ActionIdentifier actionIdentifier, ActionState actionState) {
        getInstance().logByInstance(actionPlayerIdentifier, affectedPlayerIdentifier, actionIdentifier, actionState);
    }

    private void logByInstance(PlayerIdentifier actionPlayerIdentifier, PlayerIdentifier affectedPlayerIdentifier,
                               ActionIdentifier actionIdentifier, ActionState actionState) {
        LogEntry entry = new LogEntry(actionPlayerIdentifier, affectedPlayerIdentifier, actionIdentifier, actionState);
        logEntriesList.add(entry);

        consoleLogger.printLogToConsole(entry);
    }

    public static void logRevealedCard(PlayerIdentifier actionPlayerIdentifier, CardIdentifier revealedCard) {
        getInstance().logRevealedCardByInstance(actionPlayerIdentifier, revealedCard);
    }

    private void logRevealedCardByInstance(PlayerIdentifier actionPlayerIdentifier, CardIdentifier revealedCard) {
        LogEntry entry = new LogEntry(actionPlayerIdentifier, revealedCard);
        logEntriesList.add(entry);

        consoleLogger.printLogToConsole(entry);
    }

    public static void addSeparatorToLogs() {
        getInstance().addSeparatorToLogsByInstance();
    }

    private void addSeparatorToLogsByInstance() {
        String separator = "#===========================================#";

        consoleLogger.printSeparator(separator);
    }

    public static ArrayList<LogEntry> getLogEntriesList() {
        return getInstance().logEntriesList;
    }

    public static void clear() {
        getInstance().clearByInstance();
    }

    private void clearByInstance() {
        logEntriesList.clear();
    }
}
