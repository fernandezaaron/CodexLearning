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
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private Vector3 touchpoint;
    private Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds;

    public MenuState(Manager manager) {
        super(manager);
        camera = new OrthographicCamera();
        b2dr = new Box2DDebugRenderer();

        background = new TextureRegion(new Texture(Constants.BACKGROUND_PATH));
        mainMenu = new TextureRegion(new Texture(Constants.MENU_TEXT_PATH));

        musicLogo = new TextureRegion(mainMenu, Constants.MUSIC_LOGO_X, Constants.MUSIC_LOGO_Y, Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
        javaDeluxe = new TextureRegion(mainMenu, Constants.JAVA_DELUXE_X, Constants.JAVA_DELUXE_Y, Constants.JAVA_DELUXE_WIDTH, Constants.JAVA_DELUXE_HEIGHT);
        grandpaRecipe = new TextureRegion(mainMenu, Constants.GRANDPA_RECIPE_X, Constants.GRANDPA_RECIPE_Y, Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        jediTrial = new TextureRegion(mainMenu, Constants.JEDI_TRIAL_X, Constants.JEDI_TRIAL_Y, Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        quitGame = new TextureRegion(mainMenu, Constants.QUIT_GAME_X, Constants.QUIT_GAME_Y, Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);

        touchpoint = new Vector3();
        javaDeluxeBounds = new Rectangle(Constants.TOUCH_POINT_X, Constants.TOUCH_POINT_Y,Constants.JAVA_DELUXE_WIDTH , Constants.JAVA_DELUXE_HEIGHT);
        recipeBounds = new Rectangle(Constants.TOUCH_POINT_X, Constants.TOUCH_POINT_Y,Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        jediTrialBounds = new Rectangle(Constants.TOUCH_POINT_X, Constants.TOUCH_POINT_Y,Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        quitGameBounds = new Rectangle(Constants.TOUCH_POINT_X, Constants.TOUCH_POINT_Y,Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);
        soundBounds = new Rectangle(Constants.TOUCH_POINT_X, Constants.TOUCH_POINT_Y,Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
    }

    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
       // sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.disableBlending();
        sprite.begin();

        sprite.draw(background, 0, 0, (Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2), (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2));
        sprite.end();
        sprite.enableBlending();

        sprite.begin();
        drawObject(sprite);
        //sprite.draw(javaDeluxe, 505, 221, Constants.JAVA_DELUXE_WIDTH / 2, Constants.JAVA_DELUXE_HEIGHT / 2);
//        sprite.draw(grandpaRecipe, 428, 189, Constants.GRANDPA_RECIPE_WIDTH / 2, Constants.GRANDPA_RECIPE_HEIGHT / 2);
//        sprite.draw(jediTrial, 503, 152, Constants.JEDI_TRIAL_WIDTH / 2, Constants.JEDI_TRIAL_HEIGHT / 2);
//        sprite.draw(quitGame, 518, 120, Constants.QUIT_GAME_WIDTH / 2, Constants.QUIT_GAME_HEIGHT / 2);
//        sprite.draw(musicLogo, 414, 115, Constants.MUSIC_LOGO_WIDTH / 2, Constants. MUSIC_LOGO_HEIGHT / 2);
        sprite.end();

    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(soundBounds.contains(touchpoint.x  + Constants.SOUND_BOUND_X, touchpoint.y)){
                System.out.println("You clicked at Sounds!");
            }
            if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at Java Deluxe!");
            }
            if(recipeBounds.contains(touchpoint.x + Constants.RECIPE_BOUND_X, touchpoint.y + Constants.RECIPE_BOUND_Y)){
                System.out.println("You clicked at JediGrandpa's Recipe!");
            }
            if(jediTrialBounds.contains(touchpoint.x, touchpoint.y + Constants.TRIAL_BOUND_Y)){
                System.out.println("You clicked at Jedi Trials!");
            }
            if(quitGameBounds.contains(touchpoint.x, touchpoint.y + Constants.QUIT_BOUND_Y)){
                System.out.println("You clicked at Quit Game!");
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(soundBounds.contains(touchpoint.x + Constants.SOUND_BOUND_X, touchpoint.y)){
            sprite.draw(musicLogo, soundBounds.x - Constants.SOUND_ON_SCREEN_X, soundBounds.y - Constants.SOUND_ON_SCREEN_Y,Constants.MUSIC_LOGO_WIDTH / 2, Constants.MUSIC_LOGO_HEIGHT / 2);
        }
        if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(javaDeluxe, javaDeluxeBounds.x - Constants.DELUXE_ON_SCREEN_X, javaDeluxeBounds.y - Constants.DELUXE_ON_SCREEN_Y,Constants.JAVA_DELUXE_WIDTH / 2, Constants.JAVA_DELUXE_HEIGHT / 2);
        }
        if(recipeBounds.contains(touchpoint.x + Constants.RECIPE_BOUND_X, touchpoint.y + Constants.RECIPE_BOUND_Y)){
            sprite.draw(grandpaRecipe, recipeBounds.x - Constants.RECIPE_ON_SCREEN_X, recipeBounds.y - Constants.RECIPE_ON_SCREEN_Y,Constants.GRANDPA_RECIPE_WIDTH / 2, Constants.GRANDPA_RECIPE_HEIGHT / 2);
        }
        if(jediTrialBounds.contains(touchpoint.x, touchpoint.y + Constants.TRIAL_BOUND_Y)){
            sprite.draw(jediTrial, jediTrialBounds.x - Constants.TRIAL_ON_SCREEN_X, jediTrialBounds.y - Constants.TRIAL_ON_SCREEN_Y,Constants.JEDI_TRIAL_WIDTH / 2, Constants.JEDI_TRIAL_HEIGHT / 2);
        }
        if(quitGameBounds.contains(touchpoint.x, touchpoint.y + Constants.QUIT_BOUND_Y)){
            sprite.draw(quitGame, quitGameBounds.x - Constants.QUIT_ON_SCREEN_X, quitGameBounds.y - Constants.QUIT_ON_SCREEN_Y,Constants.QUIT_GAME_WIDTH / 2, Constants.QUIT_GAME_HEIGHT / 2);
        }
    }



    @Override
    public void dispose() {

    }
}
