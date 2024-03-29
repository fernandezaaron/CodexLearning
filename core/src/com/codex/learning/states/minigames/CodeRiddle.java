package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;
import com.codex.learning.utility.decisiontree.MLDataSet;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CodeRiddle extends State {
    private ScrollPane scrollPane;
    private Label text;
    private Table table, optionsTable, codeRiddleFeedbackTable, resultFeedbackTable, avatarImage, textTable, behaviorTable;
    private DialogueBox dialogueBox, behaviorBox;
    private Group group;
    private float maxTimer;
    private List.ListStyle listStyle;
    private com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle;

    private TextButton[] textButtons;
    private ImageTextButton[] imageTextButton;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;

    private boolean once, twice, inComputer, isDone, isGivingHints, resultFeedBackOpen;
    private int currentQuestion;
    private int error;

    private FuzzyLogic fuzzyLogic;
    private ArrayList<String> behavior;
    private int behaviorIndex;
    private Random rand;

    private float timer, dialogTimer, fuzzyTimer, behaviorTimer;

    private ArrayList<ArrayList<String>> codeRiddleData;
    private int dataCounter;
    private boolean isEngaged, isNotEngaged, behaviorTableOpen;

    private MLDataSet mlDataSet;

    public CodeRiddle(Manager manager, FuzzyLogic fuzzyLogic) {
        super(manager);
        

        this.fuzzyLogic = fuzzyLogic;
        behavior = new ArrayList<>();
        timer = 0;
        dialogTimer = 0;
        error = 0;
        maxTimer = 15;
        fuzzyTimer = 0;
        behaviorIndex = 0;
        behaviorTimer = 0;
        once = false;
        twice = false;


        table = new Table();
        optionsTable = new Table();
        textTable = new Table();
        codeRiddleFeedbackTable = new Table(manager.getSkin());
        resultFeedbackTable = new Table(manager.getSkin());
        behaviorTable = new Table(manager.getSkin());
        avatarImage = new Table(manager.getSkin());
        group = new Group();
        rand = new Random();


        dialogueBox = new DialogueBox(manager.getSkin(), "dialogbox3", (manager.getStage().getWidth()/Constants.PPM)*0.02f);
        behaviorBox = new DialogueBox(manager.getSkin(), "behaviorbox", (manager.getStage().getWidth()/Constants.PPM)*0.015f);
        if(manager.getStageSelector().map().equals("1")){
            avatarImage.setBackground("jediGrandpaAvatar");
        }else if(manager.getStageSelector().map().equals("2")){
            avatarImage.setBackground("jediProfAvatar");
        }else{
            avatarImage.setBackground("jediOfficeAvatar");
        }

        textButtons = new TextButton[4];
        imageTextButton = new ImageTextButton[4];

        optionsTable.setSkin(manager.getSkin());
        optionsTable.setBackground("optionScreen");

        table.setSkin(manager.getSkin());
        table.setBackground("PCSCREEN");

        text = new Label("\n", manager.getSkin());
        text.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);


        Drawable dr = manager.getSkin().getDrawable("dialogbox1");
        listStyle = new List.ListStyle();
        listStyle.font = manager.getFont();
        listStyle.background = dr;
        listStyle.down = dr;
        listStyle.over = dr;
        listStyle.selection = dr;
        manager.getSkin().add("default", listStyle);


        labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        labelStyle.font = manager.getFont();

        labelStyle.font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        manager.getSkin().add("default", labelStyle);
        manager.getFont().setColor(Color.BLACK);


        inComputer = false;
        isDone = false;
        isGivingHints = true;
        behaviorTableOpen = false;
        isEngaged = false;

        getAQuestion(manager.getStageSelector().map());
        currentQuestion = 0;

        codeRiddleData = new ArrayList<>();
        dataCounter = 0;

        mlDataSet = new MLDataSet();
    }

    @Override
    public void update(float delta) {
        castToTable();
        manager.getStage().act(delta);
    }


    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();

        if(isInComputer()){
            timer += Gdx.graphics.getDeltaTime();
            fuzzyTimer += Gdx.graphics.getDeltaTime();
            checkBehavior(timer, fuzzyTimer);
            if (isBehaviorTableOpen()) {
                closeBehaviorTable();

            }
        }

        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();

        manager.getStage().act();
        manager.getStage().draw();

        sprite.end();

    }

    public void castToTable(){
        int right = 0;
        int wrong = 0;

        if(isInComputer()){
            switch (manager.getStageSelector().map()){
                case "1":
                    right = 0;
                    wrong = 1;
                    break;
                case "2":
                    right = 2;
                    wrong = 3;
                    break;
                case "3":
                    right = 4;
                    wrong = 5;
                    break;
            }
            table.setFillParent(true);
            table.defaults().size(manager.getStage().getWidth()*.65f, manager.getStage().getHeight()*.9f);
            table.setPosition(manager.getCamera().position.x - manager.getStage().getWidth()/2/Constants.PPM,manager.getCamera().position.x - manager.getStage().getHeight()/2/Constants.PPM - 10);

           if(currentQuestion == manager.getQuestionnaire().getQuestionLimit()){
               text.setWrap(true);
               text.setText("Press \"F\" t to close the Computer");
               for(int j=0; j<4; j++){
                   imageTextButton[j].setText(" ");
               }

           }
           else{
               text.setWrap(true);
               if(!(text.getText().contains(questions.get(currentQuestion)))){
                   text.setText(questions.get(currentQuestion));
                   text.setAlignment(Align.center);

                   for(int i=0; i<4; i++){
                       imageTextButton[i] = new ImageTextButton("",manager.getSkin());
                       imageTextButton[i].getLabel().setWrap(true);
                       imageTextButton[i].getLabel().setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.021f);
                       imageTextButton[i].setText(options.get(currentQuestion).get(i));

//                      textButtons[i] = new TextButton(options.get(currentQuestion).get(i), manager.getSkin());
                       optionsTable.add(imageTextButton[i]).width(manager.getStage().getWidth()*.73f).height(manager.getStage().getHeight()*.11f).center();
                       optionsTable.row();
                   }

                   for(int i=0; i<4; i++){
                       final int tempI = i;
                       final int finalRight = right;
                       final int finalWrong = wrong;
                       imageTextButton[i].addListener(new InputListener(){
                           @Override
                           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                               if(currentQuestion <= manager.getQuestionnaire().getQuestionLimit()-1){
                                   if(manager.getQuestionnaire().answerChecker(options.get(currentQuestion).get(tempI), currentQuestion)){
                                       currentQuestion++;
                                       createFeedBackTable(manager.getDialogue().codeRiddleFeedback(finalRight));

                                   }else{
                                       currentQuestion++;
                                       error++;
                                       createFeedBackTable(manager.getDialogue().codeRiddleFeedback(finalWrong));
                                   }
                               }
                               return true;
                           }

                           @Override
                           public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                               if(currentQuestion<= manager.getQuestionnaire().getQuestionLimit()-1){
                                   text.setText(questions.get(currentQuestion));
                                   for(int j=0; j<4; j++){
                                       imageTextButton[j].setText(options.get(currentQuestion).get(j));
                                   }
                               }else{
                                   fuzzyLogic.setNumberOfErrors(error);
                                   fuzzyLogic.setTimeConsumptions(fuzzyLogic.getTimeConsumptions() + timer);
                                   text.setAlignment(Align.center);
                                   text.setText("Your score is: " + (manager.getQuestionnaire().getQuestionLimit() - error) + "/" + manager.getQuestionnaire().getQuestionLimit() + "\n PRESS F TO CLOSE");
                                   setDone(true);
                                   for(int j=0; j<4; j++){
                                       imageTextButton[j].setText(" ");
                                   }
                               }
                           }
                       });
                   }
               }
           }

           if(!table.hasChildren()){
               textTable.add(text).height(manager.getStage().getHeight()*.2f).width(manager.getStage().getWidth()*.5f).pad(25f);
               scrollPane = new ScrollPane(textTable, manager.getSkin());
               scrollPane.layout();
               scrollPane.setScrollbarsOnTop(true);
               scrollPane.setForceScroll(false,true);
               scrollPane.setSmoothScrolling(true);

               table.add(scrollPane).height(manager.getStage().getHeight()*.45f);
               table.row();
               table.add(optionsTable).height(manager.getStage().getHeight()*.45f).width(manager.getStage().getWidth()*.75f).padBottom(15f);
               table.pack();
           }
            manager.getStage().addActor(table);
        }
    }

    public void resultFeedback(){
        resultFeedbackTable.setPosition(manager.getStage().getWidth()/5.2f,
                manager.getStage().getHeight()/1.13f);
        dialogueBox.setOpen(false);

        if(isGivingHints){
            if(!dialogueBox.isOpen()){
                setResultFeedBackOpen(true);
                if(fuzzyLogic.getPercentNumberOfErrors() < 70){
                    dialogueBox.textAnimation(manager.getDialogue().resultFeedback(1) + " Your score is " + (manager.getQuestionnaire().getQuestionLimit() - fuzzyLogic.getNumberOfErrors()));
                }
                else{
                    dialogueBox.textAnimation(manager.getDialogue().resultFeedback(0) + " Your score is " + (manager.getQuestionnaire().getQuestionLimit() - fuzzyLogic.getNumberOfErrors()));
                }

                if(!resultFeedbackTable.hasChildren()) {
                    resultFeedbackTable.defaults().width(0.38f*manager.getStage().getWidth()).height(0.08f*manager.getStage().getHeight());
                    resultFeedbackTable.add(avatarImage).width(0.045f*manager.getStage().getWidth()).height(0.08f*manager.getStage().getHeight()).padRight(15f);
                    resultFeedbackTable.add(dialogueBox).align(Align.left).width(0.3f*manager.getStage().getWidth());
                }
            }
        }

        resultFeedbackTable.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                //resets closes the table
                resultFeedbackTable.reset();
            }
        });


        manager.getStage().addActor(resultFeedbackTable);
    }

    public void closeDialogBox(){
        dialogTimer += Gdx.graphics.getDeltaTime();

        if(dialogTimer >= 2){
            resultFeedbackTable.setVisible(false);
            dialogueBox.setOpen(false);
            setResultFeedBackOpen(false);
            resultFeedbackTable.reset();
            dialogTimer = 0;
        }
    }

    public void createFeedBackTable(String text){
        codeRiddleFeedbackTable.setPosition(manager.getStage().getWidth()/5.2f,
                manager.getStage().getHeight()/1.13f);
        dialogueBox.setOpen(false);

        //if is giving hints open a dialogue box
        if(isGivingHints){
            if(!dialogueBox.isOpen()){
                dialogueBox.textAnimation(text);
                if(!codeRiddleFeedbackTable.hasChildren()) {
                    codeRiddleFeedbackTable.defaults().width(0.38f*manager.getStage().getWidth()).height(0.08f*manager.getStage().getHeight());
                    codeRiddleFeedbackTable.add(avatarImage).width(75).height(75).padRight(15f);
                    codeRiddleFeedbackTable.add(dialogueBox).align(Align.right).width(0.3f*manager.getStage().getWidth());
                }
            }
        }

        codeRiddleFeedbackTable.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                //resets closes the table
                codeRiddleFeedbackTable.reset();

            }
        });


        manager.getStage().addActor(codeRiddleFeedbackTable);
    }

    public void behaviorTable(){
        behaviorTable.setPosition(manager.getStage().getWidth()/1.3f,
                manager.getStage().getHeight()/1.13f);
        behaviorBox.setOpen(false);
        //if is giving hints open a dialogue box
        if(isEngaged() && isInComputer()){
            if(!behaviorBox.isOpen()){
                behaviorTable.setVisible(true);
                    behaviorIndex = rand.nextInt(10-1)+1;
                    behaviorBox.changeSkin(manager.getSkin(),"behaviorbox");
                    behaviorBox.textAnimation(manager.getDialogue().reader(behaviorIndex, "behavior", 0));
                    if(!behaviorTable.hasChildren()) {
                        behaviorTable.defaults().width(0.4f*manager.getStage().getWidth()).height(0.08f*manager.getStage().getHeight());
                        behaviorTable.add(avatarImage).width(75).height(75).padRight(15f);
                        behaviorTable.add(behaviorBox).align(Align.right).width(0.3f*manager.getStage().getWidth());
                    }
                setEngaged(false);
            }
        }

        if(isNotEngaged() && isInComputer()){
            if(!behaviorBox.isOpen()) {
                behaviorTable.setVisible(true);
                behaviorIndex = rand.nextInt(10-1)+1;
                behaviorBox.changeSkin(manager.getSkin(), "notengaged");
                behaviorBox.textAnimation(manager.getDialogue().reader(behaviorIndex, "notengaged", 0));
                if(!behaviorTable.hasChildren()) {
                    behaviorTable.defaults().width(0.4f*manager.getStage().getWidth()).height(0.08f*manager.getStage().getHeight());
                    behaviorTable.add(avatarImage).width(75).height(75).padRight(15f);
                    behaviorTable.add(behaviorBox).align(Align.right).width(0.3f*manager.getStage().getWidth());
                }
            }
            setNotEngaged(false);
        }


        behaviorTable.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent ie, float x, float y){
                //resets closes the table
                behaviorTable.reset();

            }
        });


        manager.getStage().addActor(behaviorTable);
    }

    public void closeBehaviorTable(){
        behaviorTimer += Gdx.graphics.getDeltaTime();
        if(behaviorTimer >= 4){
            behaviorTable.setVisible(false);
            dialogueBox.setOpen(false);
            setBehaviorTableOpen(false);
            behaviorTable.reset();
            behaviorTimer = 0;
        }
    }

    @Override
    public void dispose() {
        manager.getQuestionnaire().dispose();
    }

    public void getAQuestion(String stage){
        manager.getQuestionnaire().questionDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()));
        questions = manager.getQuestionnaire().getQuestions();
        options = manager.getQuestionnaire().getOptions();

        fuzzyLogic.setTotalQuestions(manager.getQuestionnaire().getQuestionLimit());
    }

    public void checkBehavior(float timer, float fuzzyTimer) {
        String currentBehavior = "";
        mlDataSet.setNumberOfErrors(convertNumberOfError(error - mlDataSet.getCurrentNumberOfErrors()));
        if(isDone && !twice){
            once = true;
            twice = true;
        }
        if ((timer > mlDataSet.getMaxTimer()) || once) {
            try {
                mlDataSet.setCurrentNumberOfErrors(error);
                currentBehavior = manager.getServer().calculateMLResult(
                        checkTimeConsumption((int) fuzzyTimer) +
                        mlDataSet.getNumberOfErrors());
                String[] data = currentBehavior.split(",");

                once = false;

                codeRiddleData.add(new ArrayList<String>());
                codeRiddleData.get(dataCounter).add(checkTimeConsumption((int) fuzzyTimer));
                codeRiddleData.get(dataCounter).add(mlDataSet.getNumberOfErrors());
                codeRiddleData.get(dataCounter).add(data[0]);
                codeRiddleData.get(dataCounter).add(data[1]);
                dataCounter++;
                this.timer = 0;

                if (data[0].equals("ENGAGED")) {
                    //Yung behavior na dialogue na engaged
                    setEngaged(true);
                    behaviorTable();
                    setBehaviorTableOpen(true);
                } else {
                    //Yung behavior na dialogue na not engaged
                    setNotEngaged(true);
                    behaviorTable();
                    setBehaviorTableOpen(true);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String convertNumberOfError(int numberOfError){
        float result = (float) (fuzzyLogic.getTotalQuestions() - numberOfError) / fuzzyLogic.getTotalQuestions();
        if(result <= .5)
            return "5";
        else if(result <= .6)
            return "4";
        else if(result <= .7)
            return "3";
        else if(result <= .8)
            return "2";
        else if(result <= .9)
            return "1";
        return "0";
    }

    public String checkTimeConsumption(int timer){
        switch(manager.getExpertSystem().getExpertiseLevel()){
            case "Expert":
                if(timer <= 15)
                    return "0";
                else if(timer <= 30)
                    return "1";
                else if(timer <= 45)
                    return "2";
                else if(timer <= 60)
                    return "3";
                else if(timer <= 75)
                    return "4";
                return "5";
            case "Average":
                if(timer <= 20)
                    return "0";
                else if(timer <= 40)
                    return "1";
                else if(timer <= 60)
                    return "2";
                else if(timer <= 80)
                    return "3";
                else if(timer <= 100)
                    return "4";
                return "5";
            case "Novice":
                if(timer <= 30)
                    return "0";
                else if(timer <= 60)
                    return "1";
                else if(timer <= 90)
                    return "2";
                else if(timer <= 120)
                    return "3";
                else if(timer <= 150)
                    return "4";
                return "5";
            case "Poor":
                if(timer <= 40)
                    return "0";
                else if(timer <= 80)
                    return "1";
                else if(timer <= 120)
                    return "2";
                else if(timer <= 160)
                    return "3";
                else if(timer <= 200)
                    return "4";
                return "5";
        }
        return "2";
    }


    public boolean isInComputer() {
        return inComputer;
    }

    public void setInComputer(boolean inComputer) {
        this.inComputer = inComputer;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isGivingHints() {
        return isGivingHints;
    }

    public void setGivingHints(boolean givingHints) {
        isGivingHints = givingHints;
    }

    public FuzzyLogic getFuzzyLogic() {
        return fuzzyLogic;
    }

    public void setFuzzyLogic(FuzzyLogic fuzzyLogic) {
        this.fuzzyLogic = fuzzyLogic;
    }

    public boolean isResultFeedBackOpen() {
        return resultFeedBackOpen;
    }

    public void setResultFeedBackOpen(boolean resultFeedBackOpen) {
        this.resultFeedBackOpen = resultFeedBackOpen;
    }

    public ArrayList<ArrayList<String>> getCodeRiddleData() {
        return codeRiddleData;
    }

    public void setCodeRiddleData(ArrayList<ArrayList<String>> codeRiddleData) {
        this.codeRiddleData = codeRiddleData;
    }

    public int getDataCounter() {
        return dataCounter;
    }

    public void setDataCounter(int dataCounter) {
        this.dataCounter = dataCounter;
    }

    public boolean isEngaged() {
        return isEngaged;
    }

    public void setEngaged(boolean engaged) {
        isEngaged = engaged;
    }

    public boolean isNotEngaged() {
        return isNotEngaged;
    }

    public void setNotEngaged(boolean notEngaged) {
        isNotEngaged = notEngaged;
    }

    public boolean isBehaviorTableOpen() {
        return behaviorTableOpen;
    }

    public void setBehaviorTableOpen(boolean behaviorTableOpen) {
        this.behaviorTableOpen = behaviorTableOpen;
    }

}
