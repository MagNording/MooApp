package se.nording.moo.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.nording.moo.database.DatabaseManager;
import se.nording.moo.ui.IO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GameControllerTest {
    private IO io;
    private GameLogic gameLogic;
    private GameController gameController;
    private DatabaseManager databaseManager;

    @BeforeEach
    void setUp() {
        io = mock(IO.class);
        databaseManager = mock(DatabaseManager.class);
        gameLogic = mock(GameLogic.class);
        gameController = new GameController(io, databaseManager, gameLogic);
    }

    @Test
    void testHandleGuesses() {
        when(io.getString()).thenReturn("1234", "abcd", "5678"); // två giltiga gissningar och en ogiltig
        when(gameLogic.calculateBullsAndCows(anyString(), eq("1234"))).thenReturn("1B1C");
        when(gameLogic.calculateBullsAndCows(anyString(), eq("5678"))).thenReturn("BBBB,");

        int guessCounter = gameController.handleGuesses("goal", 0);

        assertEquals(2, guessCounter);
        verify(io).addString("1234: 1B1C\n");
        verify(io).addString("Invalid guess. Please try again.\n");
        verify(io).addString("5678: BBBB,\n");
    }
}