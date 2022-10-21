package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
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
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CodeIT extends State {
    private Character jedisaur;
    private FuzzyLogic fuzzyLogic;
    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
    private Blocks[] answerBlocks;
    private BlockDispenser[] blockDispensers;
    private PauseState pause;
    float blockSize;
    private ArrayList<ArrayList<String>> minigameContainer;
    private ArrayList<String> getAnswerPool, dispenserPoolContainer;
    private ArrayList<ArrayList<Blocks>> blocksArrayList;
    private float AnsPoolY, AnsPoolX, DispenserY, DispenserX, currentStringLength, xStartingPoint, yStartingPoint;
    private int currentAnsCell, ansPoolSize, totalLineLength, currentCell, stage;
    private String dispenserGraphics;
    private float timer;

    public CodeIT(Manager manager, Character jedisaur, FuzzyLogic fuzzyLogic) {
        super(manager);
        timer = 0;
        pause = new PauseState(manager);
        getAnswerPool = new ArrayList<>();
        blocksArrayList = new ArrayList<>();
        dispenserPoolContainer = new ArrayList<>();

        this.stage = manager.getStageSelector().getStageMap();
        getAMinigame(manager.getStageSelector().map());
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];

        /** SET UP DUPES **/
        for(int i = 0; i < minigameContainer.size(); i++) {
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    dispenserPoolContainer.add(minigameContainer.get(i).get(j));
                }
            }
        }

        /** BANISH SIMILAR CELLS **/
        for(int i = 0; i < dispenserPoolContainer.size(); i++) {
            String banished = dispenserPoolContainer.get(i);
            int dupes = 1;
            for(int j = 0; j < dispenserPoolContainer.size(); j++) {
                if(dispenserPoolContainer.get(j).equals(banished) && i != j) {
                    dupes += 1;
                    dispenserPoolContainer.remove(j);
                    j = 0;
                }
            }
            if(dupes == 1) {
//                System.out.println(dispenserPoolContainer.get(i) + " added to ansblocks");
                getAnswerPool.add(dispenserPoolContainer.get(i));
            }
        }

        /** REMOVE ANSWERPOOLBLOCKS FROM DISPENSERPOOL **/
        for(int i = 0; i < dispenserPoolContainer.size(); i++) {
            for(int j = 0; j < dispenserPoolContainer.size(); j++) {
                if (dispenserPoolContainer.get(j) != null) {
                    if (getAnswerPool.contains(dispenserPoolContainer.get(j))) {
//                        System.out.println(dispenserPoolContainer.get(j) + " removed from ansblocks");
                        dispenserPoolContainer.remove(j);
                        j = 0;
                    }
                }
            }
        }

        /** START OF MINIGAME CREATION **/
        yStartingPoint = 10;
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            blocksArrayList.add(new ArrayList<Blocks>());
            xStartingPoint = -15.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j] = new BlockHolder(manager, minigameContainer.get(i).get(j));
                    blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                    blocksArrayList.get(i).add(blockHolders[i][j].getCopyBlock());
                    xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
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
//        System.out.println(getAnswerPool);
        for(int i = 0; i < ansPoolSize; i++) {
            AnsPoolX = 13;
            totalLineLength = 0;
            while (totalLineLength <= 12) {
                if (currentAnsCell == ansPoolSize) {
                    break;
                }
                currentStringLength = (float) String.valueOf(getAnswerPool.get(currentAnsCell)).length();
                totalLineLength += currentStringLength + (AnsPoolX - 11);
                answerBlocks[currentAnsCell] = new Blocks(manager, getAnswerPool.get(currentAnsCell), getAnswerPool.get(currentAnsCell), false);
                if (getAnswerPool.get(currentAnsCell) != null) {
                    if (currentStringLength <= 3) {
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.5f), Constants.BLOCKS_HEIGHT), 0);
                    }
                    else{
                        if(currentStringLength>7){
                            AnsPoolX += 2;
                        }
                        answerBlocks[currentAnsCell].create(new Vector2(AnsPoolX, AnsPoolY), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                    }
                    AnsPoolX = answerBlocks[currentAnsCell].getDupliSize().x + answerBlocks[currentAnsCell].getBody().getPosition().x + 2.25f;
                    currentAnsCell++;
                }
            }
            AnsPoolY -= 2;
        }
        /** END OF ANSWER POOL CREATION **/

        /** START OF DISPENSER POOL CREATION **/
        blockDispensers = new BlockDispenser[dispenserPoolContainer.size()];
