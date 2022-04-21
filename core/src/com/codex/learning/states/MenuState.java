package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class MenuState extends State {

    private TextureRegion mainMenu;
    private Texture musicLogo, javaDeluxe, grandpaRecipe, jediTrial, quitGame;
    private TextureRegion background;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    public MenuState(Manager manager) {
        super(manager);
        camera = new OrthographicCamera();
        b2dr = new Box2DDebugRenderer();

        background = new TextureRegion(new Texture(Constants.BACKGROUND_PATH));
        mainMenu = new TextureRegion(new Texture(Constants.MENU_TEXT_PATH));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
//        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.disableBlending();
        sprite.begin();

        sprite.draw(background, 0, 0, (Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2), (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2));
        sprite.end();
        sprite.enableBlending();
    }

    @Override
    public void dispose() {

    }
}
