package com.codex.learning.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public Workbook getWorkbook() {
        return workbook;
    }
}