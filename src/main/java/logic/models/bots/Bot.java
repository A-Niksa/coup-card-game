package logic.models.bots;

import logic.models.Player;

import java.util.Random;

public abstract class Bot extends Player {
    protected Random randomGenerator;

    public Bot(int playerIndex, String name) {
        super(playerIndex, false, name);
        randomGenerator = new Random();
    }
}
