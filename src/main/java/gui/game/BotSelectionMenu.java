package gui.game;

import gui.MainFrame;
import gui.Template;
import gui.menus.MainMenu;
import gui.panelutils.GeneralUtils;
import gui.panelutils.game.BotSelectionUtils;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BotSelectionMenu extends Template {
    public final int X_BOT_SELECTION_LABEL = 280;
    public final int Y_BOT_SELECTION_LABEL = 100;
    public final int BOT_SELECTION_HEIGHT = 40;
    public final int BOT_SELECTION_WIDTH = 400;
    public final int Y_BUTTON = 450;
    public final int X_BEGIN_BUTTON = 350;
    public final int X_BACK_BUTTON = 510;
    public final int BUTTON_HEIGHT = 40;
    public final int BEGIN_BUTTON_WIDTH = 150;
    public final int BACK_BUTTON_WIDTH = 100;
    public final int X_RADIO_BUTTON = 380;
    public final int Y_RADIO_BUTTON = 185;
    public final int RADIO_BUTTON_HEIGHT = 40;
    public final int RADIO_BUTTON_WIDTH = 200;
    public final int RADIO_BUTTON_V_SPACE = 15; // vertical space between buttons

    private JLabel menuBackgroundImage;
    private JButton backButton;
    private JButton beginGameButton;
    private JLabel botSelectionLabel;
    private JRadioButton cautiousBotRadioButton;
    private JRadioButton coupLovingBotRadioButton;
    private JRadioButton paranoidBotRadioButton;
    private JRadioButton randomBotRadioButton;
    public ArrayList<JRadioButton> botsRadioButtonsList;

    public BotSelectionMenu(MainFrame mainFrame) {
        super(mainFrame);
        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon backgroundImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.MENU_BACKGROUND));
        menuBackgroundImage = new JLabel(backgroundImageIcon);

        botSelectionLabel = new JLabel("Please choose three of the bots below:", SwingConstants.CENTER);

        cautiousBotRadioButton = new JRadioButton("Cautious Bot");
        coupLovingBotRadioButton = new JRadioButton("Coup-loving Bot");
        paranoidBotRadioButton = new JRadioButton("Paranoid Bot");
        randomBotRadioButton = new JRadioButton("Random Bot");

        botsRadioButtonsList = new ArrayList<>();
        BotSelectionUtils.addRadioButtonsToList(botsRadioButtonsList, cautiousBotRadioButton, coupLovingBotRadioButton,
                paranoidBotRadioButton, randomBotRadioButton);

        beginGameButton = new JButton("Begin Game");
        backButton = new JButton("Back");
    }

    @Override
    protected void alignComponents() {
        BotSelectionUtils.alignBotSelectionLabel(this, botSelectionLabel);

        BotSelectionUtils.alignRadioButtons(this, botsRadioButtonsList);

        beginGameButton.setBounds(X_BEGIN_BUTTON, Y_BUTTON, BEGIN_BUTTON_WIDTH, BUTTON_HEIGHT);
        add(beginGameButton);
        backButton.setBounds(X_BACK_BUTTON, Y_BUTTON, BACK_BUTTON_WIDTH, BUTTON_HEIGHT);
        add(backButton);

        GeneralUtils.alignBackground(mainFrame, this, menuBackgroundImage);
    }

    @Override
    protected void connectListeners() {
        beginGameButton.addActionListener(e -> BotSelectionUtils.beginGameIfPossible(mainFrame, this));
        backButton.addActionListener(e -> BotSelectionUtils.goToMainMenu(mainFrame));
    }
}