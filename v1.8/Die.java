import java.util.*;
public class Die implements diceInterface{
    
    private int size = 6;
    private int roll;
    Random rng = new Random();
    
    public Die() {
    }
    public Die(int size){
        this.size = size;
    }
    @Override
    public void roll() {
        roll = rng.nextInt(size) + 1;
        System.out.printf("Your roll is %d%n", roll);      
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getValue() {
        return roll;
    
    }

    @Override
    public boolean checkLoss(int loss) {
        if(roll == loss){
            return false;
        }
        return true;
    }
    
}