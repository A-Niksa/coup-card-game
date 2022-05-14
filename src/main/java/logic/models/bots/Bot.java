package logic.models.bots;

import logic.models.Player;

public abstract class Bot extends Player {
    public Bot(int playerIndex, boolean isHuman, String name) {
        super(playerIndex, isHuman, name);
    }
}
