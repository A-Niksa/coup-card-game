package gui;

import gui.menus.MainMenu;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public final int FRAME_HEIGHT = 600;
    public final int FRAME_WIDTH = 960;

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
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void repaintFrame() {
        repaint();
        revalidate();
    }
}
