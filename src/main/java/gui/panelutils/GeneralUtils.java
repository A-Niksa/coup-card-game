package gui.panelutils;

import gui.MainFrame;

import javax.swing.*;

public class GeneralUtils {
    public static void alignBackground(MainFrame mainFrame, JPanel currentPanel, JLabel menuBackgroundImage) {
        int backgroundWidth = mainFrame.FRAME_WIDTH;
        int backgroundHeight = mainFrame.FRAME_HEIGHT;
        menuBackgroundImage.setBounds(0, 0, backgroundWidth, backgroundHeight);
        currentPanel.add(menuBackgroundImage);
    }
}
