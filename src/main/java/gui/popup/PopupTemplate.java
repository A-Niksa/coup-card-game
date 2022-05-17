package gui.popup;

import gui.MainFrame;
import gui.guiconfig.frames.PopupFrameConfig;

import javax.swing.*;
import java.awt.*;

public abstract class PopupTemplate extends JPanel {
    public PopupTemplate() {
        configurePanel();
        PopupFrame popupFrame = new PopupFrame(this);
    }

    private void configurePanel() {
        setSize(new Dimension(PopupFrameConfig.FRAME_WIDTH, PopupFrameConfig.FRAME_HEIGHT));
        setLayout(null);
    }

    protected void drawPanel() {
        initializeComponents();
        alignComponents();
        connectListeners();
        repaint();
        revalidate();
    }

    protected abstract void initializeComponents();

    protected abstract void alignComponents();

    protected abstract void connectListeners();
}
