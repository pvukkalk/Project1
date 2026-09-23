import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(result > 0, "Rock should beat scissors");
    }
    
    /**
     * Test: compare returns positive when player plays paper, computer plays rock.
     * Normal case: player wins.
     */
    @Test
    void testComparePlayerWinsPaperVsRock() {
        int result = RPS.compare("paper", "rock");
        assertTrue(result > 0, "Paper should beat rock");
    }
    
    /**
     * Test: compare returns positive when player plays scissors, computer plays paper.
     * Normal case: player wins.
     */
    @Test
    void testComparePlayerWinsScissorsVsPaper() {
        int result = RPS.compare("scissors", "paper");
        assertTrue(result > 0, "Scissors should beat paper");
    }
    
    /**
     * Test: compare returns negative when player plays rock, computer plays paper.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsPaperVsRock() {
        int result = RPS.compare("rock", "paper");
        assertTrue(result < 0, "Rock should lose to paper");
    }
    
    /**
     * Test: compare returns negative when player plays paper, computer plays scissors.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsScissorsVsPaper() {
        int result = RPS.compare("paper", "scissors");
        assertTrue(result < 0, "Paper should lose to scissors");
    }
    
    /**
     * Test: compare returns negative when player plays scissors, computer plays rock.
     * Normal case: computer wins.
     */
    @Test
    void testCompareComputerWinsRockVsScissors() {
        int result = RPS.compare("scissors", "rock");
        assertTrue(result < 0, "Scissors should lose to rock");
    }
    
    /**
     * Test: compare returns zero when both play rock.
     * Bad input case: tie (should replay per spec).
     */
    @Test
    void testCompareTieRockVsRock() {
        int result = RPS.compare("rock", "rock");
        assertEquals(0, result, "Same choice should be a tie");
    }
    
    /**
     * Test: compare returns zero when both play paper.
     * Bad input case: tie.
     */
    @Test
    void testCompareTiePaperVsPaper() {
        int result = RPS.compare("paper", "paper");
        assertEquals(0, result, "Same choice should be a tie");
    }
    
    /**
     * Test: compare returns zero when both play scissors.
     * Bad input case: tie.
     */
    @Test
    void testCompareTieScissorsVsScissors() {
        int result = RPS.compare("scissors", "scissors");
        assertEquals(0, result, "Same choice should be a tie");
    }
}