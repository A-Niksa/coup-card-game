package gui.menus;

import gui.MainFrame;
import gui.Template;
import gui.game.GamePanel;
import gui.guiconfig.menus.PauseMenuConfig;
import gui.guiutils.GeneralUtils;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;

public class PauseMenu extends Template {
    private GamePanel gamePanel;
    private JLabel backgroundImage;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitGameButton;

    public PauseMenu(MainFrame mainFrame, GamePanel gamePanel) {
        super(mainFrame);
        this.gamePanel = gamePanel;

        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon backgroundImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.GAME_BACKGROUND));
        backgroundImage = new JLabel(backgroundImageIcon);

        resumeButton = new JButton("Resume");
        mainMenuButton = new JButton("Main Menu");
        exitGameButton = new JButton("Exit Game");
    }

    @Override
    protected void alignComponents() {
        resumeButton.setBounds(PauseMenuConfig.X_BUTTON, PauseMenuConfig.Y_RESUME_BUTTON, PauseMenuConfig.BUTTON_WIDTH,
                PauseMenuConfig.BUTTON_HEIGHT);
        add(resumeButton);

        mainMenuButton.setBounds(PauseMenuConfig.X_BUTTON, PauseMenuConfig.Y_MAIN_MENU_BUTTON,
                PauseMenuConfig.BUTTON_WIDTH, PauseMenuConfig.BUTTON_HEIGHT);
        add(mainMenuButton);

        exitGameButton.setBounds(PauseMenuConfig.X_BUTTON, PauseMenuConfig.Y_EXIT_GAME_BUTTON,
                PauseMenuConfig.BUTTON_WIDTH, PauseMenuConfig.BUTTON_HEIGHT);
        add(exitGameButton);

        GeneralUtils.alignBackground(this, backgroundImage);
    }

    @Override
    protected void connectListeners() {
        resumeButton.addActionListener(e -> mainFrame.setCurrentPanel(gamePanel));
        mainMenuButton.addActionListener(e -> GeneralUtils.goToMainMenu(mainFrame));
        exitGameButton.addActionListener(e -> System.exit(0));
    }
}
