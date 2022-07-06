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

//This class is used to load the Menu State of the game.
public class MenuState extends State {


    private TextureRegion musicLogo, textHighlight;
    private Vector3 touchpoint;
    private Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds;

    public MenuState(Manager manager) {
        super(manager);

//        This is used to crop each sprite in a sprite sheet.
        textHighlight = new TextureRegion(new Texture(manager.get))

//        Touch point will allow the user to have a touch response.
        touchpoint = new Vector3();
//        Used to create an invisible rectangle for touch point.
        soundBounds = new Rectangle(Constants.SOUND_ON_SCREEN_X, Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxeBounds = new Rectangle(Constants.DELUXE_ON_SCREEN_X,  Constants.DELUXE_ON_SCREEN_Y,
                Constants.JAVA_DELUXE_WIDTH , Constants.JAVA_DELUXE_HEIGHT);
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
        sprite.draw(manager.getBackground(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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
            sprite.draw(musicLogo, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.SOUND_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.SOUND_ON_SCREEN_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        }
        if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(javaDeluxe, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f + Constants.DELUXE_ON_SCREEN_X,
                    manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f + Constants.DELUXE_ON_SCREEN_Y, Constants.JAVA_DELUXE_WIDTH, Constants.JAVA_DELUXE_HEIGHT);
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
