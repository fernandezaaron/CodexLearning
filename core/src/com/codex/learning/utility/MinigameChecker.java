package com.codex.learning.utility;

import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.states.PlayState;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.states.minigames.MysteryCode;
import org.graalvm.compiler.nodes.cfg.Block;

import java.util.ArrayList;

public class MinigameChecker {
    private BlockHolder[][] blockHolders;
    private Blocks[][] updateBlocks;
    private boolean done;
    private int numberOfErrors;
    private boolean correctOutput;
    private int numberOfAttempts;

    public MinigameChecker() {
        numberOfErrors = 0;
        correctOutput = false;
        done = false;
        updateBlocks = new Blocks[20][20];
    }

    /** CHECKS EACH BLOCK HOLDER IF CORRECT ID **/
    public void minigameCheck() {
        for(int i = 0; i < blockHolders.length; i++) {
            for(int j = 0; j < blockHolders[i].length; j++) {
                if(blockHolders[i][j] != null) {
                    blockHolders[i][j].setCopyBlock(updateBlocks[i][j]);
                    System.out.println(blockHolders[i][j].getCorrectID());
                    numberOfErrors += blockHolders[i][j].checkErrors();
                    System.out.println(numberOfErrors + " errors ");
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
        return correctOutput;
    }

    public void dropCopyBlock(Blocks blocks) {
        for(int i = 0; i < blockHolders.length; i++) {
            for (int j = 0; j < blockHolders[i].length; j++) {
                if(blockHolders[i][j] != null) {
                    if(blockHolders[i][j].getCopyBlock() != null) {
                        updateBlocks[i][j] = blocks;
                    }
                }
            }
        }
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
