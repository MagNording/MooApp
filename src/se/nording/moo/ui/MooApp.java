package se.nording.moo.ui;

import se.nording.moo.database.DatabaseManager;
import se.nording.moo.game.GameController;
import se.nording.moo.game.GameLogic;

public class MooApp {

    public static void main(String[] args) {
        try {
            SimpleWindow window = new SimpleWindow("Moo");
            DatabaseManager databaseManager = new DatabaseManager();
            //ConsoleSystemIO systemIO = new ConsoleSystemIO();
            UserInterface userInterface = new UserInterface(window);
            GameLogic gameLogic = new GameLogic();

            GameController gameController = new GameController(window, databaseManager,
                    userInterface, gameLogic);
            gameController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
