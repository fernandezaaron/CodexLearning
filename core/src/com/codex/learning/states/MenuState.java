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
    private TextureRegion background, backgroundRegion;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    public MenuState(Manager manager) {
        super(manager);
        camera = new OrthographicCamera();
        b2dr = new Box2DDebugRenderer();

        background = new TextureRegion(new Texture(Gdx.files.internal("background/mainmenu1.png")));
        backgroundRegion = new TextureRegion(background, 0, 0, 1600, 900);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
//        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.disableBlending();
        sprite.begin();

        sprite.draw(backgroundRegion, 0, 0, 1600, 900);
        sprite.end();
        sprite.enableBlending();
    }

    @Override
    public void dispose() {

    }
}
