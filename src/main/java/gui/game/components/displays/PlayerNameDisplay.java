package gui.game.components.displays;

import gui.game.GamePanel;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.displays.NameDisplayConfig;
import logic.game.GameState;
import logic.models.Player;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerNameDisplay {
    private GamePanel gamePanel;
    private DisplayLocation location;
    private RectangularContainer displayContainer;
    private JLabel playerNameLabel;
    private JLabel namePromptLabel; // label containing the phrase "Bot"

    public PlayerNameDisplay(GamePanel gamePanel, DisplayLocation location) {
        this.gamePanel = gamePanel;
        this.location = location;

        initializeComponents();
        alignComponents();
    }

    private void initializeComponents() {
        initializeContainer();

        Player player = getPlayerByLocation();
        playerNameLabel = new JLabel(getBotName(player), SwingConstants.CENTER);

        namePromptLabel = new JLabel("Bot", SwingConstants.CENTER);
    }

    private String getBotName(Player player) {
        String fullName = player.getPlayerIdentifier().toString();
        String shortenedName = fullName.substring(0, fullName.length() - 4); // to cut off " Bot" at the end of fullName

        return shortenedName;
    }

    private void initializeContainer() {
        switch (location) {
            case LEFT:
            case RIGHT:
                displayContainer = new RectangularContainer(NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH,
                        NameDisplayConfig.CONTAINER_HEIGHT);
                break;

            case TOP:
                displayContainer = new RectangularContainer(NameDisplayConfig.TOP_CONTAINER_WIDTH,
                        NameDisplayConfig.CONTAINER_HEIGHT);
        }
    }

    private Player getPlayerByLocation() {
        int playerIndex;

        switch (location) {
            case LEFT:
                playerIndex = 1;
                break;
            case TOP:
                playerIndex = 2;
                break;
            case RIGHT:
                playerIndex = 3;
                break;
            default:
                playerIndex = -1; // will lead to an exception in this case
        }

        ArrayList<Player> playersList = GameState.getPlayersList();
        return playersList.get(playerIndex);
    }

    private void alignComponents() {
        switch (location) {
            case LEFT:
                playerNameLabel.setBounds(NameDisplayConfig.X_LEFT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_CONTAINER,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                namePromptLabel.setBounds(NameDisplayConfig.X_LEFT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_PROMPT_LABEL,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                displayContainer.setBounds(NameDisplayConfig.X_LEFT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_CONTAINER,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.CONTAINER_HEIGHT);
                break;

            case TOP:
                playerNameLabel.setBounds(NameDisplayConfig.X_TOP_CONTAINER, NameDisplayConfig.Y_TOP_CONTAINER,
                        NameDisplayConfig.TOP_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                namePromptLabel.setBounds(NameDisplayConfig.X_TOP_CONTAINER, NameDisplayConfig.Y_TOP_PROMPT_LABEL,
                        NameDisplayConfig.TOP_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                displayContainer.setBounds(NameDisplayConfig.X_TOP_CONTAINER, NameDisplayConfig.Y_TOP_CONTAINER,
                        NameDisplayConfig.TOP_CONTAINER_WIDTH, NameDisplayConfig.CONTAINER_HEIGHT);
                break;

            case RIGHT:
                playerNameLabel.setBounds(NameDisplayConfig.X_RIGHT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_CONTAINER,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                namePromptLabel.setBounds(NameDisplayConfig.X_RIGHT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_PROMPT_LABEL,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.LABEL_HEIGHT);
                displayContainer.setBounds(NameDisplayConfig.X_RIGHT_CONTAINER, NameDisplayConfig.Y_LEFT_RIGHT_CONTAINER,
                        NameDisplayConfig.LEFT_RIGHT_CONTAINER_WIDTH, NameDisplayConfig.CONTAINER_HEIGHT);
        }

        gamePanel.add(playerNameLabel);
        gamePanel.add(namePromptLabel);
        gamePanel.add(displayContainer);
    }
}
