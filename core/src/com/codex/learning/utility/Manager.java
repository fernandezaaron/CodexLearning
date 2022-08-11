package com.codex.learning.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.codex.learning.states.State;

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
    private TextureRegion stage1;
    private TextureRegion stageSelect, utility;
    private TextureRegion spriteSheet;
    private TextureRegion blockSheet;
    private TextureRegion reportCardSheet;
    private TextureRegion pcStateSheet;
    private TextureRegion settingsStateSheet;

    private TextureRegion pauseStateSheet;

    private BitmapFont font;
    private Skin skin;

    private DatabaseReader databaseReader;

    private Questionnaire questionnaire;

    public Manager(){

        b2dr = new Box2DDebugRenderer();

        cl = new Contact();
        world = new World(new Vector2(0,0),false);
        world.setContactListener(cl);
        databaseReader = new DatabaseReader();

        questionnaire = new Questionnaire();

        background = new TextureRegion(new Texture(Constants.BACKGROUND_PATH));
        mainMenu = new TextureRegion(new Texture(Constants.MENU_TEXT_PATH));

        stage1 = new TextureRegion(new Texture(Constants.STAGE1_PATH));

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));
        utility = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH));
        blockSheet = new TextureRegion(new Texture(Constants.BLOCK_SHEET_PATH));

        spriteSheet = new TextureRegion(new Texture(Constants.CHARACTER_SHEET_PATH));

        reportCardSheet = new TextureRegion(new Texture(Constants.REPORT_CARD_SHEET_PATH));
        pauseStateSheet = new TextureRegion(new Texture(Constants.PAUSE_STATE_PATH));
        settingsStateSheet = new TextureRegion(new Texture(Constants.SETTINGS_STATE_PATH));
        pcStateSheet = new TextureRegion(new Texture(Constants.PC_SHEET_PATH));

        font = new BitmapFont(Gdx.files.internal(Constants.FONT_STYLE));
        font.getData().scale(0.7f);

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        skin = new Skin(FileHandle.tempFile(Constants.JSON_SKIN_PATH));

        states = new Stack<State>();
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
    public TextureRegion getMainMenu() {
        return mainMenu;
    }
    public TextureRegion getBackground() {
        return background;
    }
    public TextureRegion getStage1() {
        return stage1;
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
    public DatabaseReader getDatabaseReader() {
        return databaseReader;
    }

    public void setDatabaseReader(DatabaseReader databaseReader) {
        this.databaseReader = databaseReader;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    };
    public void setQuestionnaire(Questionnaire questionnaire){
        this.questionnaire = questionnaire;
    }
    public void setMusic(String file){
        music = Gdx.audio.newMusic(Gdx.files.internal(file));
//        music.play();
//        music.setVolume(0.2f);
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

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}