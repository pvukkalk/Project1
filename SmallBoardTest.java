import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for SmallBoard class.
 * Tests one normal case and one bad-input case per public method as specified.
 */
public class SmallBoardTest {
    
    /**
     * Test: SmallBoard constructor creates a 3x3 board with null cells.
     * Normal case.
     */
    @Test
    public void testSmallBoardConstructor() {
        SmallBoard board = new SmallBoard();
        assertNull(board.getCell(0, 0));
        assertNull(board.getCell(2, 2));
    }
    
    /**
     * Test: displayBoard does not crash on empty board.
     * Normal case (output goes to stdout).
     */
    @Test
    public void testDisplayBoardEmpty() {
        SmallBoard board = new SmallBoard();
        // Should not throw
        board.displayBoard();
    }
    
    /**
     * Test: displayBoard does not crash on full board.
     * Normal case.
     */
    @Test
    public void testDisplayBoardFull() {
        SmallBoard board = new SmallBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.makeMove("X", row, col);
            }
        }
        // Should not throw
        board.displayBoard();
    }
    
    /**
     * Test: getCell returns null for empty cell.
     * Normal case.
     */
    @Test
    public void testGetCellEmpty() {
        SmallBoard board = new SmallBoard();
        assertNull(board.getCell(1, 1));
    }
    
    /**
     * Test: getCell returns marker after move.
     * Normal case.
     */
    @Test
    public void testGetCellFilled() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 0, 0);
        assertEquals("X", board.getCell(0, 0));
    }
    
    /**
     * Test: isXWinner returns false on empty board.
     * Normal case.
     */
    @Test
    public void testIsXWinnerEmpty() {
        SmallBoard board = new SmallBoard();
        assertFalse(board.isXWinner());
    }
    
    /**
     * Test: isXWinner returns true when X wins (horizontal).
     * Normal case.
     */
    @Test
    public void testIsXWinnerHorizontal() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 0, 0);
        board.makeMove("X", 0, 1);
        board.makeMove("X", 0, 2);
        assertTrue(board.isXWinner());
    }
    
    /**
     * Test: isXWinner returns false when O wins instead.
     * Bad input case: wrong marker.
     */
    @Test
    public void testIsXWinnerButOWon() {
        SmallBoard board = new SmallBoard();
        board.makeMove("O", 1, 0);
        board.makeMove("O", 1, 1);
        board.makeMove("O", 1, 2);
        assertFalse(board.isXWinner());
    }
    
    /**
     * Test: isOWinner returns false on empty board.
     * Normal case.
     */
    @Test
    public void testIsOWinnerEmpty() {
        SmallBoard board = new SmallBoard();
        assertFalse(board.isOWinner());
    }
    
    /**
     * Test: isOWinner returns true when O wins (vertical).
     * Normal case.
     */
    @Test
    public void testIsOWinnerVertical() {
        SmallBoard board = new SmallBoard();
        board.makeMove("O", 0, 1);
        board.makeMove("O", 1, 1);
        board.makeMove("O", 2, 1);
        assertTrue(board.isOWinner());
    }
    
    /**
     * Test: isOWinner returns false when X wins instead.
     * Bad input case: wrong marker.
     */
    @Test
    public void testIsOWinnerButXWon() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 0, 0);
        board.makeMove("X", 1, 1);
        board.makeMove("X", 2, 2);
        assertFalse(board.isOWinner());
    }
    
    /**
     * Test: isWinner returns true for X diagonal win.
     * Normal case.
     */
    @Test
    public void testIsWinnerXDiagonal() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 0, 0);
        board.makeMove("X", 1, 1);
        board.makeMove("X", 2, 2);
        assertTrue(board.isWinner("X"));
    }
    
    /**
     * Test: isWinner returns false for empty board.
     * Bad input case: no winner.
     */
    @Test
    public void testIsWinnerEmpty() {
        SmallBoard board = new SmallBoard();
        assertFalse(board.isWinner("X"));
        assertFalse(board.isWinner("O"));
    }
    
    /**
     * Test: makeMove places marker at given coordinates.
     * Normal case.
     */
    @Test
    public void testMakeMoveNormal() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 1, 2);
        assertEquals("X", board.getCell(1, 2));
    }
    
    /**
     * Test: makeMove overwrites existing marker (spec says it doesn't check).
     * Bad input case: occupied cell.
     */
    @Test
    public void testMakeMoveOverwrite() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 0, 0);
        board.makeMove("O", 0, 0);
        assertEquals("O", board.getCell(0, 0));
    }
    
    /**
     * Test: openSpots returns true when empty cells exist.
     * Normal case.
     */
    @Test
    public void testOpenSpotsTrue() {
        SmallBoard board = new SmallBoard();
        assertTrue(board.openSpots());
    }
    
    /**
     * Test: openSpots returns false when all 9 cells are filled.
     * Bad input case: full board.
     */
    @Test
    public void testOpenSpotsFalse() {
        SmallBoard board = new SmallBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.makeMove("X", row, col);
            }
        }
        assertFalse(board.openSpots());
    }
    
    /**
     * Test: isSpotOpen returns true for empty cell.
     * Normal case.
     */
    @Test
    public void testIsSpotOpenTrue() {
        SmallBoard board = new SmallBoard();
        assertTrue(board.isSpotOpen(0, 0));
    }
    
    /**
     * Test: isSpotOpen returns false for occupied cell.
     * Bad input case: occupied cell.
     */
    @Test
    public void testIsSpotOpenFalse() {
        SmallBoard board = new SmallBoard();
        board.makeMove("X", 2, 2);
        assertFalse(board.isSpotOpen(2, 2));
    }
}