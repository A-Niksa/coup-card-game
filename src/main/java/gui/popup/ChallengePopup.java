package gui.popup;

import gui.guiconfig.popup.ChallengePopupConfig;
import gui.guiconfig.popup.specialactionpopups.SpecialActionSelectionConfig;
import gui.guiutils.popup.ChallengePopupUtils;
import gui.guiutils.popup.SpecialActionsUtils;
import logic.game.GameState;
import logic.models.Player;
import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import logic.models.bots.botutils.PossibleActionsUtils;

import javax.swing.*;
import java.util.ArrayList;

public class ChallengePopup extends PopupTemplate {
    private ActionsStack stack;
    private ArrayList<Action> challengeableActionsList;
    private ArrayList<JRadioButton> radioButtonsList;
    private JButton confirmButton;

    public ChallengePopup(ActionsStack stack) {
        super(ConstructorMode.OPEN_NEW_FRAME);
        this.stack = stack;

        setChallengeableActionsList();

        drawPanel();
    }

    private void setChallengeableActionsList() {
        Player humanPlayer = GameState.getPlayersList().get(0);

        challengeableActionsList = ChallengePopupUtils.getListOfChallengeableActions(stack, humanPlayer);
    }

    @Override
    protected void initializeComponents() {
        radioButtonsList = new ArrayList<>();

        for (Action action : challengeableActionsList) {
            String actionPlayerName = action.getActionPlayer().getPlayerName();
            String actionIdentifierString = action.getActionIdentifier().toString();
            String radioButtonString = actionPlayerName + ": " + actionIdentifierString;

            JRadioButton radioButton = new JRadioButton(radioButtonString);
            radioButtonsList.add(radioButton);
        }

        confirmButton = new JButton("Confirm");
    }

    @Override
    protected void alignComponents() {
        int currentY = ChallengePopupConfig.Y_STARTING;

        for (JRadioButton button : radioButtonsList) {
            button.setBounds(ChallengePopupConfig.X_RADIO_BUTTON, currentY,
                    ChallengePopupConfig.BUTTON_WIDTH, ChallengePopupConfig.BUTTON_HEIGHT);
            add(button);

            currentY += ChallengePopupConfig.Y_DIFFERENCE;
        }

        confirmButton.setBounds(ChallengePopupConfig.X_CONFIRM, ChallengePopupConfig.Y_CONFIRM,
                ChallengePopupConfig.CONFIRM_WIDTH, ChallengePopupConfig.CONFIRM_HEIGHT);
        add(confirmButton);
    }

    @Override
    protected void connectListeners() {
        confirmButton.addActionListener(e -> {
            if (challengeableActionsList.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There are no actions to challenge.");
                return;
            }

            int indexOfSelectedButton = -1; // -1 so that it will throw an exception if it's still -1 at line 88
            int numberOfSelectedButtons = 0;
            JRadioButton radioButton;
            for (int i = 0; i < radioButtonsList.size(); i++) {
                radioButton = radioButtonsList.get(i);

                if (radioButton.isSelected()) {
                    numberOfSelectedButtons++;
                    indexOfSelectedButton = i;
                }
            }

            if (numberOfSelectedButtons > 1) {
                JOptionPane.showMessageDialog(frame, "You should only select one action to challenge.");
                return;
            }

            ChallengePopupUtils.challenge(this, challengeableActionsList.get(indexOfSelectedButton));
        });
    }
}
