package gui.popup.specialactionpopups;

import gui.guiconfig.popup.specialactionpopups.ExchangeConfig;
import gui.guiconfig.popup.specialactionpopups.SpecialActionSelectionConfig;
import gui.guiconfig.popup.specialactionpopups.TemplateConfig;
import gui.guiutils.popup.ExchangeUtils;
import gui.popup.PopupFrame;
import gui.popup.PopupTemplate;
import logic.game.GameState;
import logic.models.Card;
import logic.models.Player;
import logic.models.actions.ActionsStack;

import javax.swing.*;
import java.util.ArrayList;

public class ExchangePopup extends SpecialActionPopupTemplate {
    private ArrayList<Card> exchangeCardsList;
    private ArrayList<JRadioButton> playersRadioButtonsList;

    public ExchangePopup(PopupFrame frame, PopupTemplate previousPopupPanel, ActionsStack stack) {
        super(frame, previousPopupPanel, stack);

        modifyTemplate();
        setExchangeCards();
        drawPanel();
    }

    private void modifyTemplate() { // removing back button and re-aligning the confirm button
        remove(backButton);
        remove(confirmButton);

        confirmButton.setBounds(ExchangeConfig.CONFIRM_X, TemplateConfig.Y_BUTTON, TemplateConfig.BUTTON_WIDTH,
                TemplateConfig.BUTTON_HEIGHT);
        add(confirmButton);
    }

    private void setExchangeCards() {
        Player humanPlayer = GameState.getPlayersList().get(0);

        exchangeCardsList = GameState.getExchangeCardsFromDeck(humanPlayer);
    }

    @Override
    protected void initializeComponents() {
        playersRadioButtonsList = new ArrayList<>();

        for (Card card : exchangeCardsList) {
            JRadioButton radioButton = new JRadioButton(card.getIdentifier().toString());
            playersRadioButtonsList.add(radioButton);
        }
    }

    @Override
    protected void alignComponents() {
        int currentY = SpecialActionSelectionConfig.Y_STARTING;

        for (JRadioButton button : playersRadioButtonsList) {
            button.setBounds(SpecialActionSelectionConfig.X_RADIO_BUTTON, currentY,
                    SpecialActionSelectionConfig.BUTTON_WIDTH, SpecialActionSelectionConfig.BUTTON_HEIGHT);
            add(button);

            currentY += SpecialActionSelectionConfig.Y_DIFFERENCE;
        }
    }

    @Override
    protected void connectListeners() {
        confirmButton.addActionListener(e -> {
            ArrayList<Card> selectedCardsList = new ArrayList<>();

            // helps us to avoid returning duplicate cards as a single object instead fo two
            ArrayList<Boolean> cardHasBeenSelectedList = new ArrayList<>();
            for (int i = 0; i < exchangeCardsList.size(); i++) {
                cardHasBeenSelectedList.add(Boolean.FALSE);
            }

            int numberOfSelectedButtons = 0;
            for (JRadioButton button : playersRadioButtonsList) {
                if (button.isSelected()) {
                    numberOfSelectedButtons++;

                    Card card = ExchangeUtils.getCardFromListByName(exchangeCardsList, button.getText(),
                            cardHasBeenSelectedList);
                    selectedCardsList.add(card);
                }
            }

            int numberOfAllowedSelections = (exchangeCardsList.size() - 2);
            if (numberOfSelectedButtons != numberOfAllowedSelections) {
                JOptionPane.showMessageDialog(frame, "You should select " + numberOfAllowedSelections
                        + " card(s).");
                return;
            }

            ExchangeUtils.exchange(this, selectedCardsList);
        });
    }
}
