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
        stages[0] = new Circle(67, 135, 38);
        stages[1] = new Circle(232, 135, 38);
        stages[2] = new Circle(450, 135, 38);
        stages[3] = new Circle(570, 222, 38);
        stages[4] = new Circle(795, 227, 38);
        stages[5] = new Circle(910, 227, 38);
        stages[6] = new Circle(1030, 227, 38);
        stages[7] = new Circle(1190, 160, 38);
        stages[8] = new Circle(1410, 160, 38);
        stages[9] = new Circle(1410, 330, 38);
        // NOT DONE
        stages[10] = new Circle(1366, 455, 38);
        stages[11] = new Circle(1366, 690, 38);
//        stages[12] = new Circle(728, 690, 38);
//        stages[13] = new Circle(426, 690, 38);
//        stages[14] = new Circle(90, 690, 38);
//        stages[15] = new Circle(90, 690, 38);
//        stages[16] = new Circle(90, 690, 38);
//        stages[17] = new Circle(90, 690, 38);
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
            if(stages[0].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 1 + "!!");
            }
            if(stages[1].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 2 + "!!");
            }
            if(stages[2].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 3 + "!!");
            }
            if(stages[3].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 4 + "!!");
            }
            if(stages[4].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 5 + "!!");
            }
            if(stages[5].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 6 + "!!");
            }
            if(stages[6].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 7 + "!!");
            }
            if(stages[7].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 8 + "!!");
            }
            if(stages[8].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 9 + "!!");
            }
            if(stages[9].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 10 + "!!");
            }
            if(stages[10].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 11 + "!!");
            }
            if(stages[11].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 12 + "!!");
            }
            if(stages[12].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 13 + "!!");
            }
            if(stages[13].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 14 + "!!");
            }
            if(stages[14].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 15 + "!!");
            }
            if(stages[15].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 16 + "!!");
            }
            if(stages[16].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 17 + "!!");
            }
            if(stages[17].contains(touchpoint.x, touchpoint.y)){
                System.out.println("You clicked at stage " + 18 + "!!");
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
