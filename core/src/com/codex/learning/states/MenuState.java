package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;
import jdk.vm.ci.meta.Constant;

public class MenuState extends State {

    private TextureRegion mainMenu;
    private TextureRegion musicLogo, javaDeluxe, grandpaRecipe, jediTrial, quitGame;
    private TextureRegion background;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

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

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.disableBlending();
        sprite.begin();

        sprite.draw(background, 0, 0, (Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2), (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2));
        sprite.end();
        sprite.enableBlending();

        sprite.begin();
        sprite.draw(javaDeluxe, 505, 221, Constants.JAVA_DELUXE_WIDTH / 2, Constants.JAVA_DELUXE_HEIGHT / 2);
        sprite.draw(grandpaRecipe, 428, 189, Constants.GRANDPA_RECIPE_WIDTH / 2, Constants.GRANDPA_RECIPE_HEIGHT / 2);
        sprite.draw(jediTrial, 503, 152, Constants.JEDI_TRIAL_WIDTH / 2, Constants.JEDI_TRIAL_HEIGHT / 2);
        sprite.draw(quitGame, 518, 120, Constants.QUIT_GAME_WIDTH / 2, Constants.QUIT_GAME_HEIGHT / 2);
        sprite.draw(musicLogo, 414, 115, Constants.MUSIC_LOGO_WIDTH / 2, Constants. MUSIC_LOGO_HEIGHT / 2);
        sprite.end();

    }

    @Override
    public void dispose() {

    }
}
