package logic.models;

import logic.models.actions.ActionsStack;
import utils.config.PlayerIdentifier;

public class Human extends Player {
    public Human(String playerName) {
        super(PlayerIdentifier.HUMAN, 0, true, playerName); // index of the human player is 0 by default
    }

    @Override
    public void playNormalAction(ActionsStack stack) {

    }

    @Override
    public void playCounterAction(ActionsStack stack) {

    }

    @Override
    public void challenge(ActionsStack stack) {

    }
}
