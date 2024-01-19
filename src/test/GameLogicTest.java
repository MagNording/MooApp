package test;

import se.nording.moo.GameLogic;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GameLogicTest {
    GameLogic gameLogic = new GameLogic();

    @org.junit.jupiter.api.Test
    void makeTargetCombo() {
        String result = gameLogic.makeTargetCombo();
        assertTrue(result.matches("\\d{4}"));
        Set<Character> uniqueDigits = new HashSet<>();
        for (char digit : result.toCharArray()) {
            assertTrue(uniqueDigits.add(digit));
        }
    }

    @org.junit.jupiter.api.Test
    void calculateBullsAndCows() {
        String result = gameLogic.calculateBullsAndCows("1234", "4321");
        assertEquals(",CCCC", result);
    }
}