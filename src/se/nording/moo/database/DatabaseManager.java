/**
 * Database access and SQL queries
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */
package se.nording.moo.database;

import se.nording.moo.util.PlayerAverage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class DatabaseManager implements IDatabaseManager {
    private Connection connection;

    @Override
    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost/moo";
        String user = "xxx";
        String password = "xxx";
        connection = DriverManager.getConnection(url, user, password);
    }

    public ArrayList<PlayerAverage> getTopPlayers() throws SQLException {
        ArrayList<PlayerAverage> topList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from players");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                // Hämta och bearbeta spelarresultat
                topList.add(getPlayerAverage(id, name));
            }
        }
        topList.sort(Comparator.comparingDouble(PlayerAverage::getAverage));
        return topList;
    }

    private PlayerAverage getPlayerAverage(int id, String name) throws SQLException {
        String query = "select * from results where playerid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs2 = pstmt.executeQuery()) {
                int nGames = 0;
                int totalGuesses = 0;
                while (rs2.next()) {
                    nGames++;
                    totalGuesses += rs2.getInt("result");
                }
                if (nGames > 0) {
                    return new PlayerAverage(name, (double) totalGuesses / nGames);
                }
            }
        }
        return new PlayerAverage(name, 0);
    }


    @Override
    public int getPlayerId(String name) throws SQLException {
        String query = "select id from players where name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }

    @Override
    public void insertResult(int guessCounter, int playerId) throws SQLException {
        String query = "INSERT INTO results (result, playerid) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, guessCounter);
            pstmt.setInt(2, playerId);
            pstmt.executeUpdate();
        }
    }
}
