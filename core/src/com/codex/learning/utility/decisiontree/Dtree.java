package com.codex.learning.utility.decisiontree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;

public class Dtree {

    private MLThread mlThread;

    public Dtree(){
        mlThread = new MLThread();
    }

    public String removeBracket(String string){
        StringBuilder stringBuilder = new StringBuilder(string);

        stringBuilder.deleteCharAt(string.length() - 1);
        stringBuilder.deleteCharAt(string.length() - 2);
        stringBuilder.deleteCharAt(1);
        stringBuilder.deleteCharAt(0);

        return stringBuilder.toString();
    }

    public String minigameML(String movementDetected, String timeConsumption, String numberOfAttempt, String numberOfBlockInteraction){
        try {
            ProcessBuilder minigameBuilder = new ProcessBuilder("python",
                    System.getProperty("user.dir") + "\\assets\\model\\minigameScript.py",
                    movementDetected, timeConsumption, numberOfAttempt, numberOfBlockInteraction);

            Process process = minigameBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = reader.readLine()) != null) {
                return removeBracket(s);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public String codeRiddleML(String timeConsumption, String numberOfError){
        try {
            ProcessBuilder codeRiddleBuilder = new ProcessBuilder("python",
                    System.getProperty("user.dir") + "\\assets\\model\\codeRiddleScript.py",
                    timeConsumption, numberOfError);

            Process process = codeRiddleBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = reader.readLine()) != null) {
                return removeBracket(s);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
