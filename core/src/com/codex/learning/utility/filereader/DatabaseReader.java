package com.codex.learning.utility.filereader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            InputStream inputStream = getClass().getResourceAsStream("/"+Constants.EXCEL_FILE_PATH);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            workbook = new XSSFWorkbook(inputStream);
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

    public Workbook getWorkbook() {
        return workbook;
    }
}