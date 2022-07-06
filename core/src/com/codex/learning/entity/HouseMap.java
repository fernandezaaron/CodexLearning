package com.codex.learning.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.states.StageSelectState;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class HouseMap extends State {
    private Collisions upBorder, downBorder, table, cabinet, fridgeSink;
    private boolean atDoor;
    private TextureRegion door;
    public HouseMap(Manager manager) {
        super(manager);
//      Create invisible collision for the character.
        upBorder = new Collisions(manager);
        upBorder.create(new Vector2(-6, 8), new Vector2(0.2f, 4), 0);

        downBorder = new Collisions(manager);
        downBorder.create(new Vector2(-6, -8), new Vector2(0.2f, 4), 0);

        cabinet = new Collisions(manager);
        cabinet.create(new Vector2(-8.5f, 12.3f), new Vector2(2, 3), 0);

        table = new Collisions(manager);
        table.create(new Vector2(-17.5f, 5), new Vector2(3f, 2.3f), 0);

        fridgeSink = new Collisions(manager);
        fridgeSink.create(new Vector2(-20f, 12.3f), new Vector2(3.3f, 3), 0);

//      Used to exit the map
        atDoor = false;

        door = new TextureRegion(manager.getReportcardsheet(), 48,195, 263, 119);
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
//        sprite.draw(manager.getStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        checkDoor(sprite, atDoor);
        sprite.end();
    }

    @Override
    public void dispose() {
        fridgeSink.disposeBody();
        table.disposeBody();
        upBorder.disposeBody();
        downBorder.disposeBody();
        cabinet.disposeBody();
    }

    public void exitDoor(Character character){
        if(character.body.getPosition().x > -19.8f && character.body.getPosition().x < -15.5f && character.body.getPosition().y < -11){
            manager.set(new StageSelectState(manager));
        }

        atDoor = character.body.getPosition().x > -19.8f && character.body.getPosition().x < -15.5f && character.body.getPosition().y < -10;
    }

    private void checkDoor(SpriteBatch sprite, boolean atDoor){
        if(atDoor){
            sprite.draw(door, -693, -450);
        }
    }
}
