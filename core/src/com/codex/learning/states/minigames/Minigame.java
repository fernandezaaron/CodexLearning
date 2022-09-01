package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.entity.blocks.Blocks;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

public class Minigame extends State {
    private FuzzyLogic fuzzyLogic;
    private int currentMinigame;
    private FillInTheBlock s;
    private sample sample;
    private FillInTheBlock h;
    private boolean fib, mc, samplebool;
    private Character jedisaur;

    public Minigame(Manager manager, int currentMinigame, Character jedisaur){
        super(manager);
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
                s = new FillInTheBlock(manager, jedisaur);
                System.out.println("1");
                break;
            case 2:
                mc = true;
                h = new FillInTheBlock(manager, jedisaur);

                break;
            case 3:
                samplebool = true;
                sample = new sample(manager, jedisaur);
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

}
