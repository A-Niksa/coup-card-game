package gui.popup.specialactionpopups;

import gui.guiconfig.popup.specialactionpopups.SpecialActionSelectionConfig;
import gui.guiutils.popup.SpecialActionsUtils;
import gui.popup.PopupFrame;
import gui.popup.PopupTemplate;
import logic.game.GameState;
import logic.models.Player;
import logic.models.actions.ActionsStack;

import javax.swing.*;
import java.util.ArrayList;

public class CoupPopup extends SpecialActionPopupTemplate {
    private ArrayList<Player> alivePlayersList;
    private ArrayList<JRadioButton> playersRadioButtonsList;

    public CoupPopup(PopupFrame frame, PopupTemplate previousPopupPanel, ActionsStack stack) {
        super(frame, previousPopupPanel, stack);
        setListOfAlivePlayers();

        drawPanel();
    }

    private void setListOfAlivePlayers() {
        alivePlayersList = new ArrayList<>();
        ArrayList<Player> playersList = GameState.getPlayersList();

        for (int i = 1; i < playersList.size(); i++) { // i = 1 in order to skip the human player
            if (!playersList.get(i).hasLost()) {
                alivePlayersList.add(playersList.get(i));
            }
        }
    }

    @Override
    protected void initializeComponents() {
        playersRadioButtonsList = new ArrayList<>();

        for (Player player : alivePlayersList) {
            JRadioButton radioButton = new JRadioButton(player.getPlayerName());
            playersRadioButtonsList.add(radioButton);
        }
    }

    @Override
    protected void alignComponents() {
        int currentY = SpecialActionSelectionConfig.Y_STARTING;

        for (JRadioButton button : playersRadioButtonsList) {
            button.setBounds(SpecialActionSelectionConfig.X_RADIO_BUTTON, currentY, SpecialActionSelectionConfig.BUTTON_WIDTH,
                    SpecialActionSelectionConfig.BUTTON_HEIGHT);
            add(button);

            currentY += SpecialActionSelectionConfig.Y_DIFFERENCE;
        }
    }

    @Override
    protected void connectListeners() {
        confirmButton.addActionListener(e -> {
            String targetName = "";
            int numberOfSelectedButtons = 0;
            for (JRadioButton button : playersRadioButtonsList) {
                if (button.isSelected()) {
                    numberOfSelectedButtons++;
                    targetName = button.getText();
                }
            }

            if (numberOfSelectedButtons > 1) {
                JOptionPane.showMessageDialog(frame, "You should only select one opponent.");
                return;
            }

            SpecialActionsUtils.attemptCoup(frame, this, targetName);
        });
    }
}