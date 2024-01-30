package se.nording.moo.game;

import se.nording.moo.ui.IO;
import se.nording.moo.util.PlayerAverage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogic implements IGameLogic {

    // Generera en fyrsiffrig kod
    @Override
    public String makeTargetCombo() {
        StringBuilder goal = new StringBuilder();
        Set<Integer> usedDigits = new HashSet<>();
        while (goal.length() < 4) {
            int random = (int) (Math.random() * 10);
            if (!usedDigits.contains(random)) {
                usedDigits.add(random);
                goal.append(random);
            }
        }
        return goal.toString();
    }

    // Jämför spelarens gissning med målet
    // Bulls = rätt siffra på rätt plats, Cows = rätt siffra på fel plats
    @Override
    public String calculateBullsAndCows(String goal, String guess) {
        int cows = 0, bulls = 0;
        for (int i = 0; i < goal.length(); i++) {
            char goalChar = goal.charAt(i);
            char guessChar = guess.charAt(i);
            if (goalChar == guessChar) {
                bulls++;
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            if (goal.indexOf(guessChar) >= 0 && goal.charAt(i) != guessChar) {
                cows++;
            }
        }
        return "B".repeat(Math.max(0, bulls)) +
                "," +
                "C".repeat(Math.max(0, cows));
    }

    @Override
    public void showTopPlayers(List<PlayerAverage> topPlayers, IO io) {
        io.addString("Top Ten List\n    Player     Average\n");
        int pos = 1;
        for (PlayerAverage player : topPlayers) {
            io.addString(String.format("%3d %-10s%5.2f", pos++, player.getName(),
                    player.getAverage()));
            if (pos > 10) break;
        }
    }
}
