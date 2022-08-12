package com.codex.learning.utility;

import org.apache.poi.ss.usermodel.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Questionnaire {
    private DatabaseReader read;
    private Workbook workbook;

    private String question, difficulty;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> answers;


    private ArrayList<String> levels;

    private int questionID, excelQuestionLimit, questionLimit;

    private String stageValue;
    private Random randomizer;

    private int numberOfQuestions, findCell;

    //new
    private String[][] minigame, minigameAcquired;
    private int excelMinigameLimit;
    private DataFormatter formatter;

    public Questionnaire() {
        read = new DatabaseReader();

        questions = new ArrayList<>();
        options = new ArrayList<>();
        answers = new ArrayList<>();

        question = null;
        difficulty = null;
        stageValue = null;

        numberOfQuestions = 0;
        questionLimit = 0;

        excelQuestionLimit = 195;
        randomizer = new Random();

        workbook = read.getReader();
        levels = new ArrayList<>();

        //new
        excelMinigameLimit = 53;
        findCell = 0;

        minigame = null;
        minigameAcquired = null;

        formatter = new DataFormatter();
        //new
    }

    //new
    private int findRow(Sheet sheet, int cellToFind) {
        int foundCell = 0;
        for(int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row findRow = sheet.getRow(i);
            if(findRow == null) {
                continue;
            }
            else {
                Cell findCell = findRow.getCell(0);
                if (findCell == null) {
                    continue;
                }
                else
                    foundCell = (int) findCell.getNumericCellValue();
                if (foundCell == cellToFind) {
                    return i;
                }
            }
        }
        return 0;
    }
    //new

    public void questionDisplay(String stage, String expertiseLevel) {
        // For the meantime
        stage = "Stage 1";
        expertiseLevel = "Novice";
        // To be erased


        adjustDifficulty(expertiseLevel);


        difficulty = levels.get(randomizer.nextInt(levels.size()));

        // Correct Until Here

        System.out.println("QUESTION LIMIT - " + questionLimit);
        while(question == null) {

            if(numberOfQuestions == questionLimit){
                break;
            }

            questionID = randomizer.nextInt(excelQuestionLimit - 1) + 1;

            question = getExcelQuestion(questionID, 4, difficulty, stage);
            if(question != null) {

                questions.add(question);

                options.add(new ArrayList<String>());
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 5));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 6));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 7));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 8));

                answers.add(getCodeRiddle(questionID, 9));

                numberOfQuestions++;
            }
            question = null;
        }
//        Collections.shuffle(options);
    }

    public String getCodeRiddle(int rows, int col){
        Sheet sheet = getWorkbook().getSheet("CodeRiddle");
        Row row = sheet.getRow(rows);
        Cell cell = row.getCell(col);
        stageValue = (String) cell.getStringCellValue();
        return stageValue;
    }

    public String getExcelQuestion(int row1, int col1, String difficulty, String stage) {

        if((int) getWorkbook().getSheet("CodeRiddle").
                getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                getCodeRiddle(row1, 2).equals(difficulty) &&
                (getCodeRiddle(row1, 3).equals(stage))) {
            return getWorkbook().getSheet("CodeRiddle").getRow(row1).getCell(col1).getStringCellValue();
        }
        return null;
    }

    //new
    public void minigameDisplay(String stage, String expertiseLevel) {
        // For the meantime
        stage = "Stage 1";
        expertiseLevel = "Novice";
        // To be erased
        adjustDifficulty(expertiseLevel);
        difficulty = levels.get(randomizer.nextInt(levels.size()));

        minigame = new String[50][50];
        Sheet sheet = workbook.getSheet("Minigames");

        while(minigameAcquired == null) {
            randomizer = new Random();
            excelMinigameLimit = 53;
            questionID = randomizer.nextInt(excelMinigameLimit - 1) + 1;
            findCell = findRow(sheet, questionID);
            System.out.println(questionID + " " + findCell);
            getMinigame(findCell, 4, workbook, difficulty, stage);
        }
        //return minigameshow;
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                if(minigame[i][j] == null) {
                    break;
                }
                else {
                    System.out.print(minigame[i][j] + " ");
                }
            }
            if(minigame[i][0] == null) {
                break;
            }
            System.out.println();
        }
    }
    //new

    //new
    public void getMinigame(int row1, int col1, Workbook workbook, String diff, String stg) {
        minigame = new String[50][50];

        Sheet sheet = workbook.getSheet("MiniGames");

        String acquiredDifficulty = getExcelMinigame(row1, 2, workbook);
        String acquiredStage = getExcelMinigame(row1, 3, workbook);

        if((acquiredDifficulty != null && acquiredDifficulty.equals(diff)) && (acquiredStage != null && acquiredStage.equals(stg))) {
            int i = 0, j;
            for(int x = row1; x < 50; x++) {
                j = 0;
                for(int y = col1; y < 50; y++) {
                    Row minigameRow = sheet.getRow(x + 1);
                    Cell minigameCell = minigameRow.getCell(y);
                    String acquiredCell = formatter.formatCellValue(minigameCell);
                    if(acquiredCell.equals("\\"+"n")) {
                        break;
                    }
                    else {
                        minigameAcquired[i][j] = acquiredCell;
                        j++;
                    }
                }
                i++;
                Row minigameRow = sheet.getRow(x + 2);
                Cell minigameCell = minigameRow.getCell(4);
                String endCheck = formatter.formatCellValue(minigameCell);
                if(endCheck.equals("End")) {
                    break;
                }
            }
            minigame = minigameAcquired;
        }
        else
            minigameAcquired = null;
    }
    //new

    //new
    public String getExcelMinigame(int row1, int col1, Workbook workbook) {
        String minigameCellValue = null;

        Sheet sheet = workbook.getSheet("MiniGames");
        Row minigameInfoRow = sheet.getRow(row1);
        Cell minigameInfoCell = minigameInfoRow.getCell(col1);
        if (minigameInfoCell == null) {
            return null;
        }
        else {
            minigameCellValue = (String) minigameInfoCell.getStringCellValue();
            return minigameCellValue;
        }
    }
    //new

    public boolean answerChecker(String chosenAnswer, int index){
        System.out.println("ASDASDQWD - " + answers);
        if(chosenAnswer == answers.get(index)){
            return true;
        }
        return false;
    }

    public void adjustDifficulty(String expertiseLevel){
        switch (expertiseLevel){
            case "Poor":
                levels.add("Easy");
                questionLimit = 10;
                break;
            case "Novice":
                levels.add("Easy");
                levels.add("Medium");
                questionLimit = 5;
                break;
            case "Average":
                levels.add("Medium");
                levels.add("Hard");
                questionLimit = 4;
                break;
            case "Expert":
                levels.add("Hard");
                questionLimit = 3;
                break;
        }
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<ArrayList<String>> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<ArrayList<String>> options) {
        this.options = options;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getQuestionLimit() {
        return questionLimit;
    }

    public void setQuestionLimit(int questionLimit) {
        this.questionLimit = questionLimit;
    }

    public void dispose(){
        questions.clear();
        levels.clear();
        options.clear();
        answers.clear();
    }
}
