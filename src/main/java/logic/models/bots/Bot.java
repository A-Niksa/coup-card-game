package logic.models.bots;

import logic.models.Player;
import utils.config.PlayerIdentifier;

import java.util.Random;

public abstract class Bot extends Player {
    protected Random randomGenerator;

    public Bot(PlayerIdentifier playerIdentifier, int playerIndex, String name) {
        super(playerIdentifier, playerIndex, false, name);
        randomGenerator = new Random();
    }
}
