//package com.codex.learning.utility.filereader;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class LexicalAnalyzer {
//    static char[] text;
//    static ArrayList<Token> tokens;
//    static int index;
//
//    public LexicalAnalyzer(){
//        Iterator<Row> itr = sheet.iterator();    //iterating over excel file
//        while (itr.hasNext())
//        {
//            Row row = itr.next();
//            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
//            while (cellIterator.hasNext())
//            {
//                Cell cell = cellIterator.next();
//                text = cell.toString().toCharArray();
//                tokens = new ArrayList<Token>();
//                int n = text.length;
//                index = 0;
//                while (index < n) {
//                    if (text[index] == ' ' || text[index] == '\n' || text[index] == '\t') {
//                        index++;
//                        continue;
//                    }
//                    if (isDigit(text[index])) {
//                        makeNumber();
//                    } else if (isLetter(text[index])) {
//                        makeWord();
//                    } else if (isSeparators(text[index])) {
//                        makeSeparators();
//                    } else if (isOperator(text[index])) {
//                        makeOperator();
//                    } else if (isPunctuator(text[index])) {
//                        makePunctuator();
//                    } else {
//                        Token newToken = new Token();
//                        switch (text[index]) {
//                            case '"':
//                                newToken.value = String.valueOf(text[index]);
//                                newToken.type = "comment";
//                                tokens.add(newToken);
//                                break;
//                        }
//                    }
//                    index++;
//                }
//                for (Token tk : tokens) {
//                    System.out.print(tk.value + " " );
//                    // System.out.println(tk.type + " " );
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    static class Token {
//        String value;
//        String type;
//    }
//    static void makeNumber() {
//        String formedNumber = "";
//        while (index < text.length && isDigit(text[index])) {
//            formedNumber += text[index];
//            index++;
//        }
//        Token newToken = new Token();
//        newToken.value = formedNumber;
//        newToken.type = "number";
//        tokens.add(newToken);
//        index--;
//    }
//
//    static void makeWord() {
//        String formedWord = "";
//        Token newToken = new Token();
//        while (index < text.length && isLetter(text[index])) {
//            if (isLetter(text[index])) {
//                formedWord += text[index];
//                index++;
//                if (isKeyword(formedWord)) {
//                    newToken.type = "Keyword";
//                } else if (isSemiKeyword(formedWord)){
//                    newToken.type = "Semi Keyword";
//                } else if (isConditional(formedWord)){
//                    newToken.type = "Conditional";
//                } else if (isDataTypes(formedWord)){
//                    newToken.type = "Data types";
//                } else if (isBoolTypes(formedWord)){
//                    newToken.type = "Boolean types";
//                } else {
//                    newToken.type = "String";
//                }
//            }
//        }
//        newToken.value = formedWord;
//        tokens.add(newToken);
//        index--;
//    }
//
//    static void makeOperator() {
//        String formedOperator = "";
//        while (index < text.length && isOperator(text[index])) {
//            formedOperator += text[index];
//            index++;
//        }
//        Token newToken = new Token();
//        newToken.value = formedOperator;
//        newToken.type = "operator";
//        tokens.add(newToken);
//        index--;
//    }
//    static void makeSeparators() {
//        String formedSeparator = "";
//        while (index < text.length && isSeparators(text[index])) {
//            formedSeparator += text[index];
//            index++;
//        }
//        Token newToken = new Token();
//        newToken.value = formedSeparator;
//        newToken.type = "Separator";
//        tokens.add(newToken);
//        index--;
//    }
//    static void makePunctuator() {
//        String formedpunctuator = "";
//        while (index < text.length && isPunctuator(text[index])) {
//            formedpunctuator += text[index];
//            index++;
//        }
//        Token newToken = new Token();
//        newToken.value = formedpunctuator;
//        newToken.type = "Punctuator";
//        tokens.add(newToken);
//        index--;
//    }
//
//    static boolean isDigit(char ch) {
//        char[] digits = "0123456789".toCharArray();
//        for (int i = 0; i < digits.length; i++) {
//            if (digits[i] == ch) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    static boolean isLetter(char ch) {
//        String alphabet = "abcdefghijklmnopqrstuvwxyz";
//        char[] lowerLetters = alphabet.toLowerCase().toCharArray();
//        char[] upperLetters = alphabet.toUpperCase().toCharArray();
//        for (int i = 0; i < alphabet.length(); i++) {
//            if (ch == lowerLetters[i] || ch == upperLetters[i]) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isKeyword(String formedWord) {
//        String[] keywords = {"abstract", "class", "default", "enum", "extends", "implements",
//                "instanceof", "interface", "native", "package", "public", "static",
//                "strictfp", "super", "synchronized", "transient",  "void", "volatile","this",
//                "System","out","print","println", "main"};
//        for (int i = 0; i < keywords.length; i++) {
//            if (keywords[i].equals(formedWord)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isSemiKeyword(String formedWord) {
//        String[] semiKeywords = {"throw", "throws", "return", "break", "continue", "const", "import",
//                "assert", "final", "new", "private", "protected"};
//        for (int i = 0; i < semiKeywords.length; i++) {
//            if (semiKeywords[i].equals(formedWord)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isConditional(String formedWord) {
//        String[] conditional = {"catch", "try", "finally", "do", "while", "if", "else", "switch", "case", "for"};
//        for (int i = 0; i < conditional.length; i++) {
//            if (conditional[i].equals(formedWord)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isDataTypes(String formedWord) {
//        String[] dataTypes = {"boolean", "byte", "int", "char", "long", "double", "float", "short", "String"};
//        for (int i = 0; i < dataTypes.length; i++) {
//            if (dataTypes[i].equals(formedWord)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isBoolTypes(String formedWord) {
//        String[] boolTypes = {"true", "false", "TRUE", "FALSE"};
//        for (int i = 0; i < boolTypes.length; i++) {
//            if (boolTypes[i].equals(formedWord)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isSeparators(char ch) {
//        char[] separators = "{}[](),.;".toCharArray();
//        for (int i = 0; i < separators.length; i++) {
//            if (separators[i] == ch) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    static boolean isOperator(char ch) {
//        char[] operator = "-+%/*=<>!|".toCharArray();
//        for (int i = 0; i < operator.length; i++) {
//            if (operator[i] == ch) {
//                return true;
//            }
//        }
//        return false;
//    }
//    static boolean isPunctuator(char ch) {
//        char[] punctuator = "^:~?#".toCharArray();
//        for (int i = 0; i < punctuator.length; i++) {
//            if (punctuator[i] == ch) {
//                return true;
//            }
//        }
//        return false;
//    }
//}