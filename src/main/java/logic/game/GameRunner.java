package logic.game;

import logic.game.tools.iteration.PlayerIterable;
import logic.game.tools.iteration.PlayerIterator;
import logic.models.Player;
import logic.models.actions.ActionsStack;

import java.util.ArrayList;

public class GameRunner {
    public GameRunner() {
        GameStateBuilder.createNewGameState();
    }

    public void run() {
        while (!GameState.gameHasEnded()) { // each iteration corresponds to a round
            int currentTurnIndex = GameState.getIndexOfCurrentPlayer();
            Player currentPlayer = GameState.getCurrentPlayerFromTurnKeeper();

            ActionsStack stack = new ActionsStack();
            currentPlayer.playNormalAction(stack);
            suspend(1000);

            askPlayersIfTheyWantToPlayCounterAction(currentTurnIndex, stack);
            askPlayersIfTheyWantToChallenge(currentTurnIndex, stack);

            GameState.advanceTurnInTurnKeeper();
        }
    }

    private void askPlayersIfTheyWantToPlayCounterAction(int currentTurnIndex, ActionsStack stack) {
        // asks all players except the current player
        PlayerIterable partialIterable = getNewIterableForAllExceptCurrentPlayer(currentTurnIndex);

        for (Player player : partialIterable) {
            player.playCounterAction(stack);
            suspend(1000);
        }
    }

    private void askPlayersIfTheyWantToChallenge(int currentTurnIndex, ActionsStack stack) {
        // asks all players
        PlayerIterable iterable = getNewIterableForAll(currentTurnIndex);

        for (Player player : iterable) {
            player.challenge(stack);
            suspend(1000);
        }
    }

    private PlayerIterable getNewIterableForAll(int currentTurnIndex) {
        ArrayList<Player> playersList = GameState.getPlayersList();

        return new PlayerIterable(currentTurnIndex, playersList, PlayerIterator.IterationType.ALL_PLAYERS);
    }

    private PlayerIterable getNewIterableForAllExceptCurrentPlayer(int currentTurnIndex) {
        ArrayList<Player> playersList = GameState.getPlayersList();

        return new PlayerIterable(currentTurnIndex, playersList,
                PlayerIterator.IterationType.ALL_PLAYERS_EXCEPT_CURRENT_PLAYER);
    }

    private void suspend(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}