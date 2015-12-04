import java.util.*;
public class JavaApplication3 {
    
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
           
        int numOfPlayers = 0;
        int loseATurn = 0;
        int numberOfDice = 0;
        int maxScore = 0;
        int sizeOfDice = 0;     
        int playerNumber = 0; //starting player number
        int winningScore = 0;
        String winnerName = "";         
         
         System.out.println("Enter the number of players 1-3");
         numOfPlayers = sc.nextInt();
         
         while(numOfPlayers < 1 || numOfPlayers > 3){
            System.out.println("You must enter a number of players between 1 and 3!");
            System.out.println("Enter the number of players 1-3");
            numOfPlayers = sc.nextInt();
            
         }
         
         String[] names = new String[numOfPlayers];
         int[] scores = new int[numOfPlayers];
         sc.nextLine();
         
         for(int i = 0; i < names.length; i++){
            
            System.out.println("Enter the name of player " + (i+1) + ":");
            names[i] = sc.nextLine();
            
         }
        
         System.out.println("Enter a score you wish to play to 1-250");
         maxScore = sc.nextInt();
         
         System.out.println("Enter the size of the dice 6-20");
         sizeOfDice = sc.nextInt();
         
         System.out.println("Enter a 'lose a turn' roll number between 1 and your chose dice size");
         loseATurn = sc.nextInt();
         
         System.out.println("Enter the number of dice you wish to play with: 1 or 2");
         numberOfDice = sc.nextInt();
         sc.nextLine();       

               for(int i = 0; scores[playerNumber] < maxScore; i++){
                  scores[playerNumber] += play(names[playerNumber], maxScore, loseATurn, sizeOfDice, numberOfDice, scores[playerNumber]);
                  
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
    
    public static int play(String name, int maxScore, int loseATurn, int sizeOfDice, int numberOfDice, int score){
         boolean repeat = true;
         String goAgain = "";
         do{
            int randomTotal = 0;         
                        
            Random generator = new Random(); //RNG object
            
            if(numberOfDice == 2){
               int randomNumber1 = generator.nextInt(sizeOfDice) + 1;
               int randomNumber2 = generator.nextInt(sizeOfDice) + 1;
               randomTotal = (randomNumber1 + randomNumber2);
               System.out.println(name + " your rolls are: " + randomNumber1 + ", " + randomNumber2);
               
               if(randomNumber1 == loseATurn || randomNumber2 == loseATurn){
                   System.out.println("You rolled a " + loseATurn + " , your turn is over and you scored 0 points");
                   return 0;
               }
            }
            else{
               int randomNumber1 = generator.nextInt(sizeOfDice) + 1;
               randomTotal = randomNumber1;
               System.out.println(name + " your roll is " + randomNumber1);
                              
               if(randomNumber1 == loseATurn){
                   System.out.println("You rolled a " + loseATurn + " , your turn is over and you scored 0 points");
                   return 0;
               }
            }
                              
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
    

}