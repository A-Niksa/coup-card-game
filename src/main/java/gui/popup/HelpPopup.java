package gui.popup;

import gui.guiconfig.popup.HelpPopupConfig;
import gui.guiconfig.popup.PopupTemplateConfig;
import gui.popup.PopupTemplate;
import utils.resources.ImageIdentifier;
import utils.resources.ImageManager;

import javax.swing.*;

public class HelpPopup extends PopupTemplate {
    private JLabel helpImage;

    public HelpPopup() {
        super(ConstructorMode.OPEN_NEW_FRAME);
        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        ImageIcon helpImageIcon = new ImageIcon(ImageManager.getImage(ImageIdentifier.HELP));
        helpImage = new JLabel(helpImageIcon);
    }

    @Override
    protected void alignComponents() {
        helpImage.setBounds(HelpPopupConfig.X_IMAGE, HelpPopupConfig.Y_IMAGE,
                PopupTemplateConfig.POPUP_FRAME_WIDTH, PopupTemplateConfig.POPUP_FRAME_HEIGHT);
        add(helpImage);
    }

    @Override
    protected void connectListeners() {

    }
}
