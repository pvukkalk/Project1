import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Drives the game - runs 9 small boards and tracks the large board.
 *
 * @author prvuk
 * @version Sep 16, 2026
 */
public class Orchestator {

    /**
     * Driver
     * 
     * @param args
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // getting name + creating Player object
        System.out.println("Please input your name to begin the game.");
        String playerName = input.nextLine();
        Player player = new Player(playerName);

        // setting Player object marker - player always gets first pick
        System.out.println("Please select a marker, either O or X");
        String playerMarker = input.nextLine().trim().toUpperCase();

        // validates marker
        while (!playerMarker.equals("O") && !playerMarker.equals("X")) {
            System.out.println(
                "Please choose a valid marker. It must be either O or X.");
            playerMarker = input.nextLine().trim().toUpperCase();
        }
        player.setMarker(playerMarker);

        // assigns computer a marker that is not players
        Computer computer = new Computer();
        String computerMarker = "X";
        if (playerMarker.equals("X")) {
            computerMarker = "O";
        }
        computer.setMarker(computerMarker);

        // ---------------------Player and Computer now have assigned
        // markers-----------------------
        SmallBoard[][] smallBoards = new SmallBoard[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // each cell in smallBoards is instantiated as a SmallBoard
                // object
                smallBoards[row][col] = new SmallBoard();
            }
        }

        LargeBoard largeBoard = new LargeBoard();
        // ----------------all boards are now declared and
        // instantiated----------------

        // choose who goes first and print statement
        boolean isPlayerTurn = determineFirstPicker();
        if (isPlayerTurn) {
            System.out.println(player.getName() + " goes first.");
        }
        else {
            System.out.println("Computer goes first.");
        }
        // -------------Who goes first is now chosen---------------------

        String overallWinner = null;
        outerLoop: for (int bigRow = 0; bigRow < 3; bigRow++) {
            for (int bigCol = 0; bigCol < 3; bigCol++) {

                // printing status of boards
                SmallBoard board = smallBoards[bigRow][bigCol];
                System.out.println("Playing small board (" + bigRow + ", "
                    + bigCol + ") ");
                board.displayBoard();

                while (!board.isOWinner() && !board.isXWinner() && board
                    .openSpots()) {

                    if (isPlayerTurn) {
                        playerTurnStatement();
                        int[] move = getValidPlayerMove(input, board);
                        board.makeMove(playerMarker, move[0], move[1]);
                    }
                    else {
                        computerTurnStatement();
                        int[] move = computerRowColChoice(board);
                        System.out.println("Computer plays (" + move[0] + ", "
                            + move[1] + ")");
                        board.makeMove(computerMarker, move[0], move[1]);
                    }
                    board.displayBoard();
                    isPlayerTurn = !isPlayerTurn;
                }

                // record the result of this small board on the large board
                if (board.isXWinner()) {
                    largeBoard.claimSpot(bigRow, bigCol, "X");
                    System.out.println("X wins small board (" + bigRow + ", "
                        + bigCol + ")!");
                }
                else if (board.isOWinner()) {
                    largeBoard.claimSpot(bigRow, bigCol, "O");
                    System.out.println("O wins small board (" + bigRow + ", "
                        + bigCol + ")!");
                }
                else {
                    // board filled with no winner - break the tie with RPS
                    String tieWinner = RPS.resolveTie(input, player, computer);
                    largeBoard.claimSpot(bigRow, bigCol, tieWinner);
                    String tieWinnerName = tieWinner.equals(playerMarker)
                        ? player.getName()
                        : "Computer";
                    System.out.println(tieWinnerName + " claims small board ("
                        + bigRow + ", " + bigCol + ") via tiebreaker!");
                }

                largeBoard.displayBoard();

                // check the large board for a winner after every small game
                if (largeBoard.isWinner("X")) {
                    overallWinner = "X";
                    break outerLoop;
                }
                else if (largeBoard.isWinner("O")) {
                    overallWinner = "O";
                    break outerLoop;
                }
            }
        }

        // announce final result
        if (overallWinner == null) {
            System.out.println("\nThe large board ended in a tie!");
        }
        else if (overallWinner.equals(playerMarker)) {
            System.out.println("\nCongratulations " + player.getName()
                + ", you won the whole match!");
        }
        else {
            System.out.println("\nComputer won the whole match!");
        }

        input.close();
    }


    /**
     * Random choice of who goes first.
     * true if player goes first, false if computer goes first
     * 
     * @return boolean
     */
    public static boolean determineFirstPicker() {
        Random r = new Random();
        int randomChoice = r.nextInt(2); // either 0 or 1
        // 0 means Player, 1 means Computer
        return randomChoice == 0;
    }


    public static void playerTurnStatement() {
        System.out.println("Your turn! Place your marker on a open spot.");
    }


    public static void computerTurnStatement() {
        System.out.println("Computer turn.");
    }


    public static int[] getValidPlayerMove(Scanner input, SmallBoard board) {
        int row;
        int col;
        while (true) {
            System.out.println("Input row (0-2): ");
            row = input.nextInt();

            System.out.println("Input col (0-2): ");
            col = input.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println(
                    "Row and column must both be between 0 and 2.");
            }
            else if (!board.isSpotOpen(row, col)) {
                System.out.println("That spot is taken. Choose another.");
            }
            else {
                break;
            }
        }
        return new int[] { row, col };
    }


    // computer makes a row, col choice on random
    // limit: must choose an open spot.
    public static int[] computerRowColChoice(SmallBoard board) {
        ArrayList<int[]> openCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isSpotOpen(row, col)) {
                    openCells.add(new int[] { row, col });
                }
            }
        }
        Random r = new Random();
        return openCells.get(r.nextInt(openCells.size()));
    }
}
