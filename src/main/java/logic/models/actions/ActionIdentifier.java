package logic.models.actions;

public enum ActionIdentifier {
    INCOME_ACQUISITION("Income Acquisition"),
    EXTERNAL_HELP_REQUEST("External Help Request"),
    COUP("Coup"),
    TAXATION("Taxation"),
    ASSASSINATION("Assassination"),
    EXTORTION("Extortion"),
    EXCHANGE("Exchange"),

    EXTERNAL_HELP_REQUEST_COUNTER("External Help Blockage"),
    ASSASSINATION_COUNTER("Assassination Blockage"),
    EXTORTION_COUNTER("Extortion Blockage"),

    CHALLENGE("Challenge"),
    NOTHING("Passed Action Turn");

    private final String identifierString;

    private ActionIdentifier(String identifierString) {
        this.identifierString = identifierString;
    }

    @Override
    public String toString() {
        return identifierString;
    }
}
