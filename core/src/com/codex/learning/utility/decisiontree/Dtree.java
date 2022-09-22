package com.codex.learning.utility.decisiontree;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.io.*;
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
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            BufferedReader reader2 = new BufferedReader(new OutputStreamWriter(process.getOutputStream()));
//            System.out.println(reader.readLine());
            String s = null;

            if(process.isAlive()){
                System.out.println("ERROR - " + reader1);
//                System.out.println("OUTPUT - " + reader2);
            }
            System.out.println("qweqeqwe" + process.isAlive());


            while ((s = reader.readLine()) != null) {
//                System.out.println(process.exitValue());
                return removeBracket(s);
            }


        }catch(IOException e){
            e.printStackTrace();
        }

        return "";
    }
}
