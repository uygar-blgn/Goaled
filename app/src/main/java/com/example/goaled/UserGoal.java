/**
 * UserGoal: May be defined with a UserActivity or without a UserActivity.
 * Contains the time as Date it (the UserGoal itself) was defined and a 'frequency' (daily, weekly, or monthly)
 * which designates the frequency with which the UserGoal is aimed to be achieved.
 *
 * If defined with a UserActivity, this class contains the information of whether the user aims to
 * perform that UserActivity for a certain number of hours or the user aims to achieve a certain quantity of
 * Productivity Index accomplishing the UserActivity.
 *
 * If defined without a UserActivity, UserGoal can be the goal of achieving a certain increase in Productivity Index or a
 * stat.
 *
 * Hence, there are four different ways UserGoal can be defined
 * 1) Do UserActivity for x hours every [frequency]
 * 2) Achieve y amount Productivity Index from x UserActivity every [frequency]
 * 3) Achieve x amount of increase in Productivity Index every [frequency]
 * 4) Achieve x amount of increase in Stat y every [frequency]
 *
 */

package com.example.goaled;

import android.os.Build;
import java.time.LocalDateTime;

public class UserGoal {

    // Define different frequencies.
    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY
    }

    // Represents different ways that the goals can be defined.
    enum GoalType {
        UserActivityWithPI,
        UserActivityWithHours,
        OnlyStat,
        OnlyPI
    }

    // Instance Variables
    private GoalType goalType;
    private Frequency frequency;
    private UserActivity userActivity;
    private double amount;
    private double currentProgress;
    private String stat;
    private LocalDateTime timeCreated;



    // Constructor for UserGoal defined with UserActivity
    UserGoal(UserActivity userActivity, double amount, String PIOrHours, Frequency frequency) {

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeCreated = LocalDateTime.now();
        }

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeCreated = LocalDateTime.now();
        }

        this.frequency = frequency;

        if (PIOrHours == "HOURS") {

            goalType = GoalType.UserActivityWithHours;

        }

        if (PIOrHours == "PI") {

            goalType = GoalType.UserActivityWithPI;

        }

        this.amount = amount;
        this.userActivity = userActivity;

    }

    // Constructor for UserGoal defined as "achieve x PI every y [frequency]"
    UserGoal(double amount, Frequency frequency) {

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeCreated = LocalDateTime.now();
        }

        this.frequency = frequency;
        this.amount = amount;

        goalType = GoalType.OnlyPI;

    }

    // Constructor for UserGoal defined as "achieve x of Stat y every z [frequency]"
    UserGoal(double amount, String stat, Frequency frequency) {

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeCreated = LocalDateTime.now();
        }

        goalType = GoalType.OnlyStat;
        this.frequency = frequency;
        this.amount = amount;
        this.stat = stat;

    }

    // GETTER and SETTER functions

    // setCreatedDate function for testing purposes
    public void setCreatedDate(LocalDateTime newDate) {
        this.timeCreated = newDate;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public Frequency getGoalFrequency() {
        return frequency;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public double getGoalAmount() {
        return amount;
    }
    public LocalDateTime getTimeCreated() { return timeCreated; }

    public void setCurrentProgress(double progress) {
        this.currentProgress = progress;
    }

    public String getStat() {
        return stat;
    }
}
