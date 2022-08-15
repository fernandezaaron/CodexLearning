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

    private TextureRegion questionScreen, passedScoreScreen;
    private TextureRegion[] choicesScreen;

    private Rectangle[] choicesBounds;
    private Vector3 touchPoint;

    private ArrayList<String> questions;
    private ArrayList<ArrayList<String>> options;

    private boolean inComputer;
    private int currentQuestion;


    public CodeRiddle(Manager manager) {
        super(manager);
        inComputer = false;
        touchPoint = new Vector3();
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);

        questionScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);
        passedScoreScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_PASSED_X, Constants.PC_PASSED_Y, Constants.PC_PASSED_WIDTH, Constants.PC_PASSED_HEIGHT);

        choicesScreen = new TextureRegion[4];
        choicesBounds = new Rectangle[4];
        for(int i = 0; i < 4; i++){
            choicesScreen[i] = new TextureRegion(manager.getPcStateSheet(), Constants.PC_CHOICES_X, Constants.PC_CHOICES_Y, Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
            choicesBounds[i] = new Rectangle(
                    -550,
                    -(75 * (i + 1)),
                    Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
        }

        getAQuestion("Stage 1", "Novice");

        currentQuestion = 0;
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

            if (currentQuestion <= manager.getQuestionnaire().getQuestionLimit() - 1) {
                manager.getFont().draw(sprite, questions.get(currentQuestion),
                        10 + manager.getCamera().position.x * Constants.PPM - questionScreen.getRegionWidth() / 2,
                        -(manager.getCamera().position.y * Constants.PPM - questionScreen.getRegionHeight()) / 1.25f);
            }
            else{
                sprite.draw(passedScoreScreen, manager.getCamera().position.x * Constants.PPM - passedScoreScreen.getRegionWidth()/2,
                        manager.getCamera().position.y * Constants.PPM - passedScoreScreen.getRegionHeight()/2);
            }
        }
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void getAQuestion(String stage, String expertiseLevel){
        manager.getQuestionnaire().questionDisplay(stage,expertiseLevel);

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
            else{

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
