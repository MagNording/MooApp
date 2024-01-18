/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static Connection connection;
    static Statement stmt;
    static ResultSet rs;
    static SimpleWindow window;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        GameLogic gameLogic = new GameLogic();
        window = new SimpleWindow("Moo");
        boolean answer = true;

        // login
        window.addString("Enter your user name:\n");
        String name = window.getString();
        int id = 0;

        connection = DriverManager.getConnection("jdbc:mysql://localhost/Moo","root","Tester1");
        stmt = connection.createStatement();
        rs = stmt.executeQuery("select id, name from players where name = '" + name + "'");
        if (rs.next()) {
            id = rs.getInt("id");
        } else {
            window.addString("User not in database, please register with admin");
            Thread.sleep(5000);
            window.exit();
        }
        while (answer) {
            String goal = gameLogic.makeTargetCombo();
            window.clear();
            window.addString("New game:\n");

            //comment out or remove next line to play real games!
            window.addString("For practice, number is: " + goal + "\n");
            String guess = window.getString();
            window.addString(guess +"\n");

            int nGuess = 1;
            String bbcc = gameLogic.calculateBullsAndCows(goal, guess);
            window.addString(bbcc + "\n");

            while ( ! bbcc.equals("BBBB,")) {
                nGuess++;
                guess = window.getString();
                window.addString(guess +": ");
                bbcc = gameLogic.calculateBullsAndCows(goal, guess);
                window.addString(bbcc + "\n");
            }
            int ok = stmt.executeUpdate("INSERT INTO results " +
                    "(result, playerid) VALUES (" + nGuess + ", " +	id + ")" );
            showTopTen();
            answer = window.yesNo("Correct, it took " + nGuess
                    + " guesses\nContinue?");

        }
        window.exit();
    }

public static String makeGoal(){
        String goal = "";
        for (int i = 0; i < 4; i++){
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (goal.contains(randomDigit)){
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            goal = goal + randomDigit;
        }
        return goal;
    }


public static String checkBC(String goal, String guess) {
        guess += "    ";
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++ ) {
                if (goal.charAt(i) == guess.charAt(j)){
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++){
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++){
            result = result + "C";
        }
        return result;

    }


//static class PlayerAverage {
//        String name;
//        double average;
//        public PlayerAverage(String name, double average) {
//            this.name = name;
//            this.average = average;
//        }
//    }


    static void showTopTen() throws SQLException {
        ArrayList<PlayerAverage> topList = new ArrayList<>();
        Statement stmt2 = connection.createStatement();
        ResultSet rs2;
        rs = stmt.executeQuery("select * from players");
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            rs2 = stmt2.executeQuery("select * from results where playerid = "+ id );
            int nGames = 0;
            int totalGuesses = 0;
            while (rs2.next()) {
                nGames++;
                totalGuesses += rs2.getInt("result");
            }
            if (nGames > 0) {
                topList.add(new PlayerAverage(name, (double)totalGuesses/nGames));
            }

        }
        window.addString("Top Ten List\n    Player     Average\n");
        int pos = 1;
        topList.sort(Comparator.comparingDouble(PlayerAverage::getAverage));
        for (PlayerAverage p : topList) {
            window.addString(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }

    }
}
*/
