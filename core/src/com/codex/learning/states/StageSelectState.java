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

    private TextureRegion stageSelect;
    private Vector3 touchpoint;
    private Circle stages[] = new Circle[18];

    public StageSelectState(Manager manager){
        super(manager);

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));

        touchpoint = new Vector3();
        stages[0] = new Circle(67, 130, 40);
        stages[1] = new Circle(230, 130, 40);
        stages[2] = new Circle(450, 130, 40);
//        stages[3] = new Circle(529, 639, 72);
//        stages[4] = new Circle(777, 639, 72);
//        stages[5] = new Circle(994, 641, 72);
//        stages[6] = new Circle(1152, 708, 72);
//        stages[7] = new Circle(1377, 704, 72);
//        stages[8] = new Circle(1379, 534, 72);
//        stages[9] = new Circle(1329, 399, 72);
//        stages[10] = new Circle(1326, 181, 72);
//        stages[11] = new Circle(1041, 171, 72);
//        stages[12] = new Circle(728, 178, 72);
//        stages[13] = new Circle(426, 178, 72);
//        stages[14] = new Circle(90, 173, 72);
//        stages[15] = new Circle(90, 173, 72);
//        stages[16] = new Circle(90, 173, 72);
//        stages[17] = new Circle(90, 173, 72);
    }
    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(stageSelect, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
//            for(int i = 0; i < stages.length; i++){
//                System.out.println(stages[i].getX() + " - " +  stages[i].getY());
//                System.out.println(stages[i].getWidth() + " - " +  stages[i].getHeight());

                System.out.println("TOUCH - " + touchpoint.x + " - " + touchpoint.y);
                if(stages[0].contains(touchpoint.x, touchpoint.y)){
                    System.out.println("You clicked at stage " + 1 + "!!");
                }
            if(stages[1].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 2 + "!!");
            }
            if(stages[2].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 3 + "!!");
            }
//            }
        }
    }

    public void drawObject(SpriteBatch sprite){
        manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        for(int i = 0; i < stages.length; i++){
            if(stages[i].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + stages[i] + "!!");
            }
        }

    }
}
