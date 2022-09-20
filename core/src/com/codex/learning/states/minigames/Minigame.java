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
    private FillInTheBlock fillInTheBlock;
    private sample sample;
    private CodeOrder codeOrder;
    private boolean fillInTheBlockFlag, mysteryCodeFlag, codeOrderFlag, codeITFlag;
    private Character jedisaur;
    private MysteryCode mysteryCode;
    private CodeIT codeIT;

//    public Minigame(Manager manager, int currentMinigame, Character jedisaur, FuzzyLogic fuzzyLogic){
//        super(manager);
//
//    }

    public Minigame(Manager manager){
        super(manager);

    }

    public void create(int currentMinigame, Character jedisaur, FuzzyLogic fuzzyLogic){
        this.currentMinigame = currentMinigame;
        this.fuzzyLogic = fuzzyLogic;
        fillInTheBlockFlag = false;
        mysteryCodeFlag = false;
        codeOrderFlag = false;
        codeITFlag = false;
        this.jedisaur = jedisaur;
    }


    public void setMiniGame(){
        switch (currentMinigame){
            case 1:
                fillInTheBlockFlag = true;
                fillInTheBlock = new FillInTheBlock(manager, jedisaur, fuzzyLogic);
                break;
            case 2:
                mysteryCodeFlag = true;
                mysteryCode = new MysteryCode(manager, jedisaur, fuzzyLogic);
                break;
            case 3:
                codeOrderFlag = true;
                codeOrder = new CodeOrder(manager, jedisaur, fuzzyLogic);
                break;
            case 4:
                codeITFlag = true;
                codeIT = new CodeIT(manager,jedisaur, fuzzyLogic);
                break;
        }
    }

    @Override
    public void update(float delta) {
        if(fillInTheBlockFlag){
            fillInTheBlock.update(delta);
        }
        else if(mysteryCodeFlag){
            mysteryCode.update(delta);
        }else if(codeOrderFlag){
            codeOrder.update(delta);
        }
        else if(codeITFlag){
            codeIT.update(delta);
        }

    }

    @Override
    public void render(SpriteBatch sprite) {
        if(fillInTheBlockFlag){
            fillInTheBlock.render(sprite);
        }
        else if(mysteryCodeFlag){
            mysteryCode.render(sprite);
        }else if(codeOrderFlag){
            codeOrder.render(sprite);
        }
        else if(codeITFlag){
            codeIT.render(sprite);
        }

    }


    @Override
    public void dispose() {

        if(fillInTheBlockFlag){
            fillInTheBlock.dispose();
        }
        else if(mysteryCodeFlag){
            System.out.println("disposing mc");
            mysteryCode.dispose();
        }else if(codeOrderFlag){
            codeOrder.dispose();
        }
        else if(codeITFlag){
            codeIT.dispose();
        }
    }




    public void fuzzyReset(){
        fuzzyLogic.fuzzyReset();
    }

    public int getCurrentMinigame() {
        return currentMinigame;
    }

    public void setCurrentMinigame(int currentMinigame) {
        this.currentMinigame = currentMinigame;
    }

    public int getNumberOfAttempts(){
        return fuzzyLogic.getNumberOfAttempts();
    }

    public int getNumberofError(){
        return fuzzyLogic.getNumberOfErrors();
    }

    public float getTimeConsumption(){
        return  fuzzyLogic.getTimeConsumptions();
    }

    public int getCorrectOutput(){
        return fuzzyLogic.getCorrectOutput();
    }

    public int getCookies(){
        return fuzzyLogic.getCookies();
    }

    //    public void setActive(boolean active){
//        if(fib){
//            s.setActive(active);
//        }
//    }

}
