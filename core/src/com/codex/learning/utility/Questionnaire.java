package com.codex.learning.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Questionnaire {
    private DatabaseReader read;
    private Workbook workbook;

    private String question, answer, difficulty;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> answers;


    private ArrayList<String> levels;

    private int questionID, excelQuestionLimit, questionLimit;

    private String stageValue;
    private Random randomizer;

    private int numberOfQuestions;

    public Questionnaire() {
        read = new DatabaseReader();

        questions = new ArrayList<>();
        options = new ArrayList<>();
        answers = new ArrayList<>();

        question = null;
        difficulty = null;
        stageValue = null;
        answer = null;

        numberOfQuestions = 0;
        questionLimit = 0;

        excelQuestionLimit = 196;
        randomizer = new Random();

        workbook = read.getReader();
        levels = new ArrayList<>();
    }

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

            questionID = randomizer.nextInt(excelQuestionLimit - 1) + 1;
            System.out.println("WQDNIWDINQINDWNDWQNDWQNDWQNIDWQNQWD");
            question = getExcelQuestion(questionID, 4, difficulty, stage);
            if(question != null) {

                questions.add(question);

                options.add(new ArrayList<String>());
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 5));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 6));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 7));
                options.get(numberOfQuestions).add(getCodeRiddle(questionID, 8));

                answers.add(getCodeRiddle(questionID, 9));

                System.out.println(answers);

                numberOfQuestions++;
            }

            System.out.println("NUMBER OF QUESTIONS - " + numberOfQuestions);
        }

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

    public boolean answerChecker(String chosenAnswer, int index){
        if(chosenAnswer == answers.get(0)){
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

    public void dispose(){
        question = null;
        levels.clear();
        options.clear();
        answer = null;
    }
}
