import java.util.Random;

public class PairOfDice implements diceInterface {

    private int size = 6;
    private int randomTotal = 0; //total value of the pair of dice
    private int roll1;
    private int roll2;
    private Random rng = new Random();


    public PairOfDice() { //default constructor
    }

    public PairOfDice(int size) { //constructor modifying the size of the dice
        this.size = size;
    }

    @Override
    public void roll() { //roll the dice
        roll1 = rng.nextInt(size) + 1; //dice 1
        roll2 = rng.nextInt(size) + 1; //dice 2

        System.out.printf("Your rolls are %d and %d%n", roll1, roll2); //output rolls

        randomTotal = roll1 + roll2; //combine values of both dice
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    } //size of dice mutator

    @Override
    public int getValue() {
        return randomTotal;
    } //rolled value accessor

    @Override
    public boolean checkLoss(int loss) { //check for a losing roll
        if (roll1 == loss || roll2 == loss) { //check both dice
            return true;
        }
        return false;
    }
}
