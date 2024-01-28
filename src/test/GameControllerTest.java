package test;

import org.junit.jupiter.api.Test;
import se.nording.moo.*;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Test
    void testStart() throws SQLException, InterruptedException {
        SimpleWindow mockWindow = mock(SimpleWindow.class);
        DatabaseManager mockDatabaseManager = mock(DatabaseManager.class);
        UserInterface mockUserInterface = mock(UserInterface.class);
        GameLogic mockGameLogic = mock(GameLogic.class);
        // Konfigurera mockarna
        when(mockUserInterface.getUserInput()).thenReturn("testUser");
        when(mockDatabaseManager.getPlayerId("testUser")).thenReturn(1);
        when(mockGameLogic.makeTargetCombo()).thenReturn("1234");
        when(mockUserInterface.confirmContinue(anyString())).thenReturn(false);

        GameController gameController = new GameController(mockWindow, mockDatabaseManager, mockUserInterface, mockGameLogic);
        // Anropa start-metoden
        gameController.start();

        // Verifiera att viktiga metoder anropades
        verify(mockUserInterface, times(1)).getUserInput();
        verify(mockDatabaseManager, times(1)).getPlayerId("testUser");
        verify(mockGameLogic, times(1)).makeTargetCombo();
        verify(mockUserInterface, times(1)).confirmContinue(anyString());

    }
}
