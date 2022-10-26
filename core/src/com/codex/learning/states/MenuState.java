package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.states.minigames.FillInTheBlock;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;

//This class is used to load the Menu State of the game.
public class MenuState extends State {

    private Music mainMenuMusic, stageSelectMusic;
    private TextureRegion musicLogo, textHighlight, skyBG, clouds;
    private Vector3 touchpoint;
    private Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds, reportCardBounds, helpBounds, settingsBounds;
    private float xMax, xCoord;
    private Settings settings;

    public MenuState(Manager manager) {
        super(manager);

        settings = new Settings(manager, manager.getCamera().position.x, manager.getCamera().position.y);

//        This is used to crop each sprite in a sprite sheet.
        textHighlight = new TextureRegion(manager.getMainMenu(), Constants.TEXT_HIGHLIGHT_X, Constants.TEXT_HIGHLIGHT_Y, Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        skyBG = new TextureRegion(new Texture(Constants.SKY_BACKGROUND_PATH));
        clouds = new TextureRegion(new Texture(Constants.CLOUD_PATH));

//        Touch point will allow the user to have a touch response.
        touchpoint = new Vector3();
//        Used to create an invisible rectangle for touch point.
  //      soundBounds = new Rectangle(Constants.SOUND_ON_SCREEN_X, Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxeBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.DELUXE_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.DELUXE_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        recipeBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.RECIPE_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.RECIPE_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        reportCardBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.TRIAL_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.TRIAL_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        helpBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.REPORT_CARD_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 +Constants.REPORT_CARD_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        settingsBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 +  Constants.HELP_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.HELP_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
        quitGameBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.SETTINGS_ON_SCREEN_X),
                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.SETTINGS_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);
//        quitGameBounds = new Rectangle((int) (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.QUIT_ON_SCREEN_X),
//                (int) (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 +   Constants.QUIT_ON_SCREEN_Y), Constants.TEXT_HIGHLIGHT_WIDTH, Constants.TEXT_HIGHLIGHT_HEIGHT);

        xMax = 2235;
        xCoord = xMax*(-1);

        settings.setSettings(false);
        if(!manager.isMusicPaused()){
            manager.setMusic(Constants.MENU_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        }else {
            manager.setMusic(Constants.MENU_MUSIC);
         //   manager.getMusic() = Gdx.audio.newMusic(Gdx.files.internal(Constants.MENU_MUSIC));
        }


    }

    @Override
    public void update(float delta) {
        if(!settings.isSettings()){
            input();
        }

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
        settings.render(sprite);
    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
                manager.getMusic().stop();
                manager.set(new StageSelectState(manager));

            }
            if(recipeBounds.contains(touchpoint.x, touchpoint.y)){
                manager.getMusic().stop();
                manager.set(new RecipeBook(manager));
//                System.out.println("You clicked at JediGrandpa's Recipe!");
//                System.out.println(touchpoint.x + " " + touchpoint.y);
//                System.out.println(recipeBounds.x + " " + recipeBounds.y);
            }

            if(settingsBounds.contains(touchpoint.x, touchpoint.y)){
//                System.out.println("clicked at settings");
                settings.setSettings(true);
            }
            if(reportCardBounds.contains(touchpoint.x, touchpoint.y)){
                manager.getMusic().stop();
                manager.set(new ReportCardState(manager));
            }

            if(helpBounds.contains(touchpoint.x, touchpoint.y)){
                manager.getMusic().stop();
                manager.set(new HowToPlayState(manager));
            }
            if(quitGameBounds.contains(touchpoint.x, touchpoint.y)){
//                System.out.println("You clicked at Quit Game!");
                Gdx.app.exit();
                System.exit(-1);
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.DELUXE_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.DELUXE_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(recipeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.RECIPE_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.RECIPE_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(reportCardBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.TRIAL_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.TRIAL_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(helpBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.REPORT_CARD_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.REPORT_CARD_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(settingsBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.HELP_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.HELP_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }
        if(quitGameBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(textHighlight, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.SETTINGS_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.SETTINGS_ON_SCREEN_Y, Constants.TEXT_HIGHLIGHT_WIDTH , Constants.TEXT_HIGHLIGHT_HEIGHT);
        }

    }

    @Override
    public void dispose() {
    }
}
