/**
 *  
 * Manages a single 3x3 tic-tac-toe board
 * 
 *  @author prvuk
 *  @version Sep 16, 2026
 */
public class SmallBoard {
    //~ Fields ................................................................
    private String[][] board;
    
    
    //~ Constructors ..........................................................
    /**
     * Creates a SmallBoard object
     */
    public SmallBoard() {
        this.board = new String[3][3];
    }
    
    
    //~Public  Methods ........................................................
    /**
     * prints the board 
     * 
     */
    public void displayBoard() {
        
        //useful to the user when choosing a spot to place their marker
        System.out.println("    0 1 2");
        
        for(int row = 0; row < 3; row++) {
            System.out.print(row + "   ");
            for(int col =0; col < 3; col++) {
                if(board[row][col]==(null)) {
                    System.out.print("-" + " ");
                }else {
                    System.out.print(board[row][col]+" ");
                }
            }
            System.out.println();

        }
    }
    
   
    /**
     * returns value at specified cell
     * @param row
     * @param col
     * @return value(a string)
     */
    public String getCell(int row, int col) {
        return board[row][col];
    }
    
    
    /**
     *Checks whether X has three in a row anywhere on this board.
     * @return true if X has won this board
     */
    public boolean isXWinner() {
        return isWinner("X");
    }
    
    /**
     *Checks whether O has three in a row anywhere on this board.
     * @return true if O has won this board
     */
    public boolean isOWinner() {
        return isWinner("O");
    }
    
    
    
    /**
     * Checks whether the given marker has three in a row on this board
     * @param marker "X" or "O"
     * @return true if marker has won this board
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
     * Place a marker at the given spot. Does not check if the spot is already taken. 
     * 
     * @param marker the marker to place ("X" or "O")
     * @param moveRow row index (0-2)
     * @param moveCol column inden (0-2)
     *  
     */
    public void makeMove(String marker, int moveRow, int moveCol){
        board[moveRow][moveCol] = marker;
    }
    
    /**
     * Checks whether this board has at least one open (unplayed) spot.
     * @return true if any cell is still null
     */
    public  boolean openSpots() {
        boolean result = false;
        for(int row = 0; row < 3; row ++) {
            for(int col = 0; col < 3; col++) {
                if(board[row][col]==(null)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
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
    

    
}

