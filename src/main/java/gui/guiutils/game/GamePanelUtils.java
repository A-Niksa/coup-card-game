package gui.guiutils.game;

import controllers.actioncommands.PassingTurnCommand;
import gui.game.GamePanel;
import gui.game.components.RectangularContainer;
import gui.guiconfig.game.components.CommandsContainerConfig;
import logic.game.GameState;

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
}
