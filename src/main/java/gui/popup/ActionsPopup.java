package gui.popup;

import gui.MainFrame;
import gui.guiconfig.popup.ActionsPopupConfig;
import gui.guiutils.popup.ActionsPopupUtils;
import logic.models.CardIdentifier;
import logic.models.actions.ActionsStack;

import javax.swing.*;
import java.util.ArrayList;

public class ActionsPopup extends PopupTemplate {
    private MainFrame mainFrame;
    private ActionsStack stack;

    private JButton exchangeCardsButton;
    private JButton randomCardSwapButton;
    private JButton assassinateOpponentButton;
    private JButton attemptCoupButton;
    private JButton extortOpponentButton;
    private JButton acquireIncomeButton;
    private JButton requestHelpButton;
    private JButton acquireTaxButton;
    private JButton blockAssassinationButton;
    private JButton blockHelpRequestButton;
    private JButton blockExtortionAsAmbassadorButton;
    private JButton blockExtortionAsCaptainButton;
    private ArrayList<JButton> buttonsList;

    public ActionsPopup(MainFrame mainFrame, ActionsStack stack) {
        this.mainFrame = mainFrame;
        this.stack = stack;

        drawPanel();
    }

    @Override
    protected void initializeComponents() {
        exchangeCardsButton = new JButton("Exchange Cards");
        randomCardSwapButton = new JButton("Random Card Swap");
        assassinateOpponentButton = new JButton("Assassinate Opponent");
        attemptCoupButton = new JButton("Attempt Coup");
        extortOpponentButton = new JButton("Extort Opponent");
        acquireIncomeButton = new JButton("Acquire Income");
        requestHelpButton = new JButton("Request Help");
        acquireTaxButton = new JButton("Acquire Tax");
        blockAssassinationButton = new JButton("Block Assassination");
        blockHelpRequestButton = new JButton("Block External Help");
        blockExtortionAsAmbassadorButton = new JButton("Block Extortion (Ambassador)");
        blockExtortionAsCaptainButton = new JButton("Block Extortion (Captain)");

        addButtonsToList();
    }

    private void addButtonsToList() {
        buttonsList = new ArrayList<>();

        buttonsList.add(exchangeCardsButton);
        buttonsList.add(randomCardSwapButton);
        buttonsList.add(assassinateOpponentButton);
        buttonsList.add(attemptCoupButton);
        buttonsList.add(extortOpponentButton);
        buttonsList.add(acquireIncomeButton);
        buttonsList.add(requestHelpButton);
        buttonsList.add(acquireTaxButton);
        buttonsList.add(blockAssassinationButton);
        buttonsList.add(blockHelpRequestButton);
        buttonsList.add(blockExtortionAsAmbassadorButton);
        buttonsList.add(blockExtortionAsCaptainButton);
    }

    @Override
    protected void alignComponents() {
        int currentY = ActionsPopupConfig.Y_STARTING_BUTTON;

        for (JButton button : buttonsList) {
            button.setBounds(ActionsPopupConfig.X_BUTTON, currentY, ActionsPopupConfig.BUTTON_WIDTH,
                    ActionsPopupConfig.BUTTON_HEIGHT);
            add(button);

            currentY += ActionsPopupConfig.DIFFERENCE_OF_Y;
        }
    }

    @Override
    protected void connectListeners() {
        randomCardSwapButton.addActionListener(e -> ActionsPopupUtils.swapCardRandomly(mainFrame, this));
        acquireIncomeButton.addActionListener(e -> ActionsPopupUtils.acquireIncome(this));
        requestHelpButton.addActionListener(e -> ActionsPopupUtils.requestExternalHelp(this));
        acquireTaxButton.addActionListener(e -> ActionsPopupUtils.acquireTax(this));
        blockAssassinationButton.addActionListener(e -> ActionsPopupUtils.counterAssassination(mainFrame,
                this, stack));
        blockExtortionAsAmbassadorButton.addActionListener(e -> ActionsPopupUtils.blockExtortion(mainFrame,
                this, CardIdentifier.AMBASSADOR, stack));
    }
}
