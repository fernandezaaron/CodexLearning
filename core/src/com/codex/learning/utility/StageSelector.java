package com.codex.learning.utility;

public class StageSelector {
    int stageMap = 0;
    int[] currentStage;
    int[] numberOfCookies;
    boolean[] allowToPlay;


    public StageSelector(){
        currentStage = new int[16];
        numberOfCookies = new int[16];
        allowToPlay = new boolean[16];


        for(int i=0 ;i<currentStage.length; i++){
            numberOfCookies[i] = 0;
            allowToPlay[i] = false;
        }
    }

    public String map(){
        if (stageMap >= 1 && stageMap < 5){
            return "1";
        }
        else if(stageMap >= 5 && stageMap < 11){
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

    public int getStageMap(){
        return stageMap;
    }

    public int setStageMap(int stageMap){
        return this.stageMap = stageMap;
    }

    public void setCurrentStage(int index) {
        this.currentStage[index] = getStageMap();
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
