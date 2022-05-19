package gui.guiutils.popup;

import gui.game.BotSelectionMenu;
import gui.guiconfig.game.BotSelectionConfig;
import gui.popup.DefaultOptionsPopup;

import javax.swing.*;

public class DefaultOptionsUtils {
    public static String convertTextToHTMLFormat(String text) {
        String formattedText = "<html>" + text + "</html>";
        return formattedText.replaceAll("\n", "<br>");
    }

    public static void changeGameInitialSettings(BotSelectionMenu baseMenu, DefaultOptionsPopup popupMenu) {
        JComboBox<String> gameInitialHandsModeBox = popupMenu.getGameInitialHandsModeBox();
        JComboBox<String> gameBotsSelectionModeBox = popupMenu.getGameBotsSelectionModeBox();

        String selectedInitialHandsMode = (String) gameInitialHandsModeBox.getSelectedItem();
        String selectedBotsSelectionMode = (String) gameBotsSelectionModeBox.getSelectedItem();

        setInitialHandsMode(baseMenu, selectedInitialHandsMode);
        setSelectedBotsMode(baseMenu, selectedBotsSelectionMode);

        popupMenu.getFrame().dispose();
//        popupMenu.getFrame().setVisible(false);
    }

    private static void setInitialHandsMode(BotSelectionMenu baseMenu, String selectedMode) {
        if (selectedMode.equals("Random")) {
            BotSelectionConfig.shouldUseDefaultHands = false;
        } else if (selectedMode.equals("Default")) {
            BotSelectionConfig.shouldUseDefaultHands = true;
        }
    }

    private static void setSelectedBotsMode(BotSelectionMenu baseMenu, String selectedMode) {
        if (selectedMode.equals("Custom")) {
            BotSelectionConfig.shouldUseDefaultBots = false;
        } else if (selectedMode.equals("Default")) {
            BotSelectionConfig.shouldUseDefaultBots = true;
        }
    }
}