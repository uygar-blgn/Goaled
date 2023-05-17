/**
 * UserLocal: This is a class that stores user information in the local device.
 * Contains the function for updating user properties when a new Accomplishment is entered.
 */

package com.example.goaled;

import android.os.Build;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.time.LocalDateTime;

import java.lang.Math;

public class UserLocal {

    // Instance Variables
    private HashMap<String, Double> userStats;
    private HashMap<String, Double> statMultipliers;
    private int level;
    private int xp;
    private int xpForNextLevel;
    private int totalPI;
    private ArrayList<UserAccomplishment> allAccomplishments;
    private ArrayList<UserGoal> allGoals;

    UserLocal() {

        // Define the instance variables
        userStats = new HashMap<String, Double>();
        allAccomplishments = new ArrayList<UserAccomplishment>();
        statMultipliers = new HashMap<String, Double>();

        // Putting dummy values for testing
        statMultipliers.put("Intellect", 1.0);
        statMultipliers.put("Endurance", 1.3);
        statMultipliers.put("Wisdom", 1.0);
        statMultipliers.put("Strength", 1.6);
        statMultipliers.put("Creativity", 1.1);

        userStats.put("Intellect", 10.0);
        userStats.put("Endurance", 10.0);
        userStats.put("Wisdom", 10.0);
        userStats.put("Strength", 10.0);
        userStats.put("Creativity", 10.0);

        UserActivity exampleActivity = new UserActivity("Reading a book", "Wisdom", "Endurance", 5);
        UserGoal exampleGoal = new UserGoal(exampleActivity, 5, "PI", UserGoal.Frequency.DAILY);

        UserGoal exampleGoal2 = new UserGoal(7, UserGoal.Frequency.WEEKLY);
        UserGoal exampleGoal3 = new UserGoal(10, "Wisdom", UserGoal.Frequency.DAILY);

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Within a week
            exampleGoal.setCreatedDate( LocalDateTime.of(2023, 5, 15, 4, 1, 1) );

            // Within a month
            exampleGoal2.setCreatedDate( LocalDateTime.of(2023, 4, 30, 4, 1, 1) );

            // Far, far away
            exampleGoal3.setCreatedDate( LocalDateTime.of(2020, 3, 1, 1, 1, 1) );

        }

        allGoals.add(exampleGoal);
        allGoals.add(exampleGoal2);
        allGoals.add(exampleGoal3);

        level = 1;
    }


    public void newAccomplishment(UserAccomplishment userAccomplishment) {

        UserActivity _activity = userAccomplishment.getUserActivity();
        double activityDifficulty = _activity.getDifficulty();

        // Keeping track of all accomplishments to be able to serve related graphs.
        allAccomplishments.add(userAccomplishment);

        // Update the PI (Productivity Index)
        totalPI += userAccomplishment.getPI();

        // Update the stats
        String primaryStat = _activity.getPrimaryStat();
        String secondaryStat = _activity.getSecondaryStat();

        double currentPrimaryStatValue = userStats.get(primaryStat);
        double newPrimaryStatValue = currentPrimaryStatValue +
                ( userAccomplishment.getPI() * 0.7 * statMultipliers.get(primaryStat) );
        userStats.put(primaryStat, newPrimaryStatValue);

        double currentSecondaryStatValue = userStats.get(secondaryStat);
        double newSecondaryStatValue = currentSecondaryStatValue +
                ( userAccomplishment.getPI() * 0.4 * statMultipliers.get(secondaryStat) );
        userStats.put(secondaryStat, newSecondaryStatValue);


        Random rand = new Random();

        // Increase the xp and, if needed, update the level.
        xp += (int)( userAccomplishment.getPI()  * ( ((double)(rand.nextInt(101) - 90)) / 10.0 ) );
        updateLevel();

    }


    // Returns an ArrayList of accomplishments that have been entered within the entered number of days. Helper method.
    private ArrayList<UserAccomplishment> getAccomplishmentsWithinDays(ArrayList<UserAccomplishment> allAccomplishments, int days) {

        ArrayList<UserAccomplishment> accomplishmentsWithinDays = new ArrayList<UserAccomplishment>();

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime thresholdDate = LocalDateTime.now().minusDays(days);

            // This evades making a call to size() each iteration of the for loop.
            int allAccomplishmentSize = allAccomplishments.size();

            for (int i = 0; i < allAccomplishmentSize; i++) {

                UserAccomplishment anAccomplishment = allAccomplishments.get(i);

                if ( anAccomplishment.getAccomplishedDate().isAfter( thresholdDate ) ) {
                    accomplishmentsWithinDays.add(anAccomplishment);
                }

            }

        }

        return accomplishmentsWithinDays;
    }

    public void updateLevel() {

        if (xp >= xpForNextLevel) {
            level++;
            xp = xpForNextLevel - xp;

            xpForNextLevel = (int)( 90 * ( Math.pow(level, 0.1) * Math.log(level) ));


        }

    }


}
