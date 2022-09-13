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
    private ArrayList<ArrayList<Blocks>> blocksArrayList;
    private PauseState pause;
    private ArrayList<ArrayList<String>> minigameContainer;
    private Random randomizer;
    private float blockSize, xStartingPoint, currentStringLength;
    private ArrayList<Integer> banishCells, duplicatePool;
    private ArrayList<String> banishPoolContainer, dispenserPoolContainer;
    private int currentCell, minigameContainerLimit, ansPoolSize, xposition, yposition, ansPoolIterator, yStartingPoint;

    public FillInTheBlock(Manager manager, Character character) {
        super(manager);
        playroom = new PlayroomMapS1(manager);
        randomizer = new Random();
        banishCells = new ArrayList<>();
        duplicatePool = new ArrayList<>();
        banishPoolContainer = new ArrayList<>();

        getAMinigame(manager.getStageSelector().map());

        questionBlocks = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        blocksArrayList = new ArrayList<>();

        /** ASSIGN NUMBERS TO BANISH **/
        for(int i = 0; i < 5; i++) {
            int banishNumber = randomizer.nextInt(minigameContainerLimit - 1) + 1;
            if(!banishCells.contains(banishNumber))
                banishCells.add(banishNumber);
        }

        /** ADD SIMILAR CELLS **/
        currentCell = 0;
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

        /** BANISH SIMILAR CELLS **/
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


        /** START OF MINIGAME CREATION **/
        yStartingPoint = 10;
        currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            blocksArrayList.add(new ArrayList<Blocks>());
            xStartingPoint = -18.0f;
            for(int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                        blocksArrayList.get(i).add(blockHolders[i][j].getCopyBlock());
                        dispenserPoolContainer.add(minigameContainer.get(i).get(j));
                        xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
                        if(currentStringLength >7){
                            xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 2.5f;
                        }
                    } else {
                        questionBlocks[i][j] = new Blocks(manager, "\"" + minigameContainer.get(i).get(j) + "\"", minigameContainer.get(i).get(j), true);
                        if (currentStringLength <= 3){
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.4f), Constants.BLOCKS_HEIGHT), 0);
                        }
                        else{
                            if(currentStringLength>7){
                                xStartingPoint += 2.75f;
                            }
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.23f), Constants.BLOCKS_HEIGHT), 0);
                        }
                        questionBlocks[i][j].setPreDefinedContact(true);
                        blocksArrayList.get(i).add(questionBlocks[i][j]);
                        xStartingPoint =  questionBlocks[i][j].getDupliSize().x + (questionBlocks[i][j].getBody().getPosition().x) + 1.5f;
                    }
                    currentCell++;
                }
            }
            yStartingPoint -= 2.5f;
        }
        /** END OF MINIGAME CREATION **/

        /** START OF DISPENSER POOL CREATION **/
        blockDispensers = new BlockDispenser[banishPoolContainer.size() + dispenserPoolContainer.size()];
        ansPoolSize = banishPoolContainer.size();
        System.out.println(ansPoolSize);
        xposition = -22;
        yposition = -9;
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

        ansPoolIterator = 0;
        for(int i = ansPoolSize; i < dispenserPoolContainer.size() + ansPoolSize; i++) {
            blockDispensers[i] = new BlockDispenser(manager, "Down", dispenserPoolContainer.get(ansPoolIterator), dispenserPoolContainer.get(ansPoolIterator),
                    1, new Vector2(Constants.BLOCKS_BRACE_WIDTH, Constants.BLOCKS_HEIGHT));
            blockDispensers[i].create(new Vector2(xposition, yposition), new Vector2(0.3f, 1.3f), 0);
            xposition += 5;
            if(xposition == 23) {
                yposition -= 6;
                xposition = -22;
            }
            ansPoolIterator++;
        }
        /** END OF DISPENSER POOL CREATION **/

        this.jedisaur = character;
    }

    @Override
    public void update(float delta) {
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

        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        if (blockHolders[i][j].isInContact()) {
                            jedisaur.dropBlock(blockHolders[i][j]);
                            if(jedisaur.isDropped() && !blocksArrayList.get(i).contains(blockHolders[i][j].getCopyBlock())){
                                blocksArrayList.get(i).set(j, blockHolders[i][j].getCopyBlock());
                            }
                        }
                    }
                }
            }
        }

        /** below this is used for padding **/
        if(jedisaur.isCarrying()){
            blockSize = jedisaur.getCopyBlock().getDupliSize().x;
        }

        currentCell = 0;
        for (int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if (banishCells.contains(currentCell)) {
                        if (blockHolders[i][j].isInContact()) {
                            if(jedisaur.isDropped()){

                                /** tempCurrentCell is used for a local iteration of currentcell
                                 since currentcell iterates the whole indeces from 1-N **/

                                int tempCurrentCell = currentCell;

                                /** left iteration of blocks **/
                                for(int k=j-1; k>=0; k--){
                                    tempCurrentCell--;
                                    /** checks if the index is a blockholder or if it is inside the arraylist **/
                                    if(banishCells.contains(tempCurrentCell)){
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x - blockSize+0.5f, questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                }

                                /** for the blocks occupying the blockholders
                                 * it iterates from the max value of the arraylist(i), and finds the
                                 * blockholders from the leftside of the currentCell
                                 * if it is true and the blockholder is occupied, transform the copyblocks of blockholders stored in the arraylist **/
                                tempCurrentCell = currentCell;
                                for(int k=j-1; k>=0; k--){
                                    tempCurrentCell--;
                                    if(banishCells.contains(tempCurrentCell)){
                                        if(blockHolders[i][k].isOccupied()){
                                            blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize + 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);

                                        }
                                    }
                                }

                                /** right blocks in the blockholders **/
                                tempCurrentCell = currentCell;
                                for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                    tempCurrentCell++;
                                    if(banishCells.contains(tempCurrentCell)){
                                        if(blockHolders[i][k].isOccupied()){
                                            blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);

                                        }

                                    }
                                }

                                /** right iteration of blocks **/
                                tempCurrentCell = currentCell;
                                for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                    tempCurrentCell++;
                                    if(banishCells.contains(tempCurrentCell)){
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x + blockSize-0.5f , questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                }
                                jedisaur.setDropped(false);
                            }

                            /** made a new boolean flag for whenever jedisaur HAS PICKEDUP
                             because if jedisaur ISCARRYING it will always return to true thus will
                             manipulate the x-axis whenever the user carries a block
                             **/

                            if(jedisaur.isPickedUp()){
                                int tempCurrentCell = currentCell;
                                for(int k=j-1; k>=0; k--){
                                    tempCurrentCell--;
                                    if(banishCells.contains(tempCurrentCell)){
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x + blockSize-0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x + blockSize-0.5f, questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                }


                                tempCurrentCell = currentCell;
                                for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                    tempCurrentCell++;
                                    if(banishCells.contains(tempCurrentCell)){
                                        blockHolders[i][k].getBody().setTransform(blockHolders[i][k].getBody().getPosition().x - blockSize+0.5f, blockHolders[i][k].getBody().getPosition().y, 0);
                                    }
                                    else{
                                        questionBlocks[i][k].getBody().setTransform(questionBlocks[i][k].getBody().getPosition().x - blockSize+0.5f , questionBlocks[i][k].getBody().getPosition().y, 0);
                                    }
                                }

                                /** for the blocks occupying the blockholders
                                 * it iterates from the max value of the arraylist(i), and finds the
                                 * blockholders from the leftside of the currentCell
                                 * if it is true and the blockholder is occupied, transform the copyblocks of blockholders stored in the arraylist **/
                                tempCurrentCell = currentCell;
                                for(int k=j-1; k>=0; k--){
                                    tempCurrentCell--;
                                    if(banishCells.contains(tempCurrentCell)){
                                        if(blockHolders[i][k].isOccupied()){
                                            blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x + blockSize - 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                        }
                                    }
                                }

                                /** right blocks in the blockholders **/
                                tempCurrentCell = currentCell;
                                for(int k=j+1; k<minigameContainer.get(i).size(); k++){
                                    tempCurrentCell++;
                                    if(banishCells.contains(tempCurrentCell)){
                                        if(blockHolders[i][k].isOccupied()){
                                            blocksArrayList.get(i).get(k).getBody().setTransform(blocksArrayList.get(i).get(k).getBody().getPosition().x - blockSize + 0.5f, blockHolders[i][k].getBody().getPosition().y+0.5f, 0);
                                        }
                                    }
                                }
                                jedisaur.setPickedUp(false);
                            }
                        }
                    }
                    currentCell++;
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
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j].disposeBody();
                    }
                    else
                        questionBlocks[i][j].disposeBody();
                }
            }
        }

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

    public void getAMinigame(String stage){
        manager.getQuestionnaire().minigameDisplay(stage,String.valueOf(manager.getStageSelector().getStageMap()));
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
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
        for(int i = 0; i < minigameContainer.size(); i++) {
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if (minigameContainer.get(i).get(j) != null) {
                    if(banishPoolContainer.contains(minigameContainer.get(i).get(j))) {
                        blockHolders[i][j].setActive(false);
                    }
                    else
                        questionBlocks[i][j].setActive(false);
                }
            }
        }

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
