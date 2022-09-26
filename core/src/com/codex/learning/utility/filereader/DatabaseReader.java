package com.codex.learning.utility.filereader;

<<<<<<< HEAD
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
>>>>>>> parent of b736b76 (Minigames.xlsx cleaned WIP)

import com.badlogic.gdx.files.FileHandle;
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
<<<<<<< HEAD

            InputStream inputStream = getClass().getResourceAsStream("/"+Constants.EXCEL_FILE_PATH);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            File file = new File()
//            FileInputStream fis = new FileInputStream(Constants.EXCEL_FILE_PATH);
//            FileInputStream fis = new FileInputStream(file);

            workbook = new XSSFWorkbook(inputStream);
=======
            FileInputStream fs = new FileInputStream(Constants.EXCEL_FILE_PATH);
            workbook = new XSSFWorkbook(fs);
>>>>>>> parent of b736b76 (Minigames.xlsx cleaned WIP)
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        minigameSheet = workbook.getSheet("Minigame");
        questionSheet = workbook.getSheet("CodeRiddle2");
        answerPoolSheet = workbook.getSheet("AnswerPool2");
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