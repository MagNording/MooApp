package se.nording.moo;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IDatabaseManager {

    void connect() throws SQLException;

    int getPlayerId(String name) throws SQLException;

    void insertResult(int nGuess, int playerId) throws SQLException;

    ArrayList<PlayerAverage> getTopPlayers() throws SQLException;
}
