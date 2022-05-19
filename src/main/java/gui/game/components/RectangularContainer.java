package gui.game.components;

import javax.swing.*;
import java.awt.*;

public class RectangularContainer extends JLabel {
    private int width;
    private int height;

    public RectangularContainer(int width, int height) {
        this.width = width;
        this.height = height;

        configureLabel();
        setColor();
    }

    private void configureLabel() {
        setLayout(null);

        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
    }

    private void setColor() {
        setOpaque(true);
        setForeground(Color.WHITE);
    }
}
