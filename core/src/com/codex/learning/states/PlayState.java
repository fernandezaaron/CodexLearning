package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.entity.maps.OfficeMap;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.entity.maps.SchoolMap;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.utility.*;

import java.util.Random;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;
    private SchoolMap schoolMap;
    private OfficeMap officeMap;
    private Computer computer;
    private PlayroomMapS1 playroomMap;
    private Minigame minigame;
    private Random rand;
    private int randomMinigame;


    private boolean inMysteryCode, inFillInTheBlock;

    private float timer;

    private PauseState pause;

    private FuzzyLogic fuzzyLogic;

    private boolean inStartArea, atDoor;
    private TextureRegion door;
    private int stage;

    private boolean computerOnce;



    public PlayState(Manager manager) {
        super(manager);
        this.stage = manager.getStageSelector().getStageMap();
        timer = 0;
        pause = new PauseState(manager);
        rand = new Random();

        randomMinigame = 1;


        playroomMap = new PlayroomMapS1(manager);
        manager.setPlayroomMap(playroomMap);
        fuzzyLogic = new FuzzyLogic();

        if(manager.getStageSelector().map().equals("1")){
            house = new HouseMap(manager);
            computer = new Computer(manager, fuzzyLogic);
            computer.create(new Vector2(-6, 2.8f), new Vector2(0.6f, 0.6f), 0);
        }
        else if(manager.getStageSelector().map().equals("2")){
            schoolMap = new SchoolMap(manager);
            computer = new Computer(manager, fuzzyLogic);
            computer.create(new Vector2(-4, 6.5f), new Vector2(0.6f, 0.6f), 0);
        }
        else if(manager.getStageSelector().map().equals("3")){
            officeMap = new OfficeMap(manager);
            computer = new Computer(manager, fuzzyLogic);
            computer.create(new Vector2(-6.5f, 10.2f), new Vector2(0.6f, 0.6f), 0);
        }

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, -5), new Vector2(1.2f, 1.75f), 1.6f);


        jediGrandpa = new NPC(manager, "introduction", manager.getStageSelector().getStageMap()-1, false);
        jediGrandpa.create(new Vector2(0, 0), new Vector2(1, 1.4f), 0);
        jediGrandpa.setInPlayroom(false);

        manager.getMinigame().create(randomMinigame, jedisaur, fuzzyLogic);

        if(!manager.isMusicPaused()){
            manager.setMusic(Constants.HOUSE_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        }else {
            manager.setMusic(Constants.HOUSE_MUSIC);
        }

        computerOnce = true;


        door = new TextureRegion(manager.getReportCardSheet(), 48,195, 263, 119);
        inStartArea = true;
        atDoor = false;
        inFillInTheBlock = false;
        inMysteryCode = false;
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(!isInStartArea()){
            activeBody(false);
            playroomMap.setActive(true);
            playroomMap.update(delta);
            manager.getMinigame().update(delta);
            if(playroomMap.getPlayMat().isInContact()){
                jedisaur.dropBlock(playroomMap.getPlayMat());
                
            }


        }else {
            activeBody(true);
            exitDoor(jedisaur);
            playroomMap.setActive(false);

        }


        if(pause.isRunning()){
            if(jediGrandpa.isTalking()){
                jedisaurStop(delta);
                jediGrandpa.update(delta);
            }
            else{
                if(!computer.getCodeRiddle().isInComputer()){
                    timer += Gdx.graphics.getDeltaTime();


                    if(computer.isDone() && computerOnce){
                        computer.getCodeRiddle().resultFeedback();
                        computerOnce = false;
                    }

                    // CHECK THE BEHAVIOR IN STATE
//                    manager.checkIfMoving(jedisaur);
//                    manager.updateBehavior((int) timer);
//                manager.checkBehavior((int) timer, jedisaur.getNumberOfBlockInteraction(), computer.isDone(), fuzzyLogic);
                    
//              }
                    jediGrandpa.update(delta);
                    jedisaur.update(delta);
                    computer.update(delta);
                }
//                else if(playroom.isDone && npc.hasSubmitted){
//                    //Use to calculate number of cookies
//
////                manager.getExpertSystem().setCurrentCookie(fuzzyLogic.calculateFuzzy(
////                  computer.getCodeRiddle().getFuzzyLogic().getNumberOfErrors(),
////                  computer.getCodeRiddle().getFuzzyLogic().getTimeConsumptions() + timer,);
//
//                    // Update the number of cookie to write in the file
////                manager.getExpertSystem().editCookie(stage - 1);
//                    // Update the cookies in the stage select state
////                manager.getExpertSystem().writeFile(manager.getExpertSystem().getCookies());
//                    manager.getExpertSystem().readFile();
//
//                    // Write the behavior in a file
//                }

                else{
                    jedisaurStop(delta);
                    if(computer.getCodeRiddle().isInComputer() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
                        computer.getCodeRiddle().setInComputer(false);
//not working pa
//                    for (int i =0 ; i<manager.getStage().getActors().size; i++){
//                        if(manager.getStage().getActors().get(i).toString().equals("Table")){
//                            manager.getStage().getActors().get(i).remove();
////                            manager.getStage().clear();
//                        }
//                    }
                        manager.getStage().clear();
                    }
                }
            }
            if(playroomMap.getNpc().isTalking()){
                jedisaurStop(delta);
                playroomMap.getNpc().update(delta);
            }

        }else{
            jedisaurStop(delta);
            computer.getCodeRiddle().setInComputer(false);
            manager.getStage().clear();
        }


    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();

        enterPlayRoom(jedisaur);
        exitPlayroom(jedisaur);

        if(isInStartArea()){
            if(manager.getStageSelector().map().equals("1")){
                house.render(sprite);
            }
            else if(manager.getStageSelector().map().equals("2")){
                schoolMap.render(sprite);
            }
            else if(manager.getStageSelector().map().equals("3")){
                officeMap.render(sprite);
            }
            jediGrandpa.render(sprite);
            if(computer.getCodeRiddle().isInComputer()){
                jedisaur.render(sprite);
                computer.render(sprite);
            }
            else{
                computer.render(sprite);
                jedisaur.render(sprite);
            }
            jediGrandpa.tableRender(sprite);
        }else {
            playroomMap.render(sprite);
            manager.getMinigame().render(sprite);
            jedisaur.render(sprite);
            playroomMap.getObjective().render(sprite);
            playroomMap.npcRender(sprite);

        }

        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        if(isInStartArea()){
            checkDoor(sprite, atDoor);
        }
        sprite.end();
        pause.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();
        computer.disposeBody();

        if(manager.getStageSelector().map().equals("1")){
            house.dispose();
        }
        else if(manager.getStageSelector().map().equals("2")){
            schoolMap.dispose();
        }
        else{
            officeMap.dispose();
        }
        manager.getMinigame().dispose();
    }

    private void jedisaurStop(float delta){
        if(jedisaur.isMoving()){
            jedisaur.setMoving(false);
            jedisaur.update(delta);
            jedisaur.getBody().setLinearVelocity(0,0);
        }
    }

    private void activeBody(boolean active){
         jediGrandpa.getBody().setActive(active);
         computer.getBody().setActive(active);

         if(manager.getStageSelector().map().equals("1")){
             house.setActive(active);
         }
         else if(manager.getStageSelector().map().equals("2")){
             schoolMap.setActive(active);
         }
         else {
             officeMap.setActive(active);
         }

         jediGrandpa.getBody().setAwake(active);
         computer.getBody().setAwake(active);
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

    public void enterPlayRoom(Character character){
        if(character.getBody().getPosition().x > 14f && character.getBody().getPosition().y >-4 && character.getBody().getPosition().y < 2.5f && isInStartArea()){
//            if(computer.isDone()){
                setInStartArea(false);
                if(manager.getStageSelector().map().equals("1")){
                    house.setPlayroomActive(false);
                }
                else if(manager.getStageSelector().map().equals("2")){
                    schoolMap.setPlayroomActive(false);
                }
                else{
                    officeMap.setPlayroomActive(false);
                }

                manager.getMinigame().setMiniGame();
                jedisaur.getBody().setTransform(-20, 1, 0);
                jedisaur.getBody().getPosition().set(-20, 1);
//            }
//            else {
//            }
        }
    }

    private void exitPlayroom(Character character){
        if(!inStartArea && character.getBody().getPosition().x < -23 && character.getBody().getPosition().y > -0.5 && character.getBody().getPosition().y < 4f){
            setInStartArea(true);
            manager.getMinigame().dispose();
            jedisaur.getBody().setTransform(14, 1, 0);
            jedisaur.getBody().getPosition().set(14, 1);
        }
    }

    public boolean isInStartArea() {
        return inStartArea;
    }

    public void setInStartArea(boolean inStartArea) {
        this.inStartArea = inStartArea;
    }
}
