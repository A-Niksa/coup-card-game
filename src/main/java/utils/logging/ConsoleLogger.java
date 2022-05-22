package utils.logging;

public class ConsoleLogger {
    public void printLogToConsole(LogEntry entry) {
        String logEntryString = entry.getLogEntryString();

        System.out.println(logEntryString);
    }

    public void printSeparator() {
        System.out.println("#===========================================#");
    }
}
