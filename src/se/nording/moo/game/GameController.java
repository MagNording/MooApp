/**
 * Game controller class for the Bulls and Cows game
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */
package se.nording.moo.game;

import se.nording.moo.database.DatabaseManager;
import se.nording.moo.util.PlayerAverage;
import se.nording.moo.ui.IO;

import java.sql.SQLException;
import java.util.List;

public class GameController {
    private IO io;
    private DatabaseManager databaseManager;
    private GameLogic gameLogic;

    public GameController(IO io, DatabaseManager databaseManager, GameLogic gameLogic) {
        this.io = io;
        this.databaseManager = databaseManager;
        this.gameLogic = gameLogic;
    }

    public void start() {
        try {
            databaseManager.connect(); // Anslut till databasen
            int playerId = loginUser(); // Användarinloggning
            playGame(playerId); // Hantera spelet
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            io.exit();
        }
    }

    int loginUser() throws SQLException, InterruptedException {
        io.addString("Enter your user name:\n");
        String name = io.getString();
        int playerId = databaseManager.getPlayerId(name);

        if (playerId == -1) {
            io.addString("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }
        return playerId;
    }

    private void playGame(int playerId) throws SQLException, InterruptedException {
        boolean isPracticeMode = true; // Eller gör detta till en konfiguration eller parameter.
        boolean answer;
        do {
            String goal = gameLogic.makeTargetCombo();
            io.clear();
            io.addString("New game:\n");

            if (isPracticeMode) {
                io.addString("For practice, number is: " + goal + "\n");
            }
            int guessCounter = handleGuesses(goal, 0);

            saveResultAndShowToplist(guessCounter, playerId);

            answer = askToContinue(guessCounter);
        } while (answer); // Fortsätt loopen baserat på användarens svar.
    }

    private boolean askToContinue(int guessCounter) {
        return io.yesNo("Correct, it took " + guessCounter + " guesses. \nContinue?");
    }

    int handleGuesses(String goal, int guessCounter) {
        String result = "";
        do {
            String guess = io.getString();
            if (isValidGuess(guess, goal.length())) {
                guessCounter++;
                result = gameLogic.calculateBullsAndCows(goal, guess);
                io.addString(guess + ": " + result + "\n");
            } else {
                // Informera användaren om den ogiltiga gissningen
                io.addString("Invalid guess. Please try again.\n");
            }
        } while (!result.equals("BBBB,"));
        return guessCounter;
    }

    private boolean isValidGuess(String guess, int goalLength) {
        if (guess.length() != goalLength) return false;
        for (char c : guess.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private void saveResultAndShowToplist(int guessCounter, int playerId) {
        try {
            databaseManager.insertResult(guessCounter, playerId);
            List<PlayerAverage> topPlayers = databaseManager.getTopPlayers();
            gameLogic.showTopPlayers(topPlayers, io);
        } catch (SQLException e) {
            e.printStackTrace();
            io.addString("An error occurred while saving. Please try again.");
        }
    }
}



