package logic.models.actions;

import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Hand;
import logic.models.Player;

import java.util.ArrayList;

public abstract class Action {
    protected ActionIdentifier actionIdentifier;
    protected CardIdentifier cardIdentifier;
    protected Player actionPlayer;
    protected boolean isBluff;

    protected boolean isChallenge;
    protected Action challengedAction;

    protected boolean isCounterAction;
    protected Action counteredAction;

    protected boolean shouldBeSkipped; // used for skipping actions if they were bluffed and challenged

    public Action(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier, Player actionPlayer) {
        this.actionIdentifier = actionIdentifier;
        this.cardIdentifier = cardIdentifier;
        this.actionPlayer = actionPlayer;
        isBluff = determineIfActionIsBluff();

        isChallenge = false;
        challengedAction = null;
        isCounterAction = false;
        counteredAction = null;

        shouldBeSkipped = false;
    }

    public Action(ActionIdentifier actionIdentifier, Player actionPlayer, Action challengedAction) {
        this.actionIdentifier = actionIdentifier;
        this.actionPlayer = actionPlayer;
        this.challengedAction = challengedAction;
        isChallenge = true;

        isBluff = false; // bluff has no meaning if action is a challenge
        isCounterAction = false;
        counteredAction = null;

        shouldBeSkipped = false;
    }

    public Action(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier,
                  Player actionPlayer, Action counteredAction, boolean isCounterAction) {
        this.actionIdentifier = actionIdentifier;
        this.cardIdentifier = cardIdentifier;
        this.actionPlayer = actionPlayer;
        this.isCounterAction = isCounterAction;
        this.counteredAction = counteredAction;
        isBluff = determineIfActionIsBluff();

        isChallenge = false;
        challengedAction = null;

        shouldBeSkipped = false;
    }

    protected abstract void resolveAction();

    private boolean determineIfActionIsBluff() {
        // difference with the getter: this is used at instantiation and involves querying into the GameState
        Hand handOfPlayer = actionPlayer.getHand();
        ArrayList<Card> cardsListOfPlayer = handOfPlayer.getCardsList();

        for (Card card : cardsListOfPlayer) {
            if (actionFitsCard(card)) {
                return true;
            }
        }

        return false;
    }

    private boolean actionFitsCard(Card card) {
        boolean fitsCard;
        switch (card.getIdentifier()) {
            case AMBASSADOR:
                fitsCard = actionIdentifier == ActionIdentifier.EXCHANGE ||
                        actionIdentifier == ActionIdentifier.EXTORTION_COUNTER;
                break;
            case ASSASSIN:
                fitsCard = actionIdentifier == ActionIdentifier.ASSASSINATION;
                break;
            case CAPTAIN:
                fitsCard = actionIdentifier == ActionIdentifier.EXTORTION ||
                        actionIdentifier == ActionIdentifier.EXTORTION_COUNTER;
                break;
            case CONTESSA:
                fitsCard = actionIdentifier == ActionIdentifier.ASSASSINATION_COUNTER;
                break;
            case DUKE:
                fitsCard = actionIdentifier == ActionIdentifier.TAXATION ||
                        actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER;
                break;
            default:
                fitsCard = false;
        }

        return fitsCard;
    }

    public boolean isBluff() {
        return isBluff;
    }

    public boolean shouldBeSkipped() {
        return shouldBeSkipped;
    }

    public Player getActionPlayer() {
        return actionPlayer;
    }

    public ActionIdentifier getActionIdentifier() {
        return actionIdentifier;
    }

    public CardIdentifier getCardIdentifier() {
        return cardIdentifier;
    }

    public void setShouldBeSkipped(boolean shouldBeSkipped) {
        this.shouldBeSkipped = shouldBeSkipped;
    }

    public void setChallengedAction(Action challengedAction) {
        this.challengedAction = challengedAction;
        isChallenge = true;
    }
}