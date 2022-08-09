package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;

//This class is used to load the Menu State of the game.
public class MenuState extends State {

    private Music mainMenuMusic, stageSelectMusic;
    private TextureRegion musicLogo, textHighlight, skyBG, clouds;
    private Vector3 touchPoint;
    private Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds, reportCardBounds, helpBounds, settingsBounds;
    private float xMax, xCoord;

    public MenuState(Manager manager) {
        super(manager);

//        This is used to crop each sprite in a sprite sheet.
        textHighlight = new TextureRegion(manager.getMainMenu(), Constants.TEXT_HIGHLIGHT_X, Constants.TEXT_HIGHLIGHT_Y, Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        skyBG = new TextureRegion(new Texture(Constants.SKY_BACKGROUND_PATH));
        clouds = new TextureRegion(new Texture(Constants.CLOUD_PATH));

//        Touch point will allow the user to have a touch response.
        touchPoint = new Vector3();
//        Used to create an invisible rectangle for touch point.
  //      soundBounds = new Rectangle(Constants.SOUND_ON_SCREEN_X, Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxeBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.DELUXE_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.DELUXE_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        recipeBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.RECIPE_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.RECIPE_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        jediTrialBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.TRIAL_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.TRIAL_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        reportCardBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.REPORT_CARD_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 +Constants.REPORT_CARD_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        helpBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 +  Constants.HELP_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.HELP_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        settingsBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.SETTINGS_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.SETTINGS_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        quitGameBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.QUIT_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 +   Constants.QUIT_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);

        xMax = 2235;
        xCoord = xMax*(-1);

        manager.setMusic(Constants.MENU_MUSIC);

    }

    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();

        xCoord += Constants.BACKGROUND_SPEED * Gdx.graphics.getDeltaTime();
        if(xCoord >= 1000){
            xCoord = xMax*(-1);
        }

        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.draw(skyBG, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        sprite.draw(clouds, xCoord + manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + 230, Constants.CLOUDS_WIDTH, Constants.CLOUDS_HEIGHT);
        sprite.draw(manager.getBackground(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        drawObject(sprite);
        sprite.end();
    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
//            if(soundBounds.contains(touchpoint.x, touchpoint.y)){
//                System.out.println("You clicked at Sounds!");
//            }
            if(javaDeluxeBounds.contains(touchPoint.x, touchPoint.y)){
                manager.stopMusic(manager.getMusic());
                manager.set(new StageSelectState(manager));

            }
            if(recipeBounds.contains(touchPoint.x, touchPoint.y)){
                System.out.println("You clicked at JediGrandpa's Recipe!");
                System.out.println(touchPoint.x + " " + touchPoint.y);
                System.out.println(recipeBounds.x + " " + recipeBounds.y);
            }
            if(jediTrialBounds.contains(touchPoint.x, touchPoint.y)){
                System.out.println("You clicked at Jedi Trials!");
            }
            if(quitGameBounds.contains(touchPoint.x, touchPoint.y)){
                System.out.println("You clicked at Quit Game!");
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

//        if(soundBounds.contains(touchpoint.x, touchpoint.y)){
//            sprite.draw(musicLogo, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.SOUND_ON_SCREEN_X,
//                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.SOUND_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
//        }
        if(javaDeluxeBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.DELUXE_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.DELUXE_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(recipeBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.RECIPE_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.RECIPE_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(jediTrialBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.TRIAL_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.TRIAL_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(reportCardBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.REPORT_CARD_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.REPORT_CARD_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(helpBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.HELP_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.HELP_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(settingsBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.SETTINGS_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.SETTINGS_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(quitGameBounds.contains(touchPoint.x, touchPoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.QUIT_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.QUIT_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
    }

    @Override
    public void dispose() {

    }
}
