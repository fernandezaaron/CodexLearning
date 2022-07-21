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



    public PauseState(Manager manager){
        super(manager);
        state = 1;
        pauseMenu = new TextureRegion(manager.getPausestatesheet(), Constants.PAUSE_BOARD_X, Constants.PAUSE_BOARD_Y, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);
        continueButton = new TextureRegion(manager.getPausestatesheet(), Constants.CONTINUE_BUTTON_X, Constants. CONTINUE_BUTTON_Y, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);
        retryButton = new TextureRegion(manager.getPausestatesheet(), Constants.RETRY_BUTTON_X, Constants.RETRY_BUTTON_Y, Constants.RETRY_BUTTON_WIDTH, Constants.RETRY_BUTTON_HEIGHT);
        stageSelectButton = new TextureRegion(manager.getPausestatesheet(), Constants.STAGE_BUTTON_X, Constants.STAGE_BUTTON_Y, Constants.STAGE_BUTTON_WIDTH, Constants.STAGE_BUTTON_HEIGHT);
        quitButton = new TextureRegion(manager.getPausestatesheet(), Constants.QUIT_BUTTON_X, Constants.QUIT_BUTTON_Y, Constants.QUIT_BUTTON_WIDTH, Constants.QUIT_BUTTON_HEIGHT);
        hl_continueButton = new TextureRegion(manager.getPausestatesheet(), 859, 480, 161, 161);

        coords = new Vector3();
        continueBounds = new Rectangle(-300, 35, Constants.CONTINUE_BUTTON_WIDTH, Constants.CONTINUE_BUTTON_HEIGHT);


    }

    @Override
    public void update(float delta) {

     //   input();

//        switch (state){
//
//            case Constants.GAME_PAUSED:
//                break;
//
//            case Constants.GAME_RUNNING:
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
//        }
    }

    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("Paused");
            manager.push(this);
            state = 2;
            return;
        }
        if(Gdx.input.isTouched()){
            manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(continueBounds.contains(coords.x, coords.y)){
                System.out.println("pressed continue");
            }
            System.out.println(coords.x + " " + coords.y);
            System.out.println("continue bounds: " + continueBounds.x + " " + continueBounds.y);
        }
    }

    @Override
    public void render(SpriteBatch sprite) {

        sprite.begin();
        input();
        manager.getCamera().unproject(coords.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(state == 2){
            //sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            sprite.draw(pauseMenu, manager.getCamera().position.x - Constants.SCREEN_WIDTH/4 + Constants.PPM, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.PPM, Constants.PAUSE_BOARD_WIDTH, Constants.PAUSE_BOARD_HEIGHT);

            if(continueBounds.contains(coords.x, coords.y)){
                sprite.draw(hl_continueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }else{
                sprite.draw(continueButton, manager.getCamera().position.x - 300, manager.getCamera().position.y + Constants.PPM);
            }

            sprite.draw(retryButton, manager.getCamera().position.x - 100, manager.getCamera().position.y + 10);
            sprite.draw(stageSelectButton, manager.getCamera().position.x + 150, manager.getCamera().position.y + Constants.PPM);
            sprite.draw(quitButton, manager.getCamera().position.x - 100, manager.getCamera().position.y - 200);

        }







        sprite.end();
    }


    @Override
    public void dispose() {

    }


}
