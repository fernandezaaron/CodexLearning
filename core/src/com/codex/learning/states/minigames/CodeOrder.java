package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.states.PauseState;
import com.codex.learning.states.State;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

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
//    private int minigameContainerLimit;
    private int currentCell, yStartingPoint;
    private float xStartingPoint, AnsPoolY, AnsPoolX, currentStringLength;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> originalAnswerPoolContainer, answerPoolContainer;
    private String mergeResult;

    public CodeOrder(Manager manager, Character jedisaur) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager);
        randomizer = new Random();
        banishCells = new ArrayList<Integer>();

        getAMinigame(manager.getStageSelector().map(), "Poor");

        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[minigameContainer.size()];
        answerPoolContainer = new ArrayList<>();
        originalAnswerPoolContainer = new ArrayList<>();

        /** START OF MINIGAME CREATION **/
        yStartingPoint = 10;
        currentCell = 0;
        xStartingPoint = -10.0f;
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
                originalAnswerPoolContainer.add(mergeResult);
            }
            blockHolders[i] = new BlockHolder(manager, "\"" + answerPoolContainer.get(i) + "\"");
            blockHolders[i].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
            yStartingPoint -= 3f;
        }
        answerBlocks = new Blocks[answerPoolContainer.size()];
        Collections.shuffle(answerPoolContainer);
        /** END OF MINIGAME CREATION **/

        /** START OF ANSWER POOL CREATION **/
        AnsPoolY = 8;
        AnsPoolX = 8;
        int ansPoolSize = answerPoolContainer.size();
        for(int i = 0; i < ansPoolSize; i++) {
            currentStringLength = (float) String.valueOf(answerPoolContainer.get(i)).length();
            answerBlocks[i] = new Blocks(manager, "\"" + answerPoolContainer.get(i) + "\"", answerPoolContainer.get(i), true);
            if (answerPoolContainer.get(i) != null) {
                answerBlocks[i].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.2f), Constants.BLOCKS_HEIGHT), 0);
            }
            AnsPoolY -= 2.5f;
        }
        /** END OF ANSWER POOL CREATION **/

        setToCheck(blockHolders);

        this.jedisaur = jedisaur;
    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < minigameContainer.size(); i++) {
            if (minigameContainer.get(i) != null) {
                blockHolders[i].update(delta);
            }
        }

        for (int i = 0; i < answerBlocks.length; i++) {
            if (answerBlocks[i] != null) {
                answerBlocks[i].update(delta);
                if (answerBlocks[i].isInContact()) {
                    jedisaur.carryBlock(answerBlocks[i]);
                    if(jedisaur.isPickedUp()) {
                        setBlockToCheck(null, i);
                        setToCheck(blockHolders);
                    }
                }
            }
        }

        for (int i = 0; i < minigameContainer.size(); i++) {
            if (blockHolders[i].isInContact()) {
                jedisaur.dropBlock(blockHolders[i]);
                if(jedisaur.isDropped()) {
                    setBlockToCheck(blockHolders[i].getCopyBlock(), i);
                }
            }
        }

        for (int i = 0; i < answerPoolContainer.size(); i++) {
            if (answerBlocks[i] != null) {
                answerBlocks[i].update(delta);
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
    }

    @Override
    public void dispose() {
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                blockHolders[i].disposeBody();
            }
        }

        for(int i = 0; i < answerPoolContainer.size(); i++) {
            answerBlocks[i].disposeBody();
        }

        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()));
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
        while(minigameContainer.get(0).size() > 6){
            minigameContainer = manager.getQuestionnaire().getMinigameHolder();
        }
//        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
    }

//    public void checker(){
//        for(int i = 0; i < answerPoolContainer.size(); i++) {
//            if(blockHolders[i].getCopyBlock().equals(originalAnswerPoolContainer.get(i))) {
//                score++;
//            }
//        }
//    }
    public void setToCheck(BlockHolder[] blockHolders) {
        manager.getMinigameChecker().setBlockOrder(blockHolders);
    }

    public void setBlockToCheck(Blocks block, int i) {
        if(jedisaur.isPickedUp()) {
            manager.getMinigameChecker().pickUpOrderBlock(block, i);
        }
        if(jedisaur.isDropped()) {
            manager.getMinigameChecker().dropOrderBlock(block, i);
        }
    }

}
