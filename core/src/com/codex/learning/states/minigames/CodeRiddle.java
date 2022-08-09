package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;

public class CodeRiddle extends State {

    private TextureRegion screen;

    private TextureRegion questionScreen;
    private TextureRegion[] choicesScreen;

    private Rectangle[] questionBounds;
    private Vector3 touchPoint;

    private TextArea question;
    private TextArea[] choices;
    private boolean inComputer;

    public CodeRiddle(Manager manager) {
        super(manager);
        inComputer = false;
        touchPoint = new Vector3();
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);

        questionScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);

        choicesScreen = new TextureRegion[4];
        questionBounds = new Rectangle[4];
        for(int i = 0; i < 4; i++){
            choicesScreen[i] = new TextureRegion(manager.getPcStateSheet(), Constants.PC_CHOICES_X, Constants.PC_CHOICES_Y, Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
            questionBounds[i] = new Rectangle(
                    (int) -100,
                    (int) -(40 * (i + 1)),
                    Constants.PC_CHOICES_WIDTH, Constants.PC_CHOICES_HEIGHT);
        }
//        manager.getQuestionnaire().questionDisplay("","");
//
//        question = new TextArea(manager.getQuestionnaire().getQuestion(), (Skin) null);


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
        }

        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        for(int i = 0; i < 4; i++){
            if(questionBounds[i].contains(touchPoint.x, touchPoint.y)){
                sprite.draw(choicesScreen[i],
                        manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight() - 20) * i + 1);
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
