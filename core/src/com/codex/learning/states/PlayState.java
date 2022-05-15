package com.codex.learning.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Blocks;
import com.codex.learning.entity.Jedisaur;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Contact;
import com.codex.learning.utility.Manager;

import java.awt.*;

public class PlayState extends State{

    private Jedisaur character;
    private Blocks blocks;
    public PlayState(Manager manager) {
        super(manager);
        character = new Jedisaur(manager);
        character.create(new Vector2(0,0),new Vector2(42, 60),1.6f);

        blocks = new Blocks(manager);
        blocks.create(new Vector2(200,0),new Vector2(50, 50),1.6f );

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
        sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f,manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        sprite.end();
        character.render(sprite);
        blocks.render(sprite);

    }

    @Override
    public void dispose() {
        character.disposeBody();
    }
}
