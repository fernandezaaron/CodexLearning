package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class StageSelectState extends State{

    private TextureRegion stageSelect, utility;
    private TextureRegion orangeCircle, grayCircle;
    private Vector3 touchpoint;
    private Circle stages[] = new Circle[17];

    public StageSelectState(Manager manager){
        super(manager);

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));
        utility = new TextureRegion(new Texture(Constants.UTILITY_SHEET_PATH));

        orangeCircle = new TextureRegion(utility, Constants.ORANGE_CIRCLE_X, Constants.ORANGE_CIRCLE_Y, Constants.CIRCLE_R, Constants.CIRCLE_R);
        grayCircle = new TextureRegion(utility, Constants.GRAY_CIRCLE_X, Constants.GRAY_CIRCLE_Y, Constants.CIRCLE_R, Constants.CIRCLE_R);

        touchpoint = new Vector3();
        stages[0] = new Circle(Constants.STAGE_1_1_X, Constants.STAGE_1_1_Y, Constants.STAGE_RADIUS);
        stages[1] = new Circle(Constants.STAGE_1_2_X, Constants.STAGE_1_2_Y, Constants.STAGE_RADIUS);
        stages[2] = new Circle(Constants.STAGE_1_3_X, Constants.STAGE_1_3_Y, Constants.STAGE_RADIUS);
        stages[3] = new Circle(Constants.STAGE_1_4_X, Constants.STAGE_1_4_Y, Constants.STAGE_RADIUS);
        stages[4] = new Circle(Constants.STAGE_1_5_X, Constants.STAGE_1_5_Y, Constants.STAGE_RADIUS);
        stages[5] = new Circle(Constants.STAGE_1_6_X, Constants.STAGE_1_6_Y, Constants.STAGE_RADIUS);
        stages[6] = new Circle(Constants.STAGE_1_7_X, Constants.STAGE_1_7_Y, Constants.STAGE_RADIUS);
        stages[7] = new Circle(Constants.STAGE_1_8_X, Constants.STAGE_1_8_Y, Constants.STAGE_RADIUS);
        stages[8] = new Circle(Constants.STAGE_1_9_X, Constants.STAGE_1_9_Y, Constants.STAGE_RADIUS);
        stages[9] = new Circle(Constants.STAGE_1_10_X, Constants.STAGE_1_10_Y, Constants.STAGE_RADIUS);
        stages[10] = new Circle(Constants.STAGE_1_11_X, Constants.STAGE_1_11_Y, Constants.STAGE_RADIUS);
        stages[11] = new Circle(Constants.STAGE_1_12_X, Constants.STAGE_1_12_Y, Constants.STAGE_RADIUS);
        stages[12] = new Circle(Constants.STAGE_1_13_X, Constants.STAGE_1_13_Y, Constants.STAGE_RADIUS);
        stages[13] = new Circle(Constants.STAGE_1_14_X, Constants.STAGE_1_14_Y, Constants.STAGE_RADIUS);
        stages[14] = new Circle(Constants.STAGE_1_15_X, Constants.STAGE_1_15_Y, Constants.STAGE_RADIUS);
        stages[15] = new Circle(Constants.STAGE_1_16_X, Constants.STAGE_1_16_Y, Constants.STAGE_RADIUS);
        stages[16] = new Circle(Constants.STAGE_1_17_X, Constants.STAGE_1_17_Y, Constants.STAGE_RADIUS);

    }
    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(stageSelect, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        drawObject(sprite);
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            for(int i = 0; i < stages.length; i++){
                if(stages[i].contains(touchpoint.x, touchpoint.y)){
                    System.out.println("You clicked at stage " + (i + 1)  + "!!");
                }
            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        for(int i = 0; i < stages.length; i++){
                sprite.draw(grayCircle, stages[i].x - Constants.CIRCLE_R / 2, stages[i].y - Constants.CIRCLE_R / 2, Constants.CIRCLE_R, Constants.CIRCLE_R);

        }
    }
}
