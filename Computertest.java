import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Computer class.
 * Tests one normal case and one bad-input case per public method as per spec.
 */
public class ComputerTest {
    
    /**
     * Test: Computer constructor creates a computer with default name "Computer".
     * Normal case.
     */
    @Test
    void testComputerConstructor() {
        Computer computer = new Computer();
        assertEquals("Computer", computer.getName());
    }
    
    /**
     * Test: getMarker returns null initially.
     * Normal case.
     */
    @Test
    void testGetMarkerInitial() {
        Computer computer = new Computer();
        assertNull(computer.getMarker());
    }
    
    /**
     * Test: setMarker sets marker to "X".
     * Normal case.
     */
    @Test
    void testSetMarkerX() {
        Computer computer = new Computer();
        computer.setMarker("X");
        assertEquals("X", computer.getMarker());
    }
    
    /**
     * Test: setMarker sets marker to "O".
     * Normal case.
     */
    @Test
    void testSetMarkerO() {
        Computer computer = new Computer();
        computer.setMarker("O");
        assertEquals("O", computer.getMarker());
    }
    
    /**
     * Test: setMarker with invalid marker.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetMarkerInvalid() {
        Computer computer = new Computer();
        computer.setMarker("Z");
        assertEquals("Z", computer.getMarker());
    }
    
    /**
     * Test: setName updates computer name.
     * Normal case.
     */
    @Test
    void testSetName() {
        Computer computer = new Computer();
        computer.setName("AI");
        assertEquals("AI", computer.getName());
    }
    
    /**
     * Test: setName with empty string.
     * Bad input case.
     */
    @Test
    void testSetNameEmpty() {
        Computer computer = new Computer();
        computer.setName("");
        assertEquals("", computer.getName());
    }
    
    /**
     * Test: setMoveRow sets row to valid value.
     * Normal case.
     */
    @Test
    void testSetMoveRow() {
        Computer computer = new Computer();
        computer.setMoveRow(0);
        assertEquals(0, computer.getMoveRow());
    }
    
    /**
     * Test: setMoveRow with out-of-bounds value.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetMoveRowOutOfBounds() {
        Computer computer = new Computer();
        computer.setMoveRow(10);
        assertEquals(10, computer.getMoveRow());
    }
    
    /**
     * Test: setMoveCol sets column to valid value.
     * Normal case.
     */
    @Test
    void testSetMoveCol() {
        Computer computer = new Computer();
        computer.setMoveCol(2);
        assertEquals(2, computer.getMoveCol());
    }
    
    /**
     * Test: setMoveCol with out-of-bounds value.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetMoveColOutOfBounds() {
        Computer computer = new Computer();
        computer.setMoveCol(15);
        assertEquals(15, computer.getMoveCol());
    }
    
    /**
     * Test: setChoice sets RPS choice to "rock".
     * Normal case.
     */
    @Test
    void testSetChoiceRock() {
        Computer computer = new Computer();
        computer.setChoice("rock");
        assertEquals("rock", computer.getChoice());
    }
    
    /**
     * Test: setChoice sets RPS choice to "paper".
     * Normal case.
     */
    @Test
    void testSetChoicePaper() {
        Computer computer = new Computer();
        computer.setChoice("paper");
        assertEquals("paper", computer.getChoice());
    }
    
    /**
     * Test: setChoice sets RPS choice to "scissors".
     * Normal case.
     */
    @Test
    void testSetChoiceScissors() {
        Computer computer = new Computer();
        computer.setChoice("scissors");
        assertEquals("scissors", computer.getChoice());
    }
    
    /**
     * Test: setChoice with invalid RPS choice.
     * Bad input case: setter does not validate.
     */
    @Test
    void testSetChoiceInvalid() {
        Computer computer = new Computer();
        computer.setChoice("invalid");
        assertEquals("invalid", computer.getChoice());
    }
    
    /**
     * Test: toString returns correct string representation.
     * Normal case.
     */
    @Test
    void testToStringX() {
        Computer computer = new Computer();
        computer.setMarker("X");
        String expected = "Computer plays the (X) marker.";
        assertEquals(expected, computer.toString());
    }
    
    /**
     * Test: toString with O marker.
     * Normal case.
     */
    @Test
    void testToStringO() {
        Computer computer = new Computer();
        computer.setMarker("O");
        String expected = "Computer plays the (O) marker.";
        assertEquals(expected, computer.toString());
    }
}