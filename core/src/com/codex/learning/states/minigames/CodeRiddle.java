package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CodeRiddle extends State {
    private ScrollPane scrollPane;
    private Label text;
    private Table table, optionsTable, codeRiddleFeedbackTable, resultFeedbackTable, avatarImage, textTable;
    private DialogueBox dialogueBox;;
    private Group group;
    private float maxTimer;
    private List.ListStyle listStyle;
    private com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle;

    private TextButton[] textButtons;
    private ImageTextButton[] imageTextButton;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;

    private boolean inComputer, isDone, isGivingHints, resultFeedBackOpen;
    private int currentQuestion;
    private int error;

    private FuzzyLogic fuzzyLogic;
    private ArrayList<String> behavior;

    private float timer, dialogTimer, fuzzyTimer;

    private ArrayList<ArrayList<String>> codeRiddleData;
    private int dataCounter;
    private boolean once;
    private boolean twice;

    public CodeRiddle(Manager manager, FuzzyLogic fuzzyLogic) {
        super(manager);

        this.fuzzyLogic = fuzzyLogic;
        behavior = new ArrayList<>();
        timer = 0;
        dialogTimer = 0;
        error = 0;
        maxTimer = 15;
        fuzzyTimer = 0;

        table = new Table();
        optionsTable = new Table();
        textTable = new Table();
        codeRiddleFeedbackTable = new Table(manager.getSkin());
        resultFeedbackTable = new Table(manager.getSkin());
        avatarImage = new Table(manager.getSkin());
        group = new Group();
        once = false;
        twice = false;

        dialogueBox = new DialogueBox(manager.getSkin(), "dialogbox3", 0.5f);

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
        text.setFontScale(0.7f);


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

        getAQuestion(manager.getStageSelector().map());
        currentQuestion = 0;

        codeRiddleData = new ArrayList<>();
        dataCounter = 0;
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
            table.defaults().size(500, 180);
            table.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM - 10);


           if(currentQuestion == manager.getQuestionnaire().getQuestionLimit()){
               text.setWrap(true);
               text.setText("Press \"F\" t to close the Computer");

               for(int j=0; j<4; j++){
                   textButtons[j].setText(" ");
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
                       imageTextButton[i].getLabel().setFontScale(0.55f);
                       imageTextButton[i].setText(options.get(currentQuestion).get(i));

//                       textButtons[i] = new TextButton(options.get(currentQuestion).get(i), manager.getSkin());
                       optionsTable.add(imageTextButton[i]).width(358).height(45).center();
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
               textTable.add(text).width(420).pad(25f);
               scrollPane = new ScrollPane(textTable, manager.getSkin());
               scrollPane.layout();
               scrollPane.setScrollbarsOnTop(true);
               scrollPane.setForceScroll(false,true);
               scrollPane.setSmoothScrolling(true);

               table.add(scrollPane).height(200);
               table.row();
               table.add(optionsTable).height(200).width(500).padBottom(15f);
               table.pack();

           }
            manager.getStage().addActor(table);
        }
    }

    public void resultFeedback(){
        resultFeedbackTable.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM + 200,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM + 400);
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
                    resultFeedbackTable.defaults().width(600).height(50);
                    resultFeedbackTable.add(avatarImage).width(50).height(50).padRight(15f);
                    resultFeedbackTable.add(dialogueBox).align(Align.left).width(300);
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
        codeRiddleFeedbackTable.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM + 200,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM + 400);
        dialogueBox.setOpen(false);

        //if is giving hints open a dialogue box
        if(isGivingHints){
            if(!dialogueBox.isOpen()){
                dialogueBox.textAnimation(text);
                if(!codeRiddleFeedbackTable.hasChildren()) {
                    codeRiddleFeedbackTable.defaults().width(600).height(50);
                    codeRiddleFeedbackTable.add(avatarImage).width(50).height(50).padRight(15f);
                    codeRiddleFeedbackTable.add(dialogueBox).align(Align.right).width(300);
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


<<<<<<< HEAD
    public void checkBehavior(int timer){
//        String currentBehavior = "";
//        if(isDone && !twice){
//            once = true;
//            twice = true;
//        }
//        if((timer > 0 && timer % 15 == 0) || once){
//            System.out.println(manager.getDtree().codeRiddleML(checkTimeConsumption(timer),
//                    convertNumberOfError(error)));
//            currentBehavior = manager.getDtree().codeRiddleML(checkTimeConsumption(timer),
//                    convertNumberOfError(error));
//
//            codeRiddleData.add(new ArrayList<String>());
//            codeRiddleData.get(dataCounter).add(checkTimeConsumption(timer));
//            codeRiddleData.get(dataCounter).add(convertNumberOfError(error));
//            codeRiddleData.get(dataCounter).add(currentBehavior);
//            dataCounter++;
//            System.out.println("CODE RIDDLE NA YUN - " + codeRiddleData);
//            once = false;
//
//            if(currentBehavior.equals("ENGAGED")){
//                //Yung behavior na dialogue na engaged
//                System.out.println("WOW keep it up my dudes!!");
//            }
//            else{
//                //Yung behavior na dialogue na not engaged
//                System.out.println("Haha lungkot mo naman!!");
//            }
//        }
=======
    public void checkBehavior(float timer, float fuzzyTimer){
        String currentBehavior = "";
        if(isDone && !twice){
            once = true;
            twice = true;
        }
        if((timer > maxTimer) || once){
            try {
                System.out.println(manager.getServer().calculateMLResult(checkTimeConsumption((int) fuzzyTimer) +
                        convertNumberOfError(error)));
                currentBehavior = manager.getServer().calculateMLResult(checkTimeConsumption((int) fuzzyTimer) +
                        convertNumberOfError(error));
            } catch (IOException e) {
                e.printStackTrace();
            }

            codeRiddleData.add(new ArrayList<String>());
            codeRiddleData.get(dataCounter).add(checkTimeConsumption((int) fuzzyTimer));
            codeRiddleData.get(dataCounter).add(convertNumberOfError(error));
            codeRiddleData.get(dataCounter).add(currentBehavior);
            dataCounter++;
            System.out.println("CODE RIDDLE NA YUN - " + codeRiddleData);
            once = false;
            this.timer = 0;

            if(currentBehavior.equals("ENGAGED")){
                //Yung behavior na dialogue na engaged
                System.out.println("WOW keep it up my dudes!!");
            }
            else{
                //Yung behavior na dialogue na not engaged
                System.out.println("Haha lungkot mo naman!!");
            }
        }
>>>>>>> Paul
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
}
