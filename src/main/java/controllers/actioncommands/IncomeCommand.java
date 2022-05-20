package controllers.actioncommands;

import logic.models.actions.ActionIdentifier;

public class IncomeCommand extends Command {
    public IncomeCommand() {
        super(ActionIdentifier.INCOME_ACQUISITION);
    }
}
