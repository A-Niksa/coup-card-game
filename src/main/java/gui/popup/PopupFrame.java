package gui.popup;

import gui.guiconfig.frames.PopupFrameConfig;

import javax.swing.*;
import java.awt.*;

public class PopupFrame extends JFrame {

    public PopupFrame(PopupTemplate panel) {
        getContentPane().add(panel);

        configureFrame();
        repaintFrame();
    }

    private void configureFrame() {
        setSize(new Dimension(PopupFrameConfig.FRAME_WIDTH, PopupFrameConfig.FRAME_HEIGHT));
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void repaintFrame() {
        repaint();
        revalidate();
    }
}
