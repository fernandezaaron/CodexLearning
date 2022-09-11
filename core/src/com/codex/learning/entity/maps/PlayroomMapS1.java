package com.codex.learning.entity.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Collisions;
import com.codex.learning.entity.blocks.PlayMat;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.states.StageSelectState;
import com.codex.learning.states.State;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class PlayroomMapS1 extends State {
    private Collisions upBorder, downBorder;
    private boolean atDoor;
    private TextureRegion door;
    private int stage;
    private boolean inPlayroom;
    private NPC npc;
    private PlayMat playMat;
    private Minigame minigame;
    private int randomNumber;
    public PlayroomMapS1(Manager manager) {
        super(manager);
//      Create invisible collision for the character.
        upBorder = new Collisions(manager);
        upBorder.create(new Vector2(-25f, 8), new Vector2(0.4f, 5.5f), 0);

        downBorder = new Collisions(manager);
        downBorder.create(new Vector2(-25f, -9), new Vector2(0.4f, 3.3f), 0);

//      Used to exit the map
        atDoor = false;
        inPlayroom = false;

        npc = new NPC(manager, "minigames", 0, true);
        npc.create(new Vector2(0, -6), new Vector2(1, 1.4f), 0);

        playMat = new PlayMat(manager);
        playMat.create(new Vector2(17.5f, 0), new Vector2(6f, 10), 0);



//        minigame = new Minigame(manager, stage, 2);
        door = new TextureRegion(manager.getReportCardSheet(), 48,195, 263, 119);
    }

    @Override
    public void update(float delta) {
        npc.update(delta);
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.enableBlending();
        if(manager.getStageSelector().map().equals("1")){
            sprite.draw(manager.getPlayroomStage1(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }
        else if(manager.getStageSelector().map().equals("2")){
            sprite.draw(manager.getPlayroomStage2(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }
        else if(manager.getStageSelector().map().equals("3")){
            sprite.draw(manager.getPlayroomStage3(), manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        }
        sprite.end();
        npc.render(sprite);

    }

    @Override
    public void dispose() {
        npc.disposeBody();
        upBorder.disposeBody();
        downBorder.disposeBody();
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
