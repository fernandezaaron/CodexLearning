package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;
import java.util.ArrayList;

public class CodeRiddle extends State {

    private TextureRegion screen;

    private TextureRegion questionScreen;
    private TextureRegion[] choicesScreen;

    private Rectangle[] choicesBounds;
    private Vector3 touchPoint;

    private ArrayList<ArrayList<String>> questions;
    private ArrayList<ArrayList<String>> options;

    private String choice;

    private boolean inComputer;

    public CodeRiddle(Manager manager) {
        super(manager);
        inComputer = false;
        touchPoint = new Vector3();
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);

        questionScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);

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

        choice = null;
    }

    @Override
    public void update(float delta) {

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
            sprite.draw(questionScreen,
                    manager.getCamera().position.x * Constants.PPM - questionScreen.getRegionWidth() / 2,
                    manager.getCamera().position.y * Constants.PPM - questionScreen.getRegionHeight() / 1.25f);
            drawObject(sprite);

//            for(int i = 0; i < manager.getQuestionnaire().getNumberOfQuestions(); i++){
                manager.getFont().draw(sprite, questions.get(0).get(0),
                        10 + manager.getCamera().position.x * Constants.PPM - questionScreen.getRegionWidth() / 2,
                        - (manager.getCamera().position.y * Constants.PPM - questionScreen.getRegionHeight()) / 1.25f);
//            }
        }

        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void getAQuestion(String stage, String expertiseLevel){
        manager.getQuestionnaire().questionDisplay("","");


        questions = manager.getQuestionnaire().getQuestions();

        System.out.println(" QUESTIONS " + questions);
        options = manager.getQuestionnaire().getOptions();
        System.out.println(" options - - - -- " + options);
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(Gdx.input.justTouched()){
            for(int i = 0; i < 4; i++){
                if(choicesBounds[i].contains(touchPoint.x, touchPoint.y)) {
//                    for(int j = 0; j < 4; j++) {
                    System.out.println("1QWDQWDQWD1 - " + options.get(0).get(0));
                    System.out.println("2QWDQWDQWD2 - " + options.get(0).get(1));
                    System.out.println("3QWDQWDQWD3 - " + options.get(0).get(2));
                    System.out.println("4QWDQWDQWD4 - " + options.get(0).get(3));
                    if (manager.getQuestionnaire().answerChecker(options.get(0).get(i), i)) {
                        System.out.println("YOUR ANSWER IS CORRECT");
                    } else {
                        System.out.println("WRONG");
                    }
//                    }
                }
            }
        }


        for(int i = 0; i < 4; i++){
            if(choicesBounds[i].contains(touchPoint.x, touchPoint.y)){
                sprite.draw(choicesScreen[i],
                        manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight() - 20) * i + 1);
            }
//            for(int j = 0; j < 4; j++){
                manager.getFont().draw(sprite, options.get(0).get(i),
                        10 + manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight()) * i + 1);
//            }
        }
    }

    public boolean isInComputer() {
        return inComputer;
    }

    public void setInComputer(boolean inComputer) {
        this.inComputer = inComputer;
    }

}
