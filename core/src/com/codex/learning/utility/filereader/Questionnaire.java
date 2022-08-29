package com.codex.learning.utility.filereader;

import com.codex.learning.utility.filereader.DatabaseReader;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questionnaire extends DatabaseReader {
    private String question, difficulty;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> answers;

    private ArrayList<String> levels;

    private int questionID, excelQuestionLimit, questionLimit, excelMinigameLimit, minigameElementLimit, answerPoolLimit, answerPoolSelection, findCell;

    private String stageValue;
    private Random randomizer;

    private int numberOfQuestions;

    private DataFormatter formatter;

    private Sheet minigameSheet, questionSheet, answerPoolSheet;

//    private String[][] minigameGetter;
//    private String[][] minigameHolder;
    private ArrayList<ArrayList<String>> minigameHolder;
    private ArrayList<String> minigameGetter;
    private ArrayList<String> answerPool;

    public Questionnaire() {
        questions = new ArrayList<>();
        options = new ArrayList<>();
        answers = new ArrayList<>();

        question = null;
        difficulty = null;
        stageValue = null;

        numberOfQuestions = 0;
        questionLimit = 0;

        excelQuestionLimit = 196;
        excelMinigameLimit = 53;
        minigameElementLimit = 0;
        answerPoolLimit = 200;
        answerPoolSelection = 10;
        randomizer = new Random();

        levels = new ArrayList<String>();
        answerPool = new ArrayList<String>();

        formatter = new DataFormatter();

        minigameSheet = getMinigameSheet();
        questionSheet = getQuestionSheet();
        answerPoolSheet = getAnswerPoolSheet();
    }

    // for minigames
    private int findRow(Sheet sheet, int cellToFind) {
        int foundCell = 0;
        for(int i = 1; i <= sheet.getLastRowNum() + 1; i++) {
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
    // end for minigames

    public void minigameDisplay(String stage, String expertiseLevel) {
        adjustDifficulty(expertiseLevel);
//        minigameHolder = new String[50][50];
        while(minigameGetter == null) {
            questionID = randomizer.nextInt(excelMinigameLimit - 1) + 1;
//            questionID = 6;
            difficulty = "Easy";
            findCell = findRow(minigameSheet, questionID);
            getMinigame(findCell, 4, difficulty, stage);
        }
        getAnswerPool(stage);
    }

    public void getMinigame(int row1, int col1, String diff, String stg) {
//        minigameGetter = new String[50][50];
        minigameHolder = new ArrayList<ArrayList<String>>();
        String difficultyacq = getMinigameInfo(row1, 2);
        String stageacq = getMinigameInfo(row1, 3);

        if((difficultyacq != null && difficultyacq.equals(diff)) && (stageacq != null && stageacq.equals(stg))) {
//            int i = 0, j;
            for(int x = row1; x > 0; x++) {
                minigameGetter = new ArrayList<String>();
//                j = 0;
                for(int y = col1; y > 0; y++) {
                    Row qRow = minigameSheet.getRow(x + 1);
                    Cell qCell = qRow.getCell(y);
                    String cell = formatter.formatCellValue(qCell);
                    if(cell.equals("\\"+"n")) {
                        break;
                    }
                    else {
                        minigameGetter.add(cell);
                        minigameElementLimit++;
                    }
                }
                minigameHolder.add(minigameGetter);
                Row qRow = minigameSheet.getRow(x + 2);
                Cell qCell = qRow.getCell(4);
                String cell = formatter.formatCellValue(qCell);
                if(cell.equals("End")) {
                    break;
                }
            }
//            for(int row = 0; row < 50; row++) {
//                for (int col = 0; col < 50; col++) {
//                    if (minigameGetter[row][col] != null) {
//                        minigameHolder[row][col] = minigameGetter[row][col];
//                    }
//                }
//            }
        }
        else
//            minigameGetter = null;
              minigameHolder = null;
    }

    public String getMinigameInfo(int row1, int col1) {
        String stageValue = null;

        Row row = minigameSheet.getRow(row1);
        Cell cell = row.getCell(col1);
        if (cell == null) {
            return null;
        }
        else {
            String cellValue = formatter.formatCellValue(cell);
            stageValue = cellValue;
            return stageValue;
        }
    }

    public void getAnswerPool(String stage) {
        int getNumber = 0;
        while(answerPoolSelection != 0) {
            getNumber = randomizer.nextInt(80 - 1) + 1;
            Row excelRow = answerPoolSheet.getRow(getNumber);
            Cell excelCell = excelRow.getCell(2);
            String getExcelStage = formatter.formatCellValue(excelCell);
            if((int) answerPoolSheet.
                    getRow(getNumber).getCell(0).getNumericCellValue() == getNumber &&
                    (getExcelStage.equals(stage))) {
                Row ansRow = answerPoolSheet.getRow(getNumber);
                Cell ansCell = ansRow.getCell(3);
                String getAnsCell = formatter.formatCellValue(ansCell);
                if (getAnsCell != "") {
                    answerPool.add(getAnsCell);
                    answerPoolSelection--;
                } else {
                    continue;
                }
            }
        }
    }

    public void questionDisplay(String stage, String expertiseLevel) {
        adjustDifficulty(expertiseLevel);

        difficulty = levels.get(randomizer.nextInt(levels.size()));

        while(question == null) {
            difficulty = levels.get(randomizer.nextInt(levels.size()));

            if(numberOfQuestions == questionLimit){
                break;
            }

            questionID = randomizer.nextInt(excelQuestionLimit - 1) + 1;
            question = getExcelQuestion(questionID, 4, difficulty, stage);
            if(question != null) {
                if(questions.contains(question)){
                    question = null;
                    continue;
                }
                else{
                    questions.add(question);

                    options.add(new ArrayList<String>());
                    options.get(numberOfQuestions).add(getCodeRiddle(questionID, 5));
                    options.get(numberOfQuestions).add(getCodeRiddle(questionID, 6));
                    options.get(numberOfQuestions).add(getCodeRiddle(questionID, 7));
                    options.get(numberOfQuestions).add(getCodeRiddle(questionID, 8));

                    answers.add(getCodeRiddle(questionID, 9));

                    numberOfQuestions++;
                }
            }
            question = null;
        }
    }

    public String getCodeRiddle(int rows, int col){
        Row row = questionSheet.getRow(rows);
        Cell cell = row.getCell(col);
        String cellValue = formatter.formatCellValue(cell);
        stageValue = cellValue;
        return stageValue;
    }

    public String getExcelQuestion(int row1, int col1, String difficulty, String stage) {
        if((int) questionSheet.
                getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                getCodeRiddle(row1, 2).equals(difficulty) &&
                (getCodeRiddle(row1, 3).equals(stage))) {
            return getWorkbook().getSheet("CodeRiddle").getRow(row1).getCell(col1).getStringCellValue();
        }
        return null;
    }

    public boolean answerChecker(String chosenAnswer, int index){
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

//    public String[][] getMinigame() {
//        return minigameHolder;
//    }
//
//    public void setMinigame(String[][] minigameHolder) {
//        this.minigameHolder = minigameHolder;
//    }

    public ArrayList<ArrayList<String>> getMinigame() {
        return minigameHolder;
    }

    public void setMinigame(ArrayList<ArrayList<String>> minigameHolder) {
        this.minigameHolder = minigameHolder;
    }

    public ArrayList<String> getAnswerPool(){return answerPool;}

    public void setAnswerPool(ArrayList<String> answerPool){this.answerPool = answerPool;}

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

    public int getMinigameLimit(){return minigameElementLimit;}

    public void setMinigameLimit(int minigameElementLimit){this.minigameElementLimit = minigameElementLimit;}

    public void dispose(){
        questions.clear();
        levels.clear();
        options.clear();
        answers.clear();
    }
}
