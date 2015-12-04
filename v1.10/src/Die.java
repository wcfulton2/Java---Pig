import java.util.Random;

public class Die implements diceInterface {

    private int size = 6; //default dice size
    private int roll;
    private Random rng = new Random(); //new random object

    public Die() { //default constructor
    }

    public Die(int size) {
        this.size = size;
    } //constructor modifying size of dice

    @Override
    public void roll() { //roll method
        roll = rng.nextInt(size) + 1; //get random number based on dice size
        System.out.printf("Your roll is %d%n", roll); //output the roll
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    } //size of dice mutator

    @Override
    public int getValue() { //roll accessor
        return roll;

    }

    @Override
    public boolean checkLoss(int loss) { //check for a losing roll and return the status of the check
        if (roll == loss) {
            return true;
        }
        return false;
    }
}