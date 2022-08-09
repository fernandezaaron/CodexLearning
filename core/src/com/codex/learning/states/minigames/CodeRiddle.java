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
    private boolean inComputer;

    public CodeRiddle(Manager manager) {
        super(manager);
        inComputer = false;
        screen = new TextureRegion(manager.getPcStateSheet(), Constants.PC_SCREEN_X, Constants.PC_SCREEN_Y, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
//        manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(isInComputer()){
            sprite.draw(screen, manager.getCamera().position.x - Constants.SCREEN_WIDTH/4 + Constants.PPM, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.PPM, Constants.PC_SCREEN_WIDTH, Constants.PC_SCREEN_HEIGHT);
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
