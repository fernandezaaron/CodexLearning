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
    private Workbook workbook;

    public DatabaseReader() {
        workbook = null;
    }

    public Workbook getReader() {
        try {
            FileInputStream fs = new FileInputStream("assets/questions_excel/Minigames.xlsx");
            workbook = new XSSFWorkbook(fs);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e1) {
            e1.printStackTrace();
        }
        return workbook;
    }
}