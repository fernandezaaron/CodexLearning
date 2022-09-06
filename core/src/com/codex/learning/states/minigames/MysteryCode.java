package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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

public class MysteryCode extends State {

    private Character jedisaur;
    private PlayroomMapS1 playroom;

    private Blocks[] answerBlocks;

    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;

    private PauseState pause;
    float blockSize;

    private ArrayList<ArrayList<String>> minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> answerPoolContainer;
    private int currentCell, stage;
    private String stageSelect;


    public MysteryCode(Manager manager, Character jedisaur) {
        super(manager);
        pause = new PauseState(manager);
        playroom = new PlayroomMapS1(manager);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        this.stage = manager.getStageSelector().getStageNumber();

        getAMinigame(manager.getStageSelector().map(), "Poor");

        for(int i = 0; i <= 10; i++) {
            banishCells.add(randomizer.nextInt(minigameContainerLimit - 1) + 1);
        }

        // START MINIGAME CREATION
        int yStartingPoint = 11, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -18.0f;
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    if (banishCells.contains(currentCell)) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                        answerPoolContainer.add(minigameContainer.get(i).get(j));
                        xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
                    } else {
                        questionBlocks[i][j] = new Blocks(manager, "\"" + minigameContainer.get(i).get(j) + "\"", minigameContainer.get(i).get(j), true);
                        if (currentStringLength <= 3){
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.4f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
                            System.out.println(questionBlocks[i][j].getDupliSize().x );
//                            System.out.println(-questionBlocks[i][j].getBody().getPosition().x);


//                            xStartingPoint += currentStringLength + 1.5f;
                        }

                        else{
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
//                            xStartingPoint += currentStringLength / 2.2f;
                            System.out.println(questionBlocks[i][j].getDupliSize().x );

//                            xStartingPoint =  questionBlocks[i][j].getDupliSize().x + questionBlocks[i][j].getBody().getPosition().x + 1.5f;
                        }
                        xStartingPoint =  questionBlocks[i][j].getDupliSize().x + questionBlocks[i][j].getBody().getPosition().x + 1.75f;
                    }
                    currentCell++;
                }
            }
            yStartingPoint -= 2.5f;
        }
        // WILL BE USED, DON'T ERASE

        answerBlocks = new Blocks[answerPoolContainer.size()];
        Collections.shuffle(answerPoolContainer);
        // END MINIGAME CREATION

        float AnsPoolY = 11;
        int currentAnsCell = 0;
        int ansPoolSize = answerPoolContainer.size();
        for(int i = 0; i < ansPoolSize; i++) {
            float AnsPoolX = 13;
            int totalLineLength = 0;
            while (totalLineLength <= 12) {
                float currentStringLength = (float) String.valueOf(answerPoolContainer.get(currentAnsCell)).length();
                totalLineLength += currentStringLength + (AnsPoolX - 11);
                answerBlocks[currentAnsCell] = new Blocks(manager, "\"" + answerPoolContainer.get(currentAnsCell) + "\"", answerPoolContainer.get(currentAnsCell), true);
                if (answerPoolContainer.get(currentAnsCell) != null) {
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

        this.jedisaur = jedisaur;


    }


    @Override
    public void update(float delta) {
        // WILL BE USED, DON'T ERASE
//               manager.getWorld().step(1/60f,6,2);
               currentCell = 0;
               for (int i = 0; i < minigameContainer.size(); i++) {
                   for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                       if (minigameContainer.get(i).get(j) != null) {
                           if (banishCells.contains(currentCell)){
                               System.out.println(i + " " + j);
                               blockHolders[i][j].update(delta);
                           }
                           else
                               questionBlocks[i][j].update(delta);

//                           System.out.println(currentCell);
                           currentCell++;
                       }
                   }
               }
            //kung gagamitin mo to remove the blocks[i][j].update muna sa taas pero i havent tried pag magkasabay sila naka on for sure dodoble HAHA
            for (int i = 0; i < answerBlocks.length; i++) {
                if (answerBlocks[i] != null) {
                    answerBlocks[i].update(delta);
                    if (answerBlocks[i].isInContact()) {
                        jedisaur.carryBlock(answerBlocks[i]);
                    }
                }
            }
            // WILL BE USED, DON'T ERASE
        currentCell = 0;
        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (banishCells.contains(currentCell)) {
                        if (blockHolders[i][j].isInContact()) {
                            jedisaur.dropBlock(blockHolders[i][j]);
//                                System.out.println("dropped to: " + blockHolders[i][j]);
                        }
                    }
                    currentCell++;
                }
            }
        }

        if(jedisaur.isCarrying()){
            blockSize = jedisaur.getCopyBlock().getDupliSize().x;
        }

        currentCell = 0;
        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (banishCells.contains(currentCell)) {
                        if (blockHolders[i][j].isInContact()) {
                            blockHolders[i][j].update(delta);
                            if(jedisaur.isDropped()){
//                            System.out.println(jedisaur.getCopyBlock().getDupliSize());
                                System.out.println(blockSize);
                                System.out.println(blockHolders[i][j].getCopyBlock() + "asdad");

                                int currentCurrentCell = currentCell;
                                for(int k=j-1; k>=0; k--){
                                    currentCurrentCell--;
                                    if(banishCells.contains(currentCurrentCell)){
                                        System.out.println("left true");
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        System.out.println("left false");
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x - blockSize+0.5f, questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                    System.out.println("k-1: " + k);
                                }

//                            currentCurrentCell = currentCell;
//                            System.out.println("J: " + j);
//                            for(int k=j-1; k>=0; k--){
//                                currentCurrentCell--;
//
//                                if(banishCells.contains(currentCurrentCell)){
////                                    System.out.println("left true");
////                                    blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
//                                    if(blockHolders[i][k].isOccupied()){
//                                        System.out.println("is occupied :D");
//                                        blockHolders[i][k].getCopyBlock().getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
//                                    }
//                                }
//                                System.out.println("k-1: " + k);
//                            }

                                currentCurrentCell = currentCell;
                                for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                    currentCurrentCell++;
                                    System.out.println("k-1: START" + k);
                                    System.out.println(banishCells.size() + "banish");
                                    if(banishCells.contains(currentCurrentCell)){
                                        System.out.println("left true");
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        System.out.println("left false");
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x + blockSize-0.5f , questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                    System.out.println("k-1: " + k);
                                }
                                jedisaur.setDropped(false);
                            }
                        }
                    }
                    currentCell++;
                }
            }
        }


             // WILL BE USED, DON'T ERASE
             for (int i = 0; i < answerPoolContainer.size(); i++) {
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

//        playroom.render(sprite);

        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishCells.contains(currentCell)) {
                        blockHolders[i][j].render(sprite);
                    }
                    else
                        questionBlocks[i][j].render(sprite);
                    currentCell++;
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

        // WILL BE USED, DON'T ERASE

        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishCells.contains(currentCell)) {
                        blockHolders[i][j].disposeBody();
                    }
                    else
                        questionBlocks[i][j].disposeBody();
                    currentCell++;
                }
            }
        }

        for(int i = 0; i < answerPoolContainer.size(); i++) {
            if(answerBlocks[i] != null) {
                answerBlocks[i].disposeBody();
            }
        }
        // WILL BE USED, DON'T ERASE
        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageNumber()), expertiseLevel);
        minigameContainer = manager.getQuestionnaire().getMinigame();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
        answerPoolContainer = manager.getQuestionnaire().getAnswerPool();
    }


}
