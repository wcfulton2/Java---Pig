import java.util.Scanner;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Pig {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        playPig();
    } //main - playPig call to trigger gameplay

    /*
        playPig function acquires user input, instantiates the dice objects, player name and score arrays,
        compiles the player scores, and checks for a winner.
    */
    private static void playPig() {
        int numOfPlayers;
        int loseATurn; //what roll will cause a lost turn
        int numberOfDice;
        int maxScore; //what score to play to
        int sizeOfDice; //number of sides
        int playerNumber = 0; //starting player number
        int winningScore = 0;
        String winnerName = "";


        numOfPlayers = getIntInRange(2, 4, "number of players"); //2 -4 players
        sizeOfDice = getIntInRange(6, 20, "size of dice"); //6-20 sided dice
        numberOfDice = getIntInRange(1, 2, "number of dice"); //1-2 dice
        maxScore = getIntInRange((int) ((sizeOfDice * numberOfDice) * 1.5), 250, "max score"); //max score is scaled by dice size and number of dice up to a max of 250
        loseATurn = getIntInRange(1, sizeOfDice, "lose a turn roll"); //1 - the size of the dice


        String[] names = enterNames(numOfPlayers); //array of player names
        diceInterface d; //null dice object

        if (numberOfDice == 1) {
            d = new Die(sizeOfDice); //instantiate d to new instance of Die if rolling 1 dice
        } else {
            d = new PairOfDice(sizeOfDice); //instantiate d to new instance of PairOfDice if rolling 2 dice
        }

        int[] scores = new int[numOfPlayers]; //array of scores, parallel to names array

        while (scores[playerNumber] < maxScore) { //while current players score is less than the max score
            scores[playerNumber] += play(names[playerNumber], maxScore, loseATurn, scores[playerNumber], d); //add the return value of the play function current player's score

            if (scores[playerNumber] >= maxScore) { //check current player's score against the maxScore and stop gameplay loop when a winner is achieved
                winnerName = names[playerNumber]; //set winnerName to the current player
                winningScore = scores[playerNumber]; //set winningScore to the current player's score
                break; //end the loop
            } else { //else move to the next player and continue play
                displayScores(scores, names); //display current scores at the end of every player's turn
                playerNumber = nextPlayer(playerNumber, names); //move to the next player
            }
        }

        JOptionPane.showMessageDialog(null, String.format("The winner is %s with %d points", winnerName, winningScore), "WINNER!", JOptionPane.INFORMATION_MESSAGE); //display the winner's name and score
        displayScores(scores, names); //display all scores at the end of the game
    }

    private static int getIntInRange(int min, int max, String name) { //validate an integer within a specified range
        boolean valid = false; //loop flag
        int input = 0;

        do { //start validation loop
            try { //start try/catch
                System.out.println("Enter " + name + " between " + min + " and " + max); //prompt for input in range
                input = sc.nextInt(); //get input

                if (input >= min && input <= max) { //if input is between the required min and max, change flag to true
                    valid = true;
                }
                else { //else display an error
                    System.out.println("ERROR! Only enter whole numbers between " + min + " and " + max);
                } //end else
            } //end try
            catch (InputMismatchException e) { //catch input mismatch exceptions to reject non-numeric input
                System.out.println("Error! Enter numbers only"); //display error
                sc.nextLine(); //clear input stream buffer
            } //end catch
        } while (!valid); //loop until the input is valid

        return input; //return the valid input
    }

    private static String[] enterNames(int numOfPlayers) { //populate the names array with player names

        String[] names = new String[numOfPlayers]; //instantiate new local array
        sc.nextLine(); //clear input stream buffer

        for (int i = 0; i < names.length; i++) { //ask for player names until array is full
            System.out.println("Enter the name of player " + (i + 1) + ":"); //ask for input
            names[i] = sc.nextLine(); //put input in array at loop index, i
        }

        return names; //return the names array
    }

   /*
        The play function consists of the dice rolling and a preliminary winning score check
   */
    public static int play(String name, int maxScore, int loseATurn, int score, diceInterface d) {
        String goAgain;
        int turnScore = 0; //score of this turn

        System.out.println("*******************");
        System.out.println(name + "'s turn");       // display who's turn it is
        System.out.println("*******************");

        do { //start rolling loop

            d.roll(); //roll the dice

            if (d.checkLoss(loseATurn)) { //check for a loss of turn, and display dialog to denote the loss
                System.out.println("End of " + name + "'s turn\n\n");
                JOptionPane.showMessageDialog(null, name + " you rolled a " + loseATurn + " , your turn is over and you scored 0 points", "End of Turn", JOptionPane.ERROR_MESSAGE);
                return 0; //0 the players score for the turn if they rolled a losing roll
            }//end if

            turnScore += d.getValue(); //if not a loss, add the dice values to the player's turn score

            if ((score + turnScore) >= maxScore) { //check for winning score and return the current turns score
                return turnScore;
            }
            System.out.println("Do you want to roll again? y or n"); //prompt for additional rolls
            goAgain = sc.nextLine();

        } while (goAgain.toLowerCase().startsWith("y")); //repeat rolls until something other than "y" is entered
        return turnScore; //return the current turns score
    }


    private static int nextPlayer(int playerNumber, String[] names) { //Compute who the next player is

        return ((playerNumber + 1) % names.length);  //return the index of the next player in line
    }

    private static void displayScores(int[] scores, String[] names) { //display all player's current scores
        StringBuilder displayScores = new StringBuilder(); // new instance of StringBuilder
        for (int i = 0; i < names.length; i++) { //loop through names and scores
            displayScores.append(String.format("%s:%10d%n", names[i], scores[i])); // append a formatted string to the StringBuilder
        }
        JOptionPane.showMessageDialog(null, displayScores, "Current Scores", JOptionPane.INFORMATION_MESSAGE); //display the StringBuilder
    }
}
    
