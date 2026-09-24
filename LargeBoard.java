/**
 * Manages the large 3x3 board of claimed small-board markers, and checks
 * for an overall winner.
 *
 * @author advayiyer
 * @version Sep 16, 2026
 */
public class LargeBoard {
    //~ Fields ................................................................
    private String[][] board;
 
    //~ Constructors ..........................................................
    /**
     * Creates a LargeBoard object
     */
    public LargeBoard() {
        this.board = new String[3][3];
    }
 
    //~ Public Methods ........................................................
    /**
     * Claims a spot on the large board with the given marker, used after
     * a small board is won (or a tie is broken via RPS).
     * @param row row index (0-2)
     * @param col column index (0-2)
     * @param marker the marker claiming this spot ("X" or "O")
     */
    public void claimSpot(int row, int col, String marker) {
        board[row][col] = marker;
    }
 
    /**
     * Returns the marker claiming the given spot, or null if unclaimed.
     * @param row row index (0-2)
     * @param col column index (0-2)
     * @return the marker at that spot, or null
     */
    public String getCell(int row, int col) {
        return board[row][col];
    }
 
    /**
     * Checks whether the given marker has three in a row on the large board.
     * @param marker "X" or "O"
     * @return true if marker has won the large board
     */
    public boolean isWinner(String marker) {
        for (int row = 0; row < 3; row++) {
            if (marker.equals(board[row][0]) && marker.equals(board[row][1])
                    && marker.equals(board[row][2])) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (marker.equals(board[0][col]) && marker.equals(board[1][col])
                    && marker.equals(board[2][col])) {
                return true;
            }
        }
        if (marker.equals(board[0][0]) && marker.equals(board[1][1]) && marker.equals(board[2][2])) {
            return true;
        }
        if (marker.equals(board[0][2]) && marker.equals(board[1][1]) && marker.equals(board[2][0])) {
            return true;
        }
        return false;
    }
 
 
    /**
     * Prints the large board.
     */
    public void displayBoard() {
        System.out.println("\nLarge board:");
        System.out.println("  0 1 2");
        for (int row = 0; row < 3; row++) {
            StringBuilder line = new StringBuilder(row + " ");
            for (int col = 0; col < 3; col++) {
                String cell = board[row][col];
                line.append(cell == null ? "-" : cell).append(" ");
            }
            System.out.println(line.toString().trim());
        }
    }

    /**
     * Checks whether a specific spot is open.
     * @param row row index (0-2)
     * @param col column index (0-2)
     * @return true if that cell is null
     */
    public boolean isSpotOpen(int row, int col) {
        return board[row][col] == null;
    }

    /**
     * Checks whether at least one small-board spot remains unclaimed.
     * @return true if an unclaimed large-board spot exists
     */
    public boolean openSpots() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (isSpotOpen(row, col)) {
                    return true;
                }
            }
        }
        return false;
    }
}
