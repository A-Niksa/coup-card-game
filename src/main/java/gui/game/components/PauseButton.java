package gui.game.components;

import gui.MainFrame;
import gui.game.GamePanel;
import gui.guiconfig.game.components.PauseButtonConfig;
import gui.guiutils.game.components.PauseButtonUtils;
import gui.menus.PauseMenu;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton {
    private MainFrame mainFrame;
    private GamePanel gamePanel;
    private JLabel imageLabel;

    public PauseButton(MainFrame mainFrame, GamePanel gamePanel) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;

        configureButton();
        setButtonFaceImage();
        connectListener();
    }

    private void configureButton() {
        setLayout(null);

        Dimension dimension = new Dimension(PauseButtonConfig.PAUSE_BUTTON_SIZE, PauseButtonConfig.PAUSE_BUTTON_SIZE);
        setPreferredSize(dimension);
    }

    private void setButtonFaceImage() {
        Image pauseImage = ImageManager.getImage(ImageIdentifier.PAUSE_ICON);

        ImageIcon icon = new ImageIcon(pauseImage);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();

        imageLabel = new JLabel(icon);

        int xLabel = (PauseButtonConfig.PAUSE_BUTTON_SIZE - iconWidth) / 2;
        int yLabel = (PauseButtonConfig.PAUSE_BUTTON_SIZE - iconHeight) / 2;

        imageLabel.setBounds(xLabel, yLabel, iconWidth, iconHeight);
        add(imageLabel);
    }

    private void connectListener() {
        addActionListener(e -> PauseButtonUtils.goToPauseMenu(mainFrame, gamePanel));
    }
}