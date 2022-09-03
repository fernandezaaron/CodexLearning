package com.codex.learning.utility.filereader;

import com.codex.learning.utility.filereader.DatabaseReader;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questionnaire extends DatabaseReader {
    private String question, difficulty, stageTopic;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;
    private ArrayList<String> answers;

    private ArrayList<String> levels;

    private int questionID, excelQuestionLimit, questionLimit, excelMinigameLimit, minigameElementLimit, answerPoolLimit, dispenserPoolSelection, answerPoolSelection, findCell;

    private String stageValue;
    private Random randomizer;

    private int numberOfQuestions;

    private DataFormatter formatter;

    private Sheet minigameSheet, questionSheet, answerPoolSheet;

    private ArrayList<ArrayList<String>> minigameHolder;
    private ArrayList<String> minigameGetter;
    private ArrayList<String> answerPool;
    private ArrayList<String> topic;
    private ArrayList<Integer> randomPool;
    private ArrayList<String> dispenserPool;

    public Questionnaire() {
        questions = new ArrayList<>();
        options = new ArrayList<>();
        answers = new ArrayList<>();
        topic = new ArrayList<>();

        question = null;
        difficulty = null;
        stageTopic = null;
        stageValue = null;

        numberOfQuestions = 0;
        questionLimit = 0;

        excelQuestionLimit = 196;
        excelMinigameLimit = 53;
        minigameElementLimit = 0;
        answerPoolLimit = 200;
        answerPoolSelection = 5;
        dispenserPoolSelection = 3;
        randomizer = new Random();

        levels = new ArrayList<String>();
        answerPool = new ArrayList<String>();
        dispenserPool = new ArrayList<String>();

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

    public void minigameDisplay(String stage,String topics,String expertiseLevel) {
        adjustDifficulty(expertiseLevel);
        addTopic(topics);
        topics = topic.get(randomizer.nextInt(topic.size()));
        while(minigameGetter == null) {
            questionID = randomizer.nextInt(excelMinigameLimit - 1) + 1;
            difficulty = levels.get(randomizer.nextInt(levels.size()));
            findCell = findRow(minigameSheet, questionID);
            getMinigame(findCell, 4, difficulty, stage);
        }
        getAnswerPool(stage, topics);
        getDispenserPool(stage, topics);
    }

    public void getMinigame(int row1, int col1, String diff, String stg) {
        minigameHolder = new ArrayList<ArrayList<String>>();
        String difficultyacq = getMinigameInfo(row1, 2);
        String stageacq = getMinigameInfo(row1, 3);

        if((difficultyacq != null && difficultyacq.equals(diff)) && (stageacq != null && stageacq.equals(stg))) {
            for(int x = row1; x > 0; x++) {
                minigameGetter = new ArrayList<String>();
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
        }
        else
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

    public void getAnswerPool(String stage, String topics) {
        int getNumber = 0;
        randomPool = new ArrayList<>();
        while(answerPoolSelection != 0) {
            getNumber = randomizer.nextInt(80 - 1) + 1;
            Row excelRow = answerPoolSheet.getRow(getNumber);
            Cell excelCell = excelRow.getCell(2);
            Cell excelTopic = excelRow.getCell(1);
            String getExcelStage = formatter.formatCellValue(excelCell);
            String getExcelTopic = formatter.formatCellValue(excelTopic);
            if(!randomPool.contains(getNumber)){
                if ((int) answerPoolSheet.
                        getRow(getNumber).getCell(0).getNumericCellValue() == getNumber &&
                        (getExcelTopic.equals(topics)) &&
                        (getExcelStage.equals(stage))) {
                    Row ansRow = answerPoolSheet.getRow(getNumber);
                    Cell ansCell = ansRow.getCell(3);
                    String getAnsCell = formatter.formatCellValue(ansCell);
                    if (getAnsCell != "") {
                        answerPool.add(getAnsCell);
                        randomPool.add(getNumber);
                        answerPoolSelection--;
                    } else {
                        continue;
                    }
                }
            }
            else
                continue;
        }
    }

    public void getDispenserPool(String stage, String topics) {
        int getNumber = 0;
        randomPool = new ArrayList<>();
        while(dispenserPoolSelection != 0) {
            getNumber = randomizer.nextInt(80 - 1) + 1;
            Row excelRow = answerPoolSheet.getRow(getNumber);
            Cell excelCell = excelRow.getCell(2);
            Cell excelTopic = excelRow.getCell(1);
            String getExcelStage = formatter.formatCellValue(excelCell);
            String getExcelTopic = formatter.formatCellValue(excelTopic);
            if(!randomPool.contains(getNumber)){
                if ((int) answerPoolSheet.
                        getRow(getNumber).getCell(0).getNumericCellValue() == getNumber &&
                        (getExcelTopic.equals(topics)) &&
                        (getExcelStage.equals(stage))) {
                    Row ansRow = answerPoolSheet.getRow(getNumber);
                    Cell ansCell = ansRow.getCell(3);
                    String getAnsCell = formatter.formatCellValue(ansCell);
                    if (getAnsCell != "") {
                        dispenserPool.add(getAnsCell);
                        randomPool.add(getNumber);
                        dispenserPoolSelection--;
                    } else {
                        continue;
                    }
                }
            }
            else
                continue;
        }
    }

    public void questionDisplay(String stage,String topics,String expertiseLevel) {
        adjustDifficulty(expertiseLevel);
        addTopic(topics);

        difficulty = levels.get(randomizer.nextInt(levels.size()));


        while(question == null) {
            difficulty = levels.get(randomizer.nextInt(levels.size()));
            topics = topic.get(randomizer.nextInt(topic.size()));
//            System.out.println(stageTopic);
//            System.out.println("am i here");
//            System.out.println(numberOfQuestions);
            if(numberOfQuestions == questionLimit){
                break;
            }

            questionID = randomizer.nextInt(excelQuestionLimit - 1) + 1;
            question = getExcelQuestion(questionID, 4, difficulty, stage, topics);
            if(question != null) {
                if(questions.contains(question)){
                    question = null;
                    continue;
                }
                else{
                    System.out.println("QUESTION: " + question);
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

    public String getExcelQuestion(int row1, int col1, String difficulty, String stage, String topic) {
        if((int) questionSheet.
                getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                getCodeRiddle(row1, 1).equals(topic) && getCodeRiddle(row1, 2).equals(difficulty) &&
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
                questionLimit = 2;
                break;
            case "Expert":
                levels.add("Hard");
                questionLimit = 3;
                break;
        }
    }

    public void addTopic(String stageNumber){
        System.out.println(stageNumber);
        switch (stageNumber){
            case "1":
                topic.add("Syntax");
                topic.add("Comments");
                break;
            case "2":
                topic.add("Variables");
                topic.add("Data Types");
                topic.add("Type Casting");
                break;
            case "3":
                topic.add("Operators");
                break;
            case "4":
                topic.add("Syntax");
                topic.add("Comments");
                topic.add("Variables");
                topic.add("Data Types");
                topic.add("Type Casting");
                topic.add("Operators");
                break;
            case "5":
                topic.add("Conditional");
                break;
            case "6":
                topic.add("Loops");
                break;
            case "7":
                topic.add("Arrays");
                break;
            case "8":
                topic.add("Methods");
                break;
            case "9":
                topic.add("Parameters");
                topic.add("Parameter Overloading");
                break;
            case "10":
                topic.add("Conditional");
                topic.add("Loops");
                topic.add("Arrays");
                topic.add("Methods");
                topic.add("Parameters");
                topic.add("Parameter Overloading");
                break;
            case "11":
                topic.add("Classes");
                break;
            case "12":
                topic.add("Objects");
                break;
            case "13":
                topic.add("Classes");
                topic.add("Objects");
                break;
        }

    }


    public ArrayList<String> getTopic() {
        return topic;
    }

    public void setTopic(ArrayList<String> topic) {
        this.topic = topic;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }


    public ArrayList<ArrayList<String>> getMinigame() {
        return minigameHolder;
    }

    public void setMinigame(ArrayList<ArrayList<String>> minigameHolder) {
        this.minigameHolder = minigameHolder;
    }

    public ArrayList<String> getAnswerPool(){return answerPool;}

    public void setAnswerPool(ArrayList<String> answerPool){this.answerPool = answerPool;}

    public ArrayList<String> getDispenserPool(){return  dispenserPool;}

    public void setDispenserPool(ArrayList<String> dispenserPool){this.dispenserPool = dispenserPool;}

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
        topic.clear();
        question = null;
        numberOfQuestions = 0;
    }
}
