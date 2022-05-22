package logic.game;

import gui.game.GamePanel;
import logic.game.tools.iteration.PlayerIterable;
import logic.game.tools.iteration.PlayerIterator;
import logic.models.Player;
import logic.models.actions.ActionsStack;
import utils.logging.GameStateHistory;
import utils.logging.GameStateIdentifier;

import java.util.ArrayList;

public class GameRunner implements Runnable {
    private GamePanel gamePanel;
    private int currentTurnIndex;
    private int currentTurnInStageIndex; // index of player from whom an action is expected
    private Player currentPlayer;
    private ActionsStack stack;

    public GameRunner(GamePanel gamePanel) {
        GameStateBuilder.createNewGameState();
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (!GameState.gameHasEnded()) { // each iteration corresponds to a round (and each round has 3 stages)
            currentTurnIndex = GameState.getIndexOfCurrentPlayer();
            currentTurnInStageIndex = currentTurnIndex;
            currentPlayer = GameState.getCurrentPlayerFromTurnKeeper();

            stack = new ActionsStack();

            GameStateHistory.log(GameStateIdentifier.NORMAL_ACTIONS, currentPlayer.getPlayerIdentifier());
            gamePanel.updateNotifiers();

            if (currentTurnIndex != 0) { // in this case, the current player is a bot
                suspendProgram(2000);
            }

            currentPlayer.playNormalAction(stack);
            gamePanel.updateNotifiers();

            askPlayersIfTheyWantToPlayCounterAction();
            askPlayersIfTheyWantToChallenge();

            stack.resolveStack(gamePanel);

            gamePanel.updatePanel();
            GameState.advanceTurnInTurnKeeper();
        }

        gamePanel.finishGameAndShowWinner();
    }

    private void askPlayersIfTheyWantToPlayCounterAction() {
        // asks all players except the current player
        PlayerIterable partialIterable = getNewIterableForAllExceptCurrentPlayer();

        for (Player player : partialIterable) {
            if (!player.hasLost()) {
                GameStateHistory.log(GameStateIdentifier.COUNTER_ACTIONS, player.getPlayerIdentifier());
                gamePanel.updateNotifiers();

                currentTurnInStageIndex = player.getPlayerIndex();

                suspendProgram(2000);

                player.playCounterAction(stack);

                gamePanel.updateNotifiers();
            }
        }
    }

    private void askPlayersIfTheyWantToChallenge() {
        // asks all players
        PlayerIterable iterable = getNewIterableForAll();

        for (Player player : iterable) {
            if (!player.hasLost()) {
                GameStateHistory.log(GameStateIdentifier.CHALLENGES, player.getPlayerIdentifier());
                gamePanel.updateNotifiers();

                currentTurnInStageIndex = player.getPlayerIndex();

                if (player.getPlayerIndex() != 0) {
                    suspendProgram(2000);
                }

                player.challenge(stack);

                gamePanel.updateNotifiers();
            }
        }
    }

    private PlayerIterable getNewIterableForAll() {
        ArrayList<Player> playersList = GameState.getPlayersList();

        return new PlayerIterable(currentTurnIndex, playersList, PlayerIterator.IterationType.ALL_PLAYERS);
    }

    private PlayerIterable getNewIterableForAllExceptCurrentPlayer() {
        ArrayList<Player> playersList = GameState.getPlayersList();

        return new PlayerIterable(currentTurnIndex, playersList,
                PlayerIterator.IterationType.ALL_PLAYERS_EXCEPT_CURRENT_PLAYER);
    }

    private void suspendProgram(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ActionsStack getStack() {
        return stack;
    }

    public int getCurrentTurnIndex() {
        return currentTurnIndex;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentTurnInStageIndex() {
        return currentTurnInStageIndex;
    }

    public void setCurrentTurnInStageIndex(int currentTurnInStageIndex) {
        this.currentTurnInStageIndex = currentTurnInStageIndex;
    }
}