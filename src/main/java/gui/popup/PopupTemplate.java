package gui.popup;

import gui.MainFrame;
import gui.guiconfig.frames.PopupFrameConfig;

import javax.swing.*;
import java.awt.*;

public abstract class PopupTemplate extends JPanel {
    public enum ConstructorMode {
        OPEN_NEW_FRAME,
        KEEP_EXISTING_FRAME
    }

    protected PopupFrame frame;
    protected ConstructorMode mode;

    public PopupTemplate(ConstructorMode mode) {
        configurePanel();
        this.mode = mode;

        if (mode == ConstructorMode.OPEN_NEW_FRAME) {
            this.frame = new PopupFrame(this);
        }
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

    public PopupFrame getFrame() {
        return frame;
    }
}
