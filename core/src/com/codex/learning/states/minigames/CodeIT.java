package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.codex.learning.entity.blocks.BlockDispenser;
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

public class CodeIT extends State {
    private Character jedisaur;
    private PlayroomMapS1 playroom;
    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
    private Blocks[] answerBlocks;
    private PauseState pause;
    float blockSize;
    private ArrayList<ArrayList<String>> minigameContainer;
    private ArrayList<String> getAnswerPool;
    private ArrayList<ArrayList<Blocks>> blocksArrayList;
    private float AnsPoolY, AnsPoolX, currentStringLength, xStartingPoint;
    private int currentAnsCell, ansPoolSize, totalLineLength, yStartingPoint, currentCell, stage;
//    private int blockCount;
//    private boolean blockSpawn;
//    private BlockDispenser[] blockDispensers;
//    private ArrayList<Integer> duplicatePool;
//    private int currentCell;
//    private int minigameContainerLimit;
//    private Random randomizer;

    public CodeIT(Manager manager, Character character) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager);
        getAnswerPool = new ArrayList<>();
        blocksArrayList = new ArrayList<>();
//        duplicatePool = new ArrayList<Integer>();
//        randomizer = new Random();
        this.stage = manager.getStageSelector().getStageMap();
        getAMinigame(manager.getStageSelector().map(), manager.getExpertSystem().getExpertiseLevel());
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];

        /** START OF MINIGAME CREATION **/
        yStartingPoint = 10;
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            blocksArrayList.add(new ArrayList<Blocks>());
            xStartingPoint = -18.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                blocksArrayList.add(new ArrayList<Blocks>());
                if(minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                    blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                    blocksArrayList.get(i).add(blockHolders[i][j].getCopyBlock());
                    getAnswerPool.add(minigameContainer.get(i).get(j));
                    xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
                    System.out.println(xStartingPoint + " " + minigameContainer.get(i).get(j));
                    System.out.println(String.valueOf(minigameContainer.get(i).get(j)).length() + "     " + (float) String.valueOf(minigameContainer.get(i).get(j)).length());
                }
            }
            yStartingPoint -= 2.5f;
        }
        answerBlocks = new Blocks[getAnswerPool.size()];
        Collections.shuffle(getAnswerPool);
        /** END OF MINIGAME CREATION **/

        /** START OF ANSWER POOL CREATION **/
        AnsPoolY = 10;
        currentAnsCell = 0;
        ansPoolSize = getAnswerPool.size();
        for(int i = 0; i < ansPoolSize; i++) {
            AnsPoolX = 13;
            totalLineLength = 0;
            while (totalLineLength <= 12) {
                currentStringLength = (float) String.valueOf(getAnswerPool.get(currentAnsCell)).length();
                totalLineLength += currentStringLength + (AnsPoolX - 11);
                answerBlocks[currentAnsCell] = new Blocks(manager, "\"" + getAnswerPool.get(currentAnsCell) + "\"", getAnswerPool.get(currentAnsCell), false);
                if (getAnswerPool.get(currentAnsCell) != null) {
                    if (currentStringLength <= 3)
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.5f), Constants.BLOCKS_HEIGHT), 0);
                    else{
                        if(currentStringLength>7){
                            AnsPoolX += 2;
                        }
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                    }
                    AnsPoolX = answerBlocks[currentAnsCell].getDupliSize().x + answerBlocks[currentAnsCell].getBody().getPosition().x + 2.25f;
                    if (currentAnsCell == ansPoolSize - 1) {
                        break;
                    } else {
                        currentAnsCell++;
                    }
                }
            }
            if(currentAnsCell == ansPoolSize - 1) {
                break;
            }
            AnsPoolY -= 2.5;
        }
        /** END OF ANSWER POOL CREATION **/

        this.jedisaur = character;
    }

    @Override
    public void update(float delta) {
        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].update(delta);
                }
            }
        }

        for (int i = 0; i < answerBlocks.length; i++) {
            if (answerBlocks[i] != null) {
                if (answerBlocks[i].isInContact()) {
                    jedisaur.carryBlock(answerBlocks[i]);
                }
            }
        }

        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (blockHolders[i][j].isInContact()) {
                        jedisaur.dropBlock(blockHolders[i][j]);
                        if(jedisaur.isDropped() && !blocksArrayList.get(i).contains(blockHolders[i][j].getCopyBlock())){
                            blocksArrayList.get(i).add(blockHolders[i][j].getCopyBlock());

                        }
                    }
                }
            }
        }

        /** below this is used for padding **/
        if(jedisaur.isCarrying()){
            blockSize = jedisaur.getCopyBlock().getDupliSize().x;
//            blockSize = 1.5f;
        }

        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (blockHolders[i][j].isInContact()) {
                        if(jedisaur.isDropped()){

                            /** left iteration of blocks **/
                            for(int k=j-1; k>=0; k--){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j-1; k>=0; k--){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize + 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }

                            /** right blocks in the blockholders **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }

                            /** right iteration of blocks **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }
                            jedisaur.setDropped(false);
                        }

                        if(jedisaur.isPickedUp()){
                            for(int k=j-1; k>=0; k--){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j-1; k>=0; k--){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }
                            /** right blocks in the blockholders **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize + 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }
                            jedisaur.setPickedUp(false);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < getAnswerPool.size(); i++) {
            if (answerBlocks[i] != null) {
                answerBlocks[i].update(delta);
            }
        }
    }



    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.end();
        playroom.render(sprite);

        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].render(sprite);
                }
            }
        }

        for(int i = 0; i < getAnswerPool.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].render(sprite);
            }
        }
    }

    @Override
    public void dispose() {
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].disposeBody();
                }
            }
        }

        for(int i = 0; i < getAnswerPool.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].disposeBody();
            }
        }
        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()),expertiseLevel);
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
    }
}
