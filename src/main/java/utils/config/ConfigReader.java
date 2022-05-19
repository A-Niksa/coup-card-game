package utils.config;

import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigReader extends ConfigManager {
    private static ConfigReader readerMaster;

    private JsonReader reader;

    private ConfigReader() {
        initializeReader();
    }

    private void initializeReader() {
        try {
            FileReader fileReader = new FileReader(PATH_TO_FILE);
            reader = new JsonReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ConfigReader getInstance() {
        if (readerMaster == null) {
            readerMaster = new ConfigReader();
        }

        return readerMaster;
    }

    public static GameMetaData readFromMetaDataFile() {
        return getInstance().readFromMetaDataFileByInstance();
    }

    private GameMetaData readFromMetaDataFileByInstance() {
        // TODO: possible IllegalStateException
        GameMetaData gameMetaData = gson.fromJson(reader, GameMetaData.class);
        return gameMetaData;
    }
}