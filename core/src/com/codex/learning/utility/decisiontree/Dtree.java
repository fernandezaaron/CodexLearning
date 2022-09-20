package com.codex.learning.utility.decisiontree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dtree {

    public String removeBracket(String string){
        StringBuilder stringBuilder = new StringBuilder(string);

        stringBuilder.deleteCharAt(string.length() - 1);
        stringBuilder.deleteCharAt(string.length() - 2);
        stringBuilder.deleteCharAt(1);
        stringBuilder.deleteCharAt(0);


        return stringBuilder.toString();
    }

    public String minigameRiddle(String a, String b, String c, String d){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    System.getProperty("user.dir") + "\\resources\\minigameScript.py", a, b, c, d);

            Process process = processBuilder.start();
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

    public String codeRiddleML(String a, String b){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    System.getProperty("user.dir") + "\\resources\\codeRiddleScript.py", a, b);

            Process process = processBuilder.start();
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
