package utils.logging;

import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class GameStateHistory {
    private static GameStateHistory history;

    private ArrayList<GameStateEntry> gameStateEntriesList;

    public GameStateHistory() {
        gameStateEntriesList = new ArrayList<>();
    }

    private static GameStateHistory getInstance() {
        if (history == null) {
            history = new GameStateHistory();
        }

        return history;
    }

    public static void log(GameStateIdentifier gameStateIdentifier, PlayerIdentifier playerIdentifier) {
        getInstance().logByInstance(gameStateIdentifier, playerIdentifier);
    }

    private void logByInstance(GameStateIdentifier gameStateIdentifier, PlayerIdentifier playerIdentifier) {
        GameStateEntry entry = new GameStateEntry(gameStateIdentifier, playerIdentifier);
        gameStateEntriesList.add(entry);
    }

    public static GameStateEntry getLatestGameStateEntry() {
        return getInstance().getLatestGameStateEntryByInstance();
    }

    private GameStateEntry getLatestGameStateEntryByInstance() {
        int latestEntryIndex = gameStateEntriesList.size() - 1;
        return gameStateEntriesList.get(latestEntryIndex);
    }

    public static ArrayList<GameStateEntry> getGameStateEntriesList() {
        return getInstance().gameStateEntriesList;
    }
}
