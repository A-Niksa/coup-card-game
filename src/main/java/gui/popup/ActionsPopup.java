package gui.popup;

import gui.guiconfig.popup.ActionsPopupConfig;
import gui.guiutils.popup.ActionsPopupUtils;
import gui.guiutils.popup.SpecialActionsOpenerUtils;
import logic.models.CardIdentifier;
import logic.models.actions.ActionsStack;

import javax.swing.*;
import java.util.ArrayList;

public class ActionsPopup extends PopupTemplate {
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

    public ActionsPopup(ActionsStack stack) {
        super(ConstructorMode.OPEN_NEW_FRAME);
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
        randomCardSwapButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            ActionsPopupUtils.swapCardRandomly(frame, this);
        });

        acquireIncomeButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            ActionsPopupUtils.acquireIncome(frame, this);
        });

        requestHelpButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            ActionsPopupUtils.requestExternalHelp(frame, this);
        });

        acquireTaxButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            ActionsPopupUtils.acquireTax(frame, this);
        });

        exchangeCardsButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            SpecialActionsOpenerUtils.goToExchangePopup(frame, this, stack);
        });
        assassinateOpponentButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            SpecialActionsOpenerUtils.goToAssassinationPopup(frame, this, stack);
        });

        attemptCoupButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            SpecialActionsOpenerUtils.goToCoupPopup(frame, this, stack);
        });
        extortOpponentButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeCounterAction(frame)) {
                return;
            }

            SpecialActionsOpenerUtils.goToExtortionPopup(frame, this, stack);
        });

        blockAssassinationButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeNormalAction(frame)) {
                return;
            }

            ActionsPopupUtils.blockAssassination(frame, this, stack);
        });

        blockHelpRequestButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeNormalAction(frame)) {
                return;
            }

            ActionsPopupUtils.blockExternalHelp(frame, this, stack);
        });

        blockExtortionAsAmbassadorButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeNormalAction(frame)) {
                return;
            }

            ActionsPopupUtils.blockExtortion(frame, this, CardIdentifier.AMBASSADOR, stack);
        });
        blockExtortionAsCaptainButton.addActionListener(e -> {
            if (ActionsPopupUtils.checkIfShouldMakeNormalAction(frame)) {
                return;
            }

            ActionsPopupUtils.blockExtortion(frame, this, CardIdentifier.CAPTAIN, stack);
        });
    }
}