import java.util.*;
import javax.swing.JOptionPane;
public class JavaApplication3 {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
      playPig();
    }
    
    public static int play(String name, int maxScore, int loseATurn, int sizeOfDice, int numberOfDice, int score){
         boolean repeat = true;
         String goAgain = "";
         
         System.out.println(name + "'s turn");
         do{
            int randomTotal = 0;         
                        
            Random generator = new Random(); //RNG object
            
                StringBuilder sb = new StringBuilder("Your rolls are: ");
                
                
                for(int i = 0; i < numberOfDice; i++){
                  int roll = generator.nextInt(sizeOfDice) + 1;
                  sb.append(roll + " ");
                  randomTotal += roll;
                  
                  if(roll == loseATurn){
                     
                     //System.out.println("You rolled a " + loseATurn + " , your turn is over and you scored 0 points");
                     System.out.println("End of " + name + "'s turn\n\n");
                     JOptionPane.showMessageDialog(null, name + " you rolled a " + loseATurn + " , your turn is over and you scored 0 points");
                     return 0;

                  }//end if

                }// end for
                
                
                System.out.println(sb);
                
                              
            score += randomTotal; //compile score
            
            if(score >= maxScore){ //check for winning score and return
                return score;
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
        return score; //return score
    }


    private static int getPlayerNumber(int playerNumber, String[] names) {

           return ((playerNumber+1) % names.length);
    }
    
        
    private static int getIntInRange(int min, int max, String name){
      boolean valid = false;
      int input = 0;
      
      do{
         
         try{
            System.out.println("Enter " + name + " between " + min + " and " + max);
            input = sc.nextInt();
            
            if(input >= min && input <= max){
               valid = true;
            } //end if
         
            else{
               System.out.println("ERROR!");               
            } //end else
         } //end try
         catch (InputMismatchException e){
            System.out.println("Error, numbers only");
            sc.nextLine();
         } //end catch
      }while(!valid); //end do
            
       return input;
   }
   
   private static String[] getNames(int numOfPlayers){
   
   String[] names = new String[numOfPlayers];
   sc.nextLine();
   
   for(int i = 0; i < names.length; i++){
         System.out.println("Enter the name of player " + (i+1) + ":");
         names[i] = sc.nextLine();
         }
      
      return names;
   }
   
   private static void playPig(){
        int numOfPlayers = 0;
        int loseATurn = 0;
        int numberOfDice = 0;
        int maxScore = 0;
        int sizeOfDice = 0;     
        int playerNumber = 0; //starting player number
        int winningScore = 0;
        String winnerName = "";
        

        numOfPlayers = getIntInRange(1, 3, "number of players");
        sizeOfDice = getIntInRange(6, 20, "size of dice");
        numberOfDice = getIntInRange(1, 2, "number of dice");
        maxScore = getIntInRange(1, 250, "max score");
        loseATurn = getIntInRange(1, sizeOfDice, "lose a turn roll");


          
         String[] names = new String[numOfPlayers];
         names = getNames(numOfPlayers);

         
                  
         
         int[] scores = new int[numOfPlayers];

               for(int i = 0; scores[playerNumber] < maxScore; i++){
                  scores[playerNumber] += play(names[playerNumber], maxScore, loseATurn, sizeOfDice, numberOfDice, scores[playerNumber]);
                  //displayScores(scores, names);
                  if(scores[playerNumber] >= maxScore){ //check for score and terminate upon reaching winning score
                    winnerName = names[playerNumber];
                    winningScore = scores[playerNumber];
                    break;
                  }
                  else{
                     playerNumber = getPlayerNumber(playerNumber, names);
                  }
               }
                             
        System.out.println("The Winner is " + winnerName + " with " + winningScore + " points."); //output winner name and score.
   }
   
   // private static void displayScores(int[] scores, String[] names){
//       if(scores[0] == 0){
//          return;
//       }//end if
//       else{
//       StringBuilder displayScores = new StringBuilder();
//          for(int i = 0; i < names.length; i++){
//             displayScores.append(names[i] + "\t\t" + scores[i] + "\n");
//          }
//          JOptionPane.showMessageDialog(null, displayScores);
//          return;
//       }
//    }
}
    
