package gui.guiutils;

import gui.MainFrame;
import gui.guiconfig.frames.MainFrameConfig;

import javax.swing.*;

public class GeneralUtils {
    public static void alignBackground(MainFrame mainFrame, JPanel currentPanel, JLabel menuBackgroundImage) {
        int backgroundWidth = MainFrameConfig.FRAME_WIDTH;
        int backgroundHeight = MainFrameConfig.FRAME_HEIGHT;
        menuBackgroundImage.setBounds(0, 0, backgroundWidth, backgroundHeight);
        currentPanel.add(menuBackgroundImage);
    }
}
