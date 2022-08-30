package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.entity.maps.SchoolMap;
import com.codex.learning.states.minigames.FillInTheBlock;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.states.minigames.MysteryCode;
import com.codex.learning.utility.*;

public class PlayState extends State{
    private Character jedisaur;
    private NPC jediGrandpa;
    private HouseMap house;
    private SchoolMap schoolMap;
    private Computer computer;
    private PlayroomMapS1 playroomMap;
    private Minigame minigame;
    private FillInTheBlock fib;
    private MysteryCode mc;

    private boolean inMysteryCode, inFillInTheBlock;

    private float timer;

    private PauseState pause;

    private FuzzyLogic fuzzyLogic;

    private boolean inStartArea, atDoor;
    private TextureRegion door;
    private int stage;



    public PlayState(Manager manager, int stage) {
        super(manager);
        this.stage = stage;
        timer = 0;
        pause = new PauseState(manager);

        if(stage >= 1 && stage < 5){
            house = new HouseMap(manager);
        }
        else if(stage >= 5 && stage < 12){
            schoolMap = new SchoolMap(manager);
        }


        playroomMap = new PlayroomMapS1(manager, stage);
        fuzzyLogic = new FuzzyLogic();

        if(stage >= 1 && stage < 5){
            computer = new Computer(manager, fuzzyLogic, 1);
            computer.create(new Vector2(-6, 2.8f), new Vector2(0.6f, 0.6f), 0);
        }
        else if(stage >=5 && stage < 12){
            computer = new Computer(manager, fuzzyLogic, 2);
            computer.create(new Vector2(-4, 6.5f), new Vector2(0.6f, 0.6f), 0);
        }


        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, -5), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager, stage);
        jediGrandpa.create(new Vector2(0, 0), new Vector2(1, 1.4f), 0);

        minigame = new Minigame(manager, stage, 3, jedisaur);

        if(!manager.isMusicPaused()){
            manager.setMusic(Constants.HOUSE_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        }else {
            manager.setMusic(Constants.HOUSE_MUSIC);
        }


        door = new TextureRegion(manager.getReportCardSheet(), 48,195, 263, 119);
        inStartArea = true;
        atDoor = false;
        inFillInTheBlock = false;
        inMysteryCode = false;



    }

    @Override
    public void update(float delta) {
        if(!isInStartArea()){
            activeBody(false);
//            updateMinigame(delta);
//            System.out.println(minigame.getBlocks() + " Playstate block");
            minigame.update(delta);

        }else {
            activeBody(true);

        }
        manager.getWorld().step(1/60f,6,2);
        if(pause.isRunning()){
            if(!computer.getCodeRiddle().isInComputer()){
                timer += Gdx.graphics.getDeltaTime();


                if(computer.isDone()){
                    computer.getCodeRiddle().updateBehavior();
                }

                // CHECK THE BEHAVIOR IN STATE
                manager.checkIfMoving(jedisaur);
                manager.updateBehavior((int) timer);
//                manager.checkBehavior((int) timer, jedisaur.getNumberOfBlockInteraction(), computer.isDone(), fuzzyLogic);

                // if(npc.isDone()){
                //Use to calculate number of cookies
                //System.out.println(fuzzyLogic.getCookies());

//                manager.getExpertSystem().setCurrentCookie(fuzzyLogic.calculateFuzzy(
//                  computer.getCodeRiddle().getFuzzyLogic().getNumberOfErrors(),
//                  computer.getCodeRiddle().getFuzzyLogic().getTimeConsumptions() + timer,);

                // Update the number of cookie to write in the file
//                manager.getExpertSystem().editCookie(stage - 1);
                // Update the cookies in the stage select state
//                manager.getExpertSystem().writeFile(manager.getExpertSystem().getCookies());

                // Write the behavior in a file

//              }




                exitDoor(jedisaur);
                jediGrandpa.update(delta);
                jedisaur.update(delta);
                computer.update(delta);

            }
            else{
                if(jedisaur.isMoving()){
                    jedisaur.setMoving(false);
                    jedisaur.update(delta);
                    jedisaur.getBody().setLinearVelocity(0,0);
                }
                if(computer.getCodeRiddle().isInComputer() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
                    computer.getCodeRiddle().setInComputer(false);
//not working pa
//                    for (int i =0 ; i<manager.getStage().getActors().size; i++){
//                        System.out.println(manager.getStage().getActors().get(i));
//                        if(manager.getStage().getActors().get(i).toString().equals("Table")){
//                            System.out.println("true");
//                            manager.getStage().getActors().get(i).remove();
////                            manager.getStage().clear();
//                        }
//                    }
                    manager.getStage().clear();
                }
            }
        }else{
            if(jedisaur.isMoving()){
                jedisaur.setMoving(false);
                jedisaur.update(delta);
                jedisaur.getBody().setLinearVelocity(0,0);
            }
        }
    }

    @Override
    public void render(SpriteBatch sprite) {
        manager.getCamera().update();

        enterPlayRoom(jedisaur);
        exitPlayroom(jedisaur);


        if(isInStartArea()){
            if(stage >= 1 && stage < 5){
//                house.render(sprite);
            }
            else if(stage >= 5 && stage < 12){
                schoolMap.render(sprite);
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
        }else {
//            playroomMap.render(sprite);
//            minigame.setMiniGame();
            minigame.render(sprite);
//            renderMinigame(sprite);
            jedisaur.render(sprite);
        }

        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        checkDoor(sprite, atDoor);
        sprite.end();
        pause.render(sprite);

    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();
        computer.disposeBody();

        if(stage >= 1 && stage < 5){
            house.dispose();
        }
        else if(stage >= 5 && stage < 12){
            schoolMap.dispose();
        }
    }

    public void setMiniGame(int stageNumber, int currentMinigame){
        switch (currentMinigame){
            case 1:
                inFillInTheBlock = true;
                fib = new FillInTheBlock(manager, stageNumber, jedisaur);
                System.out.println("1");
                break;
            case 2:
                inMysteryCode = true;
                mc = new MysteryCode(manager, stageNumber);

                break;
            case 3:
                System.out.println("3");
                break;

        }

    }

    public void updateMinigame(float delta){
        if(inFillInTheBlock){
            fib.update(delta);
        }
        else if(inMysteryCode){
            mc.update(delta);
        }
    }

    public void renderMinigame(SpriteBatch spriteBatch){
        if(inFillInTheBlock){
            fib.render(spriteBatch);
        }
        else if(inMysteryCode){
            mc.render(spriteBatch);
        }
    }

    public void disposeMinigame(){
        if(inFillInTheBlock){
            fib.dispose();
        }
        else if(inMysteryCode){
            mc.dispose();
        }
    }

    public void activeBody(boolean active){
         jediGrandpa.getBody().setActive(active);
         computer.getBody().setActive(active);

         if(stage >= 1 && stage < 5){
             house.setActive(active);
         }
         else if(stage >= 5 && stage < 12){
             schoolMap.setActive(active);
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
            if(computer.isDone()){
                setInStartArea(false);
                if(stage >= 1 && stage < 5){
                    house.setPlayroomActive(false);
                }
                else if(stage >= 5 && stage < 12){
                    schoolMap.setPlayroomActive(false);
                }
//                setMiniGame(stage, 1);
                minigame.setMiniGame();
                jedisaur.getBody().setTransform(-20, 1, 0);
                jedisaur.getBody().getPosition().set(-20, 1);
            }
            else {
                System.out.println("bawal kapa pumasok jan xD");
            }
        }
    }

    private void exitPlayroom(Character character){
        if(!inStartArea && character.getBody().getPosition().x < -23 && character.getBody().getPosition().y > -4 && character.getBody().getPosition().y < 2.5f){
            setInStartArea(true);
            minigame.dispose();
            disposeMinigame();
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
