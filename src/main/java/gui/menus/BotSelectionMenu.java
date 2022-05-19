package gui.menus;

import gui.MainFrame;
import gui.Template;
import gui.guiconfig.menus.BotSelectionConfig;
import gui.guiutils.GeneralUtils;
import gui.guiutils.menus.BotSelectionUtils;
import gui.popup.DefaultOptionsPopup;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.util.ArrayList;

public class BotSelectionMenu extends Template {
    private JLabel menuBackgroundImage;
    private JButton backButton;
    private JButton defaultOptionsButton;
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
        defaultOptionsButton = new JButton("Default Options");
        backButton = new JButton("Back");
    }

    @Override
    protected void alignComponents() {
        BotSelectionUtils.alignBotSelectionLabel(this, botSelectionLabel);

        BotSelectionUtils.alignRadioButtons(this, botsRadioButtonsList);

        beginGameButton.setBounds(BotSelectionConfig.X_BEGIN_BUTTON, BotSelectionConfig.Y_BUTTON,
                BotSelectionConfig.BEGIN_BUTTON_WIDTH, BotSelectionConfig.BUTTON_HEIGHT);
        add(beginGameButton);

        defaultOptionsButton.setBounds(BotSelectionConfig.X_OPTIONS_BUTTON, BotSelectionConfig.Y_BUTTON,
                BotSelectionConfig.OPTIONS_BUTTON_WIDTH, BotSelectionConfig.BUTTON_HEIGHT);
        add(defaultOptionsButton);

        backButton.setBounds(BotSelectionConfig.X_BACK_BUTTON, BotSelectionConfig.Y_BUTTON,
                BotSelectionConfig.BACK_BUTTON_WIDTH, BotSelectionConfig.BUTTON_HEIGHT);
        add(backButton);

        GeneralUtils.alignBackground(this, menuBackgroundImage);
    }

    @Override
    protected void connectListeners() {
        beginGameButton.addActionListener(e -> BotSelectionUtils.beginGameIfPossible(mainFrame, this));
        defaultOptionsButton.addActionListener(e -> new DefaultOptionsPopup(this));
        backButton.addActionListener(e -> BotSelectionUtils.goToMainMenu(mainFrame));
    }
}