package utils.config;

import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class ConfigWriter extends ConfigManager {
    private static ConfigWriter writerMaster;

    private FileWriter writer;

    private ConfigWriter() {
        initializeWriter();
    }

    private void initializeWriter() {
        try {
            writer = new FileWriter(PATH_TO_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ConfigWriter getInstance() {
        if (writerMaster == null) {
            writerMaster = new ConfigWriter();
        }

        return writerMaster;
    }

    public static void writeToMetaDataFile(GameMetaData gameMetaData) {
        getInstance().writeToMetaDataFileByInstance(gameMetaData);
    }

    private void writeToMetaDataFileByInstance(GameMetaData gameMetaData) {
        try {
            writer.write(gson.toJson(gameMetaData));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
