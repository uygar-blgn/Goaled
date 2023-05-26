/**
 * UserAccomplishment: Contains the information for
 * "I did UserActivity x for y hours, at Date z"
 */
package com.example.goaled;
import android.os.Build;

import java.time.LocalDateTime;

public class UserAccomplishment {

    // Variables
    private UserActivity userActivity;
    private double hours;
    private double intensity;
    private LocalDateTime accomplishedDate;
    private double PI;

    // Constructor
    UserAccomplishment(UserActivity userActivity, double hours, double intensity) {

        this.userActivity = userActivity;
        this.hours = hours;
        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.accomplishedDate = LocalDateTime.now();
        }
        this.intensity = intensity;

        // Difficulty must be between 1 and 10
        PI = ( userActivity.getDifficulty() / 10 ) * hours * intensity;

    }

    // Getter methods
    public UserActivity getUserActivity() {
        return userActivity;
    }

    public double getHours() {
        return hours;
    }

    public double getPI() {
        return PI;
    }

    public LocalDateTime getAccomplishedDate() {
        return accomplishedDate;
    }

    public void setAccomplishedDate(LocalDateTime newDate) {
        this.accomplishedDate = newDate;
    }
}
