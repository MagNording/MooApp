package se.nording.moo;

public interface IGameLogic {

    String makeTargetCombo();

    String calculateBullsAndCows(String goal, String guess);
}