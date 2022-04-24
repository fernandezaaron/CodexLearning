package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;
import jdk.vm.ci.meta.Constant;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class MenuState extends State {

    private TextureRegion mainMenu;
    private TextureRegion musicLogo, javaDeluxe, grandpaRecipe, jediTrial, quitGame;
    private TextureRegion background;
    private Vector3 touchpoint;
    private Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds;

    public MenuState(Manager manager) {
        super(manager);
        background = new TextureRegion(new Texture(Constants.BACKGROUND_PATH));
        mainMenu = new TextureRegion(new Texture(Constants.MENU_TEXT_PATH));

        musicLogo = new TextureRegion(mainMenu, Constants.MUSIC_LOGO_X, Constants.MUSIC_LOGO_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxe = new TextureRegion(mainMenu, Constants.JAVA_DELUXE_X, Constants.JAVA_DELUXE_Y, Constants.JAVA_DELUXE_WIDTH, Constants.JAVA_DELUXE_HEIGHT);
        grandpaRecipe = new TextureRegion(mainMenu, Constants.GRANDPA_RECIPE_X, Constants.GRANDPA_RECIPE_Y, Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        jediTrial = new TextureRegion(mainMenu, Constants.JEDI_TRIAL_X, Constants.JEDI_TRIAL_Y, Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        quitGame = new TextureRegion(mainMenu, Constants.QUIT_GAME_X, Constants.QUIT_GAME_Y, Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);

        touchpoint = new Vector3();
        soundBounds = new Rectangle(Constants.SOUND_ON_SCREEN_X, Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxeBounds = new Rectangle(Constants.DELUXE_ON_SCREEN_X, Constants.DELUXE_ON_SCREEN_Y, Constants.JAVA_DELUXE_WIDTH , Constants.JAVA_DELUXE_HEIGHT);
        recipeBounds = new Rectangle(Constants.RECIPE_ON_SCREEN_X, Constants.RECIPE_ON_SCREEN_Y, Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        jediTrialBounds = new Rectangle(Constants.TRIAL_ON_SCREEN_X, Constants.TRIAL_ON_SCREEN_Y, Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        quitGameBounds = new Rectangle(Constants.QUIT_ON_SCREEN_X, Constants.QUIT_ON_SCREEN_Y, Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);

    }

    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.draw(background, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        drawObject(sprite);
        sprite.end();
    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(soundBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at Sounds!");
            }
            if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
                manager.set(new StageSelectState(manager));
            }
            if(recipeBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at JediGrandpa's Recipe!");
            }
            if(jediTrialBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at Jedi Trials!");
            }
            if(quitGameBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at Quit Game!");
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(soundBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(musicLogo, Constants.SOUND_ON_SCREEN_X, Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        }
        if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(javaDeluxe, Constants.DELUXE_ON_SCREEN_X, Constants.DELUXE_ON_SCREEN_Y, Constants.JAVA_DELUXE_WIDTH, Constants.JAVA_DELUXE_HEIGHT);
        }
        if(recipeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(grandpaRecipe, Constants.RECIPE_ON_SCREEN_X, Constants.RECIPE_ON_SCREEN_Y, Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        }
        if(jediTrialBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(jediTrial, Constants.TRIAL_ON_SCREEN_X, Constants.TRIAL_ON_SCREEN_Y, Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        }
        if(quitGameBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(quitGame, Constants.QUIT_ON_SCREEN_X, Constants.QUIT_ON_SCREEN_Y, Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);
        }
    }

    @Override
    public void dispose() {

    }
}
