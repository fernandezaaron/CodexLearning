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

public class FillInTheBlock extends State {

    private Character jedisaur;
    private PlayroomMapS1 playroom;

    private Blocks[][] questionBlocks;
    private BlockHolder[][] blockHolders;
    private BlockDispenser[] blockDispensers;

    private PauseState pause;

    private ArrayList<ArrayList<String>> minigameContainer;
    private ArrayList<ArrayList<Integer>> banishPerRow;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<Integer> duplicatePool;
    private ArrayList<String> banishPoolContainer;
    private ArrayList<String> dispenserPoolContainer;
    private int currentCell;

    public FillInTheBlock(Manager manager, Character character) {
        super(manager);
        playroom = new PlayroomMapS1(manager);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();
        duplicatePool = new ArrayList<Integer>();
        banishPoolContainer = new ArrayList<String>();

        getAMinigame(manager.getStageSelector().map(), "Poor");

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        // WILL BE USED, DON'T ERASE

        // CREATES RANDOM NUMBER TO REMOVE CELLS FROM MINIGAME
        for(int i = 0; i < banishPerRow.size(); i++) {
            int banishNumberIterator = randomizer.nextInt(2) + 1;
            for(int j = 0; j < banishPerRow.get(i).size(); j++) {
                int banishNumber = randomizer.nextInt(banishPerRow.get(i).size() - 1) + banishPerRow.get(i).get(0);
                if(banishNumberIterator == 0) {
                    break;
                }
                else if(!banishCells.contains(banishNumber)) {
                    banishCells.add(banishNumber);
                    banishNumberIterator--;
                }
            }
        }
        int currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    if (banishCells.contains(currentCell) || banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        banishPoolContainer.add(minigameContainer.get(i).get(j));
                    }
                    System.out.println("pool " + banishPoolContainer);
                }
                currentCell++;
            }
        }
        for(int i = 0; i < banishPoolContainer.size(); i++) {
            String banished = banishPoolContainer.get(i);
            int dupes = 1;
            System.out.println("eto tunay " + banishPoolContainer.get(i));
            for(int j = 0; j < banishPoolContainer.size(); j++) {
                System.out.println(banishPoolContainer.get(i) + " dupe " + banishPoolContainer.get(j));
                if(banishPoolContainer.get(j).equals(banished) && i != j) {
                    dupes += 1;
                    System.out.println("pumasok dupe");
                    banishPoolContainer.remove(j);
                }
            }
            System.out.println("luh tapos na dupe");
            duplicatePool.add(dupes);
        }
        System.out.println("pool " + banishPoolContainer);
        System.out.println("pool " + duplicatePool);


        // START MINIGAME CREATION
        int yStartingPoint = 8;
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            float xStartingPoint = -23.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2(Constants.BLOCK_HOLDER_WIDTH * 3, Constants.BLOCK_HOLDER_HEIGHT), 0);
                        dispenserPoolContainer.add(minigameContainer.get(i).get(j));
                        xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 10;
                    } else {
                        questionBlocks[i][j] = new Blocks(manager, "\"" + minigameContainer.get(i).get(j) + "\"", minigameContainer.get(i).get(j), true);
                        if (currentStringLength <= 3){
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.5f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
                            xStartingPoint += currentStringLength + 0.5f;
                        }

                        else{
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
                            xStartingPoint += currentStringLength / 1.8f;
                        }
                    }
                    currentCell++;
                }
            }
            yStartingPoint -= 2;
        }
//        Collections.shuffle(dispenserPoolContainer);
        // END MINIGAME CREATION

        blockDispensers = new BlockDispenser[banishPoolContainer.size() + dispenserPoolContainer.size()];

        int ansPoolSize = banishPoolContainer.size();
        System.out.println(ansPoolSize);
        int xposition = -22, yposition = -9;
        for(int i = 0; i < ansPoolSize; i++) {
            blockDispensers[i] = new BlockDispenser(manager, "Down", banishPoolContainer.get(i), banishPoolContainer.get(i),
                    duplicatePool.get(i), new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            blockDispensers[i].create(new Vector2(xposition, yposition), new Vector2(0.3f, 1.3f), 0);
            System.out.println(banishPoolContainer.get(i) + " many " + duplicatePool.get(i));
            xposition += 5;
            if(xposition == 23) {
                yposition -= 6;
                xposition = -22;
            }
        }
        int j = 0;
        for(int i = ansPoolSize; i < dispenserPoolContainer.size() + ansPoolSize; i++) {
            blockDispensers[i] = new BlockDispenser(manager, "Down", dispenserPoolContainer.get(j), dispenserPoolContainer.get(j),
                    1, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            blockDispensers[i].create(new Vector2(xposition, yposition), new Vector2(0.3f, 1.3f), 0);
            xposition += 5;
            if(xposition == 23) {
                yposition -= 6;
                xposition = -22;
            }
            j++;
        }

        this.jedisaur = character;
    }

    @Override
    public void update(float delta) {

         currentCell = 0;
         for(int i = 0; i < minigameContainer.size(); i++) {
             for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                 if (minigameContainer.get(i).get(j) != null) {
                     if(banishPoolContainer.contains(minigameContainer.get(i).get(j)))
                         blockHolders[i][j].update(delta);
                     else
                         questionBlocks[i][j].update(delta);
                 }
             }
         }

         for(int i = 0; i < banishPoolContainer.size() + dispenserPoolContainer.size(); i++) {
             blockDispensers[i].createBlock(new Vector2(jedisaur.getBody().getPosition().x, jedisaur.getBody().getPosition().y));
         }

         for(int i = 0; i < banishPoolContainer.size() + dispenserPoolContainer.size(); i++) {
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

         for(int i = 0; i < minigameContainer.size(); i++) {
             for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                 if (minigameContainer.get(i).get(j) != null) {
                     if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                         if (blockHolders[i][j].isInContact()) {
                             jedisaur.dropBlock(blockHolders[i][j]);
                             System.out.println("dropped" + blockHolders[i][j]);
                         }
                     }
                 }
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

        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j].render(sprite);
                    }
                    else
                        questionBlocks[i][j].render(sprite);
                }
            }
        }

        for(int i = 0; i < banishPoolContainer.size() + dispenserPoolContainer.size(); i++){
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

        // WILL BE USED, DON'T ERASE
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j].disposeBody();
                    }
                    else
                        questionBlocks[i][j].disposeBody();
                    currentCell++;
                }
            }
        }


        // WILL BE USED, DON'T ERASE

        for(int i = 0; i < banishPoolContainer.size() + dispenserPoolContainer.size(); i++){
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
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()),expertiseLevel);
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
        banishPerRow = manager.getQuestionnaire().getBanishPerRow();
        dispenserPoolContainer = manager.getQuestionnaire().getDispenserPool();
    }

    public ArrayList<String> getAnswerPoolContainer() {
        return dispenserPoolContainer;
    }

    public void setAnswerPoolContainer(ArrayList<String> answerPoolContainer) {
        this.dispenserPoolContainer = answerPoolContainer;
    }

    public void setActive(boolean active){
        System.out.println(minigameContainer.size());
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j].setActive(false);
                    }
                    else
                        questionBlocks[i][j].setActive(false);
                    currentCell++;
                }
            }
        }

//         WILL BE USED, DON'T ERASE

        for(int i = 0; i < banishPoolContainer.size() + dispenserPoolContainer.size(); i++){
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
}
