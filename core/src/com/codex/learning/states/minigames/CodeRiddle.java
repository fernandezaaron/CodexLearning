package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class CodeRiddle extends State {

    private TextureRegion screen;
    private TextureRegion questionScreen;
    private TextureRegion[] choicesScreen;

    private boolean inComputer;

    public CodeRiddle(Manager manager) {
        super(manager);
        inComputer = false;
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);
        questionScreen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_QUESTION_X, Constants.PC_QUESTION_Y, Constants.PC_QUESTION_WIDTH, Constants.PC_QUESTION_HEIGHT);

        choicesScreen = new TextureRegion[4];
        for(int i = 0; i < 4; i++){
            choicesScreen[i] = new TextureRegion(manager.getPcStateSheet(), 247, 2, 772, 57);
        }

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.begin();

//        manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(isInComputer()){
            sprite.draw(screen,
                    manager.getCamera().position.x * Constants.PPM - screen.getRegionWidth() / 2,
                    manager.getCamera().position.y * Constants.PPM - screen.getRegionHeight() / 2);

            sprite.draw(questionScreen,
                    manager.getCamera().position.x * Constants.PPM - questionScreen.getRegionWidth() / 2,
                    manager.getCamera().position.y * Constants.PPM - questionScreen.getRegionHeight() / 10);
            for(int i = 0; i < 4; i++){
                sprite.draw(choicesScreen[i],
                        manager.getCamera().position.x * Constants.PPM - choicesScreen[i].getRegionWidth() / 2,
                        - (manager.getCamera().position.y * Constants.PPM - choicesScreen[i].getRegionHeight() ));
            }
        }
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public boolean isInComputer() {
        return inComputer;
    }

    public void setInComputer(boolean inComputer) {
        this.inComputer = inComputer;
    }
}
