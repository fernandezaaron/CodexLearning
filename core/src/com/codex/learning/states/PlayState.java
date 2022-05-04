package com.codex.learning.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Jedisaur;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class PlayState extends State{
    //playstate
    TextureRegion stage1;
    Jedisaur character;

    public PlayState(Manager manager) {
        super(manager);
        character = new Jedisaur(manager);
        stage1 = new TextureRegion(new Texture(Constants.STAGE1_PATH));
        character.create(new Vector2(0,0),new Vector2(42, 60),1.6f);

    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        character.update(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();

        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.draw(stage1, manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        sprite.end();
        character.render(sprite);

    }

    @Override
    public void dispose() {
        character.disposeBody();
    }
}
