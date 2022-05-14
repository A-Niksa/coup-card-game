package utils.config;

public enum PlayerIdentifier {
    HUMAN("Human"),

    CAUTIOUS("Cautious Bot"),
    COUP_LOVING("Coup-loving Bot"),
    PARANOID("Paranoid Bot"),
    RANDOM("Random Bot");

    private String identifierString;

    private PlayerIdentifier(String identifierString) {
        this.identifierString = identifierString;
    }

    @Override
    public String toString() {
        return identifierString;
    }
}