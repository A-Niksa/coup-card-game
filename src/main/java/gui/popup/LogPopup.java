package gui.popup;

import utils.logging.LogEntry;
import utils.logging.LogHistory;

import javax.swing.*;
import java.util.ArrayList;

public class LogPopup extends PopupTemplate {
    private ArrayList<LogEntry> logEntriesList;
    private String concatenatedLogEntries;
    private JTextArea logTextArea;

    public LogPopup() {
        super(ConstructorMode.OPEN_NEW_FRAME);
        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        logEntriesList = LogHistory.getLogEntriesList();
        concatenatedLogEntries = concatenateLogEntries();

        logTextArea = new JTextArea(concatenatedLogEntries);
    }

    private String concatenateLogEntries() {
        String concatenatedLogEntries = "";

        for (LogEntry entry : logEntriesList) {
            concatenatedLogEntries += entry.getLogEntryString() + "\n";
        }

        return concatenatedLogEntries;
    }

    @Override
    protected void alignComponents() {
        logTextArea.setBounds(0, 0, 320, 510);
        add(logTextArea);
    }

    @Override
    protected void connectListeners() {

    }
}