//        System.out.println(dispenserPoolContainer);
        ansPoolSize = dispenserPoolContainer.size();
        DispenserX = 24;
        DispenserY = 10;
        dispenserGraphics = "Left";
        for(int i = 0; i < ansPoolSize; i++) {
            currentStringLength = (float) String.valueOf(dispenserPoolContainer.get(i)).length();
            if (currentStringLength <= 3){
                blockDispensers[i] = new BlockDispenser(manager, dispenserGraphics, dispenserPoolContainer.get(i), dispenserPoolContainer.get(i),
                        10, new Vector2(currentStringLength * 0.5f, Constants.BLOCKS_HEIGHT));
            }
            else {
                blockDispensers[i] = new BlockDispenser(manager, dispenserGraphics, dispenserPoolContainer.get(i), dispenserPoolContainer.get(i),
                        10, new Vector2(currentStringLength * 0.23f, Constants.BLOCKS_HEIGHT));
            }
            blockDispensers[i].create(new Vector2(DispenserX, DispenserY), new Vector2(0.3f, 1.3f), 0);
//            System.out.println(dispenserPoolContainer.get(i) + " many " + 10);
            DispenserY -= 6;
            if(DispenserY == -14) {
                dispenserGraphics = "Right";
                DispenserY = 10;
                DispenserX = -22;
            }
        }

        setToCheck(blockHolders);

        this.jedisaur = jedisaur;
        this.fuzzyLogic = fuzzyLogic;
    }

    @Override
    public void update(float delta) {
        if(!manager.getMinigameChecker().isDone()){
            timer += Gdx.graphics.getDeltaTime();
            manager.getMinigame().checkBehavior((int) timer, jedisaur);
        }

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

        for(int i = 0; i < dispenserPoolContainer.size(); i++) {
            blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
            blockDispensers[i].update(delta);
        }


        for(int i = 0; i < dispenserPoolContainer.size(); i++) {
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

        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (blockHolders[i][j].isInContact()) {
                        jedisaur.dropBlock(blockHolders[i][j]);
                        if(jedisaur.isDropped() && !blocksArrayList.get(i).contains(blockHolders[i][j].getCopyBlock())){
                            blocksArrayList.get(i).set(j, blockHolders[i][j].getCopyBlock());
                        }
                        if(jedisaur.isDropped()) {
                            setBlockToCheck(blockHolders[i][j].getCopyBlock(), i, j);
                            setToCheck(blockHolders);
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
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize/1.75f +0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j-1; k>=0; k--){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize/1.75f + 0.5f, blockHolders[i][k].getBody().getPosition().y + 0.5f, 0);
                                }
                            }

                            /** right blocks in the blockholders **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                if(blockHolders[i][k].isOccupied()){
//                                    System.out.println(" wtf ");
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize*1.2f - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }

                            /** right iteration of blocks **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize*1.2f -0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }
                            jedisaur.setDropped(false);
                        }

                        if(jedisaur.isPickedUp()){
                            blocksArrayList.get(i).set(j, null);

                            for(int k=j-1; k>=0; k--){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize/1.75f-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize*1.2f +0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                            }

                            for(int k=j-1; k>=0; k--){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize/1.75f - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }
                            /** right blocks in the blockholders **/
                            for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                if(blockHolders[i][k].isOccupied()){
                                    blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize*1.2f + 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                }
                            }
                            setBlockToCheck(null, i, j);
                            setToCheck(blockHolders);
                            jedisaur.setPickedUp(false);
                        }
                    }
                }
            }
        }
        itIsCorrect();
    }



    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.end();

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

        for(int i = 0; i < dispenserPoolContainer.size(); i++){
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
    }

    @Override
    public void dispose() {
        for(int i = 0; i < getAnswerPool.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].disposeBody();
            }
        }

        for(BlockHolder[] b: blockHolders){
            for(BlockHolder i: b){
                if(i != null){
                    i.disposeBody();
                }
            }
        }

        for(int i = 0; i < dispenserPoolContainer.size(); i++){
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
    }

    public void setActive(boolean active){
//        System.out.println(minigameContainer.size());
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].setActive(false);
                }
            }
        }

        for(int i = 0; i < dispenserPoolContainer.size(); i++){
            blockDispensers[i].disposeBody();
            if(blockDispensers[i].isCloned()){
                for (Blocks b : blockDispensers[i].getBlocks()) {
                    if (b != null) {
                        b.setActive(active);
                    }
                    else{
                        continue;
                    }
                }
            }
        }
    }

    public void getAMinigame(String stage){
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()));
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
    }

    public void setToCheck(BlockHolder[][] blockHolders) {
        manager.getMinigameChecker().setBlockHolders(blockHolders);
    }

    public void itIsCorrect(){
        if(manager.getMinigameChecker().isDone() && !fuzzyLogic.isFuzzyDone()){
            fuzzyLogic.setNumberOfAttempts(manager.getMinigameChecker().getNumberOfAttempts());
            fuzzyLogic.setCorrectOutput(1);
            fuzzyLogic.setTimeConsumptions(fuzzyLogic.getTimeConsumptions() + timer);

            fuzzyLogic.fuzzyNumberOfError();
            fuzzyLogic.fuzzyTimeConsumption();
            fuzzyLogic.fuzzyNumberOfAttempt();
            fuzzyLogic.fuzzyCorrectOutput();

            fuzzyLogic.calculateNumberOfCookies();

            manager.getExpertSystem().setCurrentCookie(fuzzyLogic.getCookies());
            manager.getExpertSystem().editCookie(manager.getStageSelector().getStageMap() - 1);
            manager.getExpertSystem().writeFile(manager.getExpertSystem().getCookies());
            manager.getExpertSystem().readFile();

            fuzzyLogic.setFuzzyDone(true);
        }
    }

    public void setBlockToCheck(Blocks block, int i, int j) {
        if(jedisaur.isPickedUp()) {
            manager.getMinigameChecker().pickUpCopyBlock(block, i, j);
        }
        if(jedisaur.isDropped()) {
            manager.getMinigameChecker().dropCopyBlock(block, i, j);
        }
    }
}
