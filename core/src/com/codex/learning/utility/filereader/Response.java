package com.codex.learning.utility.filereader;

import org.apache.poi.ss.usermodel.*;

import java.util.Random;

public class Response extends DatabaseReader {
    private String response, responseValue;
    private int responseID, findCell, excelResponseLimit;
    private Random randomizer;
    private DataFormatter formatter;
    private Sheet responseSheet;

    public Response() {
        response = null;
        randomizer = new Random();
        excelResponseLimit = 175;
        formatter = new DataFormatter();
        responseSheet = getResponseSheet();
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

    public void getResponse(String reponseType, String topic, String specificType) {
        while(response == null) {
            responseID = randomizer.nextInt(excelResponseLimit - 1) + 1;
            findCell = findRow(responseSheet, responseID);
            response = checkResponse(findCell, 4, reponseType, topic, specificType);
        }
    }

    public String getExcelResponse(int rows, int col){
        Row row = responseSheet.getRow(rows);
        Cell cell = row.getCell(col);
        String cellValue = formatter.formatCellValue(cell);
        responseValue = cellValue;
        return responseValue;
    }

    public String checkResponse(int row1, int col1, String responseType, String topic, String specificType) {
        if(topic == "") {
            if(specificType == "") {
                if ((int) responseSheet.
                        getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                        getExcelResponse(row1, 1).equals(responseType)) {
                    return responseSheet.getRow(row1).getCell(col1).getStringCellValue();
                }
            }
            else {
                if ((int) responseSheet.
                        getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                        getExcelResponse(row1, 1).equals(responseType) &&
                        getExcelResponse(row1, 3).equals(specificType)) {
                    return responseSheet.getRow(row1).getCell(col1).getStringCellValue();
                }
            }
        }
        else if(specificType == "") {
            if ((int) responseSheet.
                    getRow(row1).getCell(0).getNumericCellValue() == row1 &&
                    getExcelResponse(row1, 1).equals(responseType) &&
                    getExcelResponse(row1, 2).equals(topic)) {
                return responseSheet.getRow(row1).getCell(col1).getStringCellValue();
            }
        }
        else
            return responseSheet.getRow(row1).getCell(col1).getStringCellValue();
        return null;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void dispose(){
        response = null;
    }
}
