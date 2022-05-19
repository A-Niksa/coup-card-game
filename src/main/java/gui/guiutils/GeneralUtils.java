package gui.guiutils;

import gui.MainFrame;
import gui.guiconfig.frames.MainFrameConfig;

import javax.swing.*;

public class GeneralUtils {
    public static void alignBackground(JPanel currentPanel, JLabel backgroundImage) {
        int backgroundWidth = MainFrameConfig.FRAME_WIDTH;
        int backgroundHeight = MainFrameConfig.FRAME_HEIGHT;
        backgroundImage.setBounds(0, 0, backgroundWidth, backgroundHeight);
        currentPanel.add(backgroundImage);
    }
}
