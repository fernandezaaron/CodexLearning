package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
import org.apache.commons.codec.binary.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CodeOrder extends State {

    private Character jedisaur;
    private PlayroomMapS1 playroom;

    private Blocks[] answerBlocks;

    private Blocks[][] questionBlocks;
    private BlockHolder[] blockHolders;

    private PauseState pause;

    private ArrayList<ArrayList<String>> minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> answerPoolContainer;
    private int currentCell;
    private String mergeResult;

    public CodeOrder(Manager manager) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager,1);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();

        getAMinigame("Stage 1", "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[minigameContainer.size()];
        answerPoolContainer = new ArrayList<String>();
        // WILL BE USED, DON'T ERASE

        // START MINIGAME CREATION
        int yStartingPoint = 8, currentCell = 0;
        float xStartingPoint = -23.0f;
        for(int i = 0; i < minigameContainer.size(); i++) {
            if(minigameContainer.get(i) != null) {
                mergeResult = "";
                for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                    System.out.println(minigameContainer.get(i).get(j));
                    if (j == minigameContainer.get(i).size() - 1)
                        mergeResult += minigameContainer.get(i).get(j);
                    else
                        mergeResult += minigameContainer.get(i).get(j) + " ";
                }
                System.out.println(answerPoolContainer);
                answerPoolContainer.add(mergeResult);
            }
            float currentStringLength = (float) String.valueOf(answerPoolContainer.get(i)).length();
            blockHolders[i] = new BlockHolder(manager, "\"" + answerPoolContainer.get(i) + "\"");
            blockHolders[i].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
            yStartingPoint -= 2;
        }
        // WILL BE USED, DON'T ERASE

        answerBlocks = new Blocks[answerPoolContainer.size()];
        Collections.shuffle(answerPoolContainer);
        // END MINIGAME CREATION

        float AnsPoolY = 8;
        float AnsPoolX = -2;
        int ansPoolSize = answerPoolContainer.size();
        for(int i = 0; i < ansPoolSize; i++) {
            float currentStringLength = (float) String.valueOf(answerPoolContainer.get(i)).length();
            answerBlocks[i] = new Blocks(manager, "\"" + answerPoolContainer.get(i) + "\"", answerPoolContainer.get(i), true);
            if (answerPoolContainer.get(i) != null) {
                answerBlocks[i].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.2f), Constants.BLOCKS_HEIGHT), 0);
            }
            AnsPoolY -= 2.5;
        }

        jedisaur = new Character(manager);
        jedisaur.create(new Vector2(0, 0), new Vector2(1.2f, 1.75f), 1.6f);

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
        if(pause.isRunning()) {
            // WILL BE USED, DON'T ERASE
            currentCell = 0;
            for (int i = 0; i < minigameContainer.size(); i++) {
                if (minigameContainer.get(i) != null) {
                    blockHolders[i].update(delta);
                }
            }

            //kung gagamitin mo to remove the blocks[i][j].update muna sa taas pero i havent tried pag magkasabay sila naka on for sure dodoble HAHA
            for (int i = 0; i < answerBlocks.length; i++) {
                if (answerBlocks[i] != null) {
                    answerBlocks[i].update(delta);
                    if (answerBlocks[i].isInContact()) {
                        jedisaur.carryBlock(answerBlocks[i]);
//                            System.out.println("jedisaur carrying ");
                    }
                }
            }
            // WILL BE USED, DON'T ERASE


            // WILL BE USED, DON'T ERASE
            currentCell = 0;
            for (int i = 0; i < minigameContainer.size(); i++) {
                if (minigameContainer.get(i) != null) {
                    if (blockHolders[i].isInContact()) {
                        jedisaur.dropBlock(blockHolders[i]);
                    }
                }

            }
            // WILL BE USED, DON'T ERASE

            for (int i = 0; i < answerPoolContainer.size(); i++) {
                if (answerBlocks[i] != null) {
                    answerBlocks[i].update(delta);
                }
            }

            playroom.exitDoor(jedisaur);
            jedisaur.update(delta);
//            pause.update(delta);
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
                    blockHolders[i].render(sprite);
                }
            }
        }

        for(int i = 0; i < answerPoolContainer.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].render(sprite);
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
                blockHolders[i].disposeBody();
            }
        }

        for(int i = 0; i < answerPoolContainer.size(); i++) {
            answerBlocks[i].disposeBody();
        }
        // WILL BE USED, DON'T ERASE
        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage,expertiseLevel);
        minigameContainer = manager.getQuestionnaire().getMinigame();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
    }
}
