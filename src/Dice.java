import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int diceCount;
    int minDiceNumber = 1;
    int maxDiceNumber = 6;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int totalSum = 0;

        for (int i = 0; i < diceCount; i++) {
            totalSum += ThreadLocalRandom.current().nextInt(minDiceNumber, maxDiceNumber + 1);
            System.out.println("The dice number is: " + totalSum);
        }
        return totalSum;
    }
}
