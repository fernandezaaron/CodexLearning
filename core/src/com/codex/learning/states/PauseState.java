package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.codex.learning.utility.decisiontree.Behavior;
import org.apache.poi.ss.formula.functions.T;

import java.awt.*;

public class PauseState extends State {

    private TextureRegion pauseMenu;
    private TextureRegion continueButton, retryButton, stageSelectButton, quitButton, settingsButton;
    private TextureRegion hlContinueButton, hlRetryButton, hlStageSelectButton, hlQuitButton, hlSettingsButton;
    private int state;
    private int stage;
    private Rectangle continueBounds, retryBounds, stageBounds, quitBounds, settingsBounds;
    private Vector3 coords;
    private boolean isRunning;
    private Settings settings;

    public PauseState(Manager manager){
        super(manager);
        isRunning = true;
        settings = new Settings(manager,0,0);

        pauseMenu = new TextureRegion(manager.getPauseStateSheet(), Constants.PAUSE_BOARD_X, Constants.PAUSE_BOARD_Y, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);
        continueButton = new TextureRegion(manager.getPauseStateSheet(), Constants.CONTINUE_BUTTON_X, Constants. CONTINUE_BUTTON_Y, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryButton = new TextureRegion(manager.getPauseStateSheet(), Constants.RETRY_BUTTON_X, Constants.RETRY_BUTTON_Y, Constants.RETRY_BUTTON_WIDTH, Constants.RETRY_BUTTON_HEIGHT);
        stageSelectButton = new TextureRegion(manager.getPauseStateSheet(), Constants.STAGE_BUTTON_X, Constants.STAGE_BUTTON_Y, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitButton = new TextureRegion(manager.getPauseStateSheet(), Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
        settingsButton = new TextureRegion(manager.getPauseStateSheet(), Constants.SETTINGS_BUTTON_X, Constants.SETTINGS_BUTTON_Y, Constants.SETTINGS_BUTTON_WIDTH, Constants.SETTINGS_BUTTON_HEIGHT);
        hlContinueButton = new TextureRegion(manager.getPauseStateSheet(), 859, 480, 161, 161);
        hlRetryButton = new TextureRegion(manager.getPauseStateSheet(), 1074, 459, 197, 171);
        hlStageSelectButton = new TextureRegion(manager.getPauseStateSheet(), 1303, 477, 178, 143);
        hlQuitButton = new TextureRegion(manager.getPauseStateSheet(), 1079, 689, 177, 182);
        hlSettingsButton = new TextureRegion(manager.getPauseStateSheet(), Constants.SETTINGS_HL_BUTTON_X, Constants.SETTINGS_HL_BUTTON_Y, Constants.SETTINGS_HL_BUTTON_WIDTH, Constants.SETTINGS_HL_BUTTON_HEIGHT);

        coords = new Vector3();
        continueBounds = new Rectangle( -300,  35, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryBounds = new Rectangle(-100, 35, Constants.RETRY_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        stageBounds = new Rectangle(150, 35, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitBounds = new Rectangle(50, -200, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
        settingsBounds = new Rectangle(-200, -200, Constants.SETTINGS_BUTTON_WIDTH, Constants.SETTINGS_BUTTON_HEIGHT);

        settings.setSettings(false);

        this.stage = stage;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        if(!settings.isSettings()){
            input();
        }


        if(state == Constants.GAME_PAUSED){
            manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            sprite.draw(pauseMenu,
                    manager.getCamera().position.x / Constants.PPM - pauseMenu.getRegionWidth() / 2,
                    manager.getCamera().position.y / Constants.PPM - pauseMenu.getRegionHeight() / 2);

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

            if(settingsBounds.contains(coords.x, coords.y)){
                sprite.draw(hlSettingsButton, manager.getCamera().position.x - 200, manager.getCamera().position.y -200);
            }else{
                sprite.draw(settingsButton, manager.getCamera().position.x - 200, manager.getCamera().position.y -200);
            }

            if(quitBounds.contains(coords.x, coords.y)){
                sprite.draw(hlQuitButton, manager.getCamera().position.x + 50, manager.getCamera().position.y - 200);
            }else{
                sprite.draw(quitButton, manager.getCamera().position.x + 50, manager.getCamera().position.y -200);
            }
        }else if(state == Constants.GAME_RUNNING){
            setRunning(true);
        }

        sprite.end();
        settings.render(sprite);
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
                    manager.getMusic().stop();
                    Behavior.currentDataSet.clear();
                    manager.set(new PlayState(manager));
                }
                if(stageBounds.contains(coords.x, coords.y)){
                    manager.getMusic().stop();
                    manager.getQuestionnaire().dispose();
//                    if (manager.getQuestionnaire().getMinigameHolder() != null){
//                        manager.getQuestionnaire().clearMinigames();
//                    }

                    manager.set(new StageSelectState(manager));

                }
                if(settingsBounds.contains(coords.x, coords.y)){
                    settings.setSettings(true);
                }
                if(quitBounds.contains(coords.x, coords.y)){
                    manager.getMusic().stop();
                    manager.getQuestionnaire().dispose();
                    if (manager.getQuestionnaire().getMinigameHolder() != null){
                        manager.getQuestionnaire().clearMinigames();
                    }
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