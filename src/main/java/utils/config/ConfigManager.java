package utils.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigManager {
    protected final String PATH_TO_FILE = "src/main/resources/config/metadata.json";

    protected GsonBuilder builder;
    protected Gson gson;

    public ConfigManager() {
        builder = new GsonBuilder();
        gson = builder.create();
    }
}