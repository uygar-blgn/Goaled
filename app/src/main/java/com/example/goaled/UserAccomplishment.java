/**
 * UserAccomplishment: Contains the information for
 * "I did UserActivity x for y hours, at Date z"
 */
package com.example.goaled;
import java.util.Date;

public class UserAccomplishment {

    // Variables
    private UserActivity userActivity;
    private double hours;
    private double intensity;
    private Date accomplishedDate;
    private int PI;

    // Constructor
    UserAccomplishment(UserActivity userActivity, double hours, double intensity) {

        this.userActivity = userActivity;
        this.hours = hours;
        this.accomplishedDate = new Date();
        this.intensity = intensity;

        // Difficulty must be between 1 and 10
        PI = (int)(( userActivity.getDifficulty() / 10 ) * hours * intensity);

    }

    // Getter methods
    public UserActivity getUserActivity() {
        return userActivity;
    }

    public double getHours() {
        return hours;
    }

    public int getPI() {
        return PI;
    }

    public Date getAccomplishedDate() {
        return accomplishedDate;
    }
}
