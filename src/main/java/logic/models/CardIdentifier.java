package logic.models;

public enum CardIdentifier {
    AMBASSADOR("Ambassador"),
    ASSASSIN("Assassin"),
    CAPTAIN("Captain"),
    CONTESSA("Contessa"),
    DUKE("Duke");

    private String identifierString;

    private CardIdentifier(String identifierString) {
        this.identifierString = identifierString;
    }

    @Override
    public String toString() {
        return identifierString;
    }
}
