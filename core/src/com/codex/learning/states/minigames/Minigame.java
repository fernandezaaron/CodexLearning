package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

public class Minigame extends State {
    private FuzzyLogic fuzzyLogic;
    private int stageNumber;
    private int currentMinigame;
    private FillInTheBlock s;
    private MysteryCode h;
    private boolean fib, mc;
    private Character jedisaur;
    public Minigame(Manager manager, int stageNumber, int currentMinigame, Character jedisaur){
        super(manager);
        this.stageNumber = stageNumber;
        this.currentMinigame = currentMinigame;
        fuzzyLogic = new FuzzyLogic();
        fib = false;
        mc = false;
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
                System.out.println("3");
                break;

        }
    }


    @Override
    public void update(float delta) {
        if(fib){
            s.update(delta);
        }
        else if(mc){
            h.update(delta);
        }

    }

    @Override
    public void render(SpriteBatch sprite) {
        if(fib){
            s.render(sprite);
        }
        else if(mc){
            h.render(sprite);
        }
    }


    @Override
    public void dispose() {
        if(fib){
            s.dispose();
        }
        else if(mc){
            h.dispose();
        }
    }

    public void setActive(boolean active){
        if(fib){
            s.setActive(active);
        }
    }
}