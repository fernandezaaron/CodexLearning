package com.codex.learning.utility;

public class StageSelector {
    int stageNumber;
    int numberOfCookies;
    String stageTopic;
    boolean allowToPlay;

    public StageSelector(){
        numberOfCookies = 0;
        allowToPlay = false;

    }


    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStageTopic() {
        return stageTopic;
    }

    public void setStageTopic(String stageTopic) {
        this.stageTopic = stageTopic;
    }

    public int getNumberOfCookies() {
        return numberOfCookies;
    }

    public void setNumberOfCookies(int numberOfCookies) {
        this.numberOfCookies = numberOfCookies;
    }

    public boolean isAllowToPlay() {
        return allowToPlay;
    }

    public void setAllowToPlay(boolean allowToPlay) {
        this.allowToPlay = allowToPlay;
    }
}
