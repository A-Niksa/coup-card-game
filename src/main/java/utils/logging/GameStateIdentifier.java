package utils.logging;

public enum GameStateIdentifier {
    NORMAL_ACTIONS("normal actions"),
    COUNTER_ACTIONS("counter actions"),
    CHALLENGES("challenges");

    private String stateString;

    private GameStateIdentifier(String stateString) {
        this.stateString = stateString;
    }

    @Override
    public String toString() {
        return stateString;
    }
}
