package com.codex.learning.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.codex.learning.entity.characters.Character;
import com.codex.learning.entity.maps.PlayroomMapS1;
import com.codex.learning.states.State;
import com.codex.learning.states.minigames.Minigame;
import com.codex.learning.utility.decisiontree.Behavior;
import com.codex.learning.utility.decisiontree.DecisionTree;
import com.codex.learning.utility.filereader.Questionnaire;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
//This class is used to initiate all once a used assets to prevent multiple calls.
public class Manager {
    private final World world;
    private final OrthographicCamera camera;
    private final Stack<State> states;
    private Contact cl;
    private Box2DDebugRenderer b2dr;
    private Music music;
    private boolean musicPaused;

    private TextureRegion mainMenu, background;
    private TextureRegion startHouse, playroomStage1, playroomStage2, startSchool, startOffice, playroomStage3;
    private TextureRegion stageSelect, utility;
    private TextureRegion spriteSheet;
    private TextureRegion blockSheet;
    private TextureRegion reportCardSheet;
    private TextureRegion pcStateSheet;
    private TextureRegion settingsStateSheet;

    private TextureRegion pauseStateSheet;
    private Skin skin;
    private TextureAtlas atlas;

    private BitmapFont font;

    private Questionnaire questionnaire;
    private Stage stage;
    private Viewport viewport;
    private StageSelector stageSelector;

    private MinigameChecker minigameChecker;
    private Minigame minigame;
    private PlayroomMapS1 playroomMap;

    private DecisionTree decisionTree;

    private boolean moving;
    private int numberOfBlockInteraction;
    private int hintsIndex;

    private ExpertSystem expertSystem;

    private Dialogue dialogue;
    public Manager(){
        expertSystem = new ExpertSystem();
        expertSystem.readFile();

        questionnaire = new Questionnaire(expertSystem.getExpertiseLevel());

        b2dr = new Box2DDebugRenderer();

        cl = new Contact();
        world = new World(new Vector2(0,0),false);
        world.setContactListener(cl);


        stageSelector = new StageSelector();
        minigameChecker = new MinigameChecker();
        minigame = new Minigame(this);


        background = new TextureRegion(new Texture(Constants.BACKGROUND_PATH));
        mainMenu = new TextureRegion(new Texture(Constants.MENU_TEXT_PATH));

        startHouse = new TextureRegion(new Texture(Constants.HOUSE_PATH));
        startSchool = new TextureRegion(new Texture(Constants.SCHOOL_PATH));
        startOffice = new TextureRegion(new Texture(Constants.OFFICE_PATH));

        playroomStage1 = new TextureRegion(new Texture(Constants.STAGE1_PATH));
        playroomStage2 = new TextureRegion(new Texture(Constants.STAGE2_PATH));
        playroomStage3 = new TextureRegion(new Texture(Constants.STAGE3_PATH));

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));
        utility = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH));
        blockSheet = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH));

        spriteSheet = new TextureRegion(new Texture(Constants.CHARACTER_SHEET_PATH));

        reportCardSheet = new TextureRegion(new Texture(Constants.REPORT_CARD_SHEET_PATH));
        pauseStateSheet = new TextureRegion(new Texture(Constants.PAUSE_STATE_PATH));
        settingsStateSheet = new TextureRegion(new Texture(Constants.SETTINGS_STATE_PATH));
        pcStateSheet = new TextureRegion(new Texture(Constants.PC_SHEET_PATH));

        font = new BitmapFont(Gdx.files.internal(Constants.FONT_STYLE));
        font.getData().setScale(1.2f);


        decisionTree = new DecisionTree();
        decisionTree.createTree();

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        stage = new Stage();
        atlas = new TextureAtlas(Gdx.files.internal(Constants.ATLAS_UTILITY_PATH));
        skin = new Skin(Gdx.files.internal(Constants.JSON_DIALOG_BOX_SKIN_PATH));
        skin.addRegions(atlas);

        states = new Stack<State>();

        dialogue = new Dialogue();

        hintsIndex = 0;
    }


    public void push(State state){
        System.out.println(state + " is pushed");
        states.push(state);
    }
    public void pop(){
        dispose();
        states.pop();
    }

    public void set(State state){
        dispose();
        states.pop();
        states.push(state);
    }

    public void update(float delta){
        b2dr.render(world,camera.combined.scl(Constants.PPM));
        states.peek().update(delta);

    }

    public void render(SpriteBatch sprite){
        states.peek().render(sprite);
    }

    public void dispose(){
        states.peek().dispose();
    }
    public void disposeAll(){
        for(State s : states)
            s.dispose();
        world.dispose();
    }

    public World getWorld() {
        return world;
    }
    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStage() {
        return stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TextureRegion getMainMenu() {
        return mainMenu;
    }
    public TextureRegion getBackground() {
        return background;
    }
    public TextureRegion getStartHouse() {
        return startHouse;
    }
    public TextureRegion getPlayroomStage1() {
        return playroomStage1;
    }

    public TextureRegion getPlayroomStage2() {
        return playroomStage2;
    }

    public TextureRegion getStartSchool() {
        return startSchool;
    }

    public TextureRegion getStartOffice() {
        return startOffice;
    }

    public TextureRegion getPlayroomStage3() {
        return playroomStage3;
    }

    public TextureRegion getStageSelect() {
        return stageSelect;
    }
    public TextureRegion getUtility() {
        return utility;
    }
    public TextureRegion getPcStateSheet() {
        return pcStateSheet;
    }
    public void setPcStateSheet(TextureRegion pcStateSheet) {
        this.pcStateSheet = pcStateSheet;
    }
    public TextureRegion getSpriteSheet() {
        return spriteSheet;
    }
    public TextureRegion getBlockSheet() {
        return blockSheet;
    }
    public BitmapFont getFont() {
        return font;
    }
    public TextureRegion getReportCardSheet(){ return reportCardSheet; }
    public TextureRegion getPauseStateSheet() {
        return pauseStateSheet;
    }
    public TextureRegion getSettingsStateSheet() {return settingsStateSheet;}
    public Contact getCl() {
        return cl;
    }
    public void setCl(Contact cl) {
        this.cl = cl;
    }


    public void setMusic(String file){
        music = Gdx.audio.newMusic(Gdx.files.internal(file));
//        music.play();
        music.setVolume(0.2f);
//        music.setLooping(true);

    }
    public Music getMusic(){
        return music;
    }

    public boolean isMusicPaused() {
        return musicPaused;
    }

    public void setMusicPaused(boolean musicPaused) {
        this.musicPaused = musicPaused;
    }

    public MinigameChecker getMinigameChecker() {
        return minigameChecker;
    }

    public Minigame getMinigame() {
        return minigame;
    }

    public void setMinigame(Minigame minigame) {
        this.minigame = minigame;
    }

    public PlayroomMapS1 getPlayroomMap() {
        return playroomMap;
    }

    public void setPlayroomMap(PlayroomMapS1 playroomMap) {
        this.playroomMap = playroomMap;
    }

    public void setMinigameChecker(MinigameChecker minigameChecker) {
        this.minigameChecker = minigameChecker;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public DecisionTree getDecisionTree() {
        return decisionTree;
    }

    public void setDecisionTree(DecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }

    public StageSelector getStageSelector() {
        return stageSelector;
    }

    public void setStageSelector(StageSelector stageSelector) {
        this.stageSelector = stageSelector;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public String removeBracket(String string){
        StringBuilder stringBuilder = new StringBuilder(string);

        stringBuilder.deleteCharAt(string.length() - 1);
        stringBuilder.deleteCharAt(0);

        return stringBuilder.toString();
    }
    public ArrayList<ArrayList<String>> readDataFirst(){
        try {
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            FileReader fileReader = new FileReader(Constants.DATA_GATHERED_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean header = true;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split(",");
                data.add(new ArrayList<>(Arrays.asList(word)));
            }
            bufferedReader.close();
            fileReader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Stage #, Stage Topic, Cookie Number, Dataset (5), Behavior
    // Input after the game too
    public void writeDataGathering(int stageNumber, String stageTopic, int numberOfCookie){
        try {
            int counter = 0;
            int length = 0;
            ArrayList<String> replace = new ArrayList<>(Arrays.asList(String.valueOf(stageNumber), stageTopic, String.valueOf(numberOfCookie), "YES", "HIGH", "MEDIUM", "LOW", "LOW", "ENGAGED"));
            File file = new File(Constants.DATA_GATHERED_FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            else {
                ArrayList<ArrayList<String>> data = readDataFirst();
                System.out.println(data);

                for(ArrayList<String> i: data){
                    if(i.get(0).equals(String.valueOf(stageNumber))){
                        i.clear();
                        break;
                    }
                    else{
                        counter++;
                    }
                }

                data.add(counter, replace);

                FileWriter fileWriter = new FileWriter(Constants.DATA_GATHERED_FILE_PATH, false);

                for(ArrayList<String> arrayList: data){
                    if(arrayList.isEmpty()){
                        continue;
                    }
                    else{
                        for(String i: arrayList){
                            if(length == arrayList.size()){
                                fileWriter.write("\n");
                                length = 0;
                            }
                            length++;
                            fileWriter.write(i + ",");
                        }
                    }
                }
                fileWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateBehavior(int timer){
        String currentBehavior = "";
        String movement = (isMoving()) ? "YES":"NO";
        String time = checkTimeConsumption(timer);
        ArrayList<String> behavior = new ArrayList<>();

        if(timer > 0 && timer % 10 == 0){
            behavior.add(movement);
            behavior.add(time);
            behavior.add("");
            behavior.add("");
            behavior.add("");
            currentBehavior = String.valueOf(getDecisionTree().classify(behavior, getDecisionTree().getTree()));
            currentBehavior = removeBracket(currentBehavior);

            if(currentBehavior.equals("ENGAGED")){
                //GIVE FEEDBACK REGARDING ENGAGED
                System.out.println(currentBehavior);
                System.out.println("ENGAGED");
            }
            else{
                //GIVE FEEDBACK REGARING NOT ENGAGED
                System.out.println(behavior);
                System.out.println(currentBehavior);
                System.out.println("NOT ENGAGED");
                hintsIndex++;
            }
        }
        behavior.clear();
    }

//    public void checkBehavior(int timer, int numberOfBlockInteract, boolean computerDone, FuzzyLogic fuzzyLogic){
//        String behavior = "";
//        String movement = (isMoving()) ? "YES":"NO";
//        String interact = checkNumberOfBlockInteractionRule(numberOfBlockInteract, computerDone);
//
//        ArrayList<String> dataset = new ArrayList<String>(Arrays.asList(new String[]{"YES", "HIGH", "LOW", "", ""}));
//        if(timer % 4333 == 0 && timer > 0){
//            Behavior.currentDataSet.add(movement);
//            Behavior.currentDataSet.add(fuzzyLogic.getTimeConsumptionRules());
//            Behavior.currentDataSet.add(fuzzyLogic.getNumberOfErrorsRules());
//            Behavior.currentDataSet.add(fuzzyLogic.getNumberOfAttemptsRules());
//            Behavior.currentDataSet.add(interact);
//            behavior = String.valueOf(getDecisionTree().classify(Behavior.currentDataSet, getDecisionTree().getTree()));
////            System.out.println(getDecisionTree().classify(Behavior.currentDataSet, getDecisionTree().getTree()));
//            if(behavior.equals("ENGAGED")){
//                System.out.println(Behavior.currentDataSet);
//                System.out.println("BEHAVIOR = " + behavior);
//                //file write
//            }
//            else{
//                System.out.println(Behavior.currentDataSet);
//                System.out.println("BEHAVIOR = " + behavior);
//                //file write
//            }
//            Behavior.currentDataSet.clear();
//        }
////        System.out.println(getDecisionTree().classify(dataset, getDecisionTree().getTree()));
//    }

    public String checkTimeConsumption(int timer){
        if (timer <= 180){
            return "LOW";
        }
        else if(timer <= 300){
            return "MEDIUM";
        }
        else if(timer >= 300){
            return "HIGH";
        }
        else{
            return "";
        }
    }

    public String checkNumberOfBlockInteractionRule(int numberOfBlockInteraction, boolean computerDone){
        if(computerDone){
            if(numberOfBlockInteraction == 0){
                return "";
            }
            else if(numberOfBlockInteraction <= 10){
                return "LOW";
            }
            else if(numberOfBlockInteraction <= 20){
                return "MEDIUM";
            }
            else{
                return "HIGH";
            }
        }
        return "";
    }

    public void checkIfMoving(Character character){
        if(character.isMoving()){
            setMoving(true);
        }
        else{
            setMoving(false);
        }
    }


    public ExpertSystem getExpertSystem() {
        return expertSystem;
    }

    public void setExpertSystem(ExpertSystem expertSystem) {
        this.expertSystem = expertSystem;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public int getHintsIndex() {
        return hintsIndex;
    }

    public void setHintsIndex(int hintsIndex) {
        this.hintsIndex = hintsIndex;
    }
}