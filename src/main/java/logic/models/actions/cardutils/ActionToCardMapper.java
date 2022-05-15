package logic.models.actions.cardutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;

public class ActionToCardMapper {
    public static void mapActionToCard(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier,
                                       Player actionPlayer, Player targetPlayer) {
        switch (cardIdentifier) {
            case AMBASSADOR:
                // note that exchange is handled separately since it requires selection of desired cards
                if (actionIdentifier == ActionIdentifier.EXTORTION_COUNTER) {
                    AmbassadorActions.blockExtortion();
                }
                break;

            case ASSASSIN:
                if (actionIdentifier == ActionIdentifier.ASSASSINATION) {
                    AssassinActions.assassinate(targetPlayer);
                }
                break;

            case CONTESSA:
                if (actionIdentifier == ActionIdentifier.ASSASSINATION_COUNTER) {
                    ContessaActions.blockAssassination();
                }
                break;

            case CAPTAIN:
                if (actionIdentifier == ActionIdentifier.EXTORTION) {
                    CaptainActions.extort(actionPlayer, targetPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTORTION_COUNTER) {
                    CaptainActions.blockExtortion();
                }
                break;

            case DUKE:
                if (actionIdentifier == ActionIdentifier.TAXATION) {
                    DukeActions.getTax(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER) {
                    DukeActions.blockExternalHelp();
                }
                break;

            default:
                if (actionIdentifier == ActionIdentifier.INCOME_ACQUISITION) {
                    GeneralActions.acquireIncome(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                    GeneralActions.acquireIncome(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.COUP) {
                    GeneralActions.acquireIncome(actionPlayer);
                }
        }
    }
}
