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

public class PlayroomMapS1 extends State {
    private Collisions upBorder, downBorder;
    private boolean atDoor;
    private TextureRegion door;
    private int stage;
    private boolean inPlayroom;
    public PlayroomMapS1(Manager manager, int stage) {
        super(manager);
//      Create invisible collision for the character.
        upBorder = new Collisions(manager);
        upBorder.create(new Vector2(-25f, 8), new Vector2(0.4f, 5.5f), 0);

        downBorder = new Collisions(manager);
        downBorder.create(new Vector2(-25f, -9), new Vector2(0.4f, 3.3f), 0);

//      Used to exit the map
        atDoor = false;
        inPlayroom = false;
        this.stage = stage;
        door = new TextureRegion(manager.getReportCardSheet(), 48,195, 263, 119);
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
        if(stage >=1 && stage <5){
//            sprite.draw(manager.getPlayroomStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }
        else if(stage >= 5 && stage < 12){
            sprite.draw(manager.getPlayroomStage2(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }

        checkDoor(sprite, atDoor);
        sprite.end();
    }

    @Override
    public void dispose() {
        upBorder.disposeBody();
        downBorder.disposeBody();
    }

    public void exitDoor(Character character){
        if(character.getBody().getPosition().x > -19.8f && character.getBody().getPosition().x < -15.5f && character.getBody().getPosition().y < -11){
            manager.getMusic().stop();
            manager.set(new StageSelectState(manager));
        }

        atDoor = character.getBody().getPosition().x > -19.8f && character.getBody().getPosition().x < -15.5f && character.getBody().getPosition().y < -10;
    }

    private void checkDoor(SpriteBatch sprite, boolean atDoor){
        if(atDoor){
            sprite.draw(door, -693, -450);
        }
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public boolean isInPlayroom() {
        return inPlayroom;
    }

    public void setInPlayroom(boolean inPlayroom) {
        this.inPlayroom = inPlayroom;
    }
}
