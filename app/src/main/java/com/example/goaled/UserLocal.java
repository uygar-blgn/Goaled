/**
 * UserLocal: This is a class that stores user information in the local device.
 * Contains the function for updating user properties when a new Accomplishment is entered.
 */

package com.example.goaled;

import java.util.HashMap;

public class UserLocal {

    private HashMap<String, Integer> userStats = new HashMap<>();

    UserLocal() {

        // Putting dummy values for testing
        userStats.put("Intellect", 10);
        userStats.put("Endurance", 10);
        userStats.put("Wisdom", 10);
        userStats.put("Strength", 10);
        userStats.put("Creativity", 10);
    }



}
