package utils.logging;

public class ConsoleLogger {
    public void printLogToConsole(LogEntry entry) {
        String logEntryString = entry.getLogEntryString();

        System.out.println(logEntryString);
    }

    public void printSeparator(String separator) {
        System.out.println(separator);
    }
}
