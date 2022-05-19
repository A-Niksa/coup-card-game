package gui.guiutils.menus;

import gui.MainFrame;
import gui.menus.MainMenu;

public class PauseMenuUtils {
    public static void goToMainMenu(MainFrame mainFrame) {
        MainMenu mainMenu = new MainMenu(mainFrame);
        mainFrame.setCurrentPanel(mainMenu);
    }
}
