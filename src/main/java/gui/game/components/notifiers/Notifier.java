package gui.game.components.notifiers;

import gui.game.GamePanel;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.notifiers.NotifierConfig;

import javax.swing.*;

public abstract class Notifier {
    protected GamePanel gamePanel;
    protected RectangularContainer notifierContainer;
    protected JLabel notifierLabel;
    protected String labelText;

    public Notifier(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        notifierContainer = new RectangularContainer(NotifierConfig.CONTAINER_WIDTH, NotifierConfig.CONTAINER_HEIGHT);
    }

    protected void initializeLabel(String text) {
        notifierLabel = new JLabel(text);
    }

    protected void removeLabel() {
        gamePanel.remove(notifierLabel);
    }

    protected void removeContainer() {
        gamePanel.remove(notifierContainer);
    }

    protected abstract void setLabelText();

    protected abstract void alignLabel();

    protected abstract void alignContainer();
}
