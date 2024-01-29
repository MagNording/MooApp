package se.nording.moo.ui;

import se.nording.moo.util.PlayerAverage;

import java.util.List;

public interface IUserInterface {

    void displayMessage(String message);

    String getUserInput();

    boolean confirmContinue(String message);

    void clearScreen();

    void showTopPlayers(List<PlayerAverage> topPlayers);
}
