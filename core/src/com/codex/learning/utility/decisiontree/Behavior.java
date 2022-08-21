package com.codex.learning.utility.decisiontree;

import java.util.ArrayList;
import java.util.Arrays;

public class Behavior {
    public static ArrayList<String> headers = new ArrayList<>
            (Arrays.asList("Movement Detected", "Time Consumption", "Number of Attempts", "Number of Error", "Number of Block Interaction", "BEHAVIORS"));

    public static ArrayList<ArrayList<String>> trainingData = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "LOW", "LOW", "LOW", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "MEDIUM", "LOW", "LOW", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "MEDIUM", "MEDIUM", "LOW", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "MEDIUM", "MEDIUM", "MEDIUM", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "LOW", "MEDIUM", "MEDIUM", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "LOW", "LOW", "MEDIUM", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "LOW", "LOW", "MEDIUM", "LOW", "ENGAGED")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "LOW", "LOW", "LOW", "ENGAGED")),

                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "MEDIUM", "LOW", "LOW", "NEUTRAL")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "MEDIUM", "MEDIUM", "LOW", "NEUTRAL")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "NEUTRAL")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "LOW", "LOW", "MEDIUM", "NEUTRAL")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "LOW", "MEDIUM", "MEDIUM", "NEUTRAL")),

                    new ArrayList<String>(Arrays.asList("NO", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "BORED")),
                    new ArrayList<String>(Arrays.asList("YES", "MEDIUM", "MEDIUM", "MEDIUM", "HIGH", "BORED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "LOW", "LOW", "LOW", "BORED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "LOW", "LOW", "LOW", "BORED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "LOW", "HIGH", "LOW", "BORED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "LOW", "HIGH", "LOW", "BORED")),

                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "LOW", "HIGH", "HIGH", "CONFUSED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "LOW", "HIGH", "HIGH", "CONFUSED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "MEDIUM", "MEDIUM", "HIGH", "CONFUSED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "MEDIUM", "HIGH", "HIGH", "CONFUSED")),

                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "HIGH", "HIGH", "LOW", "FRUSTRATED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "HIGH", "HIGH", "LOW", "FRUSTRATED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "HIGH", "HIGH", "MEDIUM", "FRUSTRATED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "HIGH", "HIGH", "MEDIUM", "FRUSTRATED")),
                    new ArrayList<String>(Arrays.asList("YES", "HIGH", "HIGH", "HIGH", "HIGH", "FRUSTRATED")),
                    new ArrayList<String>(Arrays.asList("NO", "HIGH", "HIGH", "HIGH", "HIGH", "FRUSTRATED"))
            )
    );
}
