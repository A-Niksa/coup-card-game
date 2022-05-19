package gui.menus;

import gui.MainFrame;
import gui.Template;
import gui.guiconfig.menus.MainMenuConfig;
import gui.guiutils.GeneralUtils;
import gui.guiutils.menus.MainMenuUtils;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;

public class MainMenu extends Template {
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
        newGameButton.setBounds(MainMenuConfig.X_BUTTON, MainMenuConfig.Y_NEW_GAME_BUTTON, MainMenuConfig.BUTTON_WIDTH,
                MainMenuConfig.BUTTON_HEIGHT);
        add(newGameButton);

        exitButton.setBounds(MainMenuConfig.X_BUTTON, MainMenuConfig.Y_EXIT_BUTTON, MainMenuConfig.BUTTON_WIDTH,
                MainMenuConfig.BUTTON_HEIGHT);
        add(exitButton);

        gameLogoImage.setBounds(MainMenuConfig.X_LOGO, MainMenuConfig.Y_LOGO, MainMenuConfig.LOGO_WIDTH,
                MainMenuConfig.LOGO_HEIGHT);
        add(gameLogoImage);

        GeneralUtils.alignBackground(this, menuBackgroundImage);
    }

    @Override
    protected void connectListeners() {
        newGameButton.addActionListener(e -> MainMenuUtils.goToBotSelectionMenu(mainFrame));
        exitButton.addActionListener(e -> System.exit(0));
    }
}