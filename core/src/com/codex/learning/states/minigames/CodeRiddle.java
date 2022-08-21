package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

import org.apache.poi.ss.formula.functions.T;


import java.awt.*;
import java.util.ArrayList;

public class CodeRiddle extends State {

    private TextureRegion screen;

    private TextureRegion answerScreen, passedScoreScreen;
    private TextureRegion[] choicesScreen;
    private TextureAtlas atlas;
    private Skin skin;
    private Stage stage;
    private ScrollPane scrollPane;
    private Label text;
    private Table table, scrollTable;
    private Group group;
    private List.ListStyle listStyle;
    private com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle;


    private Rectangle[] choicesBounds;
    private Vector3 touchPoint;
    private TextButton[] textButtons;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;



    private boolean inComputer;
    private int currentQuestion;
    private int error;

    private FuzzyLogic fuzzyLogic;

    private float timer;

    public CodeRiddle(Manager manager, FuzzyLogic fuzzyLogic) {
        super(manager);

        this.fuzzyLogic = fuzzyLogic;
        timer = 0;
        error = 0;

//        skin = new Skin(Gdx.files.internal("text/DialogBox.json"));
//        atlas = new TextureAtlas(Gdx.files.internal("./text/DialogBox.atlas"));
//        skin.addRegions(atlas);
//        manager.getSkin().load(Constants.JSON_DIALOG_BOX_SKIN_PATH);
        table = new Table();
        scrollTable = new Table();
        group = new Group();

        textButtons = new TextButton[4];

        scrollTable.setSkin(manager.getSkin());
        scrollTable.setBackground("optionScreen");

        table.setSkin(manager.getSkin());
        table.setBackground("PCSCREEN");

        text = new Label("\n", manager.getSkin());

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
        manager.getSkin().add("pokemon", manager.getFont());


        inComputer = false;

        getAQuestion("Stage 1", "Novice");
        currentQuestion = 0;
    }

    @Override
    public void update(float delta) {
        castToTable();
        manager.getStage().act(delta);
    }


    @Override
    public void render(SpriteBatch sprite) {
        if(isInComputer()){
            timer += Gdx.graphics.getDeltaTime();
        }

        sprite.enableBlending();

        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();
        if(isInComputer()){
            timer += Gdx.graphics.getDeltaTime();
            System.out.println(timer);

        }
        manager.getStage().act();
        manager.getStage().draw();

        sprite.end();

    }

    public void castToTable(){

        if(isInComputer()){

            table.setFillParent(true);
//            table.debug();
            table.defaults().size(500, 150);
            table.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM - 10);
//            text.setText("questions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
//                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
//                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
//                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd");

            text.setWrap(true);
           if(currentQuestion == manager.getQuestionnaire().getQuestionLimit()){
               System.out.println("done na");
               text.setText("TAPOS KANA LODS");

               for(int j=0; j<4; j++){
                   textButtons[j].setText(" ");
               }

           }else{
               if(text.getText().contains(questions.get(currentQuestion))){
                   System.out.println("oh meron nayan lods");
               }else{
                   text.setText(questions.get(currentQuestion));
                   text.setAlignment(Align.center);

                   for(int i=0; i<4; i++){
                       textButtons[i] = new TextButton(options.get(currentQuestion).get(i), manager.getSkin());
                       scrollTable.add(textButtons[i]).grow().padLeft(10f).center();
                       scrollTable.row();
                   }

                   for(int i=0; i<4; i++){
                       final int tempI = i;
                       textButtons[i].addListener(new InputListener(){

                           @Override
                           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                               if(currentQuestion <= manager.getQuestionnaire().getQuestionLimit()-1){
                                   if(manager.getQuestionnaire().answerChecker(options.get(currentQuestion).get(tempI), currentQuestion)){

                                       currentQuestion++;
                                       System.out.println("Your Answer is correct!");

                                   }else{
                                       currentQuestion++;
                                       error++;
                                       System.out.println("bobo ka");
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
                                   fuzzyLogic.setTimeConsumptions(timer);

                                   fuzzyLogic.fuzzyNumberOfError();
                                   fuzzyLogic.fuzzyTimeConsumption();

                                   text.setText("YOU ARE DONE xD");
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
               scrollPane = new ScrollPane(text, manager.getSkin());
               scrollPane.layout();
//               scrollPane.updateVisualScroll();
                scrollPane.setScrollbarsOnTop(true);
               scrollPane.setForceScroll(false,true);
//               scrollPane.debugAll();
               scrollPane.setSmoothScrolling(true);
               table.add(scrollPane).height(150).padTop(25f);
               table.row();
               table.add(scrollTable).height(200).padBottom(15f);
               table.pack();
           }


            manager.getStage().addActor(table);
//           manager.getStage().setDebugAll(true);
           // manager.getStage().setScrollFocus(table.getChild(0));
        }
    }


    @Override
    public void dispose() {

    }

    public void getAQuestion(String stage, String expertiseLevel){
        manager.getQuestionnaire().questionDisplay(stage,expertiseLevel);

        questions = manager.getQuestionnaire().getQuestions();

        options = manager.getQuestionnaire().getOptions();

        fuzzyLogic.setTotalQuestions(manager.getQuestionnaire().getQuestionLimit());
    }


    public boolean isInComputer() {
        return inComputer;
    }

    public void setInComputer(boolean inComputer) {
        this.inComputer = inComputer;
    }

}
