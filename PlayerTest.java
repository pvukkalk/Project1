import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test classfor Player class.
 * Tests one normal case and one bad-input case per public method.
 */
public class PlayerTest {
    
    /**
     * Test: Player constructor creates a player with a name.
     * Normal case.
     */
    @Test
    void testPlayerConstructorNormal() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
    }
    
    /**
     * Test: Player constructor with empty string.
     * Bad input case.
     */
    @Test
    void testPlayerConstructorEmpty() {
        Player player = new Player("");
        assertEquals("", player.getName());
    }
    
    /**
     * Test: setMarker sets marker to "X".
     * Normal case.
     */
    @Test
    void testSetMarkerX() {
        Player player = new Player("Bob");
        player.setMarker("X");
        assertEquals("X", player.getMarker());
    }
    
    /**
     * Test: setMarker sets marker to "O".
     * Normal case.
     */
    @Test
    void testSetMarkerO() {
        Player player = new Player("Bob");
        player.setMarker("O");
        assertEquals("O", player.getMarker());
    }
    
    /**
     * Test: setMarker with invalid marker (should still set it).
     * Bad input case: no validation in setter.
     */
    @Test
    void testSetMarkerInvalid() {
        Player player = new Player("Bob");
        player.setMarker("Z");
        assertEquals("Z", player.getMarker());
    }
    
    /**
     * Test: setName updates player name.
     * Normal case.
     */
    @Test
    void testSetName() {
        Player player = new Player("Alice");
        player.setName("Charlie");
        assertEquals("Charlie", player.getName());
    }
    
    /**
     * Test: setName with empty string.
     * Bad input case.
     */
    @Test
    void testSetNameEmpty() {
        Player player = new Player("Alice");
        player.setName("");
        assertEquals("", player.getName());
    }
    
    /**
     * Test: setMoveRow sets row to valid value.
     * Normal case.
     */
    @Test
    void testSetMoveRow() {
        Player player = new Player("Alice");
        player.setMoveRow(1);
        assertEquals(1, player.getMoveRow());
    }
    
    /**
     * Test: setMoveRow with out-of-bounds value.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetMoveRowOutOfBounds() {
        Player player = new Player("Alice");
        player.setMoveRow(5);
        assertEquals(5, player.getMoveRow());
    }
    
    /**
     * Test: setMoveCol sets column to valid value.
     * Normal case.
     */
    @Test
    void testSetMoveCol() {
        Player player = new Player("Alice");
        player.setMoveCol(2);
        assertEquals(2, player.getMoveCol());
    }
    
    /**
     * Test: setMoveCol with out-of-bounds value.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetMoveColOutOfBounds() {
        Player player = new Player("Alice");
        player.setMoveCol(10);
        assertEquals(10, player.getMoveCol());
    }
    
    /**
     * Test: setChoice sets RPS choice to "rock".
     * Normal case.
     */
    @Test
    void testSetChoiceRock() {
        Player player = new Player("Alice");
        player.setChoice("rock");
        assertEquals("rock", player.getChoice());
    }
    
    /**
     * Test: setChoice sets RPS choice to "paper".
     * Normal case.
     */
    @Test
    void testSetChoicePaper() {
        Player player = new Player("Alice");
        player.setChoice("paper");
        assertEquals("paper", player.getChoice());
    }
    
    /**
     * Test: setChoice sets RPS choice to "scissors".
     * Normal case.
     */
    @Test
    void testSetChoiceScissors() {
        Player player = new Player("Alice");
        player.setChoice("scissors");
        assertEquals("scissors", player.getChoice());
    }
    
    /**
     * Test: setChoice with invalid RPS choice.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetChoiceInvalid() {
        Player player = new Player("Alice");
        player.setChoice("invalid");
        assertEquals("invalid", player.getChoice());
    }
    
    /**
     * Test: toString returns correct string representation.
     * Normal case.
     */
    @Test
    void testToString() {
        Player player = new Player("Alice");
        player.setMarker("X");
        String expected = "Alice plays with the X marker.";
        assertEquals(expected, player.toString());
    }
    
    /**
     * Test: toString with different marker.
     * Normal case.
     */
    @Test
    void testToStringO() {
        Player player = new Player("Bob");
        player.setMarker("O");
        String expected = "Bob plays with the O marker.";
        assertEquals(expected, player.toString());
    }
}