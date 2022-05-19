package gui.game.components.hands;

import gui.game.GamePanel;
import gui.game.components.DynamicComponent;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.hands.BotHandConfig;
import logic.game.GameState;
import logic.models.Card;
import logic.models.Player;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.util.ArrayList;

public class BotHand implements DynamicComponent {
    public enum HandLocation {
        LEFT,
        TOP,
        RIGHT
    }

    private GamePanel gamePanel;
    private HandLocation location;
    private RectangularContainer botHandContainer;
    private ArrayList<JLabel> cardImagesList;

    public BotHand(GamePanel gamePanel, HandLocation location) {
        this.gamePanel = gamePanel;
        this.location = location;

        initializeComponents();
        alignComponents();
    }

    private void initializeComponents() {
        initializeContainer();

        cardImagesList = new ArrayList<>();
        addCardImagesToList();
    }

    private void addCardImagesToList() {
        Player player = getPlayerByLocation();
        ArrayList<Card> cardsOfPlayerList = player.getHand().getCardsList();

        switch (location) {
            case LEFT:
                for (int i = 0; i < cardsOfPlayerList.size(); i++) {
                    ImageIcon imageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.CARDBACK_HORIZONTAL_LTR));
                    JLabel imageLabel = new JLabel(imageIcon);
                    cardImagesList.add(imageLabel);
                }
                break;

            case TOP:
                for (int i = 0; i < cardsOfPlayerList.size(); i++) {
                    ImageIcon imageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.CARDBACK_VERTICAL_UTD));
                    JLabel imageLabel = new JLabel(imageIcon);
                    cardImagesList.add(imageLabel);
                }
                break;

            case RIGHT:
                for (int i = 0; i < cardsOfPlayerList.size(); i++) {
                    ImageIcon imageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.CARDBACK_HORIZONTAL_RTL));
                    JLabel imageLabel = new JLabel(imageIcon);
                    cardImagesList.add(imageLabel);
                }
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

    private void initializeContainer() {
        switch (location) {
            case LEFT:
            case RIGHT:
                botHandContainer = new RectangularContainer(BotHandConfig.LEFT_RIGHT_CONTAINER_WIDTH,
                        BotHandConfig.LEFT_RIGHT_CONTAINER_HEIGHT);
                break;
            case TOP:
                botHandContainer = new RectangularContainer(BotHandConfig.TOP_CONTAINER_WIDTH,
                        BotHandConfig.TOP_CONTAINER_HEIGHT);
        }
    }

    private void alignComponents() {
        alignCardBacks();
        alignContainer();
    }

    private void alignContainer() {
        switch (location) {
            case LEFT:
                botHandContainer.setBounds(BotHandConfig.X_LEFT_CONTAINER, BotHandConfig.Y_LEFT_RIGHT_CONTAINER,
                        BotHandConfig.LEFT_RIGHT_CONTAINER_WIDTH, BotHandConfig.LEFT_RIGHT_CONTAINER_HEIGHT);
                break;
            case TOP:
                botHandContainer.setBounds(BotHandConfig.X_TOP_CONTAINER, BotHandConfig.Y_TOP_CONTAINER,
                        BotHandConfig.TOP_CONTAINER_WIDTH, BotHandConfig.TOP_CONTAINER_HEIGHT);
                break;
            case RIGHT:
                botHandContainer.setBounds(BotHandConfig.X_RIGHT_CONTAINER, BotHandConfig.Y_LEFT_RIGHT_CONTAINER,
                        BotHandConfig.LEFT_RIGHT_CONTAINER_WIDTH, BotHandConfig.LEFT_RIGHT_CONTAINER_HEIGHT);
        }

        gamePanel.add(botHandContainer);
    }

    private void alignCardBacks() {
        switch (location) {
            case LEFT:
                int currentY = BotHandConfig.Y_STARTING_LEFT_CARD;
                for (JLabel cardImage : cardImagesList) {
                    cardImage.setBounds(BotHandConfig.X_LEFT_CARD, currentY, BotHandConfig.LEFT_RIGHT_CARD_WIDTH,
                            BotHandConfig.LEFT_RIGHT_CARD_HEIGHT);
                    gamePanel.add(cardImage);

                    currentY += BotHandConfig.DIFFERENCE_OF_CARDS;
                }
                break;

            case TOP:
                int currentX = BotHandConfig.X_STARTING_TOP_CARD;
                for (JLabel cardImage : cardImagesList) {
                    cardImage.setBounds(currentX, BotHandConfig.Y_TOP_CARD, BotHandConfig.TOP_CARD_WIDTH,
                            BotHandConfig.TOP_CARD_HEIGHT);
                    gamePanel.add(cardImage);

                    currentX += BotHandConfig.DIFFERENCE_OF_CARDS;
                }

                break;
            case RIGHT:
                currentY = BotHandConfig.Y_STARTING_RIGHT_CARD;
                for (JLabel cardImage : cardImagesList) {
                    cardImage.setBounds(BotHandConfig.X_RIGHT_CARD, currentY, BotHandConfig.LEFT_RIGHT_CARD_WIDTH,
                            BotHandConfig.LEFT_RIGHT_CARD_HEIGHT);
                    gamePanel.add(cardImage);

                    currentY -= BotHandConfig.DIFFERENCE_OF_CARDS;
                }
        }
    }

    @Override
    public void updateComponent() {
        removePreviousComponents();
        updateCardImagesList();
        alignComponents();
    }

    private void removePreviousComponents() {
        gamePanel.remove(botHandContainer);

        for (JLabel cardImage : cardImagesList) {
            gamePanel.remove(cardImage);
        }
    }

    private void updateCardImagesList() {
        cardImagesList.clear();
        addCardImagesToList();
    }
}
