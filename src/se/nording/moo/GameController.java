package se.nording.moo;

import java.sql.SQLException;
import java.util.List;

public class GameController {
    private SimpleWindow window;
    private DatabaseManager databaseManager;
    private UserInterface userInterface;
    private GameLogic gameLogic;

    public GameController(SimpleWindow window, DatabaseManager databaseManager,
                          UserInterface userInterface, GameLogic gameLogic) {
        this.window = window;
        this.databaseManager = databaseManager;
        this.userInterface = userInterface;
        this.gameLogic = gameLogic;
    }

    public void start() {
        int guessCounter = 0;
        try {
            databaseManager.connect(); // Anslut till databasen
            // Användarinloggning
            userInterface.displayMessage("Enter your user name:\n");
            String name = userInterface.getUserInput();
            int playerId = databaseManager.getPlayerId(name);

            if (playerId == -1) {
                userInterface.displayMessage("User not in database, please register with admin");
                Thread.sleep(5000);
                window.exit();
            } else {
                boolean isPracticeMode = true; // Sätt till false för att dölja målkombon
                boolean answer;
                do {
                    String goal = gameLogic.makeTargetCombo();
                    userInterface.clearScreen();
                    userInterface.displayMessage("New game:\n");

                    if (isPracticeMode) {
                        userInterface.displayMessage("For practice, number is: " + goal + "\n");
                    }
                    // Loop för att hantera gissningar
                    guessCounter = handleGuesses(goal, guessCounter);

                    // Spara resultat och visa topplistan
                    saveResultAndShowToplist(guessCounter, playerId);

                    // Fråga om användaren vill fortsätta
                    answer = userInterface.confirmContinue("Correct, it took " + guessCounter + " guesses\nContinue?");
                } while (answer);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            window.exit();
        }
    }
    private int handleGuesses(String goal, int guessCounter) {
        String result;
        do {
            String guess = userInterface.getUserInput();
            guessCounter++;
            result = gameLogic.calculateBullsAndCows(goal, guess);
            userInterface.displayMessage(guess + ": " + result + "\n");
        } while (!result.equals("BBBB,"));
        return guessCounter;
    }

    private void saveResultAndShowToplist(int guessCounter, int playerId) {
        try {
            databaseManager.insertResult(guessCounter, playerId);
            List<PlayerAverage> topPlayers = databaseManager.getTopPlayers();
            userInterface.showTopPlayers(topPlayers);
        } catch (SQLException e) {
            e.printStackTrace();
            userInterface.displayMessage("An error occurred while saving. Please try again.");
        }
    }
}



