package gui.popup.specialactionpopups;

import gui.guiconfig.popup.specialactionpopups.TemplateConfig;
import gui.popup.PopupFrame;
import gui.popup.PopupTemplate;
import logic.models.actions.ActionsStack;

import javax.swing.*;

public abstract class SpecialActionPopupTemplate extends PopupTemplate {
    protected PopupTemplate previousPopupPanel;
    protected ActionsStack stack;
    protected JButton confirmButton;
    protected JButton backButton;

    public SpecialActionPopupTemplate(PopupFrame frame, PopupTemplate previousPopupPanel, ActionsStack stack) {
        super(ConstructorMode.KEEP_EXISTING_FRAME);
        this.frame = frame;
        this.previousPopupPanel = previousPopupPanel;
        this.stack = stack;

        initializeButtons();
        alignButtons();
        connectListenerOfBackButton();
    }

    private void initializeButtons() {
        confirmButton = new JButton("Confirm");
        backButton = new JButton("Back");
    }

    private void alignButtons() {
        confirmButton.setBounds(TemplateConfig.X_CONFIRM, TemplateConfig.Y_BUTTON, TemplateConfig.BUTTON_WIDTH,
                TemplateConfig.BUTTON_HEIGHT);
        add(confirmButton);

        backButton.setBounds(TemplateConfig.X_BACK, TemplateConfig.Y_BUTTON, TemplateConfig.BUTTON_WIDTH,
                TemplateConfig.BUTTON_HEIGHT);
        add(backButton);
    }

    private void connectListenerOfBackButton() {
        backButton.addActionListener(e -> {frame.setCurrentPanel(previousPopupPanel);});
    }
}
