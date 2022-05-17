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

            // note that the ASSASSIN case is handled separately by AssassinationAction

            default:
                if (actionIdentifier == ActionIdentifier.INCOME_ACQUISITION) {
                    GeneralActions.acquireIncome(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                    GeneralActions.requestExternalHelp(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.SWAP_CARD) {
                    GeneralActions.swapPlayerCardRandomly(actionPlayer);
                }
                // coup is not included here since it is to be handled separately
        }
    }
}
