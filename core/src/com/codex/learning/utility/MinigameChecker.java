package com.codex.learning.utility;

import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;

public class MinigameChecker {
    private BlockHolder[][] blockHolders;
    private BlockHolder[] blockOrder;
    private Blocks[][] updateBlocks;
    private Blocks[] updateOrder;
    private boolean done;
    private int numberOfErrors;
    private boolean correctOutput;
    private int numberOfAttempts;

    public MinigameChecker() {
        numberOfErrors = 0;
        correctOutput = false;
        done = false;
        updateBlocks = new Blocks[20][20];
        updateOrder = new Blocks[20];
    }

    /** CHECKS EACH BLOCK HOLDER IF CORRECT ID **/
    public void minigameCheck() {
        if(blockHolders == null) {
            for(int i = 0; i < blockOrder.length; i++) {
                if(blockOrder[i] != null) {
                    blockOrder[i].setCopyBlock(updateOrder[i]);
                    numberOfErrors += blockOrder[i].checkErrors();
                    System.out.println(numberOfErrors + " errors ");
                }
            }
        }
        else {
            for (int i = 0; i < blockHolders.length; i++) {
                for (int j = 0; j < blockHolders[i].length; j++) {
                    if (blockHolders[i][j] != null) {
                        blockHolders[i][j].setCopyBlock(updateBlocks[i][j]);
                        numberOfErrors += blockHolders[i][j].checkErrors();
                        System.out.println(numberOfErrors + " errors ");
                    }
                }
            }
        }
    }

    /** CHECKS CORRECT OUTPUT THROUGH NUMBER OF ERRORS **/
    public boolean correctOutputCheck() {
        if(numberOfErrors == 0) {
            correctOutput = true;
            numberOfAttempts++;
        }
        else {
            correctOutput = false;
            numberOfAttempts++;
        }
        numberOfErrors = 0;
        return correctOutput;
    }

//    public void dropCopyBlock(Blocks blocks) {
//        for(int i = 0; i < blockHolders.length; i++) {
//            for (int j = 0; j < blockHolders[i].length; j++) {
//                if(blockHolders[i][j] != null) {
//                    if(blockHolders[i][j].getCopyBlock() != null) {
//                        updateBlocks[i][j] = blocks;
//                    }
//                }
//            }
//        }
//    }

    public void pickUpCopyBlock(Blocks blocks, int i, int j) {
        updateBlocks[i][j] = blocks;
    }

    public void dropCopyBlock(Blocks blocks, int i, int j) {
        updateBlocks[i][j] = blocks;
    }

    public void setBlockHolders(BlockHolder[][] blockHolders) {
        for(int i = 0; i < blockHolders.length; i++) {
            for (int j = 0; j < blockHolders[i].length; j++) {
                if(blockHolders[i][j] != null) {
                    if(blockHolders[i][j].getCopyBlock() != null) {
                        blockHolders[i][j].setCopyBlock(blockHolders[i][j].getCopyBlock());
                    }
                }
            }
        }
        this.blockHolders = blockHolders;
    }

    public void setBlockOrder(BlockHolder[] blockOrder) {
        for(int i = 0; i < blockOrder.length; i++) {
            if(blockOrder[i] != null) {
                if(blockOrder[i].getCopyBlock() != null) {
                    blockOrder[i].setCopyBlock(blockOrder[i].getCopyBlock());
                }
            }
        }
        this.blockOrder = blockOrder;
    }

    public void pickUpOrderBlock(Blocks blocks, int i) {
        updateOrder[i] = blocks;
    }

    public void dropOrderBlock(Blocks blocks, int i) {
        updateOrder[i] = blocks;
    }

    public BlockHolder[][] getBlockHolders() {
        return blockHolders;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public boolean isCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(boolean correctOutput) {
        this.correctOutput = correctOutput;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
