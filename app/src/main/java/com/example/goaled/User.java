package com.example.goaled;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    public String fullName, age, email;
    public HashMap<String, Double> userStats;
    public HashMap<String, Double> statMultipliers;
    public int level;
    public int xp;
    public int xpForNextLevel;
    public double totalPI;
    public ArrayList<UserAccomplishment> allAccomplishments;
    public ArrayList<UserGoal> allGoals;
    public ArrayList<UserActivity> allActivities;
    public String Uid;

    public User(){

    }

    public User(String fullName, String age, String email){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
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

    public String getUid() {
        return Uid;
    }
}
