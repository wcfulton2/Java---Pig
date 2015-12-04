public interface diceInterface {

    void roll(); //roll the dice

    void setSize(int size); //manipulate the size of the dice

    int getValue(); //get the rolled value

    boolean checkLoss(int loss); //check for a losing roll

}
