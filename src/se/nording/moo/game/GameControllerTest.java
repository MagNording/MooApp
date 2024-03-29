package se.nording.moo.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.nording.moo.database.DatabaseManager;
import se.nording.moo.database.IDatabaseManager;
import se.nording.moo.ui.IO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    void testLoginUserWithUnknownUser() throws SQLException, InterruptedException {
        when(io.getString()).thenReturn("unknownUser");
        when(databaseManager.getPlayerId("unknownUser")).thenReturn(-1);

        int result = gameController.loginUser();
        // Verifiera att resultatet inte är det förväntade giltiga playerId (t.ex., 1)
        assertNotEquals(1, result);
    }

    @Test
    void testLoginUserWithUnknownUserDisplaysCorrectMessage() throws SQLException, InterruptedException {
        when(io.getString()).thenReturn("unknownUser");
        when(databaseManager.getPlayerId("unknownUser")).thenReturn(-1);
        gameController.loginUser();
        // Verifiera att rätt meddelande visas för användaren
        verify(io).addString("User not in database, please register with admin");
    }

    // Testar metoden handleGuesses i GameController
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

    @Test
    void testHandleGuesses_WithTooLongInput() {
        // För lång gissning, sedan en vanlig gissning, följt av den vinnande gissningen
        when(io.getString()).thenReturn("12345", "1234", "5678");
        when(gameLogic.calculateBullsAndCows(anyString(), eq("1234"))).thenReturn("1B1C");
        when(gameLogic.calculateBullsAndCows(anyString(), eq("5678"))).thenReturn("BBBB,");

        int guessCounter = gameController.handleGuesses("goal", 0);
        // Kontrollera att två giltiga gissningar räknades (den ogiltiga + den första giltiga)
        assertEquals(2, guessCounter);
        verify(io).addString("Invalid guess. Please try again.\n");
        verify(io).addString("1234: 1B1C\n");
        // Kontrollera att den vinnande gissningen hanteras korrekt
        verify(io).addString("5678: BBBB,\n");
    }


}