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
    Vector3 touchpoint;
    Rectangle javaDeluxeBounds, recipeBounds, jediTrialBounds, quitGameBounds, soundBounds;

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
        javaDeluxeBounds = new Rectangle((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2) + 200, (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2),Constants.JAVA_DELUXE_WIDTH , Constants.JAVA_DELUXE_HEIGHT);
        recipeBounds = new Rectangle((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2) + 200, (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2),Constants.GRANDPA_RECIPE_WIDTH, Constants.GRANDPA_RECIPE_HEIGHT);
        jediTrialBounds = new Rectangle((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2) + 200, (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2),Constants.JEDI_TRIAL_WIDTH, Constants.JEDI_TRIAL_HEIGHT);
        quitGameBounds = new Rectangle((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2) + 200, (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2),Constants.QUIT_GAME_WIDTH, Constants.QUIT_GAME_HEIGHT);
        soundBounds = new Rectangle((Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2) + 200, (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2),Constants.MUSIC_LOGO_WIDTH, Constants.MUSIC_LOGO_HEIGHT);
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
            if(soundBounds.contains(touchpoint.x + 170, touchpoint.y)){
                System.out.println("You clicked at Sounds!");
            }
            if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at Java Deluxe!");
            }
            if(recipeBounds.contains(touchpoint.x + 138, touchpoint.y + 72)){
                System.out.println("You clicked at JediGrandpa's Recipe!");
            }
            if(jediTrialBounds.contains(touchpoint.x, touchpoint.y + 150)){
                System.out.println("You clicked at Jedi Trials!");
            }
            if(quitGameBounds.contains(touchpoint.x, touchpoint.y + 222)){
                System.out.println("You clicked at Quit Game!");
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if(soundBounds.contains(touchpoint.x + 170, touchpoint.y)){
            sprite.draw(musicLogo, soundBounds.x - 586, soundBounds.y - 228,Constants.MUSIC_LOGO_WIDTH / 2, Constants.MUSIC_LOGO_HEIGHT / 2);
        }
        if(javaDeluxeBounds.contains(touchpoint.x, touchpoint.y)){
            sprite.draw(javaDeluxe, javaDeluxeBounds.x - 496, javaDeluxeBounds.y - 222,Constants.JAVA_DELUXE_WIDTH / 2, Constants.JAVA_DELUXE_HEIGHT / 2);
        }
        if(recipeBounds.contains(touchpoint.x + 138, touchpoint.y + 72)){
            sprite.draw(grandpaRecipe, recipeBounds.x - 572, recipeBounds.y - 262,Constants.GRANDPA_RECIPE_WIDTH / 2, Constants.GRANDPA_RECIPE_HEIGHT / 2);
        }
        if(jediTrialBounds.contains(touchpoint.x, touchpoint.y + 150)){
            sprite.draw(jediTrial, jediTrialBounds.x - 498, jediTrialBounds.y - 298,Constants.JEDI_TRIAL_WIDTH / 2, Constants.JEDI_TRIAL_HEIGHT / 2);
        }
        if(quitGameBounds.contains(touchpoint.x, touchpoint.y + 222)){
            sprite.draw(quitGame, quitGameBounds.x - 482, quitGameBounds.y - 337,Constants.QUIT_GAME_WIDTH / 2, Constants.QUIT_GAME_HEIGHT / 2);
        }



    }



    @Override
    public void dispose() {

    }
}
