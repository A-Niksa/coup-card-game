package gui.game;

import gui.MainFrame;
import gui.Template;
import gui.guiconfig.game.EndgamePanelConfig;
import gui.guiutils.EndgamePanelUtils;
import gui.guiutils.GeneralUtils;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;

public class EndgamePanel extends Template {
    private JLabel backgroundImage;
    private JLabel winnerNameLabel;
    private JButton mainMenuButton;
    private JButton exitGameButton;

    public EndgamePanel(MainFrame mainFrame) {
        super(mainFrame);

        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon backgroundImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.GAME_BACKGROUND));
        backgroundImage = new JLabel(backgroundImageIcon);

        winnerNameLabel = new JLabel(EndgamePanelUtils.getNameOfWinner(), SwingConstants.CENTER);

        mainMenuButton = new JButton("Main Menu");
        exitGameButton = new JButton("Exit Game");
    }

    @Override
    protected void alignComponents() {
        winnerNameLabel.setBounds(EndgamePanelConfig.X_BUTTON, EndgamePanelConfig.Y_LABEL,
                EndgamePanelConfig.BUTTON_WIDTH, EndgamePanelConfig.BUTTON_HEIGHT);
        winnerNameLabel.setOpaque(true);
        add(winnerNameLabel);

        mainMenuButton.setBounds(EndgamePanelConfig.X_BUTTON, EndgamePanelConfig.Y_MAIN_MENU_BUTTON,
                EndgamePanelConfig.BUTTON_WIDTH, EndgamePanelConfig.BUTTON_HEIGHT);
        add(mainMenuButton);

        exitGameButton.setBounds(EndgamePanelConfig.X_BUTTON, EndgamePanelConfig.Y_EXIT_GAME_BUTTON,
                EndgamePanelConfig.BUTTON_WIDTH, EndgamePanelConfig.BUTTON_HEIGHT);
        add(exitGameButton);

        GeneralUtils.alignBackground(this, backgroundImage);
    }

    @Override
    protected void connectListeners() {
        mainMenuButton.addActionListener(e -> GeneralUtils.goToMainMenu(mainFrame));
        exitGameButton.addActionListener(e -> System.exit(0));
    }
}
