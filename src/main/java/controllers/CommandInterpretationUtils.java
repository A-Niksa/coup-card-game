package controllers;

import controllers.actioncommands.*;
import gui.popup.ActionsPopup;
import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Human;
import logic.models.Player;
import logic.models.actions.Action;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.specialutils.AssassinationAction;
import logic.models.actions.cardutils.specialutils.CoupAction;
import logic.models.actions.cardutils.specialutils.ExchangeAction;
import logic.models.actions.cardutils.specialutils.ExtortionAction;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class CommandInterpretationUtils {
    public static Action interpretAssassination(Command command, Human humanPlayer) {
        AssassinationCommand assassinationCommand = (AssassinationCommand) command;

        Player targetPlayer = getPlayerByIdentifier(assassinationCommand.getTargetPlayerIdentifier());

        Random randomGenerator = new Random();
        Card randomCardOfTargetPlayer = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
        CardIdentifier targetCardIdentifier = randomCardOfTargetPlayer.getIdentifier();

        AssassinationAction action = new AssassinationAction(humanPlayer, targetPlayer, targetCardIdentifier);
        return action;
    }

    public static Action interpretExchange(Command command, Human humanPlayer) {
        ExchangeCommand exchangeCommand = (ExchangeCommand) command;

        ArrayList<Card> cardsList = exchangeCommand.getCardsList();

        ExchangeAction action = new ExchangeAction(humanPlayer, cardsList);
        return action;
    }

    public static Action interpretExtortion(Command command, Human humanPlayer) {
        ExtortionCommand extortionCommand = (ExtortionCommand) command;

        Player targetPlayer = getPlayerByIdentifier(extortionCommand.getTargetPlayerIdentifier());

        ExtortionAction action = new ExtortionAction(humanPlayer, targetPlayer);
        return action;
    }

    public static Action interpretIncomeAcquisition(Command command, Human humanPlayer) {
        NormalAction action = new NormalAction(ActionIdentifier.INCOME_ACQUISITION, null, humanPlayer,
                null);
        return action;
    }

    public static Action interpretCardSwap(Command command, Human humanPlayer) {
        NormalAction action = new NormalAction(ActionIdentifier.CARD_SWAP, null, humanPlayer, null);
        return action;
    }

    public static Action interpretHelpRequest(Command command, Human humanPlayer) {
        NormalAction action = new NormalAction(ActionIdentifier.EXTERNAL_HELP_REQUEST, null, humanPlayer,
                null);
        return action;
    }

    public static Action interpretCoup(Command command, Human humanPlayer) {
        CoupCommand coupCommand = (CoupCommand) command;

        Player targetPlayer = getPlayerByIdentifier(coupCommand.getTargetPlayerIdentifier());

        Random randomGenerator = new Random();
        Card randomCardOfTargetPlayer = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
        CardIdentifier targetCardIdentifier = randomCardOfTargetPlayer.getIdentifier();

        CoupAction action = new CoupAction(humanPlayer, targetPlayer, targetCardIdentifier);
        return action;
    }

    public static Action interpretAssassinationCounter(Command command, Human humanPlayer, ActionsStack stack) {
        Player actionPerpetrator = getAssassinationPerpetrator(stack);

        NormalAction action = new NormalAction(ActionIdentifier.ASSASSINATION_COUNTER, null, humanPlayer,
                actionPerpetrator);
        return action;
    }

    public static Action interpretExtortionCounter(Command command, Human humanPlayer) {
        ExtortionCounterCommand extortionCounterCommand = (ExtortionCounterCommand) command;

        CardIdentifier playingCardIdentifier = extortionCounterCommand.getPlayingCardIdentifier();

        NormalAction action = new NormalAction(ActionIdentifier.EXTORTION_COUNTER, playingCardIdentifier, humanPlayer,
                null);
        return action;
    }

    public static Action interpretHelpRequestCounter(Command command, Human humanPlayer) {
        HelpRequestCounterCommand helpRequestCounterCommand = (HelpRequestCounterCommand) command;

        Player targetPlayer = getPlayerByIdentifier(helpRequestCounterCommand.getTargetPlayerIdentifier());

        NormalAction action = new NormalAction(ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER, CardIdentifier.DUKE,
                humanPlayer, targetPlayer);
        return action;
    }

    private static Player getPlayerByIdentifier(PlayerIdentifier playerIdentifier) {
        ArrayList<Player> playerList = GameState.getPlayersList();

        for (Player player : playerList) {
            if (player.getPlayerIdentifier() == playerIdentifier) {
                return player;
            }
        }

        return null;
    }

    private static Player getAssassinationPerpetrator(ActionsStack stack) {
        Deque<Action> stackOfActions = stack.getStackOfActions();

        for (Action action : stackOfActions) {
            if (action.getActionIdentifier() == ActionIdentifier.ASSASSINATION) {
                return action.getActionPlayer();
            }
        }

        return null;
    }
}
