package gui.menus;

import gui.MainFrame;
import gui.Template;
import gui.game.GamePanel;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;

public class MainMenu extends Template {
    private final int BUTTON_WIDTH = 180;
    private final int BUTTON_HEIGHT = 40;
    private final int LOGO_WIDTH = 389;
    private final int LOGO_HEIGHT = 53;
    private final int X_BUTTON = 390;
    private final int Y_NEW_GAME_BUTTON = 255;
    private final int Y_EXIT_BUTTON = 305;
    private final int X_LOGO = 285;
    private final int Y_LOGO = 150;

    private JLabel menuBackgroundImage;
    private JLabel gameLogoImage;
    private JButton newGameButton;
    private JButton exitButton;

    public MainMenu(MainFrame mainFrame) {
        super(mainFrame);
        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon backgroundImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.MENU_BACKGROUND));
        menuBackgroundImage = new JLabel(backgroundImageIcon);

        ImageIcon logoImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.LOGO));
        gameLogoImage = new JLabel(logoImageIcon);

        newGameButton = new JButton("New Game");

        exitButton = new JButton("Exit Game");
    }

    @Override
    protected void alignComponents() {
        newGameButton.setBounds(X_BUTTON, Y_NEW_GAME_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(newGameButton);

        exitButton.setBounds(X_BUTTON, Y_EXIT_BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(exitButton);

        gameLogoImage.setBounds(X_LOGO, Y_LOGO, LOGO_WIDTH, LOGO_HEIGHT);
        add(gameLogoImage);

        int backgroundWidth = mainFrame.FRAME_WIDTH;
        int backgroundHeight = mainFrame.FRAME_HEIGHT;
        menuBackgroundImage.setBounds(0, 0, backgroundWidth, backgroundHeight);
        add(menuBackgroundImage);
    }

    @Override
    protected void connectListeners() {
        newGameButton.addActionListener(e -> goToGamePanel());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void goToGamePanel() {
        GamePanel gamePanel = new GamePanel(mainFrame);
        mainFrame.setCurrentPanel(gamePanel);
    }
}