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

public class CodeOrder extends State {
    private Character jedisaur;
    private FuzzyLogic fuzzyLogic;
    private Blocks[] answerBlocks;
    private BlockHolder[] blockHolders;
//    private BlockDispenser blockDispensers;
    private PauseState pause;
    private ArrayList<ArrayList<String>> minigameContainer;
//    private int minigameContainerLimit;
    private int currentCell, yStartingPoint;
    private float xStartingPoint, AnsPoolY, AnsPoolX, currentStringLength;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> originalAnswerPoolContainer, answerPoolContainer;
    private String mergeResult;
    private int timer;

    public CodeOrder(Manager manager, Character jedisaur, FuzzyLogic fuzzyLogic) {
        super(manager);
        timer = 0;
        pause = new PauseState(manager);
        randomizer = new Random();
        banishCells = new ArrayList<>();



        getAMinigame(manager.getStageSelector().map(), "Poor");

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
//        for(int i = 0; i < ansPoolSize; i++) {
//            currentStringLength = (float) String.valueOf(answerPoolContainer.get(i)).length();
//            blockDispensers = new BlockDispenser(manager, "Down", "\"" + banishPoolContainer.get(i) + "\"", banishPoolContainer.get(i),
//                        duplicatePool.get(i), new Vector2(currentStringLength * 0.5f, Constants.BLOCKS_HEIGHT));
//            blockDispensers.create(new Vector2(AnsPoolX, AnsPoolY), new Vector2(0.3f, 1.3f), 0);
//            System.out.println("this dispenser");
//        }
        /** END OF ANSWER POOL CREATION **/

        setToCheck(blockHolders);

        this.jedisaur = jedisaur;
        this.fuzzyLogic = fuzzyLogic;
    }

    @Override
    public void update(float delta) {
        if(!manager.getMinigameChecker().isDone()){
            timer += Gdx.graphics.getDeltaTime();
        }

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
//
//        blockDispensers.createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
//        blockDispensers.update(delta);


//        for (Blocks b : blockDispensers.getBlocks()) {
//            if (b != null) {
//                b.update(delta);
//                if(b.isInContact()){
//                    jedisaur.carryBlock(b);
//                }
//            }
//            else{
//                continue;
//            }
//        }


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

        itIsCorrect();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.enableBlending();
        sprite.setProjectionMatrix(manager.getCamera().combined);

        manager.getCamera().update();
        sprite.begin();
        sprite.setProjectionMatrix(manager.getCamera().combined);
        sprite.end();

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

//        blockDispensers.render(sprite);
//        if(blockDispensers.isCloned()){
//            for (Blocks b : blockDispensers.getBlocks()) {
//                if (b != null) {
//                    b.render(sprite);
//                }
//                else{
//                    continue;
//                }
//            }
//        }

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

//        blockDispensers.disposeBody();

    }

    public void setActive(boolean active){
        for(int i = 0; i < minigameContainer.size(); i++) {
            blockHolders[i].setActive(false);
        }

//        blockDispensers.disposeBody();
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

    public void setBlockToCheck(Blocks block, int i) {
        if(jedisaur.isPickedUp()) {
            manager.getMinigameChecker().pickUpOrderBlock(block, i);
        }
        if(jedisaur.isDropped()) {
            manager.getMinigameChecker().dropOrderBlock(block, i);
        }
    }

}
