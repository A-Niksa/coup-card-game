package utils.resources;

import java.awt.*;

public class ImageManager {
    private static ImageManager manager;

    private final String RESOURCES_PATH;
    private final ImagePathFinder pathFinder;
    private final ImageCache cache;

    private ImageManager() {
        RESOURCES_PATH = "src/main/resources/";

        pathFinder = new ImagePathFinder();
        cache = new ImageCache();
    }

    private static ImageManager getInstance() {
        if (manager == null) {
            manager = new ImageManager();
        }
        return manager;
    }

    public static Image getImage(ImageIdentifier identifier) {
        return getInstance().getImageByInstance(identifier);
    }

    private Image getImageByInstance(ImageIdentifier identifier) {
        ImageLoader imageLoader = (imageIdentifier) ->
                Toolkit.getDefaultToolkit().getImage(getFullPath(imageIdentifier));

        return cache.getImageFromCache(identifier, imageLoader);
    }

    private String getFullPath(ImageIdentifier identifier) {
        String partialPath = pathFinder.getPartialPath(identifier);
        return RESOURCES_PATH + partialPath;
    }
}