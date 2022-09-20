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
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;


import java.awt.*;
import java.util.ArrayList;

public class CodeRiddle extends State {
    private ScrollPane scrollPane;
    private Label text;
    private Table table, optionsTable, codeRiddleFeedbackTable, resultFeedbackTable, avatarImage, textTable;
    private DialogueBox dialogueBox;;
    private Group group;
    private List.ListStyle listStyle;
    private com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle;

    private TextButton[] textButtons;


    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;

    private boolean inComputer, isDone, isGivingHints, resultFeedBackOpen;
    private int currentQuestion;
    private int error;

    private FuzzyLogic fuzzyLogic;
    private ArrayList<String> behavior;

    private float timer, dialogTimer;

    public CodeRiddle(Manager manager, FuzzyLogic fuzzyLogic) {
        super(manager);

        this.fuzzyLogic = fuzzyLogic;
        behavior = new ArrayList<>();
        timer = 0;
        dialogTimer = 0;
        error = 0;

        table = new Table();
        optionsTable = new Table();
        textTable = new Table();
        codeRiddleFeedbackTable = new Table(manager.getSkin());
        resultFeedbackTable = new Table(manager.getSkin());
        avatarImage = new Table(manager.getSkin());
        group = new Group();



        dialogueBox = new DialogueBox(manager.getSkin(), "dialogbox3", 0.5f);


        if(manager.getStageSelector().map().equals("1")){
            avatarImage.setBackground("jediGrandpaAvatar");
        }else if(manager.getStageSelector().map().equals("2")){
            avatarImage.setBackground("jediProfAvatar");
        }else{
            avatarImage.setBackground("jediOfficeAvatar");
        }


        textButtons = new TextButton[4];

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
    }

    @Override
    public void update(float delta) {
        castToTable();
        manager.getStage().act(delta);
    }


    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();

        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        if(isInComputer()){
            timer += Gdx.graphics.getDeltaTime();
        }
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
                       textButtons[i] = new TextButton(options.get(currentQuestion).get(i), manager.getSkin());
                       optionsTable.add(textButtons[i]).grow().padLeft(10f).center();
                       optionsTable.row();
                   }

                   for(int i=0; i<4; i++){
                       final int tempI = i;
                       final int finalRight = right;
                       final int finalWrong = wrong;
                       textButtons[i].addListener(new InputListener(){

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
                                       textButtons[j].setText(options.get(currentQuestion).get(j));
                                   }
                               }else{
                                   fuzzyLogic.setNumberOfErrors(error);
                                   fuzzyLogic.setTimeConsumptions(fuzzyLogic.getTimeConsumptions() + timer);
                                   text.setAlignment(Align.center);
                                   text.setText("Your score is: " + (manager.getQuestionnaire().getQuestionLimit() - error) + "/" + manager.getQuestionnaire().getQuestionLimit() + "\n PRESS F TO CLOSE");
                                   setDone(true);
                                   for(int j=0; j<4; j++){
                                       textButtons[j].setText(" ");
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
               optionsTable.layout();
               table.add(scrollPane).height(200);
               table.row();
               table.add(optionsTable).height(200).width(780).padBottom(15f);
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



    // Time Consumption, Number of Error
    public void updateBehavior(){
        String currentBehavior = "";
        String time = checkTimeConsumption((int) timer);
        behavior.add("");
        behavior.add(time);
        behavior.add("");
        behavior.add(fuzzyLogic.getNumberOfErrorsRules());
        behavior.add("");
        currentBehavior = String.valueOf(manager.getDecisionTree().classify(behavior, manager.getDecisionTree().getTree()));

        if(currentBehavior.equals("ENGAGED") || currentBehavior.equals("NEUTRAL") || currentBehavior.equals("BORED")){
            //GIVE FEEDBACK


        }
        else{
            //GIVE HINTS

        }
        behavior.clear();
    }

    public String checkTimeConsumption(int timer){
        if(timer <= 90){
            return "LOW";
        }
        else if(timer <= 180){
            return "MEDIUM";
        }
        else if(timer <= 270){
            return "HIGH";
        }
        else{
            return "";
        }
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
}
