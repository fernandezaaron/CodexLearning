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

public class FillInTheBlock extends State {


    private PlayroomMapS1 playroom;
    private Character jedisaur;

    private int blockCount;
    private boolean blockSpawn;

    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
    private BlockDispenser[] blockDispensers;

    private ArrayList<ArrayList<String>> minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> answerPoolContainer;
    private int currentCell;

    public FillInTheBlock(Manager manager, Character jedisaur) {
        super(manager);

        playroom = new PlayroomMapS1(manager, 1);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();

        getAMinigame("Stage 1", "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        blockDispensers = new BlockDispenser[2];

        // CREATES RANDOM NUMBER TO REMOVE CELLS FROM MINIGAME
        for(int i = 0; i <= 5; i++) {
            banishCells.add(randomizer.nextInt(minigameContainerLimit - 1) + 1);
        }

        // START MINIGAME CREATION
        int yStartingPoint = 8, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -23.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    if (banishCells.contains(currentCell)) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.22f), Constants.BLOCK_HOLDER_HEIGHT), 0);
                        answerPoolContainer.add(minigameContainer.get(i).get(j));
                        System.out.println(minigameContainer.get(i).get(j));
                    } else {
                        questionBlocks[i][j] = new Blocks(manager, "\"" + minigameContainer.get(i).get(j) + "\"", minigameContainer.get(i).get(j),true);
                        if (currentStringLength == 1)
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.4f), Constants.BLOCKS_HEIGHT), 0);
                        else
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                    }
                    currentCell++;
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
            blockDispensers[i].create(new Vector2(15 * i, -6), new Vector2(0.3f, 1.3f), 0);
        }

        this.jedisaur = jedisaur;

    }

    @Override
    public void update(float delta) {

//        manager.getWorld().step(1/60f,6,2);
       // WILL BE USED, DON'T ERASE
       currentCell = 0;
       for(int i = 0; i < minigameContainer.size(); i++) {
           for (int j = 0; j < minigameContainer.get(i).size(); j++) {
               if (minigameContainer.get(i).get(j) != null) {
                   if(banishCells.contains(currentCell))
                       blockHolders[i][j].update(delta);
                   else
                       questionBlocks[i][j].update(delta);
                   currentCell++;
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




//            pause.update(delta);




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
                    if(banishCells.contains(currentCell)) {
                        blockHolders[i][j].render(sprite);
                    }
                    else
                        questionBlocks[i][j].render(sprite);
                    currentCell++;
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


//        jedisaur.render(sprite);


    }

    @Override
    public void dispose() {
//        jedisaur.disposeBody();


        // WILL BE USED, DON'T ERASE
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    blockHolders[i][j].disposeBody();
                    questionBlocks[i][j].disposeBody();
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
