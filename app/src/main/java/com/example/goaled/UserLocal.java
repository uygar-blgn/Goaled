/**
 * UserLocal: This is a class that stores user information in the local device.
 * Contains the function for updating user properties when a new Accomplishment is entered.
 */

package com.example.goaled;

import android.os.Build;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
    private double totalPI;
    private ArrayList<UserAccomplishment> allAccomplishments;
    private ArrayList<UserGoal> allGoals;
    private ArrayList<UserActivity> allActivities;

    UserLocal() {

        // Define the instance variables
        userStats = new HashMap<String, Double>();
        allAccomplishments = new ArrayList<UserAccomplishment>();
        statMultipliers = new HashMap<String, Double>();

        // Putting dummy values for testing
        totalPI = 100;

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

        allActivities.add(exampleActivity);


        level = 1;
    }

    // Call this every time a new goal is created.
    public void newGoal(UserGoal aGoal) {
        allGoals.add(aGoal);
    }

    // Call this every time a goal is erased.
    public boolean eraseGoal(UserGoal aGoal) {
        return allGoals.remove(aGoal);
    }

    // Call this every time a new activity is defined.
    public void newActivity(UserActivity anActivity) {
        allActivities.add(anActivity);
    }

    // Call this every time an activity is erased
    public void eraseActivity(UserActivity anActivity) {
        allActivities.remove(anActivity);
    }

    // Call this every time a new accomplishment is entered to update all related user details and stats.
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
        double newPrimaryStatValue = currentPrimaryStatValue + calculatePrimaryStatGain(userAccomplishment);
        userStats.put(primaryStat, newPrimaryStatValue);

        double currentSecondaryStatValue = userStats.get(secondaryStat);
        double newSecondaryStatValue = currentSecondaryStatValue + calculateSecondaryStatGain(userAccomplishment);
        userStats.put(secondaryStat, newSecondaryStatValue);


        Random rand = new Random();

        // Increase the xp and, if needed, update the level.
        xp += (int)( userAccomplishment.getPI()  * ( ((double)(rand.nextInt(101) - 90)) / 10.0 ) );
        updateLevel();

    }


    // Returns an ArrayList of accomplishments that have been entered within the entered number of days before the endDate. Helper method.
    private ArrayList<UserAccomplishment> getAccomplishmentsWithinDays(ArrayList<UserAccomplishment> allAccomplishments, int days, LocalDateTime endDate) {

        ArrayList<UserAccomplishment> accomplishmentsWithinDays = new ArrayList<UserAccomplishment>();

        // Checks if API level is enough to use LocalDateTime (needed 26 or above)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            LocalDateTime thresholdDate = endDate.minusDays(days);

            // This evades making a call to size() each iteration of the for loop.
            int allAccomplishmentSize = allAccomplishments.size();

            for (int i = 0; i < allAccomplishmentSize; i++) {

                UserAccomplishment anAccomplishment = allAccomplishments.get(i);

                LocalDateTime accomplishedDate = anAccomplishment.getAccomplishedDate();

                if ( accomplishedDate.isAfter( thresholdDate ) && endDate.isAfter( accomplishedDate ) ) {
                    accomplishmentsWithinDays.add(anAccomplishment);
                }

            }

        }

        return accomplishmentsWithinDays;
    }

    // Helper method. Returns true if two LocalDateTime objects represent the same day, regardless of hour or other finer details.
    private static boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int year1 = date1.getYear();
            int day1 = date1.getDayOfYear();

            int year2 = date2.getYear();
            int day2 = date2.getDayOfYear();

            return (year1 == year2) && (day1 == day2);
        }

        return false; // To shut the compiler up.
    }

    // Helper method.
    private double calculatePrimaryStatGain(UserAccomplishment anAccomplishment) {
        String primaryStat = anAccomplishment.getUserActivity().getPrimaryStat();
        return anAccomplishment.getPI() * 0.7 * statMultipliers.get(primaryStat);
    }

    // Helper method.
    private double calculateSecondaryStatGain(UserAccomplishment anAccomplishment) {
        String secondaryStat = anAccomplishment.getUserActivity().getSecondaryStat();
        return anAccomplishment.getPI() * 0.4 * statMultipliers.get(secondaryStat);
    }

    public void updateLevel() {

        if (xp >= xpForNextLevel) {
            level++;
            xp = xpForNextLevel - xp;

            xpForNextLevel = (int)( 90 * ( Math.pow(level, 0.1) * Math.log(level) ));


        }

    }

    // One of the most important functions.
    // Returns the user's progress on a goal on the date entered as a parameter.
    public double getGoalProgress(UserGoal goal, LocalDateTime date) {

        ArrayList<UserAccomplishment> filteredAccomplishments = new ArrayList<UserAccomplishment>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            LocalDateTime endDate = date.withHour(23).withMinute(59).withSecond(0).withNano(0);

            switch (goal.getGoalFrequency()) {

                case MONTHLY:

                    filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, 30, endDate);

                    break;

                case WEEKLY:
                    filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, 7, endDate);
                    break;

                case DAILY:
                    filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, 1, endDate);
                    break;

                default:
                    break;
            }
        }

        double aimedGain = goal.getGoalAmount();
        double currentGain = 0;
        int accomplishmentCount = filteredAccomplishments.size();

        switch (goal.getGoalType()) {

            case UserActivityWithHours:

                for (int i = 0; i < accomplishmentCount; i++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(i);

                    if ( anAccomplishment.getUserActivity() == goal.getUserActivity() ) {

                        currentGain += anAccomplishment.getHours();

                    }

                }


                break;

            case UserActivityWithPI:

                for (int i = 0; i < accomplishmentCount; i++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(i);

                    if ( anAccomplishment.getUserActivity() == goal.getUserActivity() ) {
                        currentGain += anAccomplishment.getPI();
                    }

                }

                break;

            case OnlyStat:

                for (int i = 0; i < accomplishmentCount; i++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(i);

                    if ( anAccomplishment.getUserActivity().getPrimaryStat().equals( goal.getStat() ) ) {
                        currentGain += calculatePrimaryStatGain(anAccomplishment);
                    }

                    if ( anAccomplishment.getUserActivity().getSecondaryStat().equals( goal.getStat() ) ) {
                        currentGain += calculateSecondaryStatGain(anAccomplishment);
                    }

                }

                break;

            case OnlyPI:

                for (int i = 0; i < accomplishmentCount; i++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(i);
                    currentGain += anAccomplishment.getPI();

                }

                break;

            default:
                break;
        }

        double progress = (currentGain / aimedGain) * 100;

        if (progress > 100)
            progress = 100.0;

        return progress;

    }


    /*
    Below are functions for the statistics page.
    Each function returns an ArrayList<Double> or ArrayList<Integer>, except getStatsDistributionWithinDays(int days) which returns HashMap<String, Double>.
    FOR THE FUNCTIONS THAT RETURN AN ARRAYLIST, the index of the returned ArrayList represents 'days before.'
    For example, if you make the following call,
    result = getStatWithinDays("Endurance", 7),
    result.get(0) is a Double representing how much Endurance the user obtained "0 days before" or today
    result.get(1) is a Double representing how much Endurance the user obtained "1 day before" or yesterday, and yesterday alone
    result.get(2) is a Double representing how much Endurance the user obtained "2 days before", and on that day alone
    And so on, up to and including result.get(6).
     */
    public ArrayList<Double> getStatWithinDays(String stat, int days) {
        ArrayList<Double> statOfDays = new ArrayList<Double>();
        ArrayList<UserAccomplishment> filteredAccomplishments = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, days, LocalDateTime.now());
        }

        int accomplishmentCount = filteredAccomplishments.size();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            for (int i = 0; i < days; i++) {
                LocalDateTime consideredDate = LocalDateTime.now().minusDays(i);
                double totalStatThatDay = 0;

                for (int j = 0; j < accomplishmentCount; j++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(j);

                    if ( isSameDay( anAccomplishment.getAccomplishedDate(), consideredDate ) ) {

                        UserActivity activityOfAccomplishment = anAccomplishment.getUserActivity();

                        if ( activityOfAccomplishment.getPrimaryStat().equals(stat) ) {
                            totalStatThatDay += calculatePrimaryStatGain(anAccomplishment);
                        }

                        if ( activityOfAccomplishment.getSecondaryStat().equals(stat) ) {
                            totalStatThatDay += calculateSecondaryStatGain(anAccomplishment);
                        }

                    }

                }

                statOfDays.add(totalStatThatDay);
            }
        }
        return statOfDays;
    }

    public ArrayList<Double> getPIWithinDays(int days) {

        ArrayList<Double> piOfDays = new ArrayList<Double>();
        ArrayList<UserAccomplishment> filteredAccomplishments = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, days, LocalDateTime.now());
        }

        int accomplishmentCount = filteredAccomplishments.size();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            for (int i = 0; i < days; i++) {
                LocalDateTime consideredDate = LocalDateTime.now().minusDays(i);
                double totalPIThatDay = 0;

                for (int j = 0; j < accomplishmentCount; j++) {

                    UserAccomplishment anAccomplishment = filteredAccomplishments.get(j);

                    if ( isSameDay( anAccomplishment.getAccomplishedDate(), consideredDate ) ) {

                        totalPIThatDay += anAccomplishment.getPI();

                    }

                }

                piOfDays.add(totalPIThatDay);
            }


        }

    return piOfDays;
    }

    // The Integer returned from this function represents percentage progress of all daily goals overall on that day.
    // result = getDailyGoalsProgressWithinDays(7)
    // If result.get(6) is equal to 58, that means the user accomplished 58 percent of all their daily goals 6 days before now.
    public ArrayList<Double> getDailyGoalsProgressWithinDays(int days) {
        ArrayList<UserGoal> allDailyGoals = new ArrayList<UserGoal>();
        int goalCount = allGoals.size();

        ArrayList<Double> goalProgressWithinDays = new ArrayList<Double>();


        for (int i = 0; i < goalCount; i++) {
            UserGoal aGoal = allGoals.get(i);

            if ( aGoal.getGoalFrequency() == UserGoal.Frequency.DAILY )
                allDailyGoals.add(aGoal);
        }

        int dailyGoalCount = allDailyGoals.size();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            for (int i = 0; i < days; i++) {

                LocalDateTime consideredDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(0).withNano(0).minusDays(i);

                double totalProgress = 0;

                for (int j = 0; j < dailyGoalCount; j++) {
                    totalProgress += getGoalProgress(allDailyGoals.get(j), consideredDate);
                }

                double overallProgress = totalProgress / dailyGoalCount;
                goalProgressWithinDays.add(overallProgress);

            }
        }

        return goalProgressWithinDays;
    }

    // result = getStatDistributionWithinDays(7)
    // result.get("Wisdom") is a Double representing the total amount of Wisdom the user obtained today AND the
    // previous 6 days combined.
    public HashMap<String, Double> getStatDistributionWithinDays(int days) {

        HashMap<String, Double> statDistribution = new HashMap<String, Double>();

        statDistribution.put("Endurance", 0.0);
        statDistribution.put("Wisdom", 0.0);
        statDistribution.put("Strength", 0.0);
        statDistribution.put("Intellect", 0.0);
        statDistribution.put("Creativity", 0.0);

        ArrayList<UserAccomplishment> filteredAccomplishments = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            filteredAccomplishments = getAccomplishmentsWithinDays(allAccomplishments, days, LocalDateTime.now());
        }

        int accomplishmentCount = filteredAccomplishments.size();

        for (int i = 0; i < accomplishmentCount; i++) {

            UserAccomplishment anAccomplishment = filteredAccomplishments.get(i);

            String primaryStat = anAccomplishment.getUserActivity().getPrimaryStat();
            double primaryStatGain = calculatePrimaryStatGain(anAccomplishment);
            double newPrimaryStat = statDistribution.get(primaryStat) + primaryStatGain;
            statDistribution.put(primaryStat, newPrimaryStat);

            String secondaryStat = anAccomplishment.getUserActivity().getSecondaryStat();
            double secondaryStatGain = calculateSecondaryStatGain(anAccomplishment);
            double newSecondaryStat = statDistribution.get(secondaryStat) + secondaryStatGain;
            statDistribution.put(secondaryStat, newSecondaryStat);

        }

        return statDistribution;
    }


}
