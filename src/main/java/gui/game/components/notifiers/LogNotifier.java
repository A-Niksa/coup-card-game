package gui.game.components.notifiers;

import gui.game.GamePanel;
import gui.game.components.DynamicComponent;
import gui.guiconfig.game.components.notifiers.LogNotifierConfig;
import gui.guiconfig.game.components.notifiers.NotifierConfig;
import utils.logging.LogEntry;
import utils.logging.LogHistory;

import java.util.ArrayList;

public class LogNotifier extends Notifier implements DynamicComponent {
    public LogNotifier(GamePanel gamePanel) {
        super(gamePanel);

        setLabelText();
        initializeLabel(labelText);
        alignLabel();
        alignContainer();
    }

    @Override
    protected void setLabelText() {
        ArrayList<LogEntry> logEntriesList = LogHistory.getLogEntriesList();

        String logEntryString = "";

        if (!logEntriesList.isEmpty()) {
            LogEntry latestLogEntry = logEntriesList.get(logEntriesList.size() - 1);
            logEntryString = latestLogEntry.getLogEntryString();
        }

        labelText = "Latest Log: " + logEntryString;
    }

    @Override
    protected void alignLabel() {
        notifierLabel.setBounds(NotifierConfig.X_LABEL, LogNotifierConfig.Y_LABEL, NotifierConfig.LABEL_WIDTH,
                NotifierConfig.LABEL_HEIGHT);
        gamePanel.add(notifierLabel);
    }

    @Override
    protected void alignContainer() {
        notifierContainer.setBounds(NotifierConfig.X_CONTAINER, LogNotifierConfig.Y_CONTAINER,
                NotifierConfig.CONTAINER_WIDTH, NotifierConfig.CONTAINER_HEIGHT);
        gamePanel.add(notifierContainer);
    }

    @Override
    public void updateComponent() {
        removeLabel();
        removeContainer();
        setLabelText();
        initializeLabel(labelText);
        alignLabel();
        alignContainer();
    }
}
