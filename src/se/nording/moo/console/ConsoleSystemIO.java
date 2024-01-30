package se.nording.moo.console;

import se.nording.moo.ui.IUserInterface;
import se.nording.moo.util.PlayerAverage;

import java.io.IOException;
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
    public boolean confirmContinue(String prompt) {
        System.out.println(prompt + " (y/n)");
        String answer = input.nextLine();
        return answer.equalsIgnoreCase("y");
    }

    @Override
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
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
