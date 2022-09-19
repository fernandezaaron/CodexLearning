package com.codex.learning.utility.decisiontree;
import org.pmml4s.model.Model;
import java.util.*;

public class Test {
//    private final Model model = Model.fromFile
//            (Test.class.getClassLoader().getResource("assets/model/minigameML.pmml").getFile());
    private final Model model2 = Model.fromFile("assets/model/minigameML.pmml");


    public ArrayList<ArrayList<Integer>> data = new ArrayList<>(
            Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 3, 1, 1)),
                new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
                new ArrayList<>(Arrays.asList(1, 1, 2, 3)),
                new ArrayList<>(Arrays.asList(0, 3, 2, 2)),
                new ArrayList<>(Arrays.asList(1, 2, 2, 2))
            ));


    public static void main(String[] args) {
        Test test = new Test();

        ArrayList<Integer> data = new ArrayList<>(
                Arrays.asList(0, 3, 1, 1)
        );
        int[][] array = new int[][]{{0, 3, 1, 1},
                                    {1, 1, 1, 1},
                                    {1, 1, 2, 3}};

        Map<String, Integer> values = new HashMap<String, Integer>(){{
            put("MD",0);
            put("TC", 3);
            put("NA", 1);
            put("NBI", 1);
        }};

        String[][] arr = new String[][]{{"NO", "LOW", "MEDIUM", "LOW"}};
        System.out.println("BEHAVIOR - " + test.model2.predict(values));
    }

}
