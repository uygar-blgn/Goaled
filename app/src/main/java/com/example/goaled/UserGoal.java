/**
 * UserGoal: May be defined with a UserActivity or without a UserActivity.
 * Contains the date it (the UserGoal itself) was defined and a 'frequency' (daily, weekly, or monthly)
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

public class UserGoal {

    // Define different frequencies.
    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY
    }


    // Constructor for UserGoal defined with UserActivity
    UserGoal(UserActivity userActivity, double quantity, String PIOrHours, Frequency frequency) {

        if (PIOrHours == "HOURS") {

        }

        if (PIOrHours == "PI") {

        }

    }

    // Constructor for UserGoal defined as "achieve x PI every y [frequency]"
    UserGoal(int amount, Frequency frequency) {

    }

    // Constructor for UserGoal defined as "achieve x of Stat y every z [frequency]"
    UserGoal(int amount, String stat, Frequency frequency) {

    }



}
