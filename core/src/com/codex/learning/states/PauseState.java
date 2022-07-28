package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;

public class PauseState extends State {

    private TextureRegion pauseMenu;
    private TextureRegion continueButton, retryButton, stageSelectButton, quitButton;
    private TextureRegion hlContinueButton, hlRetryButton, hlStageSelectButton, hlQuitButton;
    private int state;
    private Rectangle continueBounds, retryBounds, stageBounds, quitBounds;
    private Vector3 coords;
    private boolean isRunning;

    public PauseState(Manager manager){
        super(manager);
        isRunning = true;
        pauseMenu = new TextureRegion(manager.getPausestatesheet(), Constants.PAUSE_BOARD_X, Constants.PAUSE_BOARD_Y, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);
        continueButton = new TextureRegion(manager.getPausestatesheet(), Constants.CONTINUE_BUTTON_X, Constants. CONTINUE_BUTTON_Y, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryButton = new TextureRegion(manager.getPausestatesheet(), Constants.RETRY_BUTTON_X, Constants.RETRY_BUTTON_Y, Constants.RETRY_BUTTON_WIDTH, Constants.RETRY_BUTTON_HEIGHT);
        stageSelectButton = new TextureRegion(manager.getPausestatesheet(), Constants.STAGE_BUTTON_X, Constants.STAGE_BUTTON_Y, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitButton = new TextureRegion(manager.getPausestatesheet(), Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
        hlContinueButton = new TextureRegion(manager.getPausestatesheet(), 859, 480, 161, 161);
        hlRetryButton = new TextureRegion(manager.getPausestatesheet(), 1074, 459, 197, 171);
        hlStageSelectButton = new TextureRegion(manager.getPausestatesheet(), 1303, 477, 178, 143);
        hlQuitButton = new TextureRegion(manager.getPausestatesheet(), 1079, 689, 177, 182);

        coords = new Vector3();
        continueBounds = new Rectangle(-300, 35, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryBounds = new Rectangle(-100, 35, Constants.RETRY_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        stageBounds = new Rectangle(150, 35, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitBounds = new Rectangle(-100,-200, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        input();
        manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(state == Constants.GAME_PAUSED){
            sprite.draw(pauseMenu, manager.getCamera().position.x - Constants.SCREEN_WIDTH/4 + Constants.PPM, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.PPM, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);

            if(continueBounds.contains(coords.x, coords.y)){
                sprite.draw(hlContinueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }else{
                sprite.draw(continueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }

            if(retryBounds.contains(coords.x,coords.y)){
                sprite.draw(hlRetryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 30);
            }else{
                sprite.draw(retryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 10);
            }

            if(stageBounds.contains(coords.x, coords.y)){
                sprite.draw(hlStageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            }else{
                sprite.draw(stageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            }

            if(quitBounds.contains(coords.x, coords.y)){
                sprite.draw(hlQuitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y - 200);
            }else{
                sprite.draw(quitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y -200);
            }
        }else if(state == Constants.GAME_RUNNING){
            setRunning(true);
        }
        sprite.end();
    }


    @Override
    public void dispose() {

    }

    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            isRunning = false;
            state = Constants.GAME_PAUSED;
            return;
        }

        if(state == Constants.GAME_PAUSED){
            if(Gdx.input.isTouched()){
                manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                if(continueBounds.contains(coords.x, coords.y)){
                    state = Constants.GAME_RUNNING;
                }
                if(retryBounds.contains(coords.x, coords.y)){
                    manager.set(new PlayState(manager));
                }
                if(stageBounds.contains(coords.x, coords.y)){
                    manager.set(new StageSelectState(manager));
                }
                if(quitBounds.contains(coords.x, coords.y)){
                    manager.set(new MenuState(manager));
                }
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}