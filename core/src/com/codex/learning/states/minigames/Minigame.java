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
    private boolean fib;
    private Character jedisaur;
    public Minigame(Manager manager, int stageNumber, int currentMinigame, Character jedisaur){
        super(manager);
        this.stageNumber = stageNumber;
        this.currentMinigame = currentMinigame;
        fuzzyLogic = new FuzzyLogic();
        fib = false;
        this.jedisaur = jedisaur;
    }

    public void setMiniGame(){
        switch (currentMinigame){
            case 1:
                fib = true;
                s = new FillInTheBlock(manager);
                System.out.println("1");
                break;
            case 2:
                System.out.println("the minigame is Code-IT");
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

    }

    @Override
    public void render(SpriteBatch sprite) {
        if(fib){
            s.render(sprite);
        }
    }

    @Override
    public void dispose() {

    }
}
