package utils.config;

import logic.models.CardIdentifier;
import logic.models.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigProcessor {
    private static ConfigProcessor processor;

    private ConfigProcessor() {

    }

    private static ConfigProcessor getInstance() {
        if (processor == null) {
            processor = new ConfigProcessor();
        }

        return processor;
    }

    public static void saveBotsToConfig(ArrayList<String> botNamesList) {
        ArrayList<PlayerIdentifier> identifiersOfBotsList = getInstance().mapBotNameListToIdentifierList(botNamesList);

        GameMetaData gameMetaData = ConfigReader.readFromMetaDataFile();
        ArrayList<HandMetaData> handsOfPlayersList = gameMetaData.getHandsOfPlayersList();

        GameMetaData newGameMetaData = createNewGameMetaData(identifiersOfBotsList, handsOfPlayersList);
        ConfigWriter.writeToMetaDataFile(newGameMetaData);
    }

    private static GameMetaData createNewGameMetaData(ArrayList<PlayerIdentifier> identifiersOfBotsList,
                                               ArrayList<HandMetaData> handsOfPlayersList) {
        GameMetaData newGameMetaData = new GameMetaData();
        newGameMetaData.setPlayersList(identifiersOfBotsList);
        newGameMetaData.setHandsOfPlayersList(handsOfPlayersList);

        return newGameMetaData;
    }

    private ArrayList<PlayerIdentifier> mapBotNameListToIdentifierList(ArrayList<String> botNamesList) {
        ArrayList<PlayerIdentifier> identifiersOfBotsList = new ArrayList<>();

        for (String botName : botNamesList) {
            identifiersOfBotsList.add(mapBotNameToIdentifier(botName));
        }

        return identifiersOfBotsList;
    }

    private PlayerIdentifier mapBotNameToIdentifier(String name) {
        if (name.equals("Cautious Bot")) {
            return PlayerIdentifier.CAUTIOUS;
        } else if (name.equals("Coup-loving Bot")) {
            return PlayerIdentifier.COUP_LOVING;
        } else if (name.equals("Paranoid Bot")) {
            return PlayerIdentifier.PARANOID;
        } else if (name.equals("Random Bot")) {
            return PlayerIdentifier.RANDOM;
        }

        return null;
    }

    public static ArrayList<PlayerIdentifier> getIdentifiersOfSelectedBots() {
        GameMetaData gameMetaData = ConfigReader.readFromMetaDataFile();
        return getInstance().getBotsList(gameMetaData);
    }

    private ArrayList<PlayerIdentifier> getBotsList(GameMetaData gameMetaData) {
        ArrayList<PlayerIdentifier> playersList = gameMetaData.getPlayersList();

        ArrayList<PlayerIdentifier> botsList = new ArrayList<>();
        for (PlayerIdentifier identifier : playersList) {
            if (identifier != PlayerIdentifier.HUMAN) {
                botsList.add(identifier);
            }
        }

        return botsList;
    }

    public static ArrayList<CardIdentifier> getCardIdentifiersOfPlayer(Player player) {
        GameMetaData gameMetaData = ConfigReader.readFromMetaDataFile();
        return getInstance().getPlayerCardsList(gameMetaData, player);
    }

    private ArrayList<CardIdentifier> getPlayerCardsList(GameMetaData gameMetaData, Player player) {
        ArrayList<HandMetaData> handsOfPlayersList = gameMetaData.getHandsOfPlayersList();

        for (HandMetaData handMetaData : handsOfPlayersList) {
            if (handMetaData.getPlayerIdentifier() == player.getPlayerIdentifier()) {
                return handMetaData.getCardsList();
            }
        }

        return null;
    }
}