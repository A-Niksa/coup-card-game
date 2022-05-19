package gui.guiutils.game.components;

import gui.MainFrame;
import gui.game.GamePanel;
import gui.menus.PauseMenu;

public class PauseButtonUtils {
    public static void goToPauseMenu(MainFrame mainFrame, GamePanel gamePanel) {
        PauseMenu pauseMenu = new PauseMenu(mainFrame, gamePanel);
        mainFrame.setCurrentPanel(pauseMenu);
    }
}
