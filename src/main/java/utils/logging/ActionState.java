package utils.logging;

public enum ActionState {
    SUCCESSFUL("(successful)"),
    UNSUCCESSFUL("(unsuccessful)");

    private String stateString;

    private ActionState(String stateString) {
        this.stateString = stateString;
    }

    @Override
    public String toString() {
        return stateString;
    }
}
