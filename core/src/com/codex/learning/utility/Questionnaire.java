package com.codex.learning.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Questionnaire {
    private DatabaseReader read;
    private Workbook workbook;
    private String question, difficulty;
    private ArrayList<String> levels, options;
    private int questionID, limit;

    public Questionnaire() {
        question = null;
        difficulty = null;
    }

    public void questionDisplay(String stage, String level) {
        //for the meantime
        stage = "Stage 1";
        level = "Novice";

        read = new DatabaseReader();
        levels = new ArrayList<String>();
        Random randomizer = new Random(); //instance of random class
        workbook = read.getRiddler();
        if(level == "Poor") {
            levels.add("Easy");
        }
        else if(level == "Novice") {
            levels.add("Easy");
            levels.add("Medium");
        }
        else if(level == "Average") {
            levels.add("Hard");
            levels.add("Medium");
        }
        else if(level == "Expert") {
            levels.add("Hard");
        }
        difficulty = levels.get(randomizer.nextInt(levels.size()));
        while(question == null) {
            randomizer = new Random();
            options = new ArrayList<String>();
            limit = 196;
            questionID = randomizer.nextInt(limit - 1) + 1;
            question = getQuestion(questionID, 4, workbook, difficulty, stage);
            if(question != null) {
                options.add(getInfo(questionID, 5, workbook));
                options.add(getInfo(questionID, 6, workbook));
                options.add(getInfo(questionID, 7, workbook));
                options.add(getInfo(questionID, 8, workbook));
            }
        }
        System.out.println(question);
        Collections.shuffle(options);
        System.out.println(options.get(0));
        System.out.println(options.get(1));
        System.out.println(options.get(2));
        System.out.println(options.get(3));
    }

    public String getInfo(int row1, int col1, Workbook workbook) {
        String stageValue = null;

        Sheet sheet = workbook.getSheet("CodeRiddle");          //getting the XSSFSheet object at given index
        Row row = sheet.getRow(row1);              //returns the logical row
        Cell cell = row.getCell(col1);             //getting the cell representing the given column
        stageValue = (String) cell.getStringCellValue();    //getting cell value
        return stageValue;                         //returns the cell value
    }

    public String getQuestion(int row1, int col1, Workbook workbook, String diff, String stg) {
        int qID = 0;
        String question = null;

        Sheet sheet = workbook.getSheet("CodeRiddle");
        Row IDRow = sheet.getRow(row1);
        Cell IDCell = IDRow.getCell(0);
        qID = (int) IDCell.getNumericCellValue();

        if(qID == row1 && (getInfo(row1, 2, workbook).equals(diff)) && (getInfo(row1, 3, workbook).equals(stg))) {
            Row qRow = sheet.getRow(row1);
            Cell qCell = qRow.getCell(col1);
            question = qCell.getStringCellValue();
            return question;
        }
        else
            return null;
    }

    public void checker(String answer){
        Sheet sheet = workbook.getSheetAt(0);
        Row IDRow = sheet.getRow(questionID);
        Cell IDCell = IDRow.getCell(0);

        if(answer.equals("A") || answer.equals("a")){
            answer = options.get(0);
        }
        else if(answer.equals("B") || answer.equals("b") ){
            answer = options.get(1);
        }
        else if(answer.equals("C") || answer.equals("c") ){
            answer = options.get(2);
        }
        else if(answer.equals("D") || answer.equals("d") ){
            answer = options.get(3);
        }

        if(answer == getInfo(questionID, 9, workbook)) {
            System.out.println("Luh gamer");
        }
        else
            System.out.println("HAH BOBO");
    }
}
