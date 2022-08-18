package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.Manager;
import com.codex.learning.utility.QuestionBox;

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
    private QuestionBox db;
    private com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle;


    private Rectangle[] choicesBounds;
    private Vector3 touchPoint;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;



    private boolean inComputer;
    private int currentQuestion;


    public CodeRiddle(Manager manager) {
        super(manager);
        skin = new Skin(Gdx.files.internal("text/DialogBox.json"));
        atlas = new TextureAtlas(Gdx.files.internal("./text/DialogBox.atlas"));
        skin.addRegions(atlas);
//        db = new QuestionBox(skin, "questions");
//        stage = new Stage();
        table = new Table();
        scrollTable = new Table();
        scrollTable.setSkin(skin);
        scrollTable.setBackground("dialogbox2");

        table.setSkin(skin);
        table.setBackground("dialogbox2");

        text = new Label("\n", skin);


        //ScrollPane.ScrollPaneStyle spStyle = skin.get("verticalScroll", ScrollPane.ScrollPaneStyle.class);
        //skin.add("default", spStyle);

        labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        labelStyle.font = manager.getFont();
        labelStyle.font.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        skin.add("default", labelStyle);

        manager.getFont().setColor(Color.BLACK);
        skin.add("pokemon", manager.getFont());


        inComputer = false;
        touchPoint = new Vector3();
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);


        answerScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);
        passedScoreScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_PASSED_X, Constants.PC_PASSED_Y, Constants.PC_PASSED_WIDTH, Constants.PC_PASSED_HEIGHT);

        choicesScreen = new TextureRegion[4];
        choicesBounds = new Rectangle[4];
        for(int i = 0; i < 4; i++){
            choicesScreen[i] = new TextureRegion(manager.getPcStateSheet(), Constants.PC_CHOICES_X, Constants.PC_CHOICES_Y, Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
            choicesBounds[i] = new Rectangle(
                    (int) -100,
                    (int) -(40 * (i + 1)),
                    Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
        }

        getAQuestion("", "");

        currentQuestion = 0;
    }

    @Override
    public void update(float delta) {
       // scrollPane.act(delta);
        table.act(delta);


    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();

        if(isInComputer()){
            sprite.draw(screen,
                    manager.getCamera().position.x * Constants.PPM - screen.getRegionWidth() / 2,
                    manager.getCamera().position.y * Constants.PPM - screen.getRegionHeight() / 2);
            sprite.draw(answerScreen,
                    manager.getCamera().position.x * Constants.PPM - answerScreen.getRegionWidth() / 2,
                    manager.getCamera().position.y * Constants.PPM - answerScreen.getRegionHeight() / 1.25f);
            drawObject(sprite);
//
//            text = new Label();
//            text.setAlignment(Label.LEFT);
//            text.setText(questions.get(currentQuestion));


            if (currentQuestion <= manager.getQuestionnaire().getQuestionLimit() - 1) {
                table.setFillParent(true);
                scrollTable.setFillParent(true);
                text.setText(questions.get(currentQuestion));
                text.setScale(100,100);
                scrollTable.add(text);
                scrollTable.pack();
//                scrollTable.setTransform(true);
//                scrollTable.setOrigin(scrollTable.getWidth()/2, scrollTable.getHeight()/2);
//                scrollTable.setScale(0.5f);

               // scrollTable.draw(sprite, 1);
                scrollPane = new ScrollPane(text, skin);
                scrollPane.setFillParent(true);
                System.out.println(scrollPane.getActor());
                scrollPane.setScrollbarsVisible(true);
                scrollPane.setScrollbarsOnTop(true);
                scrollPane.setFadeScrollBars(false);
               // scrollPane.draw(sprite, 1);
               // scrollPane.debugAll();
                scrollPane.debug();

               // table.add(new Label("QUESTION: ", skin)).align(Align.center).width(table.getWidth()/2);
                table.add(scrollPane).expand().fill();

               // table.setHeight(300);
                table.setPosition( manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2/Constants.PPM);
                table.draw(sprite, 1);
                table.debug();
//                manager.getFont().draw(sprite, questions.get(currentQuestion),
//                        10 + manager.getCamera().position.x * Constants.PPM - answerScreen.getRegionWidth() / 2,
//                        -(manager.getCamera().position.y * Constants.PPM - answerScreen.getRegionHeight()) / 1.25f);
            }
            else{
                sprite.draw(passedScoreScreen, manager.getCamera().position.x * Constants.PPM - passedScoreScreen.getRegionWidth()/2,
                        manager.getCamera().position.y * Constants.PPM - passedScoreScreen.getRegionHeight()/2);
            }
        }

        sprite.end();
    }

    public void castToTable(){

//                scrollTable = new Table();
////            scrollTable.setSkin(skin);
////            scrollTable.setBackground("questions");
//                scrollTable.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2);
//                scrollTable.debug();
//
//                scrollPane = new ScrollPane(scrollTable);

               // table.add(db).expand().fill(true);
               // stage.addActor(table);
               // table.setFillParent(true);




//                db.textAnimation("asfsaga");
              //  table.add("db").fill(true).align(Align.center);



    }

    @Override
    public void dispose() {

    }

    public void getAQuestion(String stage, String expertiseLevel){
        manager.getQuestionnaire().questionDisplay("","");

        questions = manager.getQuestionnaire().getQuestions();

        options = manager.getQuestionnaire().getOptions();
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(Gdx.input.justTouched()){
            for(int i = 0; i < 4; i++){
                if (currentQuestion <= manager.getQuestionnaire().getQuestionLimit() - 1) {
                    if (choicesBounds[i].contains(touchPoint.x, touchPoint.y)) {

                        if (manager.getQuestionnaire().answerChecker(options.get(currentQuestion).get(i), currentQuestion)) {
                            currentQuestion++;
                            System.out.println("YOUR ANSWER IS CORRECT");
                        } else {
                            currentQuestion++;
                            System.out.println("WRONG");
                        }
                    }
                }
                else{

                }
            }
        }


        for(int i = 0; i < 4; i++) {
            if (choicesBounds[i].contains(touchPoint.x, touchPoint.y)) {
                sprite.draw(choicesScreen[i],
                        manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight() - 20) * i + 1);
            }
            if (currentQuestion <= manager.getQuestionnaire().getQuestionLimit() - 1) {
                manager.getFont().draw(sprite, options.get(currentQuestion).get(i),
                        10 + manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight()) * i + 1);
            }
        }
    }

    public boolean isInComputer() {
        return inComputer;
    }

    public void setInComputer(boolean inComputer) {
        this.inComputer = inComputer;
    }

}
