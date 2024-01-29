package se.nording.moo.console;

import se.nording.moo.ui.IUserInterface;
import se.nording.moo.util.PlayerAverage;
import java.util.List;
import java.util.Scanner;

public class ConsoleSystemIO implements IUserInterface {
    private Scanner input = new Scanner(System.in);

    @Override
    public void displayMessage(String message) {
        System.out.println(message + "\n");
    }

    @Override
    public String getUserInput() {
        return input.nextLine();
    }

    @Override
    public boolean confirmContinue(String message) {
        return Boolean.parseBoolean((message));
    }

    @Override
    public void clearScreen() {
        System.exit(0);
    }

    @Override
    public void showTopPlayers(List<PlayerAverage> topPlayers) {
        displayMessage("Top Ten List\n    Player     Average\n");
        int pos = 1;
        for (PlayerAverage player : topPlayers) {
            displayMessage(String.format("%3d %-10s%5.2f", pos++, player.getName(), player.getAverage()));
            if (pos > 10) break;
        }
    }
}
