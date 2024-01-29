package test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.nording.moo.database.IDatabaseManager;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DatabaseManagerTest {

    @Test
    void testGetPlayerId() throws SQLException {
        IDatabaseManager mockDBManager = Mockito.mock(IDatabaseManager.class);
        when(mockDBManager.getPlayerId("testUser")).thenReturn(1);

        int playerId = mockDBManager.getPlayerId("testUser");
        assertEquals(1, playerId);
        verify(mockDBManager).getPlayerId("testUser");
    }
}
