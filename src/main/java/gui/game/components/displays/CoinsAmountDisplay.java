package gui.game.components.displays;

import gui.game.GamePanel;
import gui.game.components.DynamicComponent;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.displays.CoinsDisplayConfig;
import logic.game.GameState;
import logic.models.Player;

import javax.swing.*;
import java.util.ArrayList;

public class CoinsAmountDisplay implements DynamicComponent {
    private GamePanel gamePanel;
    private DisplayLocation location;
    private RectangularContainer displayContainer; // display as a noun :)
    private int numberOfCoins;
    private JLabel numberOfCoinsLabel;
    private JLabel coinsPromptLabel; // a label containing "Coins" under the number of coins label

    public CoinsAmountDisplay(GamePanel gamePanel, DisplayLocation location) {
        this.gamePanel = gamePanel;
        this.location = location;

        initializeComponents();
        alignComponents();
    }

    private void initializeComponents() {
        displayContainer = new RectangularContainer(CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.CONTAINER_SIZE);

        setNumberOfCoinsOfPlayer();
        numberOfCoinsLabel = new JLabel("" + numberOfCoins, SwingConstants.CENTER);

        coinsPromptLabel = new JLabel("Coins", SwingConstants.CENTER);
    }

    private void setNumberOfCoinsOfPlayer() {
        Player player = getPlayerByLocation();
        numberOfCoins = player.getNumberOfCoins();
    }

    private Player getPlayerByLocation() {
        int playerIndex;

        switch (location) {
            case BOTTOM:
                playerIndex = 0;
                break;
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
        alignLabels();
        alignContainer();
    }

    private void alignContainer() {
        switch (location) {
            case BOTTOM:
                displayContainer.setBounds(CoinsDisplayConfig.X_CONTAINER_BOTTOM, CoinsDisplayConfig.Y_CONTAINER_BOTTOM,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.CONTAINER_SIZE);
                break;

            case LEFT:
                displayContainer.setBounds(CoinsDisplayConfig.X_CONTAINER_LEFT, CoinsDisplayConfig.Y_CONTAINER_LEFT,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.CONTAINER_SIZE);
                break;

            case TOP:
                displayContainer.setBounds(CoinsDisplayConfig.X_CONTAINER_TOP, CoinsDisplayConfig.Y_CONTAINER_TOP,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.CONTAINER_SIZE);
                break;

            case RIGHT:
                displayContainer.setBounds(CoinsDisplayConfig.X_CONTAINER_RIGHT, CoinsDisplayConfig.Y_CONTAINER_RIGHT,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.CONTAINER_SIZE);
                break;
        }

        gamePanel.add(displayContainer);
    }

    private void alignLabels() {
        switch (location) {
            case BOTTOM:
                numberOfCoinsLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_BOTTOM, CoinsDisplayConfig.Y_CONTAINER_BOTTOM,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.LABEL_HEIGHT);
                coinsPromptLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_BOTTOM,
                        CoinsDisplayConfig.Y_BOTTOM_PROMPT_LABEL, CoinsDisplayConfig.CONTAINER_SIZE,
                        CoinsDisplayConfig.LABEL_HEIGHT);
                break;

            case LEFT:
                numberOfCoinsLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_LEFT, CoinsDisplayConfig.Y_CONTAINER_LEFT,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.LABEL_HEIGHT);
                coinsPromptLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_LEFT,
                        CoinsDisplayConfig.Y_LEFT_PROMPT_LABEL, CoinsDisplayConfig.CONTAINER_SIZE,
                        CoinsDisplayConfig.LABEL_HEIGHT);
                break;

            case TOP:
                numberOfCoinsLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_TOP, CoinsDisplayConfig.Y_CONTAINER_TOP,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.LABEL_HEIGHT);
                coinsPromptLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_TOP,
                        CoinsDisplayConfig.Y_TOP_PROMPT_LABEL, CoinsDisplayConfig.CONTAINER_SIZE,
                        CoinsDisplayConfig.LABEL_HEIGHT);
                break;
            case RIGHT:
                numberOfCoinsLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_RIGHT, CoinsDisplayConfig.Y_CONTAINER_RIGHT,
                        CoinsDisplayConfig.CONTAINER_SIZE, CoinsDisplayConfig.LABEL_HEIGHT);
                coinsPromptLabel.setBounds(CoinsDisplayConfig.X_CONTAINER_RIGHT,
                        CoinsDisplayConfig.Y_RIGHT_PROMPT_LABEL, CoinsDisplayConfig.CONTAINER_SIZE,
                        CoinsDisplayConfig.LABEL_HEIGHT);
        }

        gamePanel.add(numberOfCoinsLabel);
        gamePanel.add(coinsPromptLabel);
    }

    @Override
    public void updateComponent() {
        removePreviousComponents();
        updateNumberOfCoins();
        alignComponents();
    }

    private void removePreviousComponents() {
        gamePanel.remove(displayContainer);
        gamePanel.remove(numberOfCoinsLabel);
        gamePanel.remove(coinsPromptLabel);
    }

    private void updateNumberOfCoins() {
        setNumberOfCoinsOfPlayer();
        numberOfCoinsLabel = new JLabel("" + numberOfCoins, SwingConstants.CENTER);
    }
}
