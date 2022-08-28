package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.HouseMap;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.states.PauseState;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;
import com.sun.org.apache.bcel.internal.Const;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MysteryCode extends State {

    private Character jedisaur;
    private NPC jediGrandpa;
    private PlayroomMapS1 playroom;
    private Computer computer;

    private Blocks[] totalBlocks;
    private int blockCount;
    private boolean blockSpawn;

    private Blocks[][] blocks;
    private BlockHolder[][] blockHolders;
    private BlockDispenser[] blockDispensers;

    private PauseState pause;

    private String[][] minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private int currentCell;

    public MysteryCode(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager,1);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();

        // WILL BE USED, DON'T ERASE
        blocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];

        computer = new Computer(manager, new FuzzyLogic(),1);
        computer.create(new Vector2(-18, 2.8f), new Vector2(0.6f, 0.6f), 0);

        getAMinigame("Stage 1", "Poor");

        for(int i = 0; i <= 5; i++) {
            banishCells.add(randomizer.nextInt(minigameContainerLimit - 1) + 1);
        }

        // WILL BE USED, DON'T ERASE
        int yStartingPoint = 8, currentCell = 0;
        for(int i = 0; i < minigameContainer.length; i++) {
            float xStartingPoint = -23.0f;
            for (int j = 0; j < minigameContainer[i].length; j++) {
                if(minigameContainer[i][j] != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer[i][j]).length();
                    if (banishCells.contains(currentCell)) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer[i][j] + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.22f), Constants.BLOCK_HOLDER_HEIGHT), 0);
                        System.out.println(minigameContainer[i][j]);
                    } else {
                        blocks[i][j] = new Blocks(manager, "\"" + minigameContainer[i][j] + "\"", minigameContainer[i][j], true);
                        if (currentStringLength == 1){
                            blocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.4f), Constants.BLOCKS_HEIGHT), 0);
                            blocks[i][j].setPreDefinedContact(true);
                        }

                        else{
                            blocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                            blocks[i][j].setPreDefinedContact(true);
                        }

                    }
                    currentCell++;
                    System.out.println(xStartingPoint + " " + minigameContainer[i][j]);
                    System.out.println(String.valueOf(minigameContainer[i][j]).length() + "     " + (float) String.valueOf(minigameContainer[i][j]).length());

                    if(currentStringLength <= 2)
                        xStartingPoint += currentStringLength + 0.5f;
                    else
                        xStartingPoint += currentStringLength / 1.8f;
                }
            }
            yStartingPoint -= 2;
        }
        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < 2; i++){
            if(i == 0){
                blockDispensers[i] = new BlockDispenser(manager, "Down", "}", "   }  ",
                        3, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            }
            else{
                blockDispensers[i] = new BlockDispenser(manager, "Right", "main", " main ",
                        3, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            }
            blockDispensers[i].create(new Vector2(15 * i, -6), new Vector2(0.3f, 1.3f), 0);
        }

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager, 1);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

        totalBlocks = new Blocks[6];
        blockCount = 0;

        if(!manager.isMusicPaused()){
            manager.setMusic(Constants.HOUSE_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        }else {
            manager.setMusic(Constants.HOUSE_MUSIC);
        }

        System.out.println(manager.getCamera().position.x + " " + manager.getCamera().position.y);
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1/60f,6,2);
        if(pause.isRunning()){
            if(!computer.getCodeRiddle().isInComputer()){
                // WILL BE USED, DON'T ERASE
                currentCell = 0;
                for(int i = 0; i < minigameContainer.length; i++) {
                    for (int j = 0; j < minigameContainer[i].length; j++) {
                        if (minigameContainer[i][j] != null) {
                            if(banishCells.contains(currentCell))
                                blockHolders[i][j].update(delta);
                            else
                                blocks[i][j].update(delta);
                            currentCell++;
                        }
                    }
                }

                //kung gagamitin mo to remove the blocks[i][j].update muna sa taas pero i havent tried pag magkasabay sila naka on for sure dodoble HAHA
//                for(int i=0; i<blocks.length; i++){
//                    for(int j = 0; j<blocks[i].length; j++){
//                        if(blocks[i][j] != null){
//                            blocks[i][j].update(delta);
//                            if(blocks[i][j].isInContact()){
//                                jedisaur.carryBlock(blocks[i][j]);
//                                System.out.println("jedisaur carrying ");
//                            }
//                        }
//
//                    }
//                }
//                // WILL BE USED, DON'T ERASE
//
                for(int i = 0; i < 2; i++){
                    blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
                }
//
                for(int i = 0; i < 2; i++) {
                    if(blockDispensers[i].isCloned()){
                        for (Blocks b : blockDispensers[i].getBlocks()) {
                            if (b != null) {
                                b.update(delta);
                                if(b.isInContact()){
                                    jedisaur.carryBlock(b);
                                }
                            }
                            else{
                                continue;
                            }
                        }
                    }
                }

                // WILL BE USED, DON'T ERASE
                currentCell = 0;
                for(int i = 0; i < minigameContainer.length; i++) {
                    for (int j = 0; j < minigameContainer[i].length; j++) {
                        if(minigameContainer[i][j] != null) {
                            if(banishCells.contains(currentCell)) {
                                if (blockHolders[i][j].isInContact()) {
                                    jedisaur.dropBlock(blockHolders[i][j]);
                                }
                            }
                            currentCell++;
                        }
                    }
                }
                // WILL BE USED, DON'T ERASE


                jediGrandpa.update(delta);
                jedisaur.update(delta);
                computer.update(delta);
//            pause.update(delta);
            }
            else{
                if(jedisaur.isMoving()){
                    jedisaur.setMoving(false);
                    jedisaur.update(delta);
                    jedisaur.getBody().setLinearVelocity(0,0);
                }
                if(computer.getCodeRiddle().isInComputer() && Gdx.input.isKeyJustPressed(Input.Keys.F)){
                    computer.getCodeRiddle().setInComputer(false);
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
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);

        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.end();

        playroom.render(sprite);

        currentCell = 0;
        for(int i = 0; i < minigameContainer.length; i++) {
            for (int j = 0; j < minigameContainer[i].length; j++) {
                if(minigameContainer[i][j] != null) {
                    if(banishCells.contains(currentCell)) {
                        blockHolders[i][j].render(sprite);
                    }
                    else
                        blocks[i][j].render(sprite);
                    currentCell++;
                }
            }
        }
//
        for(int i = 0; i < 2; i++){
            blockDispensers[i].render(sprite);
            if(blockDispensers[i].isCloned()){
                for (Blocks b : blockDispensers[i].getBlocks()) {
                    if (b != null) {
                        b.render(sprite);
                    }
                    else{
                        continue;
                    }
                }
            }
        }

        jediGrandpa.render(sprite);
        jedisaur.render(sprite);

        pause.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();
        jediGrandpa.disposeBody();

        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < minigameContainer.length; i++) {
            for (int j = 0; j < minigameContainer[i].length; j++) {
                if(minigameContainer[i][j] != null) {
                    blockHolders[i][j].disposeBody();
                    blocks[i][j].disposeBody();
                }
            }
        }
        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < 2; i++){
            blockDispensers[i].disposeBody();
            if(blockDispensers[i].isCloned()){
                for (Blocks b : blockDispensers[i].getBlocks()) {
                    if (b != null) {
                        b.disposeBody();
                    }
                    else{
                        continue;
                    }
                }
            }
        }

        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage,expertiseLevel);
//        minigameContainer = manager.getQuestionnaire().getMinigame();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
    }
}
