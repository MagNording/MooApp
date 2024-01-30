/**
 * DB manager interface
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */
package se.nording.moo.database;

import se.nording.moo.util.PlayerAverage;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IDatabaseManager {

    void connect() throws SQLException;

    int getPlayerId(String name) throws SQLException;

    void insertResult(int nGuess, int playerId) throws SQLException;

    ArrayList<PlayerAverage> getTopPlayers() throws SQLException;
}
