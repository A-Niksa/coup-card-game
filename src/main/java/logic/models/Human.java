package logic.models;

import controllers.HumanPlayerController;
import logic.game.GameState;
import logic.models.actions.Action;
import logic.models.actions.ActionsStack;
import utils.config.PlayerIdentifier;

public class Human extends Player {
    public Human(String playerName) {
        super(PlayerIdentifier.HUMAN, 0, true, playerName); // index of the human player is 0 by default
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        while (!GameState.humanHasChosenAction()) { // polling to see if the player has chosen an action
            suspendProgram(200);
        }

        HumanPlayerController controller = GameState.getController();
        Action action = controller.getChosenAction(stack);

        stack.addToStack(action);

        GameState.setHumanHasChosenAction(false);
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        playCounterActionOrChallenge(stack);
    }

    @Override
    public void challenge(ActionsStack stack) {
        playCounterActionOrChallenge(stack);
    }

    private void playCounterActionOrChallenge(ActionsStack stack) {
        while (!GameState.humanHasChosenAction()) { // polling to see if the player has chosen an action
            suspendProgram(200);
        }

        HumanPlayerController controller = GameState.getController();
        Action action = controller.getChosenAction(stack);

        if (action != null) {
            stack.addToStack(action);
        }

        GameState.setHumanHasChosenAction(false);
    }

    private void suspendProgram(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
