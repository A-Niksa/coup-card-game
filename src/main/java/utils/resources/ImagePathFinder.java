package utils.resources;

class ImagePathFinder {
    private final String cardsPath;
    private final String miscPath;
    private final String jpgSuffix;
    private final String pngSuffix;

    ImagePathFinder() {
        cardsPath = "images/cards/";
        miscPath = "images/misc/";
        jpgSuffix = ".jpg";
        pngSuffix = ".png";
    }

    public String getPartialPath(ImageIdentifier identifier) {
        switch (identifier) {
            case MENU_BACKGROUND:
                return formatPath(miscPath, "menubackground", jpgSuffix);
            case GAME_BACKGROUND:
                return formatPath(miscPath, "gamebackground", jpgSuffix);
            case HELP:
                return formatPath(miscPath, "help", jpgSuffix);
            case LOGO:
                return formatPath(miscPath, "logo", pngSuffix);
            case PAUSE_ICON:
                return formatPath(miscPath, "pauseicon", pngSuffix);
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
            case CARDBACK_VERTICAL_UTD:
                return formatPath(cardsPath, "cardback_vertical_utd", pngSuffix);
            case CARDBACK_HORIZONTAL_RTL:
                return formatPath(cardsPath, "cardback_horizontal_rtl", pngSuffix);
            case CARDBACK_HORIZONTAL_LTR:
                return formatPath(cardsPath, "cardback_horizontal_ltr", pngSuffix);
            default:
                return null;
        }
    }

    private String formatPath(String path, String fileName, String formatSuffix) {
        return path + fileName + formatSuffix;
    }
}