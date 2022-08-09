package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;
import org.apache.poi.ss.formula.functions.T;

public class Settings extends State{

    private TextureRegion settingsMenu;
    private TextureRegion onButton, offButton, closeButton;
    private TextureRegion onHLButton, offHLButton, closeHLButton;
    private Rectangle onBounds, offBounds, closeBounds;
    private Vector3 coords;
    private boolean isSettings;

    public Settings(Manager manager){
        super(manager);
        settingsMenu = new TextureRegion(manager.getSettingsStateSheet(), Constants.SETTINGS_BOARD_X, Constants.SETTINGS_BOARD_Y, Constants.SETTINGS_BOARD_WIDTH, Constants.SETTINGS_BOARD_HEIGHT);
        onButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.ON_NORMAL_X, Constants.ON_NORMAL_Y, Constants.ON_NORMAL_WIDTH, Constants.ON_NORMAL_HEIGHT);
        offButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.OFF_NORMAL_X, Constants.OFF_NORMAL_Y, Constants.OFF_NORMAL_WIDTH, Constants.OFF_NORMAL_HEIGHT);
        onHLButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.ON_HL_X, Constants.ON_HL_Y, Constants.ON_HL_WIDTH, Constants.ON_HL_HEIGHT);
        offHLButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.OFF_HL_X, Constants.OFF_HL_Y, Constants.OFF_HL_WIDTH, Constants.OFF_HL_HEIGHT);
        closeButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.CLOSE_NORMAL_X, Constants.CLOSE_NORMAL_Y, Constants.CLOSE_NORMAL_WIDTH, Constants.CLOSE_NORMAL_HEIGHT);
        closeHLButton = new TextureRegion(manager.getSettingsStateSheet(), Constants.CLOSE_HL_X, Constants.CLOSE_HL_Y, Constants.CLOSE_HL_WIDTH, Constants.CLOSE_HL_HEIGHT);

        coords = new Vector3();
        onBounds = new Rectangle(manager.getCamera().position.x - 200, manager.getCamera().position.y - 20, Constants.ON_NORMAL_WIDTH, Constants.ON_NORMAL_HEIGHT);
        offBounds = new Rectangle(manager.getCamera().position.x + 100, manager.getCamera().position.y - 20, Constants.OFF_NORMAL_WIDTH, Constants.OFF_NORMAL_HEIGHT);
        closeBounds = new Rectangle(manager.getCamera().position.x - 310, manager.getCamera().position.y + 280, Constants.CLOSE_NORMAL_WIDTH, Constants.CLOSE_NORMAL_HEIGHT);



    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        input();

        if(isSettings){
            manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            sprite.draw(settingsMenu, manager.getCamera().position.x - settingsMenu.getRegionWidth()/2,
                    manager.getCamera().position.y - settingsMenu.getRegionHeight()/2) ;


            if(onBounds.contains(coords.x, coords.y)){
                sprite.draw(onHLButton, manager.getCamera().position.x - 200, manager.getCamera().position.y - 20);
            }else{
                sprite.draw(onButton, manager.getCamera().position.x - 200, manager.getCamera().position.y - 20);
            }
            if(offBounds.contains(coords.x,coords.y)){
                sprite.draw(offHLButton, manager.getCamera().position.x + 100, manager.getCamera().position.y - 20);
            }else{
                sprite.draw(offButton, manager.getCamera().position.x + 100, manager.getCamera().position.y - 20);
            }
            if(closeBounds.contains(coords.x, coords.y)){
                sprite.draw(closeHLButton, manager.getCamera().position.x - 310, manager.getCamera().position.y + 280);
            }else{
                sprite.draw(closeButton, manager.getCamera().position.x - 310, manager.getCamera().position.y + 280);
            }



        }

//        System.out.println("yes");


        sprite.end();
    }

    public void input(){
        if(isSettings){
            manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(Gdx.input.isTouched()){
                if(onBounds.contains(coords.x, coords.y)){
                    manager.resumeMusic(manager.getMusic());
                }
                if(offBounds.contains(coords.x,coords.y)){
                    manager.pauseMusic(manager.getMusic());
                }
                if(closeBounds.contains(coords.x, coords.y)){
                    setSettings(false);
                }
            }
        }
    }

    @Override
    public void dispose() {

    }

    public boolean isSettings() {
        return isSettings;
    }

    public void setSettings(boolean settings) {
        isSettings = settings;
    }
}
