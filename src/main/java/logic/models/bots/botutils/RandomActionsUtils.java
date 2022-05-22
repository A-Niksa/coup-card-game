package logic.models.bots.botutils;

import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.NormalAction;

import java.util.ArrayList;
import java.util.Random;

public class RandomActionsUtils { // this class is not exclusive to the RandomBot and is to be used by all bots
    public static Player getRandomPlayer(Random randomGenerator, Player currentPlayer) {
        // gets random player except for the player itself
        ArrayList<Player> playersList = GameState.getPlayersList();

        ArrayList<Player> alivePlayersListExceptActionPlayer = new ArrayList<>();
        for (Player player : playersList) {
            if (player != currentPlayer) {
                if (!player.hasLost()) {
                    alivePlayersListExceptActionPlayer.add(player);
                }
            }
        }

        int randomIndex = randomGenerator.nextInt(alivePlayersListExceptActionPlayer.size());
        return alivePlayersListExceptActionPlayer.get(randomIndex);
    }

    public static Card getRandomCardOfPlayer(Random randomGenerator, Player player) {
        ArrayList<Card> cardsOfPlayerList = player.getHand().getCardsList();

        if (cardsOfPlayerList.isEmpty()) {
            return null;
        }

        int randomIndex = randomGenerator.nextInt(cardsOfPlayerList.size());
        return cardsOfPlayerList.get(randomIndex);
    }

    public static boolean getRandomBoolean(Random randomGenerator) {
        return randomGenerator.nextBoolean();
    }


    public static NormalAction getRandomSimpleAction(Random randomGenerator, Player actionPlayer) {
        // returns simple normal actions
        // simple actions refer to the actions which do not require the player to choose a target player (like coup)

        ArrayList<ActionIdentifier> simpleActionIdentifiersList = new ArrayList<>();
        simpleActionIdentifiersList.add(ActionIdentifier.INCOME_ACQUISITION);
        simpleActionIdentifiersList.add(ActionIdentifier.EXTERNAL_HELP_REQUEST);
        simpleActionIdentifiersList.add(ActionIdentifier.TAXATION);
        simpleActionIdentifiersList.add(ActionIdentifier.CARD_SWAP);

        int randomIndex = randomGenerator.nextInt(simpleActionIdentifiersList.size());
        ActionIdentifier randomActionIdentifier = simpleActionIdentifiersList.get(randomIndex);

        return createSimpleActionByIdentifier(randomActionIdentifier, actionPlayer);
    }

    private static NormalAction createSimpleActionByIdentifier(ActionIdentifier actionIdentifier, Player actionPlayer) {
        NormalAction action;

        switch (actionIdentifier) {
            case INCOME_ACQUISITION:
                action = new NormalAction(ActionIdentifier.INCOME_ACQUISITION, null, actionPlayer,
                        null);
                break;
            case EXTERNAL_HELP_REQUEST:
                action = new NormalAction(ActionIdentifier.EXTERNAL_HELP_REQUEST, null, actionPlayer,
                        null);
                break;
            case TAXATION:
                action = new NormalAction(ActionIdentifier.TAXATION, CardIdentifier.CAPTAIN, actionPlayer,
                        null);
                break;
            case CARD_SWAP:
                action = new NormalAction(ActionIdentifier.CARD_SWAP, null, actionPlayer, null);
                break;
            default:
                action = null;
        }

        return action;
    }
}