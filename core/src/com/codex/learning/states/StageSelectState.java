package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.codex.learning.states.minigames.CodeOrder;

import com.codex.learning.states.minigames.FillInTheBlock;
import com.codex.learning.states.minigames.MysteryCode;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.DialogueBox;
import com.codex.learning.utility.Manager;
import com.codex.learning.utility.StageSelector;

//This class is used to have a response in the circles in stage select stage.
public class StageSelectState extends State {
    private TextureRegion orangeCircle, grayCircle;
    private TextureRegion noCookie, oneCookie, twoCookies, threeCookies;
    private TextureRegion[] currentCookie;
    private Vector3 touchpoint;
    private Circle[] stages;
    private Table contentTable;
    private DialogueBox dialogueBox;
    private Rectangle pauseButtonBounds;
    private ImageButton pauseButton;
    private boolean isTouched;
    private int stageNumber;

    private boolean zeroCookie;

    public StageSelectState(Manager manager) {
        super(manager);
        stages = new Circle[16];
        stageNumber = 0;

        for (int i = 0; i < stages.length; i++) {
            manager.getStageSelector().setNumberOfCookies(i, manager.getExpertSystem().getCookies(i));
        }

        zeroCookie = false;
        currentCookie = new TextureRegion[16];


        orangeCircle = new TextureRegion(manager.getUtility(), Constants.ORANGE_CIRCLE_X, Constants.ORANGE_CIRCLE_Y, Constants.ORANGE_CIRCLE_R, Constants.ORANGE_CIRCLE_R);
        grayCircle = new TextureRegion(manager.getUtility(), Constants.GRAY_CIRCLE_X, Constants.GRAY_CIRCLE_Y, Constants.GRAY_CIRCLE_R, Constants.GRAY_CIRCLE_R);
        noCookie = new TextureRegion(manager.getUtility(), Constants.COOKIES_X, Constants.COOKIES_ZERO_Y, Constants.COOKIES_WIDTH, Constants.COOKIES_HEIGHT);
        oneCookie = new TextureRegion(manager.getUtility(), Constants.COOKIES_X, Constants.COOKIES_ONE_Y, Constants.COOKIES_WIDTH, Constants.COOKIES_HEIGHT);
        twoCookies = new TextureRegion(manager.getUtility(), Constants.COOKIES_X, Constants.COOKIES_TWO_Y, Constants.COOKIES_WIDTH, Constants.COOKIES_HEIGHT);
        threeCookies = new TextureRegion(manager.getUtility(), Constants.COOKIES_X, Constants.COOKIES_THREE_Y, Constants.COOKIES_WIDTH, Constants.COOKIES_HEIGHT);


        touchpoint = new Vector3();
        stages[0] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_1_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_1_Y, Constants.STAGE_RADIUS);
        stages[1] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_2_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_2_Y, Constants.STAGE_RADIUS);
        stages[2] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_3_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_3_Y, Constants.STAGE_RADIUS);
        stages[3] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_4_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_4_Y, Constants.STAGE_RADIUS);
        stages[4] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_5_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_5_Y, Constants.STAGE_RADIUS);
        stages[5] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_6_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_6_Y, Constants.STAGE_RADIUS);
        stages[6] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_7_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_7_Y, Constants.STAGE_RADIUS);
        stages[7] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_8_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_8_Y, Constants.STAGE_RADIUS);
        stages[8] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_9_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_9_Y, Constants.STAGE_RADIUS);
        stages[9] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_10_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_10_Y, Constants.STAGE_RADIUS);
