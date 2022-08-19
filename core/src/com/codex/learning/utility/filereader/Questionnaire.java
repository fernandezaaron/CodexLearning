package com.codex.learning.utility.filereader;

import com.codex.learning.utility.filereader.DatabaseReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Random;

public class Questionnaire extends DatabaseReader {
    private String question, difficulty;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> answers;

    private ArrayList<String> levels;

    private int questionID, excelQuestionLimit, questionLimit;

    private String stageValue;
    private Random randomizer;

    private int numberOfQuestions;

    private DataFormatter formatter;

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
        randomizer = new Random();

        levels = new ArrayList<>();

        formatter = new DataFormatter();
    }

    public void questionDisplay(String stage, String expertiseLevel) {
        adjustDifficulty(expertiseLevel);


        difficulty = levels.get(randomizer.nextInt(levels.size()));
        while(question == null) {

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
        Sheet sheet = getWorkbook().getSheet("CodeRiddle");
        Row row = sheet.getRow(rows);
        Cell cell = row.getCell(col);
        String cellValue = formatter.formatCellValue(cell);
        stageValue = cellValue;
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
//        System.out.println("ASDASDQWD - " + answers);

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
