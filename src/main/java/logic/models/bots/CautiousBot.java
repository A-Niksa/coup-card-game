package logic.models.bots;

import logic.game.GameState;
import logic.models.Card;
import logic.models.CardIdentifier;
import logic.models.Player;
import logic.models.actions.ActionIdentifier;
import logic.models.actions.ActionsStack;
import logic.models.actions.NormalAction;
import logic.models.actions.cardutils.specialutils.AssassinationAction;
import logic.models.actions.cardutils.specialutils.CoupAction;
import logic.models.actions.cardutils.specialutils.ExchangeAction;
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

            Card targetCard = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
            CardIdentifier targetCardIdentifier = targetCard.getIdentifier();

            CoupAction coup = new CoupAction(this, targetPlayer, targetCardIdentifier);

            return;
        }

        if (hasCard(CardIdentifier.ASSASSIN)) {
            assassinateRandomCard(stack);
        } else {
            if (hasCard(CardIdentifier.AMBASSADOR)) {
                tryToExchangeWithAssassin(stack);
            } else {
                NormalAction cardsSwap = new NormalAction(ActionIdentifier.CARD_SWAP, null, this,
                        null);
                stack.addToStack(cardsSwap);
            }
        }
    }

    private void assassinateRandomCard(ActionsStack stack) {
        Player targetPlayer = RandomActionsUtils.getRandomPlayer(randomGenerator, this);

        Card targetCard = RandomActionsUtils.getRandomCardOfPlayer(randomGenerator, targetPlayer);
        CardIdentifier targetCardIdentifier = targetCard.getIdentifier();

        AssassinationAction assassination = new AssassinationAction(this, targetPlayer,
                targetCardIdentifier);
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
            selectedCardsList = currentCardsOfPlayerList;
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

        return null;
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
        // does nothing
    }
}