package com.codex.learning.entity.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.Collisions;
import com.codex.learning.entity.blocks.HowToPlay;
import com.codex.learning.entity.blocks.Objective;
import com.codex.learning.entity.blocks.PlayMat;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.states.ReportCard;
import com.codex.learning.states.StageSelectState;
import com.codex.learning.states.State;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.utility.Animation;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class PlayroomMapS1 extends State {
    private Collisions upBorder, downBorder;
    private boolean atDoor;
    private Animation downArrow, rightArrow;
    private int stage;
    private boolean inPlayroom;
    private NPC npc;
    private PlayMat playMat;
    private Objective objective;
    private HowToPlay howToPlay;
    private ReportCard reportCard;

    private String npcDialog;
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

        npcDialog = "done";
        npc = new NPC(manager, npcDialog, 0, true);
        npc.create(new Vector2(10, 10f), new Vector2(1, 1.4f), 0);
        npc.setIntroDialogFlag(true);

        playMat = new PlayMat(manager);
        playMat.create(new Vector2(17.5f, 0), new Vector2(6f, 10), 0);

        objective = new Objective(manager);
        objective.create(new Vector2(6f, 13f), new Vector2(1.5f, 2f), 0);

        howToPlay = new HowToPlay(manager);
        howToPlay.create(new Vector2(8f, -10.25f), new Vector2(1f, 2f), 0);

        reportCard = new ReportCard(manager);

//        door = new TextureRegion(manager.getReportCardSheet(), 48,195, 263, 119);
        downArrow = new Animation(manager.getUtility(), Constants.DOWN_ARROW_x, Constants.DOWN_ARROW_Y, Constants.DOWN_ARROW_WIDTH, Constants.DOWN_ARROW_HEIGHT, 2, 0.75f);
        rightArrow = new Animation(manager.getUtility(), Constants.RIGHT_ARROW_x, Constants.RIGHT_ARROW_Y, Constants.RIGHT_ARROW_WIDTH, Constants.RIGHT_ARROW_HEIGHT, 2, 0.75f);
    }

    @Override
    public void update(float delta) {
        //make a condition on NPC hints dialog if wala pa and if meron na
        if(npc.isReady()){
            npcDialog = "finishCheck";
        }
        else{
            npcDialog = "hints";
        }

        if(manager.getMinigameChecker().isDone()){
            reportCard.update(delta);
        }

        npc.update(delta);
        objective.update(delta);
        howToPlay.update(delta);
        rightArrow.update(delta);
        downArrow.update(delta);
        manager.getStage().act();
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

        if(!objective.isObjectiveInteracted()){
            sprite.draw(rightArrow.getFrame(), manager.getCamera().position.x - objective.getBody().getPosition().x - 75, manager.getCamera().position.y + 350);
        }

        if(npc.isReady()){
            sprite.draw(downArrow.getFrame(), manager.getCamera().position.x - npc.getBody().getPosition().x - 10, manager.getCamera().position.y - npc.getBody().getPosition().y + 50);
        }
        sprite.end();
        npc.render(sprite);

    }

    public void npcRender(SpriteBatch sprite){
        npc.tableRender(sprite);
        manager.getStage().draw();
        howToPlay.render(sprite);
        if(npc.isAutoDialogDone()){
            howToPlay.setIntroDialogue(true);
            npc.setAutoDialogDone(false);
        }
        if(howToPlay.isIntroDialogue()){
            howToPlay.setCurrentImage(manager.getMinigame().getCurrentMinigame(), sprite);
        }
        if(manager.getMinigameChecker().isDone()){
            reportCard.render(sprite);

        }
    }


    @Override
    public void dispose() {
        npc.disposeBody();
        upBorder.disposeBody();
        downBorder.disposeBody();
        objective.disposeBody();
        howToPlay.disposeBody();
        reportCard.dispose();
    }

    public PlayMat getPlayMat() {
        return playMat;
    }

    public void setPlayMat(PlayMat playMat) {
        this.playMat = playMat;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public HowToPlay getHowToPlay() {
        return howToPlay;
    }

    public void setHowToPlay(HowToPlay howToPlay) {
        this.howToPlay = howToPlay;
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

    public void setActive(boolean active){
        playMat.getBody().setActive(active);
        npc.getBody().setActive(active);
        objective.getBody().setActive(active);
        howToPlay.getBody().setActive(active);

    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public boolean inHowToPlay(){
        if(howToPlay.isInHowToPlay()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean inObjective(){
        if(objective.isInObjective()){
            return true;
        }
        else {
            return false;
        }
    }
}
