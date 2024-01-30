package se.nording.moo.game;

import se.nording.moo.ui.IO;
import se.nording.moo.util.PlayerAverage;
import java.util.List;

public interface IGameLogic {

    String makeTargetCombo();

    String calculateBullsAndCows(String goal, String guess);

    void showTopPlayers(List<PlayerAverage> topPlayers, IO io);
}
