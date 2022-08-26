package com.codex.learning.entity.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Collisions;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.StageSelectState;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class SchoolMap extends State {
    private Collisions table, locker, upBorder, downBorder;


    public SchoolMap(Manager manager){
        super(manager);

        upBorder = new Collisions(manager);
        upBorder.create(new Vector2(-3.1f, 8), new Vector2(0.4f, 5.5f), 0);

        downBorder = new Collisions(manager);
        downBorder.create(new Vector2(-3.1f, -9), new Vector2(0.4f, 3.3f), 0);


        table = new Collisions(manager);
        table.create(new Vector2(-17.5f, 6.5f), new Vector2(6f, 0.5f), 0);

        locker = new Collisions(manager);
        locker.create(new Vector2(-23f, -5f), new Vector2(1f, 3.7f), 0);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.enableBlending();

        sprite.draw(manager.getStartSchool(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void setActive(boolean active){
        upBorder.getBody().setActive(active);
        downBorder.getBody().setActive(active);
        table.getBody().setActive(active);
        locker.getBody().setActive(active);
    }


}
