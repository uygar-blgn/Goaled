/**
 * UserLocal: This is a class that stores user information in the local device.
 * Contains the function for updating user properties when a new Accomplishment is entered.
 */

package com.example.goaled;

import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.time.LocalDateTime;

import java.lang.Math;

public class UserLocal implements Serializable {

    // Instance Variables
    private String fullName, age, email;
    private HashMap<String, Double> userStats;
    private HashMap<String, Double> statMultipliers;
    private int level;
    private int xp;
    private int xpForNextLevel;
    private double totalPI;
    private ArrayList<UserAccomplishment> allAccomplishments;
    private ArrayList<UserGoal> allGoals;
    private ArrayList<UserActivity> allActivities;
    private String uid;
    private String userClass;
    private boolean firstTime;

    UserLocal()
    {

    }

    // Constructor for populating UserLocal with data fetched from Firebase
    UserLocal(String email, String fullName, String age, String userClass, long level, long xp,
              long xpForNextLevel, double totalPI, String uid, HashMap<String, Double> userStats,
              HashMap<String, Double> statMultipliers, ArrayList<HashMap<String, ?>> allAccomplishments,
              ArrayList< HashMap<String, ?> > allGoals, ArrayList< HashMap<String, ?> > allActivities, boolean firstTime) {

        // Checks API version to be able to use LocalDateTime
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            this.email = email;
            this.fullName = fullName;
            this.age = age;
            this.userClass = userClass;
            this.level = (int) level;
            this.xp = (int) xp;
            this.xpForNextLevel = (int) xpForNextLevel;
            this.totalPI = totalPI;
            this.uid = uid;
            this.statMultipliers = statMultipliers;
            this.userStats = userStats;
            this.allActivities = new ArrayList<UserActivity>();
            this.allGoals = new ArrayList<UserGoal>();
            this.allAccomplishments = new ArrayList<UserAccomplishment>();
            this.firstTime = firstTime;

            int accomplishmentCount = allAccomplishments.size();
            int activityCount = allActivities.size();
            int goalCount = allGoals.size();

            // In order to understand the code below, you need to know how data is stored in Firebase.
            // Almost everything is stored as a HashMap<String, ?> which we need to process to populate the
            // fields of UserLocal.

            // Populate activities.
            for (int i = 0; i < activityCount; i++) {
                HashMap<String, ?> activityHashMap = allActivities.get(i);
                newActivity(new UserActivity((String) activityHashMap.get("name"), (String) activityHashMap.get("primaryStat"),
                        (String) activityHashMap.get("secondaryStat"), (Long) activityHashMap.get("difficulty")/1.0));
            }

            // Populate accomplishments.
            for (int i = 0; i < accomplishmentCount; i++) {

                // Assigned to an arbitrary UserActivity to shut the compiler up.
                UserActivity activityOfAccomplishment = new UserActivity("This is fake", "Intellect", "Endurance", 6);

                HashMap<String, ?> accomplishmentHashMap = allAccomplishments.get(i);
                String checkForName = (String) ((HashMap<String, ?>) accomplishmentHashMap.get("userActivity")).get("name");

                HashMap<String, ?> accomplishedDate = (HashMap<String, ?>) accomplishmentHashMap.get("accomplishedDate");
                LocalDateTime correctDate = LocalDateTime.of(((Long)accomplishedDate.get("year")).intValue(), ((Long)accomplishedDate.get("monthValue")).intValue(),
                        ((Long)accomplishedDate.get("dayOfMonth")).intValue(), ((Long)accomplishedDate.get("hour")).intValue(), ((Long)accomplishedDate.get("minute")).intValue(),
                        ((Long)accomplishedDate.get("second")).intValue());

                        int activityIndex = getIndexOfActivityWithName(allActivities, checkForName);

                        HashMap<String, ?> _activity = allActivities.get(activityIndex);
                        activityOfAccomplishment = new UserActivity((String) _activity.get("name"),
                                (String) _activity.get("primaryStat"), (String) _activity.get("secondaryStat"), (Long) _activity.get("difficulty")/1.0);


                UserAccomplishment _accomplishment = new UserAccomplishment(activityOfAccomplishment, (Long) accomplishmentHashMap.get("hours")/1.0, (Long) accomplishmentHashMap.get("intensity")/1.0);
                _accomplishment.setAccomplishedDate(correctDate);

                oldAccomplishment(_accomplishment);

            }

            // Populate goals
            for (int i = 0; i < goalCount; i++) {

                // Set to an arbitrary goal to shut the compiler up.
                UserGoal _goal = new UserGoal(5, UserGoal.Frequency.WEEKLY);

                HashMap<String, ?> goalHashMap = allGoals.get(i);

                HashMap<String, ?> goalDate = (HashMap<String, ?>) goalHashMap.get("timeCreated");

                LocalDateTime correctDate = LocalDateTime.of(((Long)goalDate.get("year")).intValue(), ((Long)goalDate.get("monthValue")).intValue(),
                        ((Long)goalDate.get("dayOfMonth")).intValue(), ((Long)goalDate.get("hour")).intValue(), ((Long)goalDate.get("minute")).intValue(),
                        ((Long)goalDate.get("second")).intValue());

                String goalType = (String) goalHashMap.get("goalType");
                String goalFrequency = (String) goalHashMap.get("goalFrequency");


                // Assigned to an arbitrary value to shut the compiler up.
                UserGoal.Frequency _frequency = UserGoal.Frequency.DAILY;

                // Assigned to an arbitrary value to shut the compiler up initially only.
                UserActivity _activityOfGoal = new UserActivity( "name", "Endurance",
                        "Intellect", 3.5 );

                if ( goalType.equals("UserActivityWithPI") ) {

                    HashMap<String, ?> userActivityMap = (HashMap<String, ?>) goalHashMap.get("userActivity");

                    String checkForName = (String) userActivityMap.get("name");

                    int activityIndex = getIndexOfActivityWithName(allActivities, checkForName);

                    HashMap<String, ?> _activity = allActivities.get(activityIndex);

                    _activityOfGoal = new UserActivity( (String) _activity.get("name"), (String) _activity.get("primaryStat"),
                            (String) _activity.get("secondaryStat"), (Long) _activity.get("difficulty")/1.0 );

                    _goal = new UserGoal(_activityOfGoal, (Long) goalHashMap.get("goalAmount")/1.0,
                            "PI", _frequency);
                }

                if ( goalType.equals("UserActivityWithHours") ) {

                    HashMap<String, ?> userActivityMap = (HashMap<String, ?>) goalHashMap.get("userActivity");

                    String checkForName = (String) userActivityMap.get("name");

                    int activityIndex = getIndexOfActivityWithName(allActivities, checkForName);

                    HashMap<String, ?> _activity = allActivities.get(activityIndex);

                    _activityOfGoal = new UserActivity( (String) _activity.get("name"), (String) _activity.get("primaryStat"),
                            (String) _activity.get("secondaryStat"), (Long) _activity.get("difficulty")/1.0 );

                    _goal = new UserGoal(_activityOfGoal, (Long) goalHashMap.get("goalAmount")/1.0,
                            "HOURS", _frequency);

                }

                if ( goalType.equals("OnlyStat") ) {

                    _goal = new UserGoal((Long) goalHashMap.get("goalAmount")/1.0, (String) goalHashMap.get("stat"),
                            _frequency);

                }

                if ( goalType.equals("OnlyPI") ) {

                    _goal = new UserGoal((Long) goalHashMap.get("goalAmount")/1.0, _frequency);

                }

                if ( goalFrequency.equals("DAILY") ) {
                    _frequency = UserGoal.Frequency.DAILY;
                }

                if ( goalFrequency.equals("WEEKLY") ) {
                    _frequency = UserGoal.Frequency.WEEKLY;
                }

                if ( goalFrequency.equals("MONTHLY") ) {
                    _frequency = UserGoal.Frequency.MONTHLY;
                }



                _goal.setCreatedDate(correctDate);
                newGoal(_goal);

            }

        }
    }

    // Helper function for populating UserLocal with Firebase data
    public int getIndexOfActivityWithName(ArrayList<HashMap<String, ?>> allActivities, String checkForName) {

        int activityCount = allActivities.size();

        for (int i = 0; i < activityCount; i++) {

            if (checkForName.equals( allActivities.get(i).get("name") )) {
                return i;
            }

        }

        return -1;
    }

    public void updateStatMultipliers() {

        this.statMultipliers.put("Intellect", 1.000001);
        this.statMultipliers.put("Endurance", 1.000001);
        this.statMultipliers.put("Wisdom", 1.000001);
        this.statMultipliers.put("Strength", 1.000001);
        this.statMultipliers.put("Creativity", 1.100001);

        if ( userClass.equals("Strength") ) {
            this.statMultipliers.put("Strength", 1.300001);
        }

        if ( userClass.equals("Endurance") ) {
            this.statMultipliers.put("Endurance", 1.300001);
        }

        if ( userClass.equals("Wisdom") ) {
            this.statMultipliers.put("Wisdom", 1.300001);
        }

        if ( userClass.equals("Creativity") ) {
            this.statMultipliers.put("Creativity", 1.300001);
        }

        if ( userClass.equals("Intellect") ) {
            this.statMultipliers.put("Intellect", 1.300001);
        }

    }

    UserLocal(String Uid, String email, String age, String fullName) {

        this.uid = Uid;
        this.email = email;
        this.age = age;
        this.fullName = fullName;
        this.firstTime = true;

        // Define the instance variables
        userStats = new HashMap<String, Double>();
        allAccomplishments = new ArrayList<UserAccomplishment>();
        statMultipliers = new HashMap<String, Double>();
        allGoals = new ArrayList<UserGoal>();
        allActivities = new ArrayList<UserActivity>();

        // Putting dummy values for testing
        totalPI = 100.000001;

        statMultipliers.put("Intellect", 1.000001);
        statMultipliers.put("Endurance", 1.300001);
        statMultipliers.put("Wisdom", 1.000001);
        statMultipliers.put("Strength", 1.600001);
        statMultipliers.put("Creativity", 1.100001);

        userStats.put("Intellect", 10.000001);
        userStats.put("Endurance", 10.000001);
        userStats.put("Wisdom", 10.000001);
        userStats.put("Strength", 10.000001);
        userStats.put("Creativity", 10.000001);

        UserActivity exampleActivity = new UserActivity("Reading a book", "Wisdom", "Endurance", 5);
        UserGoal exampleGoal = new UserGoal(exampleActivity, 5, "PI", UserGoal.Frequency.DAILY);

        UserAccomplishment exampleAccomplishment = new UserAccomplishment(exampleActivity, 1, 10);

        newAccomplishment(exampleAccomplishment);

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

    // Call this function when you are populating the UserLocal with accomplishments from Firebase.
    // This does not increase level or xp.
    public void oldAccomplishment(UserAccomplishment userAccomplishment) {
        allAccomplishments.add(userAccomplishment);
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
        xp += (int)( userAccomplishment.getPI()  * ( ((rand.nextInt(10)) + 1)) );
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


    public void setFirstTime(boolean isFirstTime) {
        this.firstTime = isFirstTime;
    }

    public void setUserClass(String newClass) {
        this.userClass = newClass;
    }

    public boolean getFirstTime() {
        return this.firstTime;
    }

    public HashMap<String, Double> getUserStats() {
        return userStats;
    }

    public HashMap<String, Double> getStatMultipliers() {
        return statMultipliers;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpForNextLevel() {
        return xpForNextLevel;
    }

    public double getTotalPI() {
        return totalPI;
    }

    public ArrayList<UserAccomplishment> getAllAccomplishments() {
        return allAccomplishments;
    }

    public ArrayList<UserGoal> getAllGoals() {
        return allGoals;
    }

    public ArrayList<UserActivity> getAllActivities() {
        return allActivities;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }
}
