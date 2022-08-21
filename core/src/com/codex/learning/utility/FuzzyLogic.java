package com.codex.learning.utility;

public class FuzzyLogic {
    private int numberOfErrors;
    private float timeConsumptions;
    private int correctOutput;
    private int numberOfAttempts;

    private float fuzzyNumberOfErrors;
    private float fuzzyTimeConsumptions;
    private float fuzzyNumberOfAttempts;

    private String numberOfErrorsRules;
    private String timeConsumptionRules;
    private String correctOutputRules;
    private String numberOfAttemptsRules;

    private int percentNumberOfErrors;
    private int percentTimeConsumptions;
    private int percentCorrectOutput;
    private int percentNumberOfAttempts;

    private int cookies;

    private int totalQuestions;


    public FuzzyLogic(){
        numberOfErrors = 0;
        timeConsumptions = 0;
        correctOutput = 0;
        numberOfAttempts = 0;
        totalQuestions = 0;

        timeConsumptionRules = "";
    }
    public void fuzzyNumberOfError(){
        int a = 0;
        int b = (int) (getTotalQuestions() * .30);
        int c = (int) (getTotalQuestions() * .60);
        int d = (int) (getTotalQuestions() * .90);

        setFuzzyNumberOfErrors(Math.max(min((getNumberOfErrors() - a)/(b - a), 1,
                (d - getNumberOfErrors()) / (d - c)), 0));

        if(getNumberOfErrors() <= a){
            setNumberOfErrorsRules("LOW");
            setPercentNumberOfErrors(100);
        }
        else if(getNumberOfErrors() >= a && getNumberOfErrors() <= b){
            setNumberOfErrorsRules("LOW");
            setPercentNumberOfErrors(75);
        }
        else if(getNumberOfErrors() >= c && getNumberOfErrors() <= d){
            setNumberOfErrorsRules("HIGH");
            setPercentNumberOfErrors(25);
        }
        else if(getNumberOfErrors() >= b && getNumberOfErrors() <= c){
            setNumberOfErrorsRules("MEDIUM");
            setPercentNumberOfErrors(50);
        }
        else if(getNumberOfErrors() > d) {
            setNumberOfErrorsRules("HIGH");
            setPercentNumberOfErrors(0);
        }
    }

    public void fuzzyTimeConsumption(){
        int a = 300;
        int b = 600;
        int c = 900;
        int d = 1200;

        setFuzzyTimeConsumptions(Math.max(min((getTimeConsumptions() - a)/(b - a), 1,
                (d - getTimeConsumptions()) / (d - c)), 0));

        if(getTimeConsumptions() <= a){
            setTimeConsumptionRules("LOW");
            setPercentTimeConsumptions(100);
        }
        else if(getTimeConsumptions() >= a && getTimeConsumptions() <= b){
            setTimeConsumptionRules("LOW");
            setPercentTimeConsumptions(75);
        }
        else if(getTimeConsumptions() >= c && getTimeConsumptions() <= d){
            setTimeConsumptionRules("HIGH");
            setPercentTimeConsumptions(25);
        }
        else if(getTimeConsumptions() >= b && getTimeConsumptions() <= c){
            setTimeConsumptionRules("MEDIUM");
            setPercentTimeConsumptions(50);
        }
        else{
            setTimeConsumptionRules("HIGH");
            setPercentTimeConsumptions(0);
        }

        System.out.println("RULE = " + getTimeConsumptionRules());
        System.out.println("PERCENT = " + getPercentTimeConsumptions());
    }

    public void fuzzyCorrectOutput(){
        switch (getCorrectOutput()){
            case 1:
                setCorrectOutputRules("YES");
                setPercentCorrectOutput(100);
                break;
            case 0:
                setCorrectOutputRules("NO");
                setPercentCorrectOutput(0);
                break;
        }
    }

