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
import java.util.Stack;

public class MysteryCode extends State {

    private Character jedisaur;
    private PlayroomMapS1 playroom;

    private Blocks[] answerBlocks;

    private Blocks[][] questionBlocks, answerBlocksArray;
    private BlockHolder[][] blockHolders;
    private ArrayList<ArrayList<Blocks>> blocksArrayList;

    private PauseState pause;
    float blockSize;

    private ArrayList<ArrayList<String>> minigameContainer;
    private int minigameContainerLimit;
    private Random randomizer;
    private ArrayList<Integer> banishCells;
    private ArrayList<String> answerPoolContainer;
    private ArrayList<ArrayList<Integer>> banishPerRow;
//    private Stack<Stack<Blocks>> blocksArrayList;
    private int currentCell, stage;
    private String stageSelect;
//    private int blockArrayIndex;


    public MysteryCode(Manager manager, Character jedisaur) {
        super(manager);
        pause = new PauseState(manager);
//        playroom = new PlayroomMapS1(manager);

        randomizer = new Random();
        banishCells = new ArrayList<Integer>();
//        blockArrayIndex = 0;

        // WILL BE USED, DON'T ERASE
        questionBlocks = new Blocks[20][20];
        answerBlocksArray = new Blocks[20][20];
        blockHolders = new BlockHolder[20][20];
        blocksArrayList = new ArrayList<>();
        // WILL BE USED, DON'T ERASE

        this.stage = manager.getStageSelector().getStageMap();

        getAMinigame(manager.getStageSelector().map(), manager.getExpertSystem().getExpertiseLevel());

//        for(int i = 0; i < banishPerRow.size(); i++) {
//            int banishNumberIterator = randomizer.nextInt(2) + 1;
//            int numberRepeat = 0;
//            System.out.println("how many? " + banishNumberIterator);
//            for (int j = 0; j < banishPerRow.get(i).size(); j++) {
////                System.out.println((banishPerRow.get(i).size()) + "    " + banishPerRow.get(i).get(0));
//                int banishNumber = randomizer.nextInt(banishPerRow.get(i).size() - 1) + banishPerRow.get(i).get(0);
//                System.out.println(banishNumber);
//                if (banishNumberIterator == 0 || numberRepeat == 5) {
////                    System.out.println("berak");
//                    break;
//                } else if (!banishCells.contains(banishNumber)) {
//                    banishCells.add(banishNumber);
//                    banishNumberIterator--;
////                    System.out.println("baka sa iterator " + banishNumberIterator +
////                            " eh sa last element " + (banishPerRow.get(i).size() - 1) +
////                            " first? " + banishPerRow.get(i).get(0) +
////                            " eto ibabanish " + banishNumber);
//                } else {
//                    System.out.println("number repeated");
//                    numberRepeat++;
//                }
//            }
//        }


        // START MINIGAME CREATION
        int yStartingPoint = 10, currentCell = 0;
        for(int i = 0; i < minigameContainer.size(); i++) {
            blocksArrayList.add(new ArrayList<Blocks>());
            float xStartingPoint = -18.0f;
            for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                if(minigameContainer.get(i).get(j) != null) {
                    float currentStringLength = (float) String.valueOf(minigameContainer.get(i).get(j)).length();
                    if (banishCells.contains(currentCell)) {
                        blockHolders[i][j] = new BlockHolder(manager, "\"" + minigameContainer.get(i).get(j) + "\"");
                        blockHolders[i][j].create(new Vector2(xStartingPoint, yStartingPoint - 0.5f), new Vector2(Constants.BLOCK_HOLDER_WIDTH, Constants.BLOCK_HOLDER_HEIGHT), 0);
                        blocksArrayList.get(i).add(blockHolders[i][j].getCopyBlock());
                        answerPoolContainer.add(minigameContainer.get(i).get(j));
                        xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 1.75f;
                        if(currentStringLength >7){
                            xStartingPoint += Constants.BLOCK_HOLDER_WIDTH + 2.5f;

                        }
                    } else {
                        questionBlocks[i][j] = new Blocks(manager, "\"" + minigameContainer.get(i).get(j) + "\"", minigameContainer.get(i).get(j), true);
                        if (currentStringLength <= 3){
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.4f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
                            blocksArrayList.get(i).add(questionBlocks[i][j]);

                        }

                        else{
                            if(currentStringLength>7){
                                xStartingPoint += 2.75f;
                            }
                            questionBlocks[i][j].create(new Vector2(xStartingPoint, yStartingPoint), new Vector2((currentStringLength * 0.27f), Constants.BLOCKS_HEIGHT), 0);
                            questionBlocks[i][j].setPreDefinedContact(true);
                            blocksArrayList.get(i).add(questionBlocks[i][j]);
                        }
                        xStartingPoint =  questionBlocks[i][j].getDupliSize().x + (questionBlocks[i][j].getBody().getPosition().x) + 1.5f;
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

        float AnsPoolY = 10;
        int currentAnsCell = 0;
        int ansPoolSize = answerPoolContainer.size();
        for(int i = 0; i < ansPoolSize; i++) {
            float AnsPoolX = 13;
            int totalLineLength = 0;
            while (totalLineLength <= 12) {
                float currentStringLength = (float) String.valueOf(answerPoolContainer.get(currentAnsCell)).length();
                totalLineLength += currentStringLength + (AnsPoolX - 11);
                answerBlocks[currentAnsCell] = new Blocks(manager, "\"" + answerPoolContainer.get(currentAnsCell) + "\"", answerPoolContainer.get(currentAnsCell), false);
                if (answerPoolContainer.get(currentAnsCell) != null) {
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

        this.jedisaur = jedisaur;


    }


    @Override
    public void update(float delta) {
        // WILL BE USED, DON'T ERASE
//            playroom.update(delta);
//            if(playroom.getPlayMat().isInContact()){
//                jedisaur.dropBlock(playroom.getPlayMat());
//            }

            currentCell = 0;
               for (int i = 0; i < minigameContainer.size(); i++) {
                   for (int j = 0; j < minigameContainer.get(i).size(); j++) {
                       if (minigameContainer.get(i).get(j) != null) {
                           if (banishCells.contains(currentCell)){
                               blockHolders[i][j].update(delta);
                           }
                           else
                               questionBlocks[i][j].update(delta);
                           currentCell++;
                       }
                   }
               }
            //kung gagamitin mo to remove the blocks[i][j].update muna sa taas pero i havent tried pag magkasabay sila naka on for sure dodoble HAHA
            for (int i = 0; i < answerBlocks.length; i++) {
                if (answerBlocks[i] != null) {
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
                            if(jedisaur.isDropped() && !blocksArrayList.get(i).contains(blockHolders[i][j].getCopyBlock())){
                                    blocksArrayList.get(i).set(j, blockHolders[i][j].getCopyBlock());
                            }
                        }
                    }
                    currentCell++;
                }
            }
        }

        /** below this is used for padding **/
        if(jedisaur.isCarrying()){
            blockSize = jedisaur.getCopyBlock().getDupliSize().x;
        }

        // WILL BE USED, DON'T ERASE
        for (int i = 0; i < answerPoolContainer.size(); i++) {
            if (answerBlocks[i] != null) {
                answerBlocks[i].update(delta);
            }
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

//            playroom.getObjective().render(sprite);


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
//        playroom.dispose();
    }

    public void getAMinigame(String stage, String expertiseLevel){
        manager.getQuestionnaire().minigameDisplay(stage, String.valueOf(manager.getStageSelector().getStageMap()), expertiseLevel);
        minigameContainer = manager.getQuestionnaire().getMinigameHolder();
        minigameContainerLimit = manager.getQuestionnaire().getMinigameLimit();
        banishPerRow = manager.getQuestionnaire().getBanishPerRow();
        answerPoolContainer = manager.getQuestionnaire().getAnswerPool();
    }


}
