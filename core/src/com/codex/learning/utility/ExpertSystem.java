package com.codex.learning.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpertSystem {

    private int currentCookie;

    private int[] cookies;
    private int totalUserCookies;
    private int overAllCookies;
    private boolean start;


    private String expertiseLevel;
    private String currentBehavior;

    public ExpertSystem(){
        cookies = new int[16];
        currentBehavior = null;
        currentCookie = 0;
        totalUserCookies = 0;
        overAllCookies = 0;
        start = false;
    }

    // To determine the expert level of the user
    public String updateExpertiseLevel(){

        for(int i: cookies){
            if(cookies[0] == 0){
                start = true;
                break;
            }
            else{
                start = false;
            }
            totalUserCookies = totalUserCookies + i;
            if(i != 0){

                overAllCookies = overAllCookies + 3;
            }
        }

        System.out.println("START = " + start);
        System.out.println("USER COOKIES - " + totalUserCookies);
        System.out.println("OVERALL COOKIES - " + overAllCookies);
        if(start){
            return "Novice";
        }
        else{
            float result = (float) totalUserCookies / overAllCookies;

            if(result >= .95){
                return "Expert";
            }
            else if(result >= .9){
                return "Average";
            }
            else if(result >= .8){
                return "Novice";
            }
            else{
                return "Poor";
            }
        }

    }

    // Write the save file of the user
    public void writeFile(int[] cookies){
        try {
            File file = new File(Constants.SAVE_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                FileWriter fileWriter = new FileWriter(Constants.SAVE_FILE_PATH, false);

                for(int i = 0; i < cookies.length; i++){
                    fileWriter.write(cookies[i] + ",");
                }
                fileWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the save file of the user
    public void readFile(){
        try {
            FileReader fileReader = new FileReader(Constants.SAVE_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split(",");

                for(String i: word){
                    cookies[counter] = Integer.parseInt(i);
                    counter++;
                }
            }

            setExpertiseLevel(updateExpertiseLevel());
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<ArrayList<String>> readDataFirst(){
        try {
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            FileReader fileReader = new FileReader(Constants.DATA_GATHERED_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean header = true;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split(",");
                data.add(new ArrayList<>(Arrays.asList(word)));
            }
            bufferedReader.close();
            fileReader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Stage #, Stage Topic, Cookie Number, Dataset (5), Behavior
    // Input after the game too
    public void writeDataGathering(int stageNumber, String stageTopic, String expertiseLevel, int numberOfCookie, ArrayList<ArrayList<String>> codeRiddleData, ArrayList<ArrayList<String>> minigameData){
        try {
            int counter = 0;
            int length = 0;
            String codeRiddleListSize = String.valueOf(codeRiddleData.size());
            String minigameListSize = String.valueOf(minigameData.size());
            String codeRiddleEngagedPercentage = String.valueOf(calculateEngagedPercentage(codeRiddleData));
            String minigameEngagedPercentage = String.valueOf(calculateEngagedPercentage(minigameData));
            ArrayList<String> replace = new ArrayList<>(Arrays.asList(String.valueOf(stageNumber), stageTopic,
                    expertiseLevel, String.valueOf(numberOfCookie),
                    "ENGAGED", codeRiddleEngagedPercentage, codeRiddleListSize,
                    "ENGAGED", minigameEngagedPercentage, minigameListSize));


            File file = new File(Constants.DATA_GATHERED_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                ArrayList<ArrayList<String>> data = readDataFirst();

                for(ArrayList<String> i: data){
                    if(i.get(0).equals(String.valueOf(stageNumber))){
                        i.clear();
                        break;
                    }
                    else{
                        counter++;
                    }
                }

                data.add(counter, replace);

                FileWriter fileWriter = new FileWriter(Constants.DATA_GATHERED_FILE_PATH, false);

                for(ArrayList<String> arrayList: data){
                    if(arrayList.isEmpty()){
                        continue;
                    }
                    else{
                        for(String i: arrayList){
                            if(length == arrayList.size()){
                                fileWriter.write("\n");
                                length = 0;
                            }
                            length++;
                            fileWriter.write(i + ",");
                        }
                    }
                }
                fileWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGameDataGathered(int stageNumber, String topic, ArrayList<ArrayList<String>> data){

    }

    public int calculateEngagedPercentage(ArrayList<ArrayList<String>> data){
        int total = 0;
        int current = 0;
        float percent;
        if(data.size() == 1){
            if(data.get(0).get(data.size()).equals("ENGAGED"))
                return 100;
            return 0;
        }
        for(int i = 0; i < data.size(); i++){
            System.out.println("I = " + i);
            if(data.get(i).get(data.size()).equals("ENGAGED")){
                total++;
                current++;
            }
            else{
                total++;
            }
        }
        percent = ((float) current/total) * 100;
        return (int) percent;
    }

    public int[] getCookies() {
        return cookies;
    }

    public int getCookies(int index) {
        return cookies[index];
    }

    public void editCookie(int index){
        cookies[index] = getCurrentCookie();
    }

    public void setCookies(int[] cookies) {
        this.cookies = cookies;
    }

    public int getTotalUserCookies() {
        return totalUserCookies;
    }

    public void setTotalUserCookies(int totalUserCookies) {
        this.totalUserCookies = totalUserCookies;
    }

    public String getExpertiseLevel() {
        return expertiseLevel;
    }

    public void setExpertiseLevel(String expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    public String getCurrentBehavior() {
        return currentBehavior;
    }

    public void setCurrentBehavior(String currentBehavior) {
        this.currentBehavior = currentBehavior;
    }

    public int getCurrentCookie() {
        return currentCookie;
    }

    public void setCurrentCookie(int currentCookie) {
        this.currentCookie = currentCookie;
    }
}