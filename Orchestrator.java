import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Drives the game - runs 9 small boards and tracks the large board.
 *
 * @author prvuk, Blake Adkins
 * @version Sep 21, 2026
 */
public class Orchestrator {

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

        // setting Player object marker
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
        while (largeBoard.openSpots()) {
                // The player who is about to move selects the next small board.
                largeBoard.displayBoard();
                int bigBoardRow;
                int bigBoardCol;
                if (isPlayerTurn) {
                    System.out.println(player.getName()
                        + ", choose an open spot on the large board.");
                    int[] bigBoardSpot = getValidPlayerMoveBigBoard(input,
                        largeBoard);
                    bigBoardRow = bigBoardSpot[0];
                    bigBoardCol = bigBoardSpot[1];
                }
                else {
                    int[] bigBoardSpot = computerRowColChoice(largeBoard);
                    bigBoardRow = bigBoardSpot[0];
                    bigBoardCol = bigBoardSpot[1];
                    System.out.println("Computer chooses large-board spot ("
                        + bigBoardRow + ", " + bigBoardCol + ").");
                }
                SmallBoard board = smallBoards[bigBoardRow][bigBoardCol];
                System.out.println("Playing small board (" + bigBoardRow + ", "
                    + bigBoardCol + ").");
                board.displayBoard();

                while (!board.isOWinner() && !board.isXWinner() && board
                    .openSpots()) {

                    if (isPlayerTurn) {
                        playerTurnStatement(player.getName());
                        int[] smallBoardSpot = getValidPlayerMove(input, board);
                        int smallBoardRow = smallBoardSpot[0];
                        int smallBoardCol = smallBoardSpot[1];
                        board.makeMove(playerMarker, smallBoardRow,
                            smallBoardCol);
                    }
                    else {
                        computerTurnStatement();
                        int[] smallBoardSpot = computerRowColChoice(board);
                        int smallBoardRow = smallBoardSpot[0];
                        int smallBoardCol = smallBoardSpot[1];
                        System.out.println("Computer plays (" + smallBoardRow
                            + ", " + smallBoardCol + ")");
                        board.makeMove(computerMarker, smallBoardRow,
                            smallBoardCol);
                    }
                    board.displayBoard();
                    isPlayerTurn = !isPlayerTurn;
                }

                // record the result of this small board on the large board
                if (board.isXWinner()) {
                    largeBoard.claimSpot(bigBoardRow, bigBoardCol, "X");
                    System.out.println("X wins small board (" + bigBoardRow
                        + ", " + bigBoardCol + ")!");
                }
                else if (board.isOWinner()) {
                    largeBoard.claimSpot(bigBoardRow, bigBoardCol, "O");
                    System.out.println("O wins small board (" + bigBoardRow
                        + ", " + bigBoardCol + ")!");
                }
                else {
                    // board filled with no winner - break the tie with RPS
                    String tieWinner = RPS.resolveTie(input, player, computer);
                    largeBoard.claimSpot(bigBoardRow, bigBoardCol, tieWinner);
                    String tieWinnerName = tieWinner.equals(playerMarker)
                        ? player.getName()
                        : "Computer";
                    System.out.println(tieWinnerName + " claims small board ("
                        + bigBoardRow + ", " + bigBoardCol
                        + ") via tiebreaker!");
                }

                largeBoard.displayBoard();

                // check the large board for a winner after every small game
                if (largeBoard.isWinner("X")) {
                    overallWinner = "X";
                    break;
                }
                else if (largeBoard.isWinner("O")) {
                    overallWinner = "O";
                    break;
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


    public static void playerTurnStatement(String playerName) {
        System.out.println(playerName
            + ", place your marker on an open spot in this small board.");
    }


    public static void computerTurnStatement() {
        System.out.println("Computer turn.");
    }

    public static int[] getValidPlayerMoveBigBoard(Scanner input,
        LargeBoard board) {
        int row;
        int col;
        while (true) {
            row = getCoordinate(input, "row");

            col = getCoordinate(input, "column");

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

    public static int[] getValidPlayerMove(Scanner input, SmallBoard board) {
        int row;
        int col;
        while (true) {
            row = getCoordinate(input, "row");

            col = getCoordinate(input, "column");

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

    /** Reads one board coordinate and rejects non-numeric input. */
    public static int getCoordinate(Scanner input, String coordinateName) {
        while (true) {
            System.out.println("Enter " + coordinateName + " (0-2):");
            if (input.hasNextInt()) {
                return input.nextInt();
            }
            System.out.println("Please enter a number from 0 to 2 for the "
                + coordinateName + ".");
            input.next();
        }
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

    /** Chooses an unclaimed spot on the large board at random. */
    public static int[] computerRowColChoice(LargeBoard board) {
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
