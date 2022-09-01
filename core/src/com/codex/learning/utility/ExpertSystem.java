package com.codex.learning.utility;

import java.io.*;

public class ExpertSystem {

    private int currentCookie;

    private int[] cookies;
    private int totalUserCookies;
    private int overAllCookies;


    private String expertiseLevel;
    private String currentBehavior;

    public ExpertSystem(){
        cookies = new int[17];
        currentBehavior = null;
        currentCookie = 0;
        totalUserCookies = 0;
        overAllCookies = 0;
    }

    public String updateExpertiseLevel(){
        for(int i: cookies){
            totalUserCookies = totalUserCookies + i;
            if(i != 0){
                overAllCookies = overAllCookies + 3;
            }
        }
        int a = (int) (overAllCookies * .69);
        int b = (int) (overAllCookies * .80);
        int c = (int) (overAllCookies * .90);

        if(totalUserCookies < a){
            return "Poor";
        }
        else if(totalUserCookies < b){
            return "Novice";
        }
        else if(totalUserCookies < c){
            return "Average";
        }
        else{
            return "Expert";
        }
    }

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
