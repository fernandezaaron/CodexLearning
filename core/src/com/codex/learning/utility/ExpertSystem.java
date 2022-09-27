package com.codex.learning.utility;

import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
                FileWriter fileWriter = new FileWriter(Constants.SAVE_FILE_PATH, false);
                for(int i = 0; i < cookies.length; i++){
                    fileWriter.write(0 + ",");
                }
                fileWriter.close();
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
            File file = new File(Constants.SAVE_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(Constants.SAVE_FILE_PATH, false);
                for(int i = 0; i < cookies.length; i++){
                    fileWriter.write(0 + ",");
                }
                fileWriter.close();
            }
            FileReader fileReader = new FileReader(file);
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
//            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<ArrayList<String>> readDataFirst(String path){
        try {
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            FileReader fileReader = new FileReader(path);
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
            String codeRiddleListSize = String.valueOf(codeRiddleData.size() - 1);
            String minigameListSize = String.valueOf(minigameData.size() - 1);
            String codeRiddleEngagedPercentage = String.valueOf(calculateEngagedPercentage(codeRiddleData));
            String minigameEngagedPercentage = String.valueOf(calculateEngagedPercentage(minigameData));
            ArrayList<String> replace = new ArrayList<>(Arrays.asList(String.valueOf(stageNumber), stageTopic,
                    expertiseLevel, String.valueOf(numberOfCookie),
                    "ENGAGED", codeRiddleEngagedPercentage + "%", codeRiddleListSize,
                    "ENGAGED", minigameEngagedPercentage + "%", minigameListSize));


            File file = new File(Constants.DATA_GATHERED_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(Constants.DATA_GATHERED_FILE_PATH, false);
                boolean header = true;
                for(int i = 0; i < 17; i++){
                    if(header){
                        fileWriter.write("Stage Number,Topic,Expertise Level,Number of Cookies,Engaged,Percentage,CodeRiddle Total Data,Engaged,Percentage, Minigame Total Data,\n");
                        header = false;
                    }
                    else{
                        fileWriter.write(i + ",-,-,-,-,-,-,-,-,-,\n");
                    }
                }
                fileWriter.close();
            }
            ArrayList<ArrayList<String>> data = readDataFirst(Constants.DATA_GATHERED_FILE_PATH);
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
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGameDataGathered(boolean flag, String path, int stageNumber, String topic, ArrayList<ArrayList<String>> newData) {
        try {
            int length = 0;

            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(path, false);
                if(flag){
                    fileWriter.write("0Stage Number,Topic,Iteration,Time Consumption,Number of Error,Behavior,\n");
                }
                else{
                    fileWriter.write("0Stage Number,Topic,Iteration,Movement,Time Consumption,Number of Attempts,Number of Block Interaction,Behavior,\n");
                }
            }
            ArrayList<ArrayList<String>> data = readDataFirst(path);
            for(int i = 0; i < newData.size(); i++){
                newData.get(i).add(0, String.valueOf(stageNumber));
                newData.get(i).add(1, topic);
                newData.get(i).add(2, String.valueOf(i + 1));
            }
            if(!data.isEmpty()){
                for(ArrayList<String> i: data){
                    if(i.get(0).equals(String.valueOf(stageNumber))){
                        i.clear();
                    }
                }
            }
            for(int i = 0; i < data.size(); i++){
                if(data.get(i).isEmpty()){
                    continue;
                }
                else{
                    newData.add(data.get(i));
                }
            }
            Collections.sort(newData, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> a, ArrayList<String> b) {
                    return a.get(0).compareTo(b.get(0));
                }
            });
            FileWriter fileWriter = new FileWriter(path, false);
            for (ArrayList<String> arrayList : newData) {
                for (String i : arrayList) {
                    if(length == arrayList.size()){
                        fileWriter.write("\n");
                        length = 0;
                    }
                    length++;
                    fileWriter.write(i + ",");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int calculateEngagedPercentage(ArrayList<ArrayList<String>> data){
        int total = 0;
        int current = 0;
        float percent;
        if(data.size() == 2){
            if(data.get(1).get(data.get(0).size() - 1).equals("ENGAGED"))
                return 100;
            return 0;
        }
        for(int i = 0; i < data.size(); i++){
            if(i == 0){
                continue;
            }
            else if(data.get(i).get(data.get(i).size() - 1).equals("ENGAGED")){
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