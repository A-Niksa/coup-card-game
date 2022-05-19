package gui.game.components.hands;

import gui.game.GamePanel;
import gui.game.components.DynamicComponent;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.hands.HumanHandConfig;
import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.util.ArrayList;

public class HumanHand implements DynamicComponent {
    private GamePanel gamePanel;
    private RectangularContainer humanHandContainer;
    private ArrayList<JLabel> cardImagesList;

    public HumanHand(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        initializeComponents();
        alignComponents();
    }

    private void initializeComponents() {
        humanHandContainer = new RectangularContainer(HumanHandConfig.CONTAINER_WIDTH, HumanHandConfig.CONTAINER_HEIGHT);

        cardImagesList = new ArrayList<>();
        addCardImagesToList();
    }

    private void addCardImagesToList() {
        Player human = GameState.getPlayersList().get(0);
        ArrayList<Card> cardsOfHumanList = human.getHand().getCardsList();

        for (Card card : cardsOfHumanList) {
            JLabel cardImage = new JLabel(getImageIconByCard(card));
            cardImagesList.add(cardImage);
        }
    }

    private ImageIcon getImageIconByCard(Card card) {
        CardIdentifier identifier = card.getIdentifier();

        switch(identifier) {
            case AMBASSADOR:
                return new ImageIcon(ImageManager.getImage(ImageIdentifier.AMBASSADOR));
            case ASSASSIN:
                return new ImageIcon(ImageManager.getImage(ImageIdentifier.ASSSASSIN));
            case CONTESSA:
                return new ImageIcon(ImageManager.getImage(ImageIdentifier.CONTESSA));
            case CAPTAIN:
                return new ImageIcon(ImageManager.getImage(ImageIdentifier.CAPTAIN));
            case DUKE:
                return new ImageIcon(ImageManager.getImage(ImageIdentifier.DUKE));
        }

        return null;
    }

    private void alignComponents() {
        alignCardImages();

        humanHandContainer.setBounds(HumanHandConfig.X_CONTAINER, HumanHandConfig.Y_CONTAINER,
                HumanHandConfig.CONTAINER_WIDTH, HumanHandConfig.CONTAINER_HEIGHT);
        gamePanel.add(humanHandContainer);
    }

    private void alignCardImages() {
        int currentX = HumanHandConfig.X_STARTING_CARD;

        for (JLabel cardImage : cardImagesList) {
            cardImage.setBounds(currentX, HumanHandConfig.Y_CARD, HumanHandConfig.CARD_WIDTH,
                    HumanHandConfig.CARD_HEIGHT);
            gamePanel.add(cardImage);

            currentX += HumanHandConfig.DIFFERENCE_OF_X;
        }
    }

    @Override
    public void updateComponent() {
        removePreviousComponents();
        updateCardImagesList();
        alignComponents();
    }

    private void removePreviousComponents() {
        gamePanel.remove(humanHandContainer);

        for (JLabel cardImage : cardImagesList) {
            gamePanel.remove(cardImage);
        }
    }

    private void updateCardImagesList() {
        cardImagesList.clear();
        addCardImagesToList();
    }
}