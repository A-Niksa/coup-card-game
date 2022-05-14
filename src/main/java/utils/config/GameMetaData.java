package utils.config;

import logic.models.CardIdentifier;

import java.util.ArrayList;
import java.util.EnumMap;

public class GameMetaData {
    ArrayList<PlayerIdentifier> playersList;
    ArrayList<HandMetaData> handsOfPlayersList;

    public GameMetaData() {
        playersList = new ArrayList<>();
        handsOfPlayersList = new ArrayList<>();
    }

    public void mapPlayerToInitialHand(PlayerIdentifier identifier, CardIdentifier firstCard,
                                       CardIdentifier secondCard) {
        HandMetaData handMetaData = new HandMetaData(identifier, firstCard, secondCard);
        handsOfPlayersList.add(handMetaData);
    }

    public void resetGameBots() {
        playersList.clear();
    }

    public void setGameBots(PlayerIdentifier firstBot, PlayerIdentifier secondBot, PlayerIdentifier thirdBot) {
        resetGameBots();

        if (playersList.isEmpty()) {
            playersList.add(firstBot);
            playersList.add(secondBot);
            playersList.add(thirdBot);
        }
    }

    @Override
    public String toString() {
        return "GameMetaData{" +
                "playersList=" + playersList +
                ", handsOfPlayersList=" + handsOfPlayersList +
                '}';
    }
}