//        stages[10] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH/2 + Constants.STAGE_1_11_X,
//                manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2 + Constants.STAGE_1_11_Y, Constants.STAGE_RADIUS);
        stages[10] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_12_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_12_Y, Constants.STAGE_RADIUS);
        stages[11] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_13_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_13_Y, Constants.STAGE_RADIUS);
        stages[12] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_14_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_14_Y, Constants.STAGE_RADIUS);
        stages[13] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_15_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_15_Y, Constants.STAGE_RADIUS);
        stages[14] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_16_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_16_Y, Constants.STAGE_RADIUS);
        stages[15] = new Circle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2 + Constants.STAGE_1_17_X,
                manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2 + Constants.STAGE_1_17_Y, Constants.STAGE_RADIUS);


        contentTable = new Table(manager.getSkin());
        dialogueBox = new DialogueBox(manager.getSkin(), "dialogbox3", manager.getStage().getWidth() / 32 * 0.024f);
        isTouched = false;
        pauseButton = new ImageButton(manager.getSkin(), "PauseButton");
        pauseButtonBounds = new Rectangle(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2f + 50, manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2f + 795, 49,60);


        if (!manager.isMusicPaused()) {
            manager.setMusic(Constants.STAGE_SELECT_MUSIC);
            manager.getMusic().play();
            manager.getMusic().setLooping(true);
        } else {
            manager.setMusic(Constants.HOUSE_MUSIC);
        }
    }

    @Override
    public void update(float delta) {
        input();

        dialogueBox.act(delta);

    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(manager.getStageSelect(), manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2f, manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
//        pauseButton.draw(sprite, 1);
//        pauseButton.setPosition(manager.getCamera().position.x - Constants.SCREEN_WIDTH / 2f + 500, manager.getCamera().position.y - Constants.SCREEN_HEIGHT / 2f + 600);
//        pauseButton.addListener(new InputListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
//                manager.set(new MenuState(manager));
//                return true;
//            }
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
//
//            }
//        });
        drawCookies(sprite);
        drawObject(sprite);

        if(isTouched){
            drawTable(sprite);
            isTouched = false;
        }else {
            dialogueBox.setOpen(false);
        }
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void createTable(String text) {
        if(!dialogueBox.isOpen()){
            dialogueBox.textAnimation(text);
        }

        if (!contentTable.hasChildren()) {
            contentTable.defaults().width(0.38f * manager.getStage().getWidth()).height(0.08f * manager.getStage().getHeight());
            contentTable.add(dialogueBox).width(0.38f * manager.getStage().getWidth()).height(0.08f * manager.getStage().getHeight());
            contentTable.setPosition(manager.getCamera().position.x - manager.getCamera().viewportWidth/3f + 500,
                    manager.getCamera().position.y - manager.getCamera().viewportHeight/2 + 500);
        }


    }

    public void drawCookies(SpriteBatch sprite) {
        for (int i = 0; i < stages.length; i++) {
            if (manager.getStageSelector().getNumberOfCookies(i) == 0) {
                currentCookie[i] = noCookie;

                if (!zeroCookie) {
                    manager.getStageSelector().setAllowToPlay(i, true);
                    zeroCookie = true;
                }
            } else if (manager.getStageSelector().getNumberOfCookies(i) == 1) {
                currentCookie[i] = oneCookie;
                manager.getStageSelector().setAllowToPlay(i, true);
            } else if (manager.getStageSelector().getNumberOfCookies(i) == 2) {
                currentCookie[i] = twoCookies;
                manager.getStageSelector().setAllowToPlay(i, true);
            } else if (manager.getStageSelector().getNumberOfCookies(i) == 3) {
                currentCookie[i] = threeCookies;
                manager.getStageSelector().setAllowToPlay(i, true);
            }
            sprite.draw(currentCookie[i], stages[i].x - Constants.ORANGE_CIRCLE_R / 2 - 13,
                    (stages[i].y - Constants.ORANGE_CIRCLE_R / 2) + 75, Constants.COOKIES_WIDTH, Constants.COOKIES_HEIGHT);
        }
    }

    public void input() {
        if (Gdx.input.justTouched()) {
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(pauseButtonBounds.contains(touchpoint.x, touchpoint.y)){
                manager.getMusic().stop();
                manager.set(new MenuState(manager));
            }
              // LATEST ZERO COOKIES MUST BE PLAYABLE
            for (int i = 0; i < stages.length; i++) {

                if (manager.getStageSelector().getAllowToPlay(i)) {
                    if (stages[i].contains(touchpoint.x, touchpoint.y)) {
                        manager.getMusic().stop();
                        manager.getStageSelector().setStageMap(i + 1);
                        manager.getStageSelector().setCurrentStage(i);
                        manager.set(new PlayState(manager));


                        // ITO COMMENT OUT TO COMPARE
//                    manager.getReader().getQuestions("Easy","Stage 1","");
                        // ITO COMMENT OUT TO COMPARE
                    }
                }
            }
        }
    }

    public void drawObject(SpriteBatch sprite) {
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        for (int i = 0; i < stages.length; i++) {
            sprite.draw(orangeCircle, stages[i].x - Constants.ORANGE_CIRCLE_R / 2,
                    stages[i].y - Constants.ORANGE_CIRCLE_R / 2, Constants.ORANGE_CIRCLE_R, Constants.ORANGE_CIRCLE_R);
//            sprite.draw(orangeCircle, (manager.getCamera().position.x - Constants.SCREEN_WIDTH/2f) + stages[i].x - Constants.ORANGE_CIRCLE_R / 2,
//                    (manager.getCamera().position.y - Constants.SCREEN_HEIGHT/2f) +  stages[i].y - Constants.ORANGE_CIRCLE_R / 2, Constants.ORANGE_CIRCLE_R, Constants.ORANGE_CIRCLE_R);
            if (manager.getStageSelector().getAllowToPlay(i)) {
                if (stages[i].contains(touchpoint.x, touchpoint.y)) {
                    isTouched = true;
                    sprite.draw(grayCircle, stages[i].x - Constants.GRAY_CIRCLE_R / 2,
                            stages[i].y - Constants.GRAY_CIRCLE_R / 2, Constants.GRAY_CIRCLE_R, Constants.GRAY_CIRCLE_R);

                }
            }
        }
    }

    public void drawTable(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        for (int i = 0; i < stages.length; i++) {
            if (manager.getStageSelector().getAllowToPlay(i)) {
                if (stages[i].contains(touchpoint.x, touchpoint.y)) {
                    createTable(getTopic(i+1));
                    contentTable.draw(sprite,1);
                }
            }
        }
    }

    public String getTopic(int stageNumber) {
        switch (stageNumber) {
            case 1:
                return "Syntax and Comments";
            case 2:
                return "Variables, Data Types, and Type Casting";
            case 3:
                return "Operators";
            case 4:
                return "Syntax, Comments, Variables, Data Types, Type Casting, Operators";
            case 5:
                return "Conditional";
            case 6:
                return "Loops";
            case 7:
                return "Arrays";
            case 8:
                return "Methods";
            case 9:
                return "Parameters, Parameter Overloading";
            case 10:
                return "Conditional, Loops, Arrays, Methods, Parameters, Parameter Overloading";
            case 11:
                return "Classes";
            case 12:
                return "Objects";
            case 13:
                return "Classes, Objects";
            default:
                return "Every lesson ever";
        }
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
}
