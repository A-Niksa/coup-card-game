package logic.models.bots;

import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.*;
import logic.models.actions.specialactions.AssassinationAction;
import logic.models.actions.specialactions.CoupAction;
import logic.models.actions.specialactions.ExchangeAction;
import logic.models.bots.botutils.PossibleActionsUtils;
import logic.models.bots.botutils.RandomActionsUtils;
import utils.config.PlayerIdentifier;

import java.util.ArrayList;

public class CautiousBot extends Bot {
    public CautiousBot(int playerIndex, String name) {
        super(PlayerIdentifier.CAUTIOUS, playerIndex, name);
    }

    @Override
    public void playNormalAction(ActionsStack stack) {
        if (numberOfCoins >= 10) {
            Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

            CoupAction coup = new CoupAction(this, targetPlayer);

            return;
        }

        if (hasCard(CardIdentifier.ASSASSIN)) {
            if (numberOfCoins >= 3) {
                assassinateRandomCard(stack);
            } else {
                // will acquire 1 coin from the treasury
                NormalAction incomeAcquisition = new NormalAction(ActionIdentifier.INCOME_ACQUISITION, null,
                        this, null);
                stack.addToStack(incomeAcquisition);
            }
        } else {
            if (hasCard(CardIdentifier.AMBASSADOR)) {
                tryToExchangeWithAssassin(stack);
            } else {
                if (numberOfCoins >= 1) {
                    NormalAction cardsSwap = new NormalAction(ActionIdentifier.CARD_SWAP, null, this,
                            null);
                    stack.addToStack(cardsSwap);
                } else {
                    // will acquire 1 coin from the treasury
                    NormalAction incomeAcquisition = new NormalAction(ActionIdentifier.INCOME_ACQUISITION, null,
                            this, null);
                    stack.addToStack(incomeAcquisition);
                }

            }
        }
    }

    private void assassinateRandomCard(ActionsStack stack) {
        Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

        AssassinationAction assassination = new AssassinationAction(this, targetPlayer);
        stack.addToStack(assassination);
    }

    private void tryToExchangeWithAssassin(ActionsStack stack) {
        ArrayList<Card> selectedCardsList = new ArrayList<>();

        ArrayList<Card> currentCardsOfPlayerList = hand.getCardsList();
        ArrayList<Card> exchangeCardsList = GameState.getExchangeCardsFromDeck(this);

        Card possibleAssassinCard = returnCardIfInList(CardIdentifier.ASSASSIN, exchangeCardsList);
        if (possibleAssassinCard != null) {
            selectedCardsList.add(possibleAssassinCard);

            if (currentCardsOfPlayerList.size() == 2) {
                selectedCardsList.add(getCardOtherThanAmbassador());
            }
        } else {
            selectedCardsList = new ArrayList<>(currentCardsOfPlayerList); // shallow-copying the current cards list
        }

        ExchangeAction exchange = new ExchangeAction(this, selectedCardsList);
        stack.addToStack(exchange);
    }

    private Card getCardOtherThanAmbassador() {
        for (Card card : hand.getCardsList()) {
            if (card.getIdentifier() != CardIdentifier.AMBASSADOR) {
                return card;
            }
        }

        return hand.getCardsList().get(0);
    }

    private Card returnCardIfInList(CardIdentifier identifier, ArrayList<Card> cardsList) {
        for (Card card : cardsList) {
            if (card.getIdentifier() == identifier) {
                return card;
            }
        }

        return null;
    }

    private boolean hasCard(CardIdentifier identifier) {
        ArrayList<Card> cardsList = hand.getCardsList();

        for (Card card : cardsList) {
            if (card.getIdentifier() == identifier) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void playCounterAction(ActionsStack stack) {
        // does nothing
    }

    @Override
    public void challenge(ActionsStack stack) {
        if (RandomActionsUtils.getRandomBoolean(randomGenerator)) {
            boolean thereHaveBeenTooManyChallenges = PossibleActionsUtils.thereHaveAlreadyBeenTooManyChallenges(stack);

            if (!thereHaveBeenTooManyChallenges) {
                ArrayList<Action> challengableActionsList =
                        PossibleActionsUtils.getListOfChallengeableActions(stack, this);

                if (challengableActionsList.isEmpty()) {
                    return;
                }

                int randomIndex = randomGenerator.nextInt(challengableActionsList.size());
                Action actionToChallenge = challengableActionsList.get(randomIndex);

                Challenge challenge = new Challenge(this, actionToChallenge.getActionPlayer(),
                        actionToChallenge);
                stack.addToStack(challenge);
            }
        }
    }
}