/**
 * UserLocal: This is a class that stores user information in the local device.
 * Contains the function for updating user properties when a new Accomplishment is entered.
 */

package com.example.goaled;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.lang.Math;

public class UserLocal {

    // Instance Variables
    private HashMap<String, Integer> userStats;
    private int level;
    private int xp;
    private int xpForNextLevel;
    private int totalPI;
    private ArrayList<UserAccomplishment> allAccomplishments;

    UserLocal() {

        // Define the instance variables
        userStats = new HashMap<String, Integer>();
        allAccomplishments = new ArrayList<UserAccomplishment>();

        // Putting dummy values for testing
        userStats.put("Intellect", 10);
        userStats.put("Endurance", 10);
        userStats.put("Wisdom", 10);
        userStats.put("Strength", 10);
        userStats.put("Creativity", 10);

        level = 1;
    }


    public void newAccomplishment(UserAccomplishment userAccomplishment) {

        UserActivity _activity = userAccomplishment.getUserActivity();
        double activityDifficulty = _activity.getDifficulty();

        // Keeping track of all accomplishments to be able to serve related graphs.
        allAccomplishments.add(userAccomplishment);
        totalPI += userAccomplishment.getPI();

        Random rand = new Random();

        // Increase the xp and, if needed, update the level.
        xp += (int)( userAccomplishment.getPI()  * ( rand.nextInt(101) - 90 / 10 ) );
        updateLevel();

    }


    public void updateLevel() {

        if (xp >= xpForNextLevel) {
            level++;
            xp = xpForNextLevel - xp;

            xpForNextLevel = (int)( 90 * ( Math.pow(level, 0.1) * Math.log(level) ));


        }

    }


}
