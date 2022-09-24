package com.codex.learning.utility.filereader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

import com.codex.learning.utility.Constants;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class DatabaseReader {
    private Workbook workbook;
    private Sheet minigameSheet;
    private Sheet questionSheet;
    private Sheet answerPoolSheet;
    private Sheet responseSheet;

    public DatabaseReader() {
        try {
            FileInputStream fis = new FileInputStream(Constants.EXCEL_FILE_PATH);
            workbook = new XSSFWorkbook(fis);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        minigameSheet = workbook.getSheet("Minigame");
        questionSheet = workbook.getSheet("CodeRiddle");
        answerPoolSheet = workbook.getSheet("AnswerPool");
        responseSheet = workbook.getSheet("Response");
    }

    public Sheet getMinigameSheet() {
        return minigameSheet;
    }

    public Sheet getQuestionSheet() {
        return questionSheet;
    }

    public Sheet getAnswerPoolSheet() {
        return answerPoolSheet;
    }

    public Sheet getResponseSheet() {
        return responseSheet;
    }

    public Workbook getWorkbook() {
        return workbook;
    }
}