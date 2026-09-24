import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for LargeBoard class.
 * Tests one normal case and one bad-input case per public method as specified.
 */
public class LargeBoardTest {
    
    /**
     * Test: LargeBoard constructor creates a 3x3 board with null cells.
     * Normal case.
     */
    @Test
    void testLargeBoardConstructor() {
        LargeBoard board = new LargeBoard();
        assertNull(board.getCell(0, 0));
        assertNull(board.getCell(2, 2));
    }
    
    /**
     * Test: claimSpot places a marker on the large board.
     * Normal case.
     */
    @Test
    void testClaimSpotNormal() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 0, "X");
        assertEquals("X", board.getCell(0, 0));
    }
    
    /**
     * Test: claimSpot overwrites a previously claimed spot.
     * Bad input case: overwriting.
     */
    @Test
    void testClaimSpotOverwrite() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(1, 1, "X");
        board.claimSpot(1, 1, "O");
        assertEquals("O", board.getCell(1, 1));
    }
    
    /**
     * Test: getCell returns null for unclaimed cell.
     * Normal case.
     */
    @Test
    void testGetCellEmpty() {
        LargeBoard board = new LargeBoard();
        assertNull(board.getCell(0, 0));
    }
    
    /**
     * Test: getCell returns marker after claim.
     * Normal case.
     */
    @Test
    void testGetCellClaimed() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(2, 2, "O");
        assertEquals("O", board.getCell(2, 2));
    }
    
    /**
     * Test: isWinner returns true for X horizontal win (top row).
     * Normal case.
     */
    @Test
    void testIsWinnerHorizontalX() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 0, "X");
        board.claimSpot(0, 1, "X");
        board.claimSpot(0, 2, "X");
        assertTrue(board.isWinner("X"));
    }
    
    /**
     * Test: isWinner returns false for empty board.
     * Bad input case: no winner.
     */
    @Test
    void testIsWinnerEmpty() {
        LargeBoard board = new LargeBoard();
        assertFalse(board.isWinner("X"));
        assertFalse(board.isWinner("O"));
    }
    
    /**
     * Test: isWinner returns true for O vertical win (middle column).
     * Normal case.
     */
    @Test
    void testIsWinnerVerticalO() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 1, "O");
        board.claimSpot(1, 1, "O");
        board.claimSpot(2, 1, "O");
        assertTrue(board.isWinner("O"));
    }
    
    /**
     * Test: isWinner returns true for X diagonal win.
     * Normal case.
     */
    @Test
    void testIsWinnerDiagonalX() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 0, "X");
        board.claimSpot(1, 1, "X");
        board.claimSpot(2, 2, "X");
        assertTrue(board.isWinner("X"));
    }
    
    /**
     * Test: isWinner returns false when checking for wrong marker.
     * Bad input case: wrong marker.
     */
    @Test
    void testIsWinnerWrongMarker() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 0, "O");
        board.claimSpot(0, 1, "O");
        board.claimSpot(0, 2, "O");
        assertFalse(board.isWinner("X"));
    }
    
    /**
     * Test: displayBoard does not crash on empty board.
     * Normal case.
     */
    @Test
    void testDisplayBoardEmpty() {
        LargeBoard board = new LargeBoard();
        board.displayBoard();
    }
    
    /**
     * Test: displayBoard does not crash on full board.
     * Normal case.
     */
    @Test
    void testDisplayBoardFull() {
        LargeBoard board = new LargeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.claimSpot(row, col, "X");
            }
        }
        board.displayBoard();
    }
    
    /**
     * Test: isSpotOpen returns true for unclaimed cell.
     * Normal case.
     */
    @Test
    void testIsSpotOpenTrue() {
        LargeBoard board = new LargeBoard();
        assertTrue(board.isSpotOpen(0, 0));
    }
    
    /**
     * Test: isSpotOpen returns false for claimed cell.
     * Bad input case: claimed cell.
     */
    @Test
    void testIsSpotOpenFalse() {
        LargeBoard board = new LargeBoard();
        board.claimSpot(0, 0, "X");
        assertFalse(board.isSpotOpen(0, 0));
    }
    
    /**
     * Test: openSpots returns true when unclaimed cells exist.
     * Normal case.
     */
    @Test
    void testOpenSpotsTrue() {
        LargeBoard board = new LargeBoard();
        assertTrue(board.openSpots());
    }
    
    /**
     * Test: openSpots returns false when all 9 cells are claimed.
     * Bad input case: full board.
     */
    @Test
    void testOpenSpotsFalse() {
        LargeBoard board = new LargeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.claimSpot(row, col, "X");
            }
        }
        assertFalse(board.openSpots());
    }
}