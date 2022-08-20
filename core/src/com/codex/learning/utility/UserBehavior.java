package com.codex.learning.utility;
import java.util.HashMap;


public class UserBehavior {
    private String currentBehavior;

    private int movementDetected;
    private int timeConsumption;
    private int numberOfAttempt;
    private int numberOfError;
    private int numberOfBlockInteraction;
    private HashMap<String, Integer> dictionary;


    public UserBehavior(){
        currentBehavior = null;
        dictionary.put("VERY LOW", 1);
        dictionary.put("LOW", 1);
        dictionary.put("MEDIUM", 2);
        dictionary.put("HIGH", 3);
        dictionary.put("VERY HIGH", 3);
        dictionary.put("YES", 1);
        dictionary.put("NO", 2);
    }
    public void updateBehavior(){

    }

    public void convertBehavior(String movementDetected, String timeConsumption, String numberOfAttempt, String numberOfError, String numberOfBlockInteraction){

    }

    public String getCurrentBehavior() {
        return currentBehavior;
    }

    public void setCurrentBehavior(String currentBehavior) {
        this.currentBehavior = currentBehavior;
    }
}
