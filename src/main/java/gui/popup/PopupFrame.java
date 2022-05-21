package gui.popup;

import gui.guiconfig.frames.PopupFrameConfig;

import javax.swing.*;
import java.awt.*;

public class PopupFrame extends JFrame {
    private PopupTemplate panel;

    public PopupFrame(PopupTemplate panel) {
        this.panel = panel;
        getContentPane().add(panel);

        configureFrame();
        repaintFrame();
    }

    public void setCurrentPanel(PopupTemplate panel) {
        this.panel = panel;
        getContentPane().removeAll();
        getContentPane().add(this.panel);

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
