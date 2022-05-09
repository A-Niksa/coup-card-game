package gui.panelutils.game;

import gui.MainFrame;
import gui.game.BotSelectionMenu;
import gui.menus.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BotSelectionUtils {
    public static void addRadioButtonsToList(ArrayList<JRadioButton> radioButtonsList, JRadioButton... buttons) {
        radioButtonsList.addAll(Arrays.asList(buttons));
    }

    public static void alignBotSelectionLabel(BotSelectionMenu selectionPanel, JLabel botSelectionLabel) {
        botSelectionLabel.setBounds(selectionPanel.X_BOT_SELECTION_LABEL, selectionPanel.Y_BOT_SELECTION_LABEL,
                selectionPanel.BOT_SELECTION_WIDTH, selectionPanel.BOT_SELECTION_HEIGHT);
        botSelectionLabel.setBackground(Color.WHITE);
        botSelectionLabel.setFont(new Font("", Font.BOLD, 15));

        selectionPanel.add(botSelectionLabel);
    }

    public static void alignRadioButtons(BotSelectionMenu selectionPanel,
                                              ArrayList<JRadioButton> radioButtonsList) {
        int xCurrentButton = selectionPanel.X_RADIO_BUTTON;
        int yCurrentButton = selectionPanel.Y_RADIO_BUTTON;
        int vSpace = selectionPanel.RADIO_BUTTON_V_SPACE;

        for (JRadioButton button : radioButtonsList) {
            button.setBounds(xCurrentButton, yCurrentButton, selectionPanel.RADIO_BUTTON_WIDTH,
                    selectionPanel.RADIO_BUTTON_HEIGHT);
            selectionPanel.add(button);

            yCurrentButton += selectionPanel.RADIO_BUTTON_HEIGHT + vSpace;
        }
    }

    public static void goToMainMenu(MainFrame mainFrame) {
        MainMenu mainMenu = new MainMenu(mainFrame);
        mainFrame.setCurrentPanel(mainMenu);
    }

    public static void beginGameIfPossible(MainFrame mainFrame, BotSelectionMenu selectionPanel) {
        // TODO
    }
}
