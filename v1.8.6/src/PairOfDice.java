import java.util.*;

public class PairOfDice implements diceInterface {

    private int size = 6;
    private int randomTotal = 0;
    private int roll1;
    private int roll2;


    public PairOfDice() {
    }

    public PairOfDice(int size) {
        this.size = size;
    }

    @Override
    public void roll() {
        Random rng = new Random();

        roll1 = rng.nextInt(size) + 1;
        roll2 = rng.nextInt(size) + 1;

        System.out.printf("Your rolls are %d and %d%n", roll1, roll2);

        randomTotal = roll1 + roll2;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getValue() {
        return randomTotal;
    }

    @Override
    public boolean checkLoss(int loss) {
        if (roll1 == loss || roll2 == loss) {
            return false;
        }
        return true;
    }


}
