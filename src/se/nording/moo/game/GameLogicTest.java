/**
 * Test class for GameLogic
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */

package se.nording.moo.game;

import org.junit.jupiter.api.Test;
import se.nording.moo.game.GameLogic;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class GameLogicTest {
    GameLogic gameLogic = new GameLogic();

    @Test
    void makeTargetCombo() {
        String result = gameLogic.makeTargetCombo();
        assertTrue(result.matches("\\d{4}"));
        Set<Character> uniqueDigits = new HashSet<>();
        for (char digit : result.toCharArray()) {
            assertTrue(uniqueDigits.add(digit));
        }
    }

    @Test
    void makeTargetCombo_notAllUnique() {
        String result = gameLogic.makeTargetCombo();
        assertFalse(result.matches("^(\\d)\\1{3}$"));
    }

    @Test
    void calculateBullsAndCows() {
        String result = gameLogic.calculateBullsAndCows("1234", "4321");
        assertEquals(",CCCC", result);
    }

    @Test
    void calculateBullsAndCows_withNonDigit() {
            // Testar att bokstäver inte räknas som kor, "o" istället för "0"
            String result = gameLogic.calculateBullsAndCows("1034", "1o34");
            assertEquals("BBB,", result);
    }

    @Test
    void calculateBullsAndCows_noMatches() {
        String result = gameLogic.calculateBullsAndCows("1234", "5678");
        assertEquals(",", result);
    }

    @Test
    void testCalculateBullsAndCows_notAllBulls() {
        String result = gameLogic.calculateBullsAndCows("1234", "1235");
        assertFalse(result.equals("BBBB"));
    }


}