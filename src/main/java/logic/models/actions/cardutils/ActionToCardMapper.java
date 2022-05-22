package logic.models.actions.cardutils;

import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;

public class ActionToCardMapper {
    public static void mapActionToCard(ActionIdentifier actionIdentifier, CardIdentifier cardIdentifier,
                                       Player actionPlayer, Player targetPlayer) {
        if (cardIdentifier == null) {
            if (actionIdentifier == ActionIdentifier.INCOME_ACQUISITION) {
                GeneralActions.acquireIncome(actionPlayer);
            } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                GeneralActions.requestExternalHelp(actionPlayer);
            } else if (actionIdentifier == ActionIdentifier.CARD_SWAP) {
                GeneralActions.swapPlayerCardRandomly(actionPlayer);
            }

            return;
        }

        switch (cardIdentifier) {
            case AMBASSADOR:
                // note that exchange is handled separately since it requires selection of desired cards
                if (actionIdentifier == ActionIdentifier.EXTORTION_COUNTER) {
                    AmbassadorActions.blockExtortion();
                }
                break;

            case ASSASSIN:
                if (actionIdentifier == ActionIdentifier.ASSASSINATION) {
                    AssassinActions.assassinate(actionPlayer, targetPlayer);
                }

            case CONTESSA:
                if (actionIdentifier == ActionIdentifier.ASSASSINATION_COUNTER) {
                    ContessaActions.blockAssassination(actionPlayer, targetPlayer);
                }
                break;

            case CAPTAIN:
                if (actionIdentifier == ActionIdentifier.EXTORTION_COUNTER) {
                    CaptainActions.blockExtortion(actionPlayer);
                } // extortion is handled separately. so it has not been listed here
                break;

            case DUKE:
                if (actionIdentifier == ActionIdentifier.TAXATION) {
                    DukeActions.getTax(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST_COUNTER) {
                    DukeActions.blockExternalHelp(actionPlayer);
                }
                break;

            // note that the ASSASSIN case is handled separately by AssassinationAction

            default:
                if (actionIdentifier == ActionIdentifier.INCOME_ACQUISITION) {
                    GeneralActions.acquireIncome(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.EXTERNAL_HELP_REQUEST) {
                    GeneralActions.requestExternalHelp(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.CARD_SWAP) {
                    GeneralActions.swapPlayerCardRandomly(actionPlayer);
                } else if (actionIdentifier == ActionIdentifier.COUP) {
                    GeneralActions.attemptCoup(actionPlayer, targetPlayer);
                }
        }
    }
}
