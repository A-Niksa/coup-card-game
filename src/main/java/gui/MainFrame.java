package gui;

import gui.guiconfig.frames.MainFrameConfig;
import gui.menus.MainMenu;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel currentPanel;

    public MainFrame() {
        MainMenu mainMenu = new MainMenu(this);
        setCurrentPanel(mainMenu);
        configureFrame();
        repaintFrame();
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
        getContentPane().removeAll();
        getContentPane().add(this.currentPanel);

        repaintFrame();
    }

    private void configureFrame() {
        setSize(new Dimension(MainFrameConfig.FRAME_WIDTH, MainFrameConfig.FRAME_HEIGHT));
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void repaintFrame() {
        repaint();
        revalidate();
    }
}
