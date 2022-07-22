package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;
import org.w3c.dom.Text;

import java.awt.*;

public class PauseState extends State {

    private TextureRegion pauseMenu;
    private TextureRegion continueButton, retryButton, stageSelectButton, quitButton;
    private TextureRegion hl_continueButton, hl_retryButton, hl_stageSelectButton, hl_quitButton;
    private int state;
    private Rectangle continueBounds, retryBounds, stageBounds, quitBounds;
    private Vector3 coords;
    private boolean isRunning;


    public PauseState(Manager manager){
        super(manager);
       // state = 1;
        isRunning = true;
        pauseMenu = new TextureRegion(manager.getPausestatesheet(), Constants.PAUSE_BOARD_X, Constants.PAUSE_BOARD_Y, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);
        continueButton = new TextureRegion(manager.getPausestatesheet(), Constants.CONTINUE_BUTTON_X, Constants. CONTINUE_BUTTON_Y, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryButton = new TextureRegion(manager.getPausestatesheet(), Constants.RETRY_BUTTON_X, Constants.RETRY_BUTTON_Y, Constants.RETRY_BUTTON_WIDTH, Constants.RETRY_BUTTON_HEIGHT);
        stageSelectButton = new TextureRegion(manager.getPausestatesheet(), Constants.STAGE_BUTTON_X, Constants.STAGE_BUTTON_Y, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitButton = new TextureRegion(manager.getPausestatesheet(), Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
        hl_continueButton = new TextureRegion(manager.getPausestatesheet(), 859, 480, 161, 161);
        hl_retryButton = new TextureRegion(manager.getPausestatesheet(), 1074, 459, 197, 171);
        hl_stageSelectButton = new TextureRegion(manager.getPausestatesheet(), 1303, 477, 178, 143);
        hl_quitButton = new TextureRegion(manager.getPausestatesheet(), 1079, 689, 177, 182);

        coords = new Vector3();
        continueBounds = new Rectangle(-300, 35, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryBounds = new Rectangle(-100, 35, Constants.RETRY_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        stageBounds = new Rectangle(150, 35, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitBounds = new Rectangle(-100,-200, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);


    }

    @Override
    public void update(float delta) {


//
        System.out.println(state);
//        switch (state){
//
//            case Constants.GAME_PAUSED:
//                manager.push(this);
//                break;
//
//            case Constants.GAME_RUNNING:
//                System.out.println("im here");
//                manager.pop();
//                break;
//
//            case Constants.GAME_QUIT:
//                manager.pop();
//                manager.set(new MenuState(manager));
//                break;
//
//            case Constants.GAME_STAGE_SELECT:
//                manager.pop();
//                manager.set(new StageSelectState(manager));
//                break;
//        }
    }

    public void input(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.out.println("Paused");
            //manager.push(this);
            isRunning = false;
//            Gdx.input.setOnscreenKeyboardVisible(false);
            state = Constants.GAME_PAUSED;
            return;
        }
        if(Gdx.input.isTouched()){
            manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(continueBounds.contains(coords.x, coords.y)){
                System.out.println("pressed continue");
                state = Constants.GAME_RUNNING;
            }
//            System.out.println(coords.x + " " + coords.y);
//            System.out.println("continue bounds: " + continueBounds.x + " " + continueBounds.y);
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

    @Override
    public void render(SpriteBatch sprite) {

        sprite.begin();
        input();
        manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(state == Constants.GAME_PAUSED){
            //sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            sprite.draw(pauseMenu, manager.getCamera().position.x - Constants.SCREEN_WIDTH/4 + Constants.PPM, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.PPM, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);

            if(continueBounds.contains(coords.x, coords.y)){
                sprite.draw(hl_continueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }else{
                sprite.draw(continueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }

            if(retryBounds.contains(coords.x,coords.y)){
                sprite.draw(hl_retryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 30);
            }else{
                sprite.draw(retryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 10);
            }

            if(stageBounds.contains(coords.x, coords.y)){
                sprite.draw(hl_stageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            }else{
                sprite.draw(stageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            }

            if(quitBounds.contains(coords.x, coords.y)){
                sprite.draw(hl_quitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y - 200);
            }else{
                sprite.draw(quitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y -200);
            }




           // sprite.draw(retryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 10);
           // sprite.draw(stageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            //sprite.draw(quitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y - 200);

        }else if(state == Constants.GAME_RUNNING){
            setRunning(true);
           // System.out.println("hehe");
        }







        sprite.end();
    }


    @Override
    public void dispose() {

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


}
