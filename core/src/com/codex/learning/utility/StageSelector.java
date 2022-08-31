package com.codex.learning.utility;

public class StageSelector {
    int stageNumber = 0;
    int[] currentStage;
    int[] numberOfCookies;
    boolean[] allowToPlay;


    public StageSelector(){
        currentStage = new int[17];
        numberOfCookies = new int[17];
        allowToPlay = new boolean[17];


        for(int i=0 ;i<currentStage.length; i++){
            numberOfCookies[i] = 0;
            allowToPlay[i] = false;
        }
    }

    public String map(){
        if (stageNumber >= 1 && stageNumber < 5){
            return "1";
        }
        else if(stageNumber >= 5 && stageNumber < 12){
            return "2";
        }
        else{
            return "3";
        }

    }

    public int[] getCurrentStage() {
        return currentStage;
    }

    public int getCurrentStage(int index){
        return currentStage[index];
    }

    public int getStageNumber(){
        return stageNumber;
    }

    public int setStageNumber(int stageNumber){
        return this.stageNumber = stageNumber;
    }

    public void setCurrentStage(int index) {
        this.currentStage[index] = getStageNumber();
    }

    public int[] getNumberOfCookies() {
        return numberOfCookies;
    }

    public int getNumberOfCookies(int index) {
        return numberOfCookies[index];
    }

    public void setNumberOfCookies(int index, int numberOfCookies) {
        this.numberOfCookies[index] = numberOfCookies;
    }

    public boolean[] getAllowToPlay() {
        return allowToPlay;
    }

    public boolean getAllowToPlay(int index){
        return allowToPlay[index];
    }

    public void setAllowToPlay(int index, boolean allowToPlay) {
        this.allowToPlay[index] = allowToPlay;
    }




}
