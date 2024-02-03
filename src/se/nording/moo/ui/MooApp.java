/**
 * Main method for the MOO game.
 *
 * @author Magnus Nording, magnus.nording@iths.se
 * @version 1.8 (2024-01-30)
 */
package se.nording.moo.ui;

import se.nording.moo.console.ConsoleSystemIO;
import se.nording.moo.database.DatabaseManager;
import se.nording.moo.game.GameController;
import se.nording.moo.game.GameLogic;

public class MooApp {
    // Obs krasch kan uppstå om inte Mockito och Byte-buddy är installerade korrekt
    // Möjligen kommentera ut de berörda testerna
    public static void main(String[] args) {
        try {
            IO io = new ConsoleSystemIO();     // Kör i terminalen
            //IO io = new SimpleWindow("Moo"); // Kör i ett fönster
            DatabaseManager databaseManager = new DatabaseManager();
            GameLogic gameLogic = new GameLogic();

            GameController gameController = new GameController(io, databaseManager, gameLogic);
            gameController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
