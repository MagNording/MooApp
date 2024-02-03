/**
 * Test class for DatabaseManager
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */

package se.nording.moo.database;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DatabaseManagerTest {

    // Testar att få tillbaka ett playerId
    @Test
    void testGetPlayerId() throws SQLException {
        IDatabaseManager mockDBManager = mock(IDatabaseManager.class);
        when(mockDBManager.getPlayerId("testUser")).thenReturn(1);

        int playerId = mockDBManager.getPlayerId("testUser");
        assertEquals(1, playerId);
        verify(mockDBManager).getPlayerId("testUser");
    }

    // Testar att kasta en SQLException
    @Test
    void testGetPlayerIdThrowsSQLException() throws SQLException {
        IDatabaseManager mockDBManager = mock(IDatabaseManager.class);
        when(mockDBManager.getPlayerId("testUser")).thenThrow(new SQLException());

        assertThrows(SQLException.class, () -> mockDBManager.getPlayerId("testUser"));

        verify(mockDBManager).getPlayerId("testUser");
    }
}
