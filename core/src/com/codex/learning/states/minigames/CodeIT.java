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
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CodeIT extends State {

    private Character jedisaur;
    private PlayroomMapS1 playroom;

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

    public CodeIT(Manager manager, int stage, Character character) {
        super(manager);
        pause = new PauseState(manager);

        playroom = new PlayroomMapS1(manager);

        randomizer = new Random();

        getAMinigame("Stage 1", "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];


        // START MINIGAME CREATION
        int yStartingPoint = 8, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -23.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                    blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2(Constants.BLOCK_HOLDER_WIDTH * 3, Constants.BLOCK_HOLDER_HEIGHT), 0);
                    answerPoolContainer.add(minigameContainer.get(i).get(j));
                    xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 10;

                    System.out.println(xStartingPoint + " " + minigameContainer.get(i).get(j));
                    System.out.println(String.valueOf(minigameContainer.get(i).get(j)).length() + "     " + (float) String.valueOf(minigameContainer.get(i).get(j)).length());
                }
            }
            yStartingPoint -= 2;
        }
        Collections.shuffle(answerPoolContainer);
        // END MINIGAME CREATION

        int currentAnsCell = 0;
        int ansPoolSize = answerPoolContainer.size();
        int xposition = -22, yposition = -6;
        for(int i = 0; i < ansPoolSize; i++) {
            blockDispensers[i] = new BlockDispenser(manager, "Down", answerPoolContainer.get(currentAnsCell), answerPoolContainer.get(currentAnsCell),
                    1, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            System.out.println(answerPoolContainer.get(currentAnsCell));
            currentAnsCell++;
            blockDispensers[i].create(new Vector2(xposition, yposition), new Vector2(0.3f, 1.3f), 0);
            xposition += 5;
            if(xposition == 23) {
                yposition -= 6;
                xposition = -22;
            }
        }

        this.jedisaur = character;
    }

    @Override
    public void update(float delta) {
        manager.getWorld().step(1 / 60f, 6, 2);
        if (pause.isRunning()) {
            // WILL BE USED, DON'T ERASE
            currentCell = 0;
            for (int i = 0; i < minigameContainer.size(); i++) {
                for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                    if (minigameContainer.get(i).get(j) != null) {
                        blockHolders[i][j].update(delta);
                    }
                }
            }
            // WILL BE USED, DON'T ERASE

            for (int i = 0; i < getAnswerPoolContainer().size(); i++) {
                blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
            }

            for (int i = 0; i < getAnswerPoolContainer().size(); i++) {
                if (blockDispensers[i].isCloned()) {
                    for (Blocks b : blockDispensers[i].getBlocks()) {
                        if (b != null) {
                            b.update(delta);
                            if (b.isInContact()) {
                                jedisaur.carryBlock(b);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }

            // WILL BE USED, DON'T ERASE
            currentCell = 0;
            for (int i = 0; i < minigameContainer.size(); i++) {
                for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                    if (minigameContainer.get(i).get(j) != null) {
                        if (blockHolders[i][j].isInContact()) {
                            jedisaur.dropBlock(blockHolders[i][j]);
                        }
                    }
                }
            }
            // WILL BE USED, DON'T ERASE


            jedisaur.update(delta);

//            pause.update(delta);
        } else {
            if (jedisaur.isMoving()) {
                jedisaur.setMoving(false);
                jedisaur.update(delta);
                jedisaur.getBody().setLinearVelocity(0, 0);
            }

        }
        // WILL BE USED, DON'T ERASE
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

        for(int i = 0; i < getAnswerPoolContainer().size(); i++){
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

        jedisaur.render(sprite);

        pause.render(sprite);
    }

    @Override
    public void dispose() {
        jedisaur.disposeBody();

        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].disposeBody();
                }
            }
        }

        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < getAnswerPoolContainer().size(); i++){
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

    public ArrayList<String> getAnswerPoolContainer() {
        return answerPoolContainer;
    }

    public void setAnswerPoolContainer(ArrayList<String> answerPoolContainer) {
        this.answerPoolContainer = answerPoolContainer;
    }
}
