package gui.popup;

import gui.menus.BotSelectionMenu;
import gui.guiconfig.popup.DefaultOptionsConfig;
import gui.guiutils.popup.DefaultOptionsUtils;

import javax.swing.*;

public class DefaultOptionsPopup extends PopupTemplate {
    private BotSelectionMenu botSelectionMenu;
    private JButton confirmButton;
    private JLabel defaultModeExplanationLabel;
    private JLabel gameInitialHandsLabel;
    private JLabel gameBotsSelectionLabel;
    private String[] gameInitialHandsModeArray;
    private JComboBox<String> gameInitialHandsModeBox;
    private String[] gameBotsSelectionModeArray;
    private JComboBox<String> gameBotsSelectionModeBox;

    public DefaultOptionsPopup(BotSelectionMenu botSelectionMenu) {
        super(ConstructorMode.OPEN_NEW_FRAME);
        this.botSelectionMenu = botSelectionMenu;
        initializeComboBoxArrays();

        drawPanel();
    }

    private void initializeComboBoxArrays() {
        gameInitialHandsModeArray = new String[] {"Random", "Default"};
        gameBotsSelectionModeArray = new String[] {"Custom", "Default"};
    }

    @Override
    protected void initializeComponents() {
        defaultModeExplanationLabel = new JLabel(DefaultOptionsUtils.convertTextToHTMLFormat(
                "\"Default\" refers to the modes set by \nthe configuration files."), SwingConstants.CENTER);

        gameInitialHandsLabel = new JLabel("Initial Hands Mode:");
        gameBotsSelectionLabel = new JLabel("Bots Selection Mode:");

        gameInitialHandsModeBox = new JComboBox<>(gameInitialHandsModeArray);
        gameBotsSelectionModeBox = new JComboBox<>(gameBotsSelectionModeArray);

        confirmButton = new JButton("Confirm");
    }

    @Override
    protected void alignComponents() {
        defaultModeExplanationLabel.setBounds(DefaultOptionsConfig.X_EXPLANATION_LABEL,
                DefaultOptionsConfig.Y_EXPLANATION_LABEL, DefaultOptionsConfig.EXPLANATION_LABEL_WIDTH,
                DefaultOptionsConfig.EXPLANATION_LABEL_HEIGHT);
        add(defaultModeExplanationLabel);

        gameInitialHandsLabel.setBounds(DefaultOptionsConfig.X_BOX_LABEL, DefaultOptionsConfig.Y_HANDS_BOX,
                DefaultOptionsConfig.BOX_LABEL_WIDTH, DefaultOptionsConfig.BOX_HEIGHT);
        add(gameInitialHandsLabel);

        gameBotsSelectionLabel.setBounds(DefaultOptionsConfig.X_BOX_LABEL, DefaultOptionsConfig.Y_BOTS_BOX,
                DefaultOptionsConfig.BOX_LABEL_WIDTH, DefaultOptionsConfig.BOX_HEIGHT);
        add(gameBotsSelectionLabel);

        gameInitialHandsModeBox.setBounds(DefaultOptionsConfig.X_BOX, DefaultOptionsConfig.Y_HANDS_BOX,
                DefaultOptionsConfig.BOX_WIDTH, DefaultOptionsConfig.BOX_HEIGHT);
        add(gameInitialHandsModeBox);

        gameBotsSelectionModeBox.setBounds(DefaultOptionsConfig.X_BOX, DefaultOptionsConfig.Y_BOTS_BOX,
                DefaultOptionsConfig.BOX_WIDTH, DefaultOptionsConfig.BOX_HEIGHT);
        add(gameBotsSelectionModeBox);

        confirmButton.setBounds(DefaultOptionsConfig.X_CONFIRM_BUTTON, DefaultOptionsConfig.Y_CONFIRM_BUTTON,
                DefaultOptionsConfig.CONFIRM_BUTTON_WIDTH, DefaultOptionsConfig.CONFIRM_BUTTON_HEIGHT);
        add(confirmButton);
    }

    @Override
    protected void connectListeners() {
        confirmButton.addActionListener(e -> DefaultOptionsUtils.changeGameInitialSettings(botSelectionMenu,
                this));
    }

    public JComboBox<String> getGameInitialHandsModeBox() {
        return gameInitialHandsModeBox;
    }

    public JComboBox<String> getGameBotsSelectionModeBox() {
        return gameBotsSelectionModeBox;
    }
}