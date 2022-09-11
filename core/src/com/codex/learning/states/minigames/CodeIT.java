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

    private int blockCount;
    private boolean blockSpawn;

    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
//    private BlockDispenser[] blockDispensers;
    private Blocks[] answerBlocks;

    private PauseState pause;
    float blockSize;

    private ArrayList<ArrayList<String>> minigameContainer;
//    private int minigameContainerLimit;
//    private Random randomizer;
    private ArrayList<String> getAnswerPool;
    private ArrayList<ArrayList<Blocks>> blocksArrayList;
//    private ArrayList<Integer> duplicatePool;
//    private int currentCell;

    public CodeIT(Manager manager, Character character) {
        super(manager);
        pause = new PauseState(manager);

        playroom = new PlayroomMapS1(manager);

//        duplicatePool = new ArrayList<Integer>();
        getAnswerPool = new ArrayList<String>();
        blocksArrayList = new ArrayList<>();
//        randomizer = new Random();

        getAMinigame(manager.getStageSelector().map(), "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        // START MINIGAME CREATION
        int yStartingPoint = 8, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -20.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                blocksArrayList.add(new ArrayList<Blocks>());
                if(minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                    blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                    getAnswerPool.add(minigameContainer.get(i).get(j));
                    xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
                    System.out.println(xStartingPoint + " " + minigameContainer.get(i).get(j));
                    System.out.println(String.valueOf(minigameContainer.get(i).get(j)).length() + "     " + (float) String.valueOf(minigameContainer.get(i).get(j)).length());
                }
            }
            yStartingPoint -= 2;
        }
//        Collections.shuffle(getAnswerPool);
        // END MINIGAME CREATION

//        for(int i = 0; i < getAnswerPool.size(); i++) {
//            String banished = getAnswerPool.get(i);
//            int dupes = 1;
//            System.out.println("eto tunay " + getAnswerPool.get(i));
//            for(int j = 0; j < getAnswerPool.size(); j++) {
//                System.out.println(getAnswerPool.get(i) + " dupe " + getAnswerPool.get(j));
//                if(getAnswerPool.get(j).equals(banished) && i != j) {
//                    dupes += 1;
//                    System.out.println("pumasok dupe");
//                    getAnswerPool.remove(j);
//                }
//            }
//            System.out.println("luh tapos na dupe");
//            duplicatePool.add(dupes);
//        }
//        System.out.println("pool " + getAnswerPool);
//        System.out.println("pool " + duplicatePool);
//
//        blockDispensers = new BlockDispenser[getAnswerPool.size()];
//
//        int ansPoolSize = getAnswerPool.size();
//        System.out.println(ansPoolSize);
//        int xposition = -17, yposition = -3;
//        for(int i = 0; i < ansPoolSize; i++) {
//            blockDispensers[i] = new BlockDispenser(manager, "Down", getAnswerPool.get(i), getAnswerPool.get(i),
//                    duplicatePool.get(i), new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
//            blockDispensers[i].create(new Vector2(xposition, yposition), new Vector2(0.3f, 1.3f), 0);
//            System.out.println(getAnswerPool.get(i) + " many " + duplicatePool.get(i));
//            xposition += 5;
//            if(xposition == 23) {
//                yposition -= 6;
//                xposition = -17;
//            }
//        }

        answerBlocks = new Blocks[getAnswerPool.size()];
        Collections.shuffle(getAnswerPool);
        // END MINIGAME CREATION

        float AnsPoolY = 10;
        int currentAnsCell = 0;
        int ansPoolSize = getAnswerPool.size();
        for(int i = 0; i < ansPoolSize; i++) {
            float AnsPoolX = 13;
            int totalLineLength = 0;
            while (totalLineLength <= 12) {
                float currentStringLength = (float) String.valueOf(getAnswerPool.get(currentAnsCell)).length();
                totalLineLength += currentStringLength + (AnsPoolX - 11);
                answerBlocks[currentAnsCell] = new Blocks(manager, "\"" + getAnswerPool.get(currentAnsCell) + "\"", getAnswerPool.get(currentAnsCell), false);
                if (getAnswerPool.get(currentAnsCell) != null) {
                    if (currentStringLength <= 3)
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.5f), Constants.BLOCKS_HEIGHT), 0);
                    else
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
//
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

        this.jedisaur = character;
    }

    @Override
    public void update(float delta) {
        // WILL BE USED, DON'T ERASE
//        currentCell = 0;
        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].update(delta);
                }
            }
        }
        // WILL BE USED, DON'T ERASE
//        for (int i = 0; i < getAnswerPool.size(); i++) {
//            blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
//        }
//        for (int i = 0; i < getAnswerPool.size(); i++) {
//            if (blockDispensers[i].isCloned()) {
//                for (Blocks b : blockDispensers[i].getBlocks()) {
//                    if (b != null) {
//                        b.update(delta);
//                        if (b.isInContact()) {
//                            jedisaur.carryBlock(b);
//                        }
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        }
        // WILL BE USED, DON'T ERASE
//        currentCell = 0;

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
        // WILL BE USED, DON'T ERASE

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

//        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].render(sprite);
                }
            }
        }

//        for(int i = 0; i < getAnswerPool.size(); i++){
//            blockDispensers[i].render(sprite);
//            if(blockDispensers[i].isCloned()){
//                for (Blocks b : blockDispensers[i].getBlocks()) {
//                    if (b != null) {
//                        b.render(sprite);
//                    }
//                    else{
//                        continue;
//                    }
//                }
//            }
//        }
        for(int i = 0; i < getAnswerPool.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].render(sprite);
            }
        }
    }

    @Override
    public void dispose() {

        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].disposeBody();
                }
            }
        }

        // WILL BE USED, DON'T ERASE

//        for(int i = 0; i < getAnswerPool.size(); i++){
//            blockDispensers[i].disposeBody();
//            if(blockDispensers[i].isCloned()){
//                for (Blocks b : blockDispensers[i].getBlocks()) {
//                    if (b != null) {
//                        b.disposeBody();
//                    }
//                    else{
//                        continue;
//                    }
//                }
//            }
//        }
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
//        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
    }

    public ArrayList<String> getAnswerPoolContainer() {
        return getAnswerPool;
    }

    public void setAnswerPoolContainer(ArrayList<String> answerPoolContainer) {
        this.getAnswerPool = answerPoolContainer;
    }
}
