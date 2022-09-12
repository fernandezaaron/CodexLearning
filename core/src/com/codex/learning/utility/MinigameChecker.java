package com.codex.learning.utility;

import com.codex.learning.entity.blocks.BlockHolder;
import com.codex.learning.entity.characters.NPC;
import com.codex.learning.states.PlayState;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.states.minigames.MysteryCode;
import org.graalvm.compiler.nodes.cfg.Block;

public class MinigameChecker {
    private BlockHolder[][] blockHolders;
    private int numberOfErrors;
    private boolean correctOutput;

    public MinigameChecker() {
        blockHolders = new BlockHolder[20][20];
        numberOfErrors = 0;
        correctOutput = false;
    }

    /** CHECKS EACH BLOCK HOLDER IF CORRECT ID **/
    public void minigameCheck() {
        for(int i = 0; i < blockHolders.length; i++) {
            for(int j = 0; j < blockHolders[i].length; j++) {
                if(blockHolders[i][j] != null) {
                    numberOfErrors += blockHolders[i][j].getBody().checkErrors();
                    System.out.println(numberOfErrors);
                }
            }
        }
    }

    /** CHECKS CORRECT OUTPUT THROUGH NUMBER OF ERRORS **/
    public boolean correctOutputCheck() {
        if(numberOfErrors == 0) {
            correctOutput = true;
        }
        else {
            correctOutput = false;
        }
        return correctOutput;
    }

    public void setBlockHolders(BlockHolder[][] blockHolders) {
        this.blockHolders = blockHolders;
    }

    public BlockHolder[][] getBlockHolders() {
        return blockHolders;
    }
}
