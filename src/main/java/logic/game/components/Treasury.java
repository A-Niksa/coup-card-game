package logic.game.components;

public class Treasury {
    private int numberOfCoins;

    public Treasury() {
        numberOfCoins = 50; // initial number of coins
    }

    public int requestCoins(int numberOfRequestedCoins) {
        if (numberOfCoins - numberOfRequestedCoins >= 0) { // checking to see if the treasury has enough money
            numberOfCoins -= numberOfRequestedCoins;
            return numberOfRequestedCoins;
        }

        return 0; // the request was not possible in this case
    }

    public void returnCoins(int numberOfCoinsToReturn) {
        numberOfCoins += numberOfCoinsToReturn;
    }
}
