import java.util.*;
import javax.swing.JOptionPane;

public class Pig {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        playPig();
    }

    private static void playPig() {
        int numOfPlayers;
        int loseATurn;
        int numberOfDice;
        int maxScore;
        int sizeOfDice;
        int playerNumber = 0; //starting player number
        int winningScore = 0;
        String winnerName = "";


        numOfPlayers = getIntInRange(2, 4, "number of players");
        sizeOfDice = getIntInRange(6, 20, "size of dice");
        numberOfDice = getIntInRange(1, 2, "number of dice");
        maxScore = getIntInRange((int) ((sizeOfDice * numberOfDice) * 1.5), 250, "max score");
        loseATurn = getIntInRange(1, sizeOfDice, "lose a turn roll");


        String[] names = enterNames(numOfPlayers);
        diceInterface d;

        if (numberOfDice == 1) {
            d = new Die(sizeOfDice);
        } else {
            d = new PairOfDice(sizeOfDice);
        }

        int[] scores = new int[numOfPlayers];

        while (scores[playerNumber] < maxScore) {
            scores[playerNumber] += play(names[playerNumber], maxScore, loseATurn, scores[playerNumber], d);

            if (scores[playerNumber] >= maxScore) { //check for score and terminate upon reaching winning score
                winnerName = names[playerNumber];
                winningScore = scores[playerNumber];
                break;
            } else {
                displayScores(scores, names);
                playerNumber = nextPlayer(playerNumber, names);
            }
        }

        //System.out.println("The Winner is " + winnerName + " with " + winningScore + " points."); //output winner name and score.
        JOptionPane.showMessageDialog(null, String.format("The winner is %s with %d points", winnerName, winningScore), "WINNER!", JOptionPane.INFORMATION_MESSAGE);
        displayScores(scores, names);
    }

    private static int getIntInRange(int min, int max, String name) {
        boolean valid = false;
        int input = 0;

        do {

            try {
                System.out.println("Enter " + name + " between " + min + " and " + max);
                input = sc.nextInt();

                if (input >= min && input <= max) {
                    valid = true;
                } //end if

                else {
                    System.out.println("ERROR!Only enter whole numbers between " + min + " and " + max);
                } //end else
            } //end try
            catch (InputMismatchException e) {
                System.out.println("Error, numbers only");
                sc.nextLine();
            } //end catch
        } while (!valid); //end do

        return input;
    }

    private static String[] enterNames(int numOfPlayers) {

        String[] names = new String[numOfPlayers];
        sc.nextLine();

        for (int i = 0; i < names.length; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ":");
            names[i] = sc.nextLine();
        }

        return names;
    }

    public static int play(String name, int maxScore, int loseATurn, int score, diceInterface d) {
        boolean repeat;
        String goAgain;
        int turnScore = 0;

        System.out.println("*******************");
        System.out.println(name + "'s turn");
        System.out.println("*******************");
        do {

            d.roll();


            if (!d.checkLoss(loseATurn)) {
                System.out.println("End of " + name + "'s turn\n\n");
                JOptionPane.showMessageDialog(null, name + " you rolled a " + loseATurn + " , your turn is over and you scored 0 points", "End of Turn", JOptionPane.ERROR_MESSAGE);
                return 0;
            }//end if

            turnScore += d.getValue();

            if ((score + turnScore) >= maxScore) { //check for winning score and return
                return turnScore;
            }
            System.out.println("Do you want to roll again? y or n"); //prompt for additional rolls
            goAgain = sc.nextLine();
            goAgain = goAgain.toLowerCase();

            repeat = goAgain.equals("y");
        } while (repeat); //repeat rolls until something other than "y" is entered.
        return turnScore; //return score
    }


    private static int nextPlayer(int playerNumber, String[] names) {

        return ((playerNumber + 1) % names.length);
    }

    private static void displayScores(int[] scores, String[] names) {
        StringBuilder displayScores = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            //displayScores.append(names[i] + ":\t\t" + scores[i] + "\n");
            displayScores.append(String.format("%s:%10d%n", names[i], scores[i]));

        }
        JOptionPane.showMessageDialog(null, displayScores, "Current Scores", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
