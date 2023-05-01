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
    private Date accomplishedDate;

    // Constructor
    UserAccomplishment(UserActivity userActivity, double hours, Date accomplishedDate) {

        this.userActivity = userActivity;
        this.hours = hours;
        this.accomplishedDate = accomplishedDate;

    }

    // Getter methods
    public UserActivity getUserActivity() {
        return userActivity;
    }

    public double getHours() {
        return hours;
    }

    public Date getAccomplishedDate() {
        return accomplishedDate;
    }
}
