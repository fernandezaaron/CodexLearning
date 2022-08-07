package com.codex.learning.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseReader extends Thread {
    private Workbook wb;
    private String question, difficulty, stage, topic, option1, option2, option3,option4;

    public DatabaseReader() {
        wb = null;
        question = null;
        difficulty = "";
        stage = "";
        topic = "";
        option1 = "";
        option2 = "";
        option3 = "";
        option4 = "";
    }

    public void getQuestions(String difficulty, String stage, String topic) {
        try {
            FileInputStream fs = new FileInputStream("assets/questions_excel/Trial.xlsx");
            wb = new XSSFWorkbook(fs);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e1) {
            e1.printStackTrace();
        }
        while(question == null) {
            Random randomNumber = new Random(); //instance of random class
            int limit = 23;
            int questionID = randomNumber.nextInt(limit);

            question = getQuestion(questionID, 4, wb, difficulty, stage);
            if(question != null) {
                option1 = getInfo(questionID, 5, wb);
                option2 = getInfo(questionID , 6, wb);
                option3 = getInfo(questionID, 7, wb);
                option4 = getInfo(questionID, 8, wb);
            }
        }
        System.out.println(question);
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
    }

    public String getInfo(int row1, int col1, Workbook wb) {
        String stageValue = null;

        Sheet sheet = wb.getSheetAt(0);          //getting the XSSFSheet object at given index
        Row row = sheet.getRow(row1);              //returns the logical row
        Cell cell = row.getCell(col1);             //getting the cell representing the given column
        stageValue = cell.getStringCellValue();    //getting cell value
        return stageValue;                         //returns the cell value
    }

    public String getQuestion(int row1, int col1, Workbook wb, String diff, String stg) {
        int qID = 0;
        String question = null;

        Sheet sheet = wb.getSheetAt(0);
        Row IDRow = sheet.getRow(row1);
        Cell IDCell = IDRow.getCell(0);
        qID = (int) IDCell.getNumericCellValue();

        if(qID == row1 && (getInfo(row1, 2, wb).equals(diff)) && (getInfo(row1, 3, wb).equals(stg))) {
            Row qRow = sheet.getRow(row1);
            Cell qCell = qRow.getCell(col1);
            question = qCell.getStringCellValue();
            return question;
        }
        else
            return null;
    }
}