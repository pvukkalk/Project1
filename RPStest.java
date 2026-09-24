import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for RPS class.
 * Tests the public compare() method - one normal and one bad-input case per comparison type.
 */
public class RPSTest {
    
    /**
     * Test: compare returns positive when player plays rock, computer plays scissors.
     * Normal case: player wins.
     */
    @Test
    void testComparePlayerWinsRockVsScissors() {
        int result = RPS.compare("rock", "scissors");
        assertTrue("Rock should beat scissors", result > 0);
    }
    
    /**
     * Test: compare returns positive when player plays paper, computer plays rock.
     * Normal case: player wins.
     */
    @Test
    void testComparePlayerWinsPaperVsRock() {
        int result = RPS.compare("paper", "rock");
        assertTrue("Paper should beat rock", result > 0);
    }
    
    /**
     * Test: compare returns positive when player plays scissors, computer plays paper.
     * Normal case: player wins.
     */
    @Test
    void testComparePlayerWinsScissorsVsPaper() {
        int result = RPS.compare("scissors", "paper");
        assertTrue("Scissors should beat paper", result > 0);
    }
    
    /**
     * Test: compare returns negative when player plays rock, computer plays paper.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsPaperVsRock() {
        int result = RPS.compare("rock", "paper");
        assertTrue("Rock should lose to paper", result < 0);
    }
    
    /**
     * Test: compare returns negative when player plays paper, computer plays scissors.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsScissorsVsPaper() {
        int result = RPS.compare("paper", "scissors");
        assertTrue("Paper should lose to scissors", result < 0);
    }
    
    /**
     * Test: compare returns negative when player plays scissors, computer plays rock.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsRockVsScissors() {
        int result = RPS.compare("scissors", "rock");
        assertTrue("Scissors should lose to rock", result < 0);
    }
    
    /**
     * Test: compare returns zero when both play rock.
     * Bad input case: tie (should replay per spec).
     */
    @Test
    void testCompareTieRockVsRock() {
        int result = RPS.compare("rock", "rock");
        assertEquals("Same choice should be a tie", 0, result);
    }
    
    /**
     * Test: compare returns zero when both play paper.
     * Bad input case: tie.
     */
    @Test
    void testCompareTiePaperVsPaper() {
        int result = RPS.compare("paper", "paper");
        assertEquals("Same choice should be a tie", 0, result);
    }
    
    /**
     * Test: compare returns zero when both play scissors.
     * Bad input case: tie.
     */
    @Test
    void testCompareTieScissorsVsScissors() {
        int result = RPS.compare("scissors", "scissors");
        assertEquals("Same choice should be a tie", 0, result);
    }
}