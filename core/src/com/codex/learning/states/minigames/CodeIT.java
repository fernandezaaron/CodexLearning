package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.blocks.BlockDispenser;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.blocks.Computer;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.states.PauseState;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CodeIT extends State {

    private Character jedisaur;
    private NPC jediGrandpa;
    private PlayroomMapS1 playroom;
    private Computer computer;

    private int blockCount;
    private boolean blockSpawn;

    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
    private BlockDispenser[] blockDispensers;

    private PauseState pause;

    private ArrayList<ArrayList<String>> minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<String> answerPoolContainer;
    private int currentCell;

    public CodeIT(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager,1);

        randomizer = new Random();

        getAMinigame("Stage 1", "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];
//
//        computer = new Computer(manager);
//        computer.create(new Vector2(-18, 2.8f), new Vector2(0.6f, 0.6f), 0);

        // START MINIGAME CREATION
        int yStartingPoint = 8, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -23.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                    blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.22f), Constants.BLOCK_HOLDER_HEIGHT), 0);
                    answerPoolContainer.add(minigameContainer.get(i).get(j));
                    System.out.println(minigameContainer.get(i).get(j));

                    System.out.println(xStartingPoint + " " + minigameContainer.get(i).get(j));
                    System.out.println(String.valueOf(minigameContainer.get(i).get(j)).length() + "     " + (float) String.valueOf(minigameContainer.get(i).get(j)).length());

                    if(currentStringLength <= 2)
                        xStartingPoint += currentStringLength + 0.5f;
                    else
                        xStartingPoint += currentStringLength / 1.8f;
                }
            }
            yStartingPoint -= 2;
        }
        Collections.shuffle(answerPoolContainer);
        // END MINIGAME CREATION

        int currentAnsCell = 0;
        for(int i = 0; i < 2; i++){
            if(i == 0){
                int ansPoolSize = answerPoolContainer.size();
                for(int j = 0; j <= ansPoolSize; j++) {
                    blockDispensers[i] = new BlockDispenser(manager, "Down", answerPoolContainer.get(currentAnsCell), answerPoolContainer.get(currentAnsCell),
                            1, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
                }
            }
            else{
                blockDispensers[i] = new BlockDispenser(manager, "Right", "main", " main ",
                        3, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            }
            blockDispensers[i].create(new Vector2(13, 8 * i), new Vector2(0.3f, 1.3f), 0);
        }

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

        jediGrandpa = new NPC(manager,1);
        jediGrandpa.create(new Vector2(-10, 0), new Vector2(1, 1.4f), 0);

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
                for(int i = 0; i < minigameContainer.size(); i++) {
                    for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                        if (minigameContainer.get(i).get(j) != null) {
                            blockHolders[i][j].update(delta);
                        }
                    }
                }
                // WILL BE USED, DON'T ERASE

                for(int i = 0; i < 2; i++){
                    blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
                }

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
                for(int i = 0; i < minigameContainer.size(); i++) {
                    for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                        if (minigameContainer.get(i).get(j) != null) {
                            if (blockHolders[i][j].isInContact()) {
                                jedisaur.dropBlock(blockHolders[i][j]);
                            }
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
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].render(sprite);
                }
            }
        }

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
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].disposeBody();
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
        minigameContainer = manager.getQuestionnaire().getMinigame();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
        answerPoolContainer = manager.getQuestionnaire().getAnswerPool();
    }
}
