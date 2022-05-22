package gui.popup;

import utils.logging.LogEntry;
import utils.logging.LogHistory;

import javax.swing.*;
import java.util.ArrayList;

public class LogPopup extends PopupTemplate {
    private ArrayList<LogEntry> logEntriesList;
    private String concatenatedLogEntries;
    private JTextArea logTextArea;
    private JScrollPane scrollableTextArea;

    public LogPopup() {
        super(ConstructorMode.OPEN_NEW_FRAME);
        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        logEntriesList = LogHistory.getLogEntriesList();
        concatenatedLogEntries = concatenateLogEntries();

        logTextArea = new JTextArea(concatenatedLogEntries);

        scrollableTextArea = new JScrollPane(logTextArea);
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
        scrollableTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollableTextArea.setBounds(0, 0, 300, 470);
        add(scrollableTextArea);
    }

    @Override
    protected void connectListeners() {

    }
}
