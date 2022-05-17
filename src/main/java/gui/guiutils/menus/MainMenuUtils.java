package gui.guiutils.menus;

import gui.MainFrame;
import gui.game.BotSelectionMenu;

public class MainMenuUtils {
    public static void goToBotSelectionMenu(MainFrame mainFrame) {
        BotSelectionMenu botSelectionMenu = new BotSelectionMenu(mainFrame);
        mainFrame.setCurrentPanel(botSelectionMenu);
    }
}
