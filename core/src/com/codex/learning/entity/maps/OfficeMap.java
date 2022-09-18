package com.codex.learning.entity.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Collisions;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class OfficeMap extends State {

    private Collisions leftBorder, upBorder, downBorder, playroomBorder, lTableLeft, lTableMiddle, lTableRight, ltableLeftHorizontal, lTableRightHorizontal, lTableMiddleHorizontal;
    private Collisions tableLeft, tableRight, couches;
    public OfficeMap(Manager manager){
        super(manager);

        upBorder = new Collisions(manager);
        upBorder.create(new Vector2(15.1f, 8), new Vector2(0.4f, 5.5f), 0);

        downBorder = new Collisions(manager);
        downBorder.create(new Vector2(15.1f, -9), new Vector2(0.4f, 3.3f), 0);

        playroomBorder = new Collisions(manager);
        playroomBorder.create(new Vector2(16.3f, -1.5f), new Vector2(0.4f, 3.5f), 0);

        leftBorder = new Collisions(manager);
        leftBorder.create(new Vector2(-12f, 12.3f), new Vector2(0.4f, 30), 0);

        lTableLeft = new Collisions(manager);
        lTableLeft.create(new Vector2(-9f, 9), new Vector2(1, 2f), 0);
        ltableLeftHorizontal = new Collisions(manager);
        ltableLeftHorizontal.create(new Vector2(-8, 10.6f), new Vector2(2,1), 0);

        lTableMiddle = new Collisions(manager);
        lTableMiddle.create(new Vector2(-1, 9), new Vector2(1, 2f), 0);

        lTableMiddleHorizontal = new Collisions(manager);
        lTableMiddleHorizontal.create(new Vector2(0, 10.6f), new Vector2(2,1), 0);

        lTableRight = new Collisions(manager);
        lTableRight.create(new Vector2(7.5f, 9), new Vector2(1, 2f), 0);

        lTableRightHorizontal = new Collisions(manager);
        lTableRightHorizontal.create(new Vector2(8.5f, 10.6f), new Vector2(2,1), 0);

        tableLeft = new Collisions(manager);
        tableLeft.create(new Vector2(8, -6), new Vector2(5,1f),0);




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

        sprite.draw(manager.getStartOffice(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

//        checkDoor(sprite, atDoor);
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void setActive(boolean active){
        upBorder.getBody().setActive(active);
        downBorder.getBody().setActive(active);
        leftBorder.getBody().setActive(active);
        lTableLeft.getBody().setActive(active);
        ltableLeftHorizontal.getBody().setActive(active);
        lTableMiddle.getBody().setActive(active);
        lTableMiddleHorizontal.getBody().setActive(active);
        lTableRight.getBody().setActive(active);
        lTableRightHorizontal.getBody().setActive(active);
        tableLeft.getBody().setActive(active);
    }

    public void setPlayroomActive(boolean active){
        playroomBorder.getBody().setActive(active);
    }
}
