/**
 *  Manages player and computer
 * 
 *  @author prvuk
 *  @version Sep 12, 2026
 */
import java.util.Random;
import java.util.Scanner;

public class Orchestator {
   public static void main(String[] args) {
       
       Scanner input = new Scanner(System.in);
       
       //getting name + creating Player object
       System.out.println("Please input your name to begin the game.");
       String playerName = input.nextLine();
       Player player = new Player(playerName);
       
       //setting Player object marker - player always gets first pick
       System.out.println("Please select a marker, either O or X");
       String playerMarker = input.nextLine().trim().toUpperCase();
       
       //validates marker
       while(!playerMarker.equals("O") && !playerMarker.equals("X")) {
           System.out.println("Please choose a valid marker. It must be either O or X.");
           playerMarker = input.nextLine().trim().toUpperCase();
       }
       player.setMarker(playerMarker);
       
       //assigns computer a marker that is not players
       Computer computer = new Computer();
       String computerMarker = "X";
       if(playerMarker.equals("X")) {
           computerMarker = "O";
       }
       computer.setMarker(computerMarker);
       
       //---------------------Player and Computer now have assigned markers-----------------------
       SmallBoard[][] smallBoards = new SmallBoard[3][3];
       for(int row = 0; row < 3; row++) {
           for(int col = 0; col<3; col++) {
               //each cell in smallBoards is a instaniated as a SmallBoard object
               smallBoards[row][col] = new SmallBoard();
           }
       }
       
       String[][] largeBoard = new String[3][3];
       //----------------all boards are now declared and instantiated----------------
       
       //choose who goes first and print statement
       if(determineFirstPicker()) {
           System.out.println(player.getName() + " goes first.");
           
       }else {
           System.out.println("Computer goes first.");
       }
       //-------------Who goes first is now chosen---------------------
       
       String overallWinner = null;
       outerLoop:
       for(int bigRow = 0; bigRow< 3; bigRow++) {
           for(int bigCol = 0; bigCol < 3; bigCol++) {
             
               //printing status of boards
               SmallBoard board = smallBoards[bigRow][bigCol];
               System.out.println("Playing small board (" + bigRow + ", " + bigCol + ") ");
               board.displayBoard();
               
               while(!board.isOWinner() && !board.isXWinner() && board.openSpots()) {
                   
                   if(isPlayerTurn()) {
                       playerTurnStatement();
                       int[] move = getValidPlayerMove(input, board);
                       board.makeMove(playerMarker, move[0], move[1]);
                   }else {
                       computerTurnStatement();
                       int[] move = computerRowColChoice(board);
                       System.out.println("Computer plays (" + move[0] + ", " + move[1] + ")");
                       board.makeMove(computerMarker, move[0], move[1]);
                   }
                   board.displayBoard();
                   isPlayerTurn =! isPlayerTurn;
               }
               
               
             //record the result of this small board on the large board
               if (board.isXWinner()) {
                   largeBoard[bigRow][bigCol] = "X";
                   System.out.println("X wins small board (" + bigRow + ", " + bigCol + ")!");
               } else if (board.isOWinner()) {
                   largeBoard[bigRow][bigCol] = "O";
                   System.out.println("O wins small board (" + bigRow + ", " + bigCol + ")!");
               } else {
                   largeBoard[bigRow][bigCol] = "T";
                   System.out.println("Small board (" + bigRow + ", " + bigCol + ") tied.");
               }

               displayLargeBoard(largeBoard);

               //check the large board for a winner after every small game
               if (checkLargeBoardWinner(largeBoard, "X")) {
                   overallWinner = "X";
                   break outerLoop;
               } else if (checkLargeBoardWinner(largeBoard, "O")) {
                   overallWinner = "O";
                   break outerLoop;
               }
           }
       }

       //announce final result
       if (overallWinner == null) {
           System.out.println("\nThe large board ended in a tie!");
       } else if (overallWinner.equals(playerMarker)) {
           System.out.println("\nCongratulations " + player.getName() + ", you won the whole match!");
       } else {
           System.out.println("\nComputer won the whole match!");
       }

       input.close();
       
   }
    /**
     * Random choice of who goes first.
     *true if player goes first, false if computer goes first
     * @return boolean
     */
    public static boolean determineFirstPicker() {
        //randomly choose who goes first
        Random r = new Random();
        int randomChoice = r.nextInt(2); //either 0 or 1
        //0 means Player
        //1 means Computer
        if(randomChoice == 0) {
            return true;
        }
        return false;
    }
    public static void playerTurnStatement() {
        System.out.println("Your turn! Place your marker on a open spot.");
    }
    public static void ComputerTurnStatement() {
        System.out.println("Computer turn.");
    }
    
    public static int[] getValidPlayerMove(Scanner input, SmallBoard board) {
        int row;
        int col;
        while(true) {
            System.out.println("Input row (0-2): ");
            row = input.nextInt();
            
            System.out.println("Input col (0-2): ");
            col = input.nextInt();
            
            if(row < 0 || row >2 || col < 0 || col > 2) {
                System.out.println("Row and column must both be between 0 and 2.");
            }else if(!board.isSpotOpen(row, col)) {
                System.out.println("That spot is taken. Choose another.");
                
            }else {
                break;
            }
        }
        return new int[] {row, col};
    }
    //computer makes a row, col choice on random
    //limit: must choose an open spot. 
    public static int[] computerRowColChoice(SmallBoard board) {
        ArrayList<int[]> openCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isSpotOpen(row, col)) {
                    openCells.add(new int[]{row, col});
                }
            }
        }
        Random r = new Random();
        return openCells.get(r.nextInt(openCells.size()));
    }
    public static void displayLargeBoard(String[][] largeBoard) {
        System.out.println("\nLarge board:");
        for (int row = 0; row < 3; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < 3; col++) {
                String cell = largeBoard[row][col];
                line.append(cell == null ? "-" : cell).append(" ");
            }
            System.out.println(line.toString().trim());
        }
    }
 
    /**
     * Checks whether the given marker has 3 in a row on the large board.
     */
    public static boolean checkLargeBoardWinner(String[][] largeBoard, String marker) {
        for (int row = 0; row < 3; row++) {
            if (marker.equals(largeBoard[row][0]) && marker.equals(largeBoard[row][1])
                    && marker.equals(largeBoard[row][2])) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (marker.equals(largeBoard[0][col]) && marker.equals(largeBoard[1][col])
                    && marker.equals(largeBoard[2][col])) {
                return true;
            }
        }
        if (marker.equals(largeBoard[0][0]) && marker.equals(largeBoard[1][1])
                && marker.equals(largeBoard[2][2])) {
            return true;
        }
        if (marker.equals(largeBoard[0][2]) && marker.equals(largeBoard[1][1])
                && marker.equals(largeBoard[2][0])) {
            return true;
        }
        return false;
    }
    
    
   
}
