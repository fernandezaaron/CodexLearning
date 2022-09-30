package com.codex.learning.utility.decisiontree;

public class MLDataSet {
    private float maxTimer;
    private float currentTime;
    private String time;
    private int currentNumberOfErrors;
    private String numberOfErrors;
    private String movement;
    private int currentNumberOfAttempts;
    private String numberOfAttempts;
    private int currentNumberOfBlockInteraction;
    private String numberOfBlockInteraction;
    public MLDataSet(){
        maxTimer = 10f;
        currentTime = 0;
        time = null;
        currentNumberOfErrors = 0;
        numberOfErrors = null;
        movement = null;
        currentNumberOfAttempts = 0;
        numberOfAttempts = null;
        currentNumberOfBlockInteraction = 0;
        numberOfBlockInteraction = null;
    }

    public String checkNumberOfBlockInteractionRule(int numberOfBlockInteraction){
        if(numberOfBlockInteraction <= 10)
            return "1";
        else if(numberOfBlockInteraction <= 20)
            return "2";
        return "3";
    }

    public String convertNumberOfAttempt(int numberOfAttempt){
        if(numberOfAttempt <= 1)
            return "1";
        else if(numberOfAttempt <= 3)
            return "2";
        return "3";
    }

    public String checkTimeConsumption(int timer){
        if(timer <= 80)
            return "1";
        else if(timer <= 160)
            return "2";
        return "3";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(String numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(String numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getNumberOfBlockInteraction() {
        return numberOfBlockInteraction;
    }

    public void setNumberOfBlockInteraction(String numberOfBlockInteraction) {
        this.numberOfBlockInteraction = numberOfBlockInteraction;
    }

    public float getMaxTimer() {
        return maxTimer;
    }

    public void setMaxTimer(float maxTimer) {
        this.maxTimer = maxTimer;
    }

    public int getCurrentNumberOfErrors() {
        return currentNumberOfErrors;
    }

    public void setCurrentNumberOfErrors(int currentNumberOfErrors) {
        this.currentNumberOfErrors = currentNumberOfErrors;
    }

    public int getCurrentNumberOfAttempts() {
        return currentNumberOfAttempts;
    }

    public void setCurrentNumberOfAttempts(int currentNumberOfAttempts) {
        this.currentNumberOfAttempts = currentNumberOfAttempts;
    }

    public int getCurrentNumberOfBlockInteraction() {
        return currentNumberOfBlockInteraction;
    }

    public void setCurrentNumberOfBlockInteraction(int currentNumberOfBlockInteraction) {
        this.currentNumberOfBlockInteraction = currentNumberOfBlockInteraction;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }
}
