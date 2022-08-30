package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

public class Minigame extends State {
    private FuzzyLogic fuzzyLogic;
    private int stageNumber;
    private int currentMinigame;
    private FillInTheBlock s;
    private sample sample;
    private MysteryCode h;
    private boolean fib, mc, samplebool;
    private Character jedisaur;
    private Blocks[] answerBlock;

    public Minigame(Manager manager, int stageNumber, int currentMinigame, Character jedisaur){
        super(manager);
        this.stageNumber = stageNumber;
        this.currentMinigame = currentMinigame;
        fuzzyLogic = new FuzzyLogic();
        fib = false;
        mc = false;
        this.jedisaur = jedisaur;
    }

    public Minigame(Manager manager, int stageNumber, int currentMinigame){
        super(manager);
        this.stageNumber = stageNumber;
        this.currentMinigame = currentMinigame;
        fuzzyLogic = new FuzzyLogic();
        fib = false;
         samplebool = false;
        mc = false;
    }

    public Character getJedisaur() {
        return jedisaur;
    }

    public void setJedisaur(Character jedisaur) {
        this.jedisaur = jedisaur;
    }

    public void setMiniGame(){
        switch (currentMinigame){
            case 1:
                fib = true;
                s = new FillInTheBlock(manager, stageNumber, jedisaur);
                System.out.println("1");
                break;
            case 2:
                mc = true;
                h = new MysteryCode(manager, stageNumber, jedisaur);

                break;
            case 3:
                samplebool = true;
                sample = new sample(manager, jedisaur);
                System.out.println("3");
                break;

        }
    }

    public Blocks[] getBlocks(){
        if(mc){
            return h.getAnswerBlocks();
        }

        return h.getAnswerBlocks();
    }


    @Override
    public void update(float delta) {
        if(fib){
            s.update(delta);
        }
        else if(mc){
            h.update(delta);
//            h.setJedisaur(getJedisaur());
        }else if(samplebool){
            sample.update(delta);
        }

    }

    @Override
    public void render(SpriteBatch sprite) {
        if(fib){
            s.render(sprite);
        }
        else if(mc){
            h.render(sprite);
        }else if(samplebool){
            sample.render(sprite);
        }
    }


    @Override
    public void dispose() {
        if(fib){
            s.dispose();
        }
        else if(mc){
            h.dispose();
        }else if(samplebool){
            sample.dispose();
        }
    }

    public void setActive(boolean active){
        if(fib){
            s.setActive(active);
        }
    }

    public Blocks[] getAnswerBlock() {
        return answerBlock;
    }

    public void setAnswerBlock(Blocks[] answerBlock) {
        this.answerBlock = answerBlock;
    }
}
