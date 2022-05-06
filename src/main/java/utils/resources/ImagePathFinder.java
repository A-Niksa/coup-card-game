package utils.resources;

class ImagePathFinder {
    private final String cardsPath;
    private final String componentsPath;
    private final String miscPath;
    private final String jpgSuffix;
    private final String pngSuffix;

    ImagePathFinder() {
        cardsPath = "images/cards/";
        componentsPath = "images/components/";
        miscPath = "images/misc/";
        jpgSuffix = ".jpg";
        pngSuffix = ".png";
    }

    public String getPartialPath(ImageIdentifier identifier) {
        switch (identifier) {
            case MENU_BACKGROUND:
                return formatPath(miscPath, "menubackground", jpgSuffix);
            case HELP:
                return formatPath(miscPath, "help", jpgSuffix);
            case LOGO:
                return formatPath(miscPath, "logo", pngSuffix);
            case COIN:
                return formatPath(componentsPath, "coin", pngSuffix);
            case AMBASSADOR:
                return formatPath(cardsPath, "ambassador", jpgSuffix);
            case ASSSASSIN:
                return formatPath(cardsPath, "assassin", jpgSuffix);
            case CAPTAIN:
                return formatPath(cardsPath, "captain", jpgSuffix);
            case CONTESSA:
                return formatPath(cardsPath, "contessa", jpgSuffix);
            case DUKE:
                return formatPath(cardsPath, "duke", jpgSuffix);
            case CARD_BACK:
                return formatPath(cardsPath, "cardback", jpgSuffix);
            default:
                return null;
        }
    }

    private String formatPath(String path, String fileName, String formatSuffix) {
        return path + fileName + formatSuffix;
    }
}