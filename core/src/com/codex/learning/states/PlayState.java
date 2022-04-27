package com.codex.learning.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Jedisaur;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class PlayState extends State{

    TextureRegion stage1;
    Jedisaur character;

    public PlayState(Manager manager) {
        super(manager);
        character = new Jedisaur(manager);
        stage1 = new TextureRegion(new Texture(Constants.STAGE1_PATH));
        character.create(new Vector2(0,0),new Vector2(32,32),1.6f);

    }

    @Override
    public void update(float delta) {
        character.update(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(stage1, 0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        sprite.end();
        character.render(sprite);

    }

    @Override
    public void dispose() {
        character.disposeBody();
    }
}
