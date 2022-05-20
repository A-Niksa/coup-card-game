package gui.guiutils;

import gui.MainFrame;
import gui.guiconfig.frames.MainFrameConfig;
import gui.menus.MainMenu;

import javax.swing.*;

public class GeneralUtils {
    public static void alignBackground(JPanel currentPanel, JLabel backgroundImage) {
        int backgroundWidth = MainFrameConfig.FRAME_WIDTH;
        int backgroundHeight = MainFrameConfig.FRAME_HEIGHT;
        backgroundImage.setBounds(0, 0, backgroundWidth, backgroundHeight);
        currentPanel.add(backgroundImage);
    }

    public static void goToMainMenu(MainFrame mainFrame) {
        MainMenu mainMenu = new MainMenu(mainFrame);
        mainFrame.setCurrentPanel(mainMenu);
    }
}
