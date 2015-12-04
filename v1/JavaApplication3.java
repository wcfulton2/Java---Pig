package javaapplication3;
import java.util.*;
public class JavaApplication3 {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String player1 = "";
        String player2 = "";
        String goAgain = "";
        int playerOneScore = 0;
        int playerTwoScore = 0;
        int playerNumber = 1; //starting player number
        int winningScore = 0;
        boolean repeat = true; //for use in the playerOne and playerTwo loops
        String winnerName = "";
        
        System.out.println("Enter player 1 name");
        player1 = sc.nextLine();
        System.out.println("Enter player 2 name");
        player2 = sc.nextLine();
        
        
        
        do{
            playerOneScore = playerOne(player1, goAgain, playerOneScore, repeat); //playerOne rolling
            
            if(playerOneScore >= 20){ //check for score and terminate upon reaching winning score
              winnerName = player1;
              winningScore = playerOneScore;
              break;
            }

          playerTwoScore = playerOne(player2, goAgain, playerTwoScore, repeat); //playerTwo rolling
          

          if(playerTwoScore >=20){ //check for score and termination upon win.
              winnerName = player2;
              winningScore = playerTwoScore;
              break;
          }
          playerNumber = getPlayerNumber(playerNumber); //switch between player 1 and player 2
          
         

        }while(playerOneScore < 20 && playerTwoScore < 20); //repeat play and winner check until winner acheived.
                
        System.out.println("The Winner is " + winnerName + " with " + winningScore + " points."); //output winner name and score.
        

        //System.out.println("p1: " + playerOneScore + " p2: " + playerTwoScore); //debug
    
    }
    
    public static int playerOne(String player1, String goAgain, int playerOneScore, boolean repeat){
        do{
            
            int roll = 0;
            Random generator = new Random(); //RNG object
            int randomNumber = generator.nextInt(6) + 1; //assigning random number to randomNumber variable
            System.out.println(player1 + " your roll is " + randomNumber); //output the roll
            roll = randomNumber;
            
                if(roll == 1){ //check for a end-of-roll roll and return
                    System.out.println("You rolled a 1, your turn is over and you scored 0 points");
                    return 0;
                    
                }
                
            playerOneScore += roll; //compile score
            
            if(playerOneScore >= 20){ //check for winning score and return
                return playerOneScore;
            }
            System.out.println("Do you want to roll again?"); //prompt for additional rolls
            goAgain = sc.nextLine();
            goAgain = goAgain.toLowerCase();
            
            
            if(goAgain.equals("y")){
                repeat = true;
            }
            else{
                repeat = false;
            }
        }while(repeat == true); //repeat rolls until something other than "y" is entered.
        return playerOneScore; //return score
    }
        
//     public static int playerTwo(String player2, String goAgain, int playerTwoScore, boolean repeat){
//         do{
//             int roll = 0;
//             Random generator = new Random();
//             int randomNumber = generator.nextInt(6) + 1;
//             System.out.println(player2 + " your roll is " + randomNumber);
//             roll = randomNumber;
//             
//             if(roll == 1){
//                 System.out.println("You rolled a 1, your turn is over and you scored 0 points");
//                 return 0;
//             }
//             playerTwoScore += roll;
//                 
//             if(playerTwoScore >= 20){
//                 return playerTwoScore;
//             }
//             System.out.println("Do you want to roll again?");
//             goAgain = sc.nextLine();
//             goAgain = goAgain.toLowerCase();
//             
//             if(goAgain.equals("y")){
//                 repeat = true;
//             }
//             else{
//                 repeat = false;
//             }
//         }while(repeat == true);
//         return playerTwoScore;
//     }

    private static int getPlayerNumber(int playerNumber) {
        if(playerNumber == 1){ //if 1 then 2
            playerNumber = 2;
        }
        else{ //if 2 then 1
            playerNumber = 1;
        }
        
        return playerNumber; //return number 
    }
}