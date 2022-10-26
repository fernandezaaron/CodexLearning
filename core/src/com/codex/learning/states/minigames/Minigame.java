package com.codex.learning.states.minigames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codex.learning.entity.characters.Character;
import com.codex.learning.states.State;
import com.codex.learning.utility.FuzzyLogic;
import com.codex.learning.utility.Manager;
import com.codex.learning.utility.decisiontree.MLDataSet;

import java.io.IOException;
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
    private boolean isEngaged, isNotEngaged;
    private float maxTimer, fuzzyTimer;
    private MLDataSet mlDataSet;


    public Minigame(Manager manager){
        super(manager);
    }

    public void create(int currentMinigame, Character jedisaur, FuzzyLogic fuzzyLogic){
        this.currentMinigame = currentMinigame;
        this.fuzzyLogic = fuzzyLogic;
        mlDataSet = new MLDataSet();
        fillInTheBlockFlag = false;
        mysteryCodeFlag = false;
        codeOrderFlag = false;
        codeITFlag = false;
        this.jedisaur = jedisaur;
        minigameData = new ArrayList<>();
        dataCounter = 0;
        isEngaged = false;
        isNotEngaged = false;
        maxTimer = 60;
        fuzzyTimer = 0;

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
//            case 4:
//                codeITFlag = true;
//                codeIT = new CodeIT(manager,jedisaur, fuzzyLogic);
//                break;
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
//        else if(codeITFlag){
//            codeIT.update(delta);
//        }

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
//        else if(codeITFlag){
//            codeIT.render(sprite);
//        }

    }


    @Override
    public void dispose() {

        if(fillInTheBlockFlag){
            fillInTheBlock.dispose();
        }
        else if(mysteryCodeFlag){
//            System.out.println("disposing mc");
            mysteryCode.dispose();
        }else if(codeOrderFlag){
            codeOrder.dispose();
        }
//        else if(codeITFlag){
//            codeIT.dispose();
//        }
    }

    public void checkBehavior(float timer, Character jedisaur){
        fuzzyTimer += Gdx.graphics.getDeltaTime();
        String currentBehavior = "";
        mlDataSet.setMovement((manager.isMoving()) ? "0":"1");
        mlDataSet.setNumberOfAttempts(mlDataSet.convertNumberOfAttempt(manager.getMinigameChecker().getNumberOfAttempts() - mlDataSet.getCurrentNumberOfAttempts()));
        mlDataSet.setNumberOfBlockInteraction(mlDataSet.checkNumberOfBlockInteractionRule(jedisaur.getNumberOfBlockInteraction() - mlDataSet.getCurrentNumberOfBlockInteraction()));
        if(fuzzyTimer > mlDataSet.getMaxTimer()){
            mlDataSet.setCurrentNumberOfAttempts(manager.getMinigameChecker().getNumberOfAttempts());
            mlDataSet.setCurrentNumberOfBlockInteraction(jedisaur.getNumberOfBlockInteraction());
            fuzzyTimer = 0;
            try {
                currentBehavior = manager.getServer().calculateMLResult(
                        mlDataSet.getMovement() +
                        mlDataSet.checkTimeConsumption((int) timer) +
                        mlDataSet.getNumberOfAttempts() +
                        mlDataSet.getNumberOfBlockInteraction());
                String[] data = currentBehavior.split(",");

                minigameData.add(new ArrayList<String>());
                minigameData.get(dataCounter).add(mlDataSet.getMovement());
                minigameData.get(dataCounter).add(mlDataSet.checkTimeConsumption((int) timer));
                minigameData.get(dataCounter).add(mlDataSet.getNumberOfAttempts());
                minigameData.get(dataCounter).add(mlDataSet.getNumberOfBlockInteraction());
                minigameData.get(dataCounter).add(data[0]);
                minigameData.get(dataCounter).add(data[1]);
                dataCounter++;

                if(data[0].equals("ENGAGED")){
                    setEngaged(true);
                }
                else{
                    setNotEngaged(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void reset(){
        fuzzyLogic.fuzzyReset();
        minigameData = null;
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


    public ArrayList<ArrayList<String>> getMinigameData() {
        return minigameData;
    }

    public void setMinigameData(ArrayList<ArrayList<String>> minigameData) {
        this.minigameData = minigameData;
    }

    public boolean isEngaged() {
        return isEngaged;
    }

    public void setEngaged(boolean engaged) {
        isEngaged = engaged;
    }

    public boolean isNotEngaged() {
        return isNotEngaged;
    }

    public void setNotEngaged(boolean notEngaged) {
        isNotEngaged = notEngaged;
    }

    public String getNumberOfAttemptsRule(){
        return fuzzyLogic.getNumberOfAttemptsRules();
    }

    public String getTimeConsumptionRules(){
        return fuzzyLogic.getTimeConsumptionRules();
    }

    public String getCorrectOutputRulles(){
        return fuzzyLogic.getCorrectOutputRules();
    }

    public String getNumberOfErrorsRules(){
        return fuzzyLogic.getNumberOfErrorsRules();
    }
}
