package se.nording.moo;

import java.sql.SQLException;
import java.util.List;

public class GameController {
    private SimpleWindow window;
    private DatabaseManager databaseManager;
    private UserInterface userInterface;
    private GameLogic gameLogic;

    public GameController() {
        window = new SimpleWindow("Moo");
        databaseManager = new DatabaseManager();
        userInterface = new UserInterface(window);
        gameLogic = new GameLogic();
    }

    public void start() {
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
                    int nGuess = 0;
                    String result;
                    do {
                        String guess = userInterface.getUserInput();
                        nGuess++;
                        result = gameLogic.calculateBullsAndCows(goal, guess);
                        userInterface.displayMessage(guess + ": " + result + "\n");
                    } while (!result.equals("BBBB,"));

                    // Spara resultat och visa topplistan
                    databaseManager.insertResult(nGuess, playerId);
                    List<PlayerAverage> topPlayers = databaseManager.getTopPlayers();
                    userInterface.showTopPlayers(topPlayers);

                    // Fråga om användaren vill fortsätta
                    answer = userInterface.confirmContinue("Correct, it took " + nGuess + " guesses\nContinue?");
                } while (answer);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            window.exit();
        }
    }
}
