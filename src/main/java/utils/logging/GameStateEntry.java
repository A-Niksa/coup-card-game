package utils.logging;

import utils.config.PlayerIdentifier;

public class GameStateEntry {
    private GameStateIdentifier gameStateIdentifier;
    private PlayerIdentifier playerIdentifier;

    public GameStateEntry(GameStateIdentifier gameStateIdentifier, PlayerIdentifier playerIdentifier) {
        this.gameStateIdentifier = gameStateIdentifier;
        this.playerIdentifier = playerIdentifier;
    }

    public String getGameStateEntryString() {
        String lowerCaseIdentifierString = playerIdentifier.toString().toLowerCase();
        String entryString = "Asking " + lowerCaseIdentifierString + " for any " + gameStateIdentifier;

        return entryString;
    }

    public GameStateIdentifier getGameState() {
        return gameStateIdentifier;
    }

    public PlayerIdentifier getPlayerIdentifier() {
        return playerIdentifier;
    }
}
