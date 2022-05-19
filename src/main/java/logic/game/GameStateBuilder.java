package logic.game;

import logic.game.components.Deck;
import logic.game.components.Treasury;
import logic.game.tools.EndgameChecker;
import logic.game.tools.TurnKeeper;
import logic.models.Human;
import logic.models.Player;
import logic.models.bots.*;
import utils.config.ConfigProcessor;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class GameStateBuilder {
    private static GameStateBuilder builder;

    private GameStateBuilder() {

    }

    private static GameStateBuilder getInstance() {
        if (builder == null) {
            builder = new GameStateBuilder();
        }

        return builder;
    }

    static void createNewGameState() {
        getInstance().createNewTools();
        getInstance().createNewComponents();
        getInstance().createNewPlayers();

        GameState.setIndexOfCurrentPlayer(0); // starts from 0 by default (so starts with human player)
    }

    private void createNewPlayers() {
        ArrayList<Player> playersList = new ArrayList<>();

        Human human = new Human("Human");
        ArrayList<Bot> botsList = createGameBots();

        playersList.add(human);
        playersList.addAll(botsList);

        GameState.setPlayersList(playersList);
    }

    private ArrayList<Bot> createGameBots() {
        ArrayList<PlayerIdentifier> identifiersOfBotsList = ConfigProcessor.getIdentifiersOfSelectedBots();

        ArrayList<Bot> botsList = new ArrayList<>();
        for (int i = 1; i <= identifiersOfBotsList.size(); i++) {
            Bot bot = createBot(identifiersOfBotsList.get(i-1), i);

            if (bot != null) { // createBot returns null if it can't match the identifier to the desired bot
                botsList.add(bot);
            }
        }

        return botsList;
    }

    private Bot createBot(PlayerIdentifier identifier, int botIndex) {
        Bot bot;
        switch (identifier) {
            case CAUTIOUS:
                bot = new CautiousBot(botIndex, PlayerIdentifier.CAUTIOUS.toString());
                break;
            case COUP_LOVING:
                bot = new CoupLovingBot(botIndex, PlayerIdentifier.COUP_LOVING.toString());
                break;
            case PARANOID:
                bot = new ParanoidBot(botIndex, PlayerIdentifier.PARANOID.toString());
                break;
            case RANDOM:
                bot = new RandomBot(botIndex, PlayerIdentifier.RANDOM.toString());
                break;
            default:
                bot = null;
        }

        return bot;
    }

    private void createNewComponents() {
        Deck deck = new Deck();
        Treasury treasury = new Treasury();

        GameState.setDeck(deck);
        GameState.setTreasury(treasury);
    }

    private void createNewTools() {
        TurnKeeper turnKeeper = new TurnKeeper();
        EndgameChecker endgameChecker = new EndgameChecker();

        GameState.setTurnKeeper(turnKeeper);
        GameState.setEndgameChecker(endgameChecker);
    }
}