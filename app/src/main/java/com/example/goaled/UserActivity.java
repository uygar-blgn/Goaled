/**
 * UserActivity: An Activity like "Reading a book" or "Working out".
 * Contains the name of the activity, primary stat that the activity is linked to,
 * secondary stat that the activity is linked to, and the activity's difficulty level.
 */
package com.example.goaled;

public class UserActivity {

    private String name;
    private String primaryStat;
    private String secondaryStat;
    private double difficulty;

    UserActivity(String name, String primaryStat, String secondaryStat, double difficulty) {
        this.name = name;
        this.primaryStat = primaryStat;
        this.secondaryStat = secondaryStat;
        this.difficulty = difficulty;
    }

    // getter methods


    public String getName() {
        return name;
    }

    public String getPrimaryStat() {
        return primaryStat;
    }

    public String getSecondaryStat() {
        return secondaryStat;
    }

    public double getDifficulty() {
        return difficulty;
    }
}
