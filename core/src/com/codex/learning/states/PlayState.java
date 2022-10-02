package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
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
    private Random rand;
    private int randomMinigame;

    private PauseState pause;
    private FuzzyLogic fuzzyLogic;

    private boolean inStartArea, atDoor, inHowToPlay;
    private TextureRegion door,  howToPlayScreen;
    private Animation downArrow, rightArrow, smallDownArrow, smallRightArrow;
    private int stage;

    private boolean computerOnce;


    private int count;
    private float hintTimer;


    public PlayState(Manager manager) {
        super(manager);
        hintTimer = 0;
        count = 5;

        this.stage = manager.getStageSelector().getStageMap();
        pause = new PauseState(manager);
        rand = new Random();



//        randomMinigame = rand.nextInt(4-1)+1;

        randomMinigame = 3;

        playroomMap = new PlayroomMapS1(manager);
        manager.setPlayroomMap(playroomMap);

        fuzzyLogic = new FuzzyLogic();

        if(manager.getStageSelector().map().equals("1")){
            house = new HouseMap(manager);

            manager.setHouseMap(house);
            computer = new Computer(manager, fuzzyLogic);
            computer.create(new Vector2(-6, 2.8f), new Vector2(0.6f, 0.6f), 0);
        }
        else if(manager.getStageSelector().map().equals("2")){
            schoolMap = new SchoolMap(manager);
            manager.setSchoolMap(schoolMap);
            computer = new Computer(manager, fuzzyLogic);
            computer.create(new Vector2(-4, 6.5f), new Vector2(0.6f, 0.6f), 0);
        }
        else if(manager.getStageSelector().map().equals("3")){
            officeMap = new OfficeMap(manager);
            manager.setOfficeMap(officeMap);
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

        rightArrow = new Animation(manager.getUtility(), Constants.RIGHT_ARROW_x, Constants.RIGHT_ARROW_Y, Constants.RIGHT_ARROW_WIDTH, Constants.RIGHT_ARROW_HEIGHT, 2, 0.75f);
        downArrow = new Animation(manager.getUtility(), Constants.DOWN_ARROW_x, Constants.DOWN_ARROW_Y, Constants.DOWN_ARROW_WIDTH, Constants.DOWN_ARROW_HEIGHT, 2, 0.75f);
        howToPlayScreen = new TextureRegion(new Texture(Constants.START_AREA), 0,0,1600,900);

        inStartArea = true;
        atDoor = false;
        inHowToPlay = false;

        if(manager.getStageSelector().getStageMap() == 1){
            manager.setNewPlayer(true);
        }
        else{
            manager.setNewPlayer(false);
        }
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(!isInStartArea()){
            activeBody(false);
            manager.checkIfMoving(jedisaur);
            playroomMap.setActive(true);
            playroomMap.update(delta);
            manager.getMinigame().update(delta);
            if(playroomMap.getPlayMat().isInContact()){
                jedisaur.dropBlock(playroomMap.getPlayMat());
            }


        }else {
            downArrow.update(delta);
            rightArrow.update(delta);
            activeBody(true);
            exitDoor(jedisaur);
            playroomMap.setActive(false);

        }


        if(pause.isRunning()){
            if(jediGrandpa.isTalking() || playroomMap.getNpc().isTalking() || playroomMap.inHowToPlay() || playroomMap.inObjective()){
                jedisaurStop(delta);
                if(isInStartArea()){
                    jediGrandpa.update(delta);
                }
            }
            else{
                if(!computer.getCodeRiddle().isInComputer()){

                    if(computer.isDone() && computerOnce){
                        computer.getCodeRiddle().resultFeedback();
                        manager.setCodeRiddle(computer.getCodeRiddle());
                        System.out.println("COMPUTER IS DONE - " + manager.getCodeRiddle().getCodeRiddleData());
                        computerOnce = false;
                    }

                    if(computer.isDone() && computer.getCodeRiddle().isResultFeedBackOpen()){
                        computer.getCodeRiddle().closeDialogBox();
                    }

                    if(computer.getCodeRiddle().isBehaviorTableOpen()){
                        computer.getCodeRiddle().closeBehaviorTable();
                    }

                    // CHECK THE BEHAVIOR IN STATE
//                    manager.updateBehavior((int) timer);
//                manager.checkBehavior((int) timer, jedisaur.getNumberOfBlockInteraction(), computer.isDone(), fuzzyLogic);
                    
//              }
                    jediGrandpa.update(delta);
                    jedisaur.update(delta);
                    if(jediGrandpa.isComputerReady()){
                        computer.update(delta);
                    }
                    else {
                        computer.setInContact(false);
                    }
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
                        manager.getStage().clear();
                    }
                }
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
            if(jediGrandpa.isComputerReady() && !computer.getCodeRiddle().isInComputer() && !computer.isDone()){

                if(manager.getStageSelector().map().equals("1")){
                    sprite.draw(downArrow.getFrame(), manager.getCamera().position.x  - computer.getBody().getPosition().x - 260,
                            manager.getCamera().position.y  - computer.getBody().getPosition().y + 115);
                }
                else if(manager.getStageSelector().map().equals("2")){
                    sprite.draw(downArrow.getFrame(), manager.getCamera().position.x  - computer.getBody().getPosition().x - 200,
                            manager.getCamera().position.y  - computer.getBody().getPosition().y + 230);
                }
                else {
                    sprite.draw(downArrow.getFrame(), manager.getCamera().position.x  - computer.getBody().getPosition().x - 280,
                            manager.getCamera().position.y  - computer.getBody().getPosition().y + 350);
                }

            }

            if(!jediGrandpa.isComputerReady()) {

                sprite.draw(downArrow.getFrame(), manager.getCamera().position.x - jediGrandpa.getBody().getPosition().x - 55,manager.getCamera().position.y - jediGrandpa.getBody().getPosition().y + 35);

            }


            if(computer.isDone() && !computer.getCodeRiddle().isInComputer()){
                sprite.draw(rightArrow.getFrame(),manager.getCamera().position.x + 150 ,manager.getCamera().position.y - 90 );
            }

            if(jediGrandpa.isNewPlayerDialogueDone()){
                sprite.draw(howToPlayScreen,
                        (manager.getCamera().position.x / Constants.PPM - howToPlayScreen.getRegionWidth() / 2) + 25,
                        (manager.getCamera().position.y / Constants.PPM - howToPlayScreen.getRegionHeight() / 2) + 15);
                inHowToPlay = true;
            }

            if(inHowToPlay && jediGrandpa.isNewPlayerDialogueDone() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
                inHowToPlay = false;
                jediGrandpa.setNewPlayerDialogueDone(false);
            }
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
        playroomMap.dispose();
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
        if(character.getBody().getPosition().x > -9.0f && character.getBody().getPosition().x < -3.0f && character.getBody().getPosition().y < -11){

        }

        atDoor = character.getBody().getPosition().x > -9.5f && character.getBody().getPosition().x < -3.0f && character.getBody().getPosition().y < -10;
    }

    private void checkDoor(SpriteBatch sprite, boolean atDoor){
        if(atDoor){
            sprite.draw(door, -330, -450);
            manager.getMusic().stop();
            manager.getQuestionnaire().dispose();
            if (manager.getQuestionnaire().getMinigameHolder() != null){
                manager.getQuestionnaire().clearMinigames();
                manager.getMinigame().dispose();
            }
            manager.getPlayroomMap().dispose();
            if(manager.getStageSelector().map().equals("1")){
                manager.getHouseMap().dispose();
            }
            else if(manager.getStageSelector().map().equals("2")){
                manager.getSchoolMap().dispose();
            }
            else {
                manager.getOfficeMap().dispose();
            }
            manager.getMinigameChecker().setNumberOfAttempts(0);
            manager.getMinigameChecker().setDone(false);
            manager.getMinigame().reset();
            manager.set(new StageSelectState(manager));
        }

    }

    public void enterPlayRoom(Character character){
        if(character.getBody().getPosition().x > 14f && character.getBody().getPosition().y >-4 && character.getBody().getPosition().y < 2.5f && isInStartArea()){
            jediGrandpa.setInPlayroomCarpet(true);
            if(computer.isDone()){
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
            }
            else {
                jediGrandpa.setDirection("east");
                jediGrandpa.noToPlayroom(jedisaur);

            }
        }
        else {
            jediGrandpa.setInPlayroomCarpet(false);

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

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
