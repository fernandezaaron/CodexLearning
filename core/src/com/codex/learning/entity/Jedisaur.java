package com.codex.learning.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class Jedisaur extends entity{

    public Jedisaur(Manager manager) {
        super(manager);
    }

    @Override
    public void create(Vector2 position, Vector2 size, float density) {
        this.position = position;
        this.size = size;

        this.position.x /= Constants.PPM;
        this.position.y /= Constants.PPM;


    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {

    }
}
