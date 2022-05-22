package gui.guiutils.game;

import controllers.actioncommands.PassingTurnCommand;
import gui.MainFrame;
import gui.game.GamePanel;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.CommandsContainerConfig;
import logic.game.GameRunner;
import logic.game.GameState;
import logic.models.actions.ActionsStack;
import logic.models.bots.botutils.PossibleActionsUtils;
import utils.logging.GameStateEntry;
import utils.logging.GameStateHistory;
import utils.logging.GameStateIdentifier;

import javax.swing.*;

public class GamePanelUtils {
    public static void alignCommandsContainer(GamePanel gamePanel, JButton actionsButton, JButton challengeButton,
                                              JButton passButton, RectangularContainer commandsContainer) {
        actionsButton.setBounds(CommandsContainerConfig.X_BUTTON, CommandsContainerConfig.Y_ACTIONS_BUTTON,
                CommandsContainerConfig.BUTTON_WIDTH, CommandsContainerConfig.BUTTON_HEIGHT);
        challengeButton.setBounds(CommandsContainerConfig.X_BUTTON, CommandsContainerConfig.Y_CHALLENGE_BUTTON,
                CommandsContainerConfig.BUTTON_WIDTH, CommandsContainerConfig.BUTTON_HEIGHT);
        passButton.setBounds(CommandsContainerConfig.X_BUTTON, CommandsContainerConfig.Y_PASS_BUTTON,
                CommandsContainerConfig.BUTTON_WIDTH, CommandsContainerConfig.BUTTON_HEIGHT);
        commandsContainer.setBounds(CommandsContainerConfig.X_COMMAND_CONTAINER,
                CommandsContainerConfig.Y_COMMAND_CONTAINER, CommandsContainerConfig.COMMAND_CONTAINER_WIDTH,
                CommandsContainerConfig.COMMAND_CONTAINER_HEIGHT);

        gamePanel.add(actionsButton);
        gamePanel.add(challengeButton);
        gamePanel.add(passButton);
        gamePanel.add(commandsContainer);
    }

    public static void passTurn() { // passes turn in each of the 3 stages in a round
        PassingTurnCommand command = new PassingTurnCommand();
        GameState.setCurrentCommandInController(command);
    }

    public static boolean canPassTurn() {
        GameStateEntry latestEntry = GameStateHistory.getLatestGameStateEntry();
        return latestEntry.getGameState() != GameStateIdentifier.NORMAL_ACTIONS;
    }

    public static boolean checkIfIsTurnOfPlayer(MainFrame mainFrame, GameRunner gameRunner) {
        if (gameRunner.getCurrentTurnInStageIndex() != 0) {
            JOptionPane.showMessageDialog(mainFrame, "It's not your turn.");
            return false;
        }

        return true;
    }

    public static boolean checkIfShouldNotChallenge(MainFrame mainFrame) {
        GameStateEntry latestEntry = GameStateHistory.getLatestGameStateEntry();

        if (latestEntry.getGameState() != GameStateIdentifier.CHALLENGES) {
            JOptionPane.showMessageDialog(mainFrame, "This is not the stage for challenging other players.");
            return true;
        }

        return false;
    }

    public static boolean checkIfShouldNotPlayNormalOrCounterActions(MainFrame mainFrame) {
        GameStateEntry latestEntry = GameStateHistory.getLatestGameStateEntry();

        if (latestEntry.getGameState() == GameStateIdentifier.CHALLENGES) {
            JOptionPane.showMessageDialog(mainFrame, "This is challenging stage. You cannot play a normal or " +
                    "a counter action.");
            return true;
        }

        return false;
    }

    public static boolean checkIfThereHasAlreadyBeenAChallenge(MainFrame mainFrame, ActionsStack stack) {
        if (PossibleActionsUtils.thereHaveAlreadyBeenTooManyChallenges(stack)) {
            JOptionPane.showMessageDialog(mainFrame, "You cannot challenge anyone anymore.");
            return true;
        }

        return false;
    }
}
