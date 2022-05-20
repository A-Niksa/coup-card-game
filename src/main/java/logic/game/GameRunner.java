package logic.game;

import gui.game.GamePanel;
import logic.game.tools.iteration.PlayerIterable;
import logic.game.tools.iteration.PlayerIterator;
import logic.models.Player;
import logic.models.actions.ActionsStack;

import java.util.ArrayList;

public class GameRunner implements Runnable {
    private GamePanel gamePanel;
    private ActionsStack stack;

    public GameRunner(GamePanel gamePanel) {
        GameStateBuilder.createNewGameState();
        this.gamePanel = gamePanel;
    }

    public void run() {
        while (!GameState.gameHasEnded()) { // each iteration corresponds to a round (and each round has 3 stages)
            int currentTurnIndex = GameState.getIndexOfCurrentPlayer();
            Player currentPlayer = GameState.getCurrentPlayerFromTurnKeeper();

            stack = new ActionsStack();

            if (currentTurnIndex != 0) { // in this case, the current player is a bot
                suspendProgram(800);
            }

            currentPlayer.playNormalAction(stack);
            gamePanel.updatePanel();

            askPlayersIfTheyWantToPlayCounterAction(currentTurnIndex, stack);
            askPlayersIfTheyWantToChallenge(currentTurnIndex, stack);

            stack.resolveStack(gamePanel);

            GameState.advanceTurnInTurnKeeper();
        }

        gamePanel.finishGameAndShowWinner();
    }

    private void askPlayersIfTheyWantToPlayCounterAction(int currentTurnIndex, ActionsStack stack) {
        // asks all players except the current player
        PlayerIterable partialIterable = getNewIterableForAllExceptCurrentPlayer(currentTurnIndex);

        for (Player player : partialIterable) {
            if (!player.hasLost()) {
                System.out.println("*" + player.getPlayerIdentifier());
                suspendProgram(800);
                player.playCounterAction(stack);

                gamePanel.updatePanel();
            }
        }
    }

    private void askPlayersIfTheyWantToChallenge(int currentTurnIndex, ActionsStack stack) {
        // asks all players
        PlayerIterable iterable = getNewIterableForAll(currentTurnIndex);

        for (Player player : iterable) {
            if (!player.hasLost()) {
                System.out.println("**" + player.getPlayerIdentifier());
                suspendProgram(800);
                player.challenge(stack);

                gamePanel.updatePanel();
            }
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
}