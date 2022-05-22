package controllers;

import controllers.actioncommands.Command;
import logic.game.GameState;
import logic.models.Human;
import logic.models.Player;
import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;

import java.util.ArrayList;

public class HumanPlayerController {
    private boolean humanHasChosenAction; // action in this context refers to challenges as well
    private Human humanPlayer;
    private Command currentCommand;

    public HumanPlayerController() {
        humanHasChosenAction = false; // initial value
        humanPlayer = getHumanPlayerForConstructor();
    }

    private Human getHumanPlayerForConstructor() {
        ArrayList<Player> playersList = GameState.getPlayersList();

        return (Human) playersList.get(0);
    }

    private Action interpretCommand(ActionsStack stack) {
        Action action = null;
        ActionIdentifier identifier = currentCommand.getActionIdentifier();

        switch (identifier) {
            case ASSASSINATION:
                action = CommandInterpretationUtils.interpretAssassination(currentCommand, humanPlayer);
                break;
            case EXCHANGE:
                action = CommandInterpretationUtils.interpretExchange(currentCommand, humanPlayer);
                break;
            case EXTORTION:
                action = CommandInterpretationUtils.interpretExtortion(currentCommand, humanPlayer);
                break;
            case INCOME_ACQUISITION:
                action = CommandInterpretationUtils.interpretIncomeAcquisition(currentCommand, humanPlayer);
                break;
            case CARD_SWAP:
                action = CommandInterpretationUtils.interpretCardSwap(currentCommand, humanPlayer);
                break;
            case EXTERNAL_HELP_REQUEST:
                action = CommandInterpretationUtils.interpretHelpRequest(currentCommand, humanPlayer);
                break;
            case COUP:
                action = CommandInterpretationUtils.interpretCoup(currentCommand, humanPlayer);
                break;
            case TAXATION:
                action= CommandInterpretationUtils.interpretTaxation(currentCommand, humanPlayer);
                break;

            case ASSASSINATION_COUNTER:
                action = CommandInterpretationUtils.interpretAssassinationCounter(humanPlayer, stack);
                break;
            case EXTORTION_COUNTER:
                action = CommandInterpretationUtils.interpretExtortionCounter(currentCommand, humanPlayer, stack);
                break;
            case EXTERNAL_HELP_REQUEST_COUNTER:
                action = CommandInterpretationUtils.interpretHelpRequestCounter(currentCommand, humanPlayer, stack);
                break;

            case CHALLENGE:
                action = CommandInterpretationUtils.interpretChallenge(currentCommand, humanPlayer);
                break;
            case NOTHING:
                action = null; // action is already null. added this assignment for explicitness of the code
        }

        return action;
    }

    public Action getChosenAction(ActionsStack stack) {
        Action action = interpretCommand(stack);
        return action;
    }

    public boolean humanHasChosenAction() {
        return humanHasChosenAction;
    }

    public void setHumanHasChosenAction(boolean humanHasChosenAction) {
        this.humanHasChosenAction = humanHasChosenAction;
    }

    public void setCurrentCommand(Command currentCommand) {
        humanHasChosenAction = true;

        this.currentCommand = currentCommand;
    }
}