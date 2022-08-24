package com.codex.learning.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpertSystem {
    private int[] cookies;
    private int totalCookies;
    private String expertiseLevel;
    private String currentBehavior;


    public ExpertSystem(){
        cookies = new int[17];
        currentBehavior = null;
    }

    public void updateExpertiseLevel(){

    }

    public void writeFile(){
        try {
            File file = new File(Constants.SAVE_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                FileWriter fileWriter = new FileWriter(Constants.SAVE_FILE_PATH, false);
                System.out.println("LENGTHJ - " + cookies.length);
                for(int i = 0; i < cookies.length; i++){
                    cookies[i] = 2;
                    fileWriter.write(cookies[i] + ",");
                }
                fileWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getCookies() {
        return cookies;
    }

    public void setCookies(int[] cookies) {
        this.cookies = cookies;
    }

    public int getTotalCookies() {
        return totalCookies;
    }

    public void setTotalCookies(int totalCookies) {
        this.totalCookies = totalCookies;
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
}
