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


    public CodeRiddle(Manager manager) {
        super(manager);
        skin = new Skin(Gdx.files.internal("text/DialogBox.json"));
        atlas = new TextureAtlas(Gdx.files.internal("./text/DialogBox.atlas"));
        skin.addRegions(atlas);

//        db = new QuestionBox(skin, "questions");

        table = new Table();
        scrollTable = new Table();
        group = new Group();

        textButtons = new TextButton[4];

        scrollTable.setSkin(skin);
        scrollTable.setBackground("questions");

        table.setSkin(skin);
        table.setBackground("dialogbox1");

        text = new Label("\n", skin);

        Drawable dr = skin.getDrawable("dialogbox1");
        listStyle = new List.ListStyle();
        listStyle.font = manager.getFont();
        listStyle.background = dr;
        listStyle.down = dr;
        listStyle.over = dr;
        listStyle.selection = dr;
        skin.add("default", listStyle);

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
        //scrollPane.act(delta);
        castToTable(delta);
        manager.getStage().act(delta);
//        manager.getStage().draw();
       // manager.getStage().setDebugAll(true);
//        manager.getStage().getViewport().setScreenSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
      //  System.out.println(group.getChildren());
       // table.act(delta);
      //  stage.act(delta);


    }


    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();

        if(isInComputer()){
//            sprite.draw(screen,
//                    manager.getCamera().position.x * Constants.PPM - screen.getRegionWidth() / 2,
//                    manager.getCamera().position.y * Constants.PPM - screen.getRegionHeight() / 2);
//            sprite.draw(answerScreen,
//                    manager.getCamera().position.x * Constants.PPM - answerScreen.getRegionWidth() / 2,
//                    manager.getCamera().position.y * Constants.PPM - answerScreen.getRegionHeight() / 1.25f);
//            drawObject(sprite);




            if (currentQuestion <= manager.getQuestionnaire().getQuestionLimit() - 1) {

//                manager.getFont().draw(sprite, questions.get(currentQuestion),
//                        10 + manager.getCamera().position.x * Constants.PPM - answerScreen.getRegionWidth() / 2,
//                        -(manager.getCamera().position.y * Constants.PPM - answerScreen.getRegionHeight()) / 1.25f);
            }
            else{
//                sprite.draw(passedScoreScreen, manager.getCamera().position.x * Constants.PPM - passedScoreScreen.getRegionWidth()/2,
//                        manager.getCamera().position.y * Constants.PPM - passedScoreScreen.getRegionHeight()/2);
            }
        }

        //group.draw(sprite, 3);
        manager.getStage().draw();
        sprite.end();
    }

    public void castToTable(float delta){

        if(isInComputer()){
            table.setFillParent(true);
            table.debug();
            table.defaults().size(500, 150);
//            table.setPosition(manager.getStage().getWidth()/2 , manager.getStage().getHeight()/2 , Align.bottomLeft);
            table.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2/Constants.PPM + 25,manager.getCamera().position.x - Constants.SCREEN_HEIGHT/2/Constants.PPM + 15);
            text.setText("questions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd" +
                    "\nquestions.get(currentQuestion\nasd\nasd\nquestions.get(currentQuesasdasdadadation\nasd\nasd\nasd\nasd\nasd\nquestions.get(currentQuestion\nasd\nasd");

           // text.setFontScale(2.2f);
            text.setWrap(true);

//            List list = new List(skin);
//            list.setItems(new String[]{questions.get(currentQuestion)});

//            scrollTable.add(new Label("QUESTION: \n", skin)).top().left().padLeft(10f);
//            text.setText(questions.get(currentQuestion));
            for(int i=0; i<4; i++){
                textButtons[i] = new TextButton(options.get(currentQuestion).get(i), skin);
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
                                System.out.println("bobo ka");
                            }
                        }else {
                            System.out.println("tapos na");
                        }
//                        System.out.println("new+ " + currentQuestion);

                        //naglagay ako ng separate if condition para sa pagset ng text kasi nag iindexoutofbounds pag sinama ko sa currentquestion++

                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                        if(currentQuestion<= manager.getQuestionnaire().getQuestionLimit()-1){
                            text.setText(questions.get(currentQuestion));
                            for(int j=0; j<4; j++){
                                textButtons[j].setText(options.get(currentQuestion).get(j));
                            }
                        }
                    }

                });
            }





            scrollPane = new ScrollPane(text, skin);
            scrollPane.layout();
            scrollPane.updateVisualScroll();
            scrollPane.setForceScroll(false,true);
            scrollPane.debugAll();

            table.add(scrollPane).height(200).padTop(25f);
            table.row();
            table.add(scrollTable).height(250).padBottom(15f);
            table.pack();
            manager.getStage().addActor(table);
        }
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
        if(Gdx.input.justTouched()) {
            for (int i = 0; i < 4; i++) {
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
                } else {

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
