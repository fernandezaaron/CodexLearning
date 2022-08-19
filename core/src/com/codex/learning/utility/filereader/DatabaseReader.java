package com.codex.learning.utility.filereader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class DatabaseReader {
    private Workbook workbook;

    public DatabaseReader() {
        try {
            FileInputStream fs = new FileInputStream("assets/questions_excel/Minigames.xlsx");
            workbook = new XSSFWorkbook(fs);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Sheet getMinigameSheet() {
        Sheet minigameSheet = workbook.getSheet("Minigames");
        return minigameSheet;
    }

    public Sheet getQuestionSheet() {
        Sheet minigameSheet = workbook.getSheet("CodeRiddle");
        return minigameSheet;
    }

    public Workbook getWorkbook() {
        return workbook;
    }
}