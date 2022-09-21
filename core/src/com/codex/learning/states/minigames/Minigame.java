package com.codex.learning.states.minigames;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;

public class Minigame extends State {
    private FuzzyLogic fuzzyLogic;
    private int currentMinigame;
    private FillInTheBlock fillInTheBlock;
    private CodeOrder codeOrder;
    private boolean fillInTheBlockFlag, mysteryCodeFlag, codeOrderFlag, codeITFlag;
    private Character jedisaur;
    private MysteryCode mysteryCode;
    private CodeIT codeIT;
    private ArrayList<ArrayList<String>> minigameData;
    private int dataCounter;

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
        minigameData = new ArrayList<>();
        dataCounter = 0;
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

    public void checkBehavior(int timer, Character jedisaur){
        String currentBehavior = "";
        String movement = (manager.isMoving()) ? "0":"1";
        String numberOfAttempt = convertNumberOfAttempt(manager.getMinigameChecker().getNumberOfAttempts());
        String numberOfBlockInteraction = (checkNumberOfBlockInteractionRule(jedisaur.getNumberOfBlockInteraction()));
        if(timer > 0 && timer % 10 == 0){
            System.out.println("XD - " + manager.getDtree().minigameML(movement, checkTimeConsumption(timer),
                    numberOfAttempt, numberOfBlockInteraction));
            currentBehavior = manager.getDtree().minigameML(movement, checkTimeConsumption(timer),
                    numberOfAttempt, numberOfBlockInteraction);

            minigameData.add(new ArrayList<String>());
            minigameData.get(dataCounter).add(movement);
            minigameData.get(dataCounter).add(checkTimeConsumption(timer));
            minigameData.get(dataCounter).add(numberOfAttempt);
            minigameData.get(dataCounter).add(numberOfBlockInteraction);
            minigameData.get(dataCounter).add(currentBehavior);
            dataCounter++;

            System.out.println("MINIGAME DATA NA YUN - " + minigameData);

            if(currentBehavior.equals("ENGAGED")){
                System.out.println("WOW keep it up my dudes!!");
            }
            else{
                System.out.println("Haha lungkot mo naman!!");
            }
        }


    }

    public String checkNumberOfBlockInteractionRule(int numberOfBlockInteraction){

        if(numberOfBlockInteraction <= 10)
            return "1";
        else if(numberOfBlockInteraction <= 20)
            return "2";
        return "3";
    }

    public String convertNumberOfAttempt(int numberOfAttempt){
        if(numberOfAttempt <= 1)
            return "1";
        else if(numberOfAttempt <= 3)
            return "2";
        return "3";
    }

    public String checkTimeConsumption(int timeConsumption){
        if(timeConsumption <= 150)
            return "1";
        else if(timeConsumption <= 240)
            return "2";
        return "3";
    }

    public void reset(){
        fuzzyLogic.fuzzyReset();
        minigameData.clear();
        dataCounter = 0;
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

    public ArrayList<ArrayList<String>> getMinigameData() {
        return minigameData;
    }

    public void setMinigameData(ArrayList<ArrayList<String>> minigameData) {
        this.minigameData = minigameData;
    }
}
