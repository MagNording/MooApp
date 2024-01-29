package se.nording.moo.game;

public interface IGameLogic {

    String makeTargetCombo();

    String calculateBullsAndCows(String goal, String guess);
}
