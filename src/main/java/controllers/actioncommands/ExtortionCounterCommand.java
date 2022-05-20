package controllers.actioncommands;

import logic.models.CardIdentifier;
import logic.models.actions.ActionIdentifier;

public class ExtortionCounterCommand extends Command {
    private CardIdentifier playingCardIdentifier;

    public ExtortionCounterCommand(CardIdentifier playingCardIdentifier) {
        super(ActionIdentifier.EXTORTION_COUNTER);
        this.playingCardIdentifier = playingCardIdentifier;
    }

    public CardIdentifier getPlayingCardIdentifier() {
        return playingCardIdentifier;
    }
}
