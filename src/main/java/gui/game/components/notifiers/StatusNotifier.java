package gui.game.components.notifiers;

import gui.game.GamePanel;
import gui.game.components.DynamicComponent;
import gui.guiconfig.game.components.notifiers.NotifierConfig;
import gui.guiconfig.game.components.notifiers.StatusNotifierConfig;
import utils.logging.GameStateEntry;
import utils.logging.GameStateHistory;

import java.util.ArrayList;

public class StatusNotifier extends Notifier implements DynamicComponent {
    public StatusNotifier(GamePanel gamePanel) {
        super(gamePanel);

        setLabelText();
        initializeLabel(labelText);
        alignLabel();
        alignContainer();
    }

    @Override
    protected void setLabelText() {
        ArrayList<GameStateEntry> entriesList = GameStateHistory.getGameStateEntriesList();

        String entryString = "";

        if (!entriesList.isEmpty()) {
            GameStateEntry latestEntry = entriesList.get(entriesList.size() - 1);
            entryString = latestEntry.getGameStateEntryString();
        }

        labelText = "Game State: " + entryString;
    }

    @Override
    protected void alignLabel() {
        notifierLabel.setBounds(NotifierConfig.X_LABEL, StatusNotifierConfig.Y_LABEL, NotifierConfig.LABEL_WIDTH,
                NotifierConfig.LABEL_HEIGHT);
        gamePanel.add(notifierLabel);
    }

    @Override
    protected void alignContainer() {
        notifierContainer.setBounds(NotifierConfig.X_CONTAINER, StatusNotifierConfig.Y_CONTAINER,
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