    public void fuzzyNumberOfAttempt(){
        int a = 1;
        int b = 3;
        int c = 5;

        setFuzzyNumberOfAttempts(Math.max(Math.min((getFuzzyNumberOfAttempts() - a)/(b - a),
                (c - getFuzzyNumberOfAttempts())/(c - b)), 0));

         if(getNumberOfAttempts() <= a){
             setNumberOfAttemptsRules("LOW");
             setPercentNumberOfAttempts(100);
         }
         else if(getNumberOfAttempts() >= a && getNumberOfAttempts() <= b){
             setNumberOfAttemptsRules("MEDIUM");
             setPercentNumberOfAttempts(75);
         }
         else if(getNumberOfAttempts() >= b && getNumberOfAttempts() <= c){
             setNumberOfAttemptsRules("HIGH");
             setPercentNumberOfAttempts(50);
         }
         else if(getNumberOfAttempts() >= c){
             setNumberOfAttemptsRules("HIGH");
             setPercentNumberOfAttempts(25);
         }
    }

    public void calculateNumberOfCookies(){
        int total = 0;

        total = (getPercentNumberOfErrors() + getPercentTimeConsumptions()
                + getPercentCorrectOutput() + getPercentNumberOfAttempts()) / 4;

        if(total == 100){
            setCookies(3);
        }
        else if(total >= 80 && total < 100){
            setCookies(2);
        }
        else if(total >= 50 && total <= 80){
            setCookies(1);
        }
        else{
            setCookies(0);
        }
    }

    public static float min(double a, double b, double c) {
        return (float) Math.min(Math.min(a, b), c);
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public float getTimeConsumptions() {
        return timeConsumptions;
    }

    public void setTimeConsumptions(float timeConsumptions) {
        this.timeConsumptions = timeConsumptions;
    }

    public int getCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(int correctOutput) {
        this.correctOutput = correctOutput;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public float getFuzzyNumberOfErrors() {
        return fuzzyNumberOfErrors;
    }

    public void setFuzzyNumberOfErrors(float fuzzyNumberOfErrors) {
        this.fuzzyNumberOfErrors = fuzzyNumberOfErrors;
    }

    public float getFuzzyNumberOfAttempts() {
        return fuzzyNumberOfAttempts;
    }

    public void setFuzzyNumberOfAttempts(float fuzzyNumberOfAttempts) {
        this.fuzzyNumberOfAttempts = fuzzyNumberOfAttempts;
    }

    public float getFuzzyTimeConsumptions() {
        return fuzzyTimeConsumptions;
    }

    public void setFuzzyTimeConsumptions(float fuzzyTimeConsumptions) {
        this.fuzzyTimeConsumptions = fuzzyTimeConsumptions;
    }

    public String getNumberOfErrorsRules() {
        return numberOfErrorsRules;
    }

    public void setNumberOfErrorsRules(String numberOfErrorsRules) {
        this.numberOfErrorsRules = numberOfErrorsRules;
    }

    public String getTimeConsumptionRules() {
        return timeConsumptionRules;
    }

    public void setTimeConsumptionRules(String timeConsumptionRules) {
        this.timeConsumptionRules = timeConsumptionRules;
    }

    public String getCorrectOutputRules() {
        return correctOutputRules;
    }

    public void setCorrectOutputRules(String correctOutputRules) {
        this.correctOutputRules = correctOutputRules;
    }

    public String getNumberOfAttemptsRules() {
        return numberOfAttemptsRules;
    }

    public void setNumberOfAttemptsRules(String numberOfAttemptsRules) {
        this.numberOfAttemptsRules = numberOfAttemptsRules;
    }

    public int getPercentNumberOfErrors() {
        return percentNumberOfErrors;
    }

    public void setPercentNumberOfErrors(int percentNumberOfErrors) {
        this.percentNumberOfErrors = percentNumberOfErrors;
    }

    public int getPercentTimeConsumptions() {
        return percentTimeConsumptions;
    }

    public void setPercentTimeConsumptions(int percentTimeConsumptions) {
        this.percentTimeConsumptions = percentTimeConsumptions;
    }

    public int getPercentCorrectOutput() {
        return percentCorrectOutput;
    }

    public void setPercentCorrectOutput(int percentCorrectOutput) {
        this.percentCorrectOutput = percentCorrectOutput;
    }

    public int getPercentNumberOfAttempts() {
        return percentNumberOfAttempts;
    }

    public void setPercentNumberOfAttempts(int percentNumberOfAttempts) {
        this.percentNumberOfAttempts = percentNumberOfAttempts;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCookies() {
        return cookies;
    }

    public void setCookies(int cookies) {
        this.cookies = cookies;
    }
}