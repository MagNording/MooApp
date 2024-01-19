package se.nording.moo;

import java.util.List;

public class UserInterface implements IUserInterface {
    private SimpleWindow window;

    public UserInterface(SimpleWindow window) {
        this.window = window;
    }

    @Override
    public void displayMessage(String message) {
        window.addString(message + "\n");
    }

    @Override
    public String getUserInput() {
        return window.getString();
    }

    @Override
    public boolean confirmContinue(String message) {
        return window.yesNo(message);
    }

    @Override
    public void clearScreen() {
        window.clear();
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
