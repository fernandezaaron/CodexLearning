package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

public class Minigame extends State {
    private FuzzyLogic fuzzyLogic;
    private int stageNumber;
    private int currentMinigame;
    public Minigame(Manager manager, int stageNumber, int currentMinigame){
        super(manager);
        this.stageNumber = stageNumber;

        fuzzyLogic = new FuzzyLogic();

        switch (currentMinigame){
            case 1:
                FillInTheBlock fib = new FillInTheBlock(manager);
                manager.set(fib);

                break;


        }
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch sprite) {

    }

    @Override
    public void dispose() {

    }
}
