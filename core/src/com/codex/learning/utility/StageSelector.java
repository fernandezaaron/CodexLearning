package com.codex.learning.utility;

public class StageSelector {
    int stageNumber = 0;
    int[] currentStage;
    int[] numberOfCookies;
    String stageTopic;
    boolean allowToPlay;

    public StageSelector(){
        currentStage = new int[17];
        numberOfCookies = new int[17];
        allowToPlay = false;

    }

    public int[] getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int index, int stageNumber) {
        this.currentStage[index] = stageNumber;
    }

    public int[] getNumberOfCookies() {
        return numberOfCookies;
    }

    public void setNumberOfCookies(int[] numberOfCookies) {
        this.numberOfCookies = numberOfCookies;
    }

    public String getStageTopic() {
        return stageTopic;
    }

    public void setStageTopic(String stageTopic) {
        this.stageTopic = stageTopic;
    }

//    public int getNumberOfCookies() {
//        return numberOfCookies;
//    }
//
//    public void setNumberOfCookies(int numberOfCookies) {
//        this.numberOfCookies = numberOfCookies;
//    }

    public boolean isAllowToPlay() {
        return allowToPlay;
    }

    public void setAllowToPlay(boolean allowToPlay) {
        this.allowToPlay = allowToPlay;
    }
}
