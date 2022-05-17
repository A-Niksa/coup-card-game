package gui;

import gui.guiconfig.frames.MainFrameConfig;

import javax.swing.*;
import java.awt.*;

public abstract class Template extends JPanel {
    protected MainFrame mainFrame;

    public Template(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        configurePanel();
    }

    protected void drawPanel() {
        initializeComponents();
        alignComponents();
        connectListeners();
        repaint();
        revalidate();
    }

    private void configurePanel() {
        setSize(new Dimension(MainFrameConfig.FRAME_WIDTH, MainFrameConfig.FRAME_HEIGHT));
        setLayout(null);
    }

    protected abstract void initializeComponents();

    protected abstract void alignComponents();

    protected abstract void connectListeners();
}
