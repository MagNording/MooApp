/**
 * Game helper class for storing player name and average score.
 *
 * @author Magnus Nording
 * @version 1.8 (2024-01-30)
 */

package se.nording.moo.util;

public class PlayerAverage {
    private String name;
    private double average;

    public PlayerAverage(String name, double average) {
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }
}

