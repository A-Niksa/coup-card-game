package logic.models;

public class Card {
    private CardIdentifier identifier;

    public Card(CardIdentifier identifier) {
        this.identifier = identifier;
    }

    public CardIdentifier getIdentifier() {
        return identifier;
    }
}