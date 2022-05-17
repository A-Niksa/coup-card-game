package gui.guiutils.game;

import gui.MainFrame;
import gui.game.BotSelectionMenu;
import gui.guiconfig.game.BotSelectionConfig;
import gui.menus.MainMenu;
import utils.config.ConfigProcessor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BotSelectionUtils {
    public static void addRadioButtonsToList(ArrayList<JRadioButton> radioButtonsList, JRadioButton... buttons) {
        radioButtonsList.addAll(Arrays.asList(buttons));
    }

    public static void alignBotSelectionLabel(BotSelectionMenu selectionPanel, JLabel botSelectionLabel) {
        botSelectionLabel.setOpaque(true);
        botSelectionLabel.setBounds(BotSelectionConfig.X_BOT_SELECTION_LABEL, BotSelectionConfig.Y_BOT_SELECTION_LABEL,
                BotSelectionConfig.BOT_SELECTION_WIDTH, BotSelectionConfig.BOT_SELECTION_HEIGHT);
        botSelectionLabel.setFont(new Font("", Font.BOLD, 15));

        selectionPanel.add(botSelectionLabel);
    }

    public static void alignRadioButtons(BotSelectionMenu selectionPanel,
                                              ArrayList<JRadioButton> radioButtonsList) {
        int xCurrentButton = BotSelectionConfig.X_RADIO_BUTTON;
        int yCurrentButton = BotSelectionConfig.Y_RADIO_BUTTON;
        int vSpace = BotSelectionConfig.RADIO_BUTTON_V_SPACE;

        for (JRadioButton button : radioButtonsList) {
            button.setBounds(xCurrentButton, yCurrentButton, BotSelectionConfig.RADIO_BUTTON_WIDTH,
                    BotSelectionConfig.RADIO_BUTTON_HEIGHT);
            selectionPanel.add(button);

            yCurrentButton += BotSelectionConfig.RADIO_BUTTON_HEIGHT + vSpace;
        }
    }

    public static void goToMainMenu(MainFrame mainFrame) {
        MainMenu mainMenu = new MainMenu(mainFrame);
        mainFrame.setCurrentPanel(mainMenu);
    }

    public static void beginGameIfPossible(MainFrame mainFrame, BotSelectionMenu selectionPanel) {
        ArrayList<JRadioButton> botsRadioButtonsList = selectionPanel.botsRadioButtonsList;

        int numberOfCheckedRadioButtons = getNumberOfCheckedRadioButtons(botsRadioButtonsList);
        if (numberOfCheckedRadioButtons != 3) {
            JOptionPane.showMessageDialog(mainFrame, "You have to choose 3 bots.");
            return;
        }

        saveSelectedBotsToConfigFile(selectionPanel);
        // TODO: change the structure -> for importing config if desired
    }

    private static void saveSelectedBotsToConfigFile(BotSelectionMenu selectionPanel) {
        ArrayList<JRadioButton> botsRadioButtonsList = selectionPanel.botsRadioButtonsList;

        ArrayList<String> botNamesList = new ArrayList<>();
        for (JRadioButton button : botsRadioButtonsList) {
            if (button.isSelected()) {
                botNamesList.add(button.getText());
            }
        }

        ConfigProcessor.saveBotsToConfig(botNamesList);
    }

    private static int getNumberOfCheckedRadioButtons(ArrayList<JRadioButton> radioButtonsList) {
        int count = 0;
        for (JRadioButton button : radioButtonsList) {
            if (button.isSelected()) count++;
        }
        return count;
    }
}