package com.codex.learning.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

public class StageSelectState extends State{

    private TextureRegion stageSelect;
    private Vector3 touchpoint;
    private Rectangle stages[] = new Rectangle[15];

    public StageSelectState(Manager manager){
        super(manager);

        stageSelect = new TextureRegion(new Texture(Constants.STAGE_SELECT_PATH));

        touchpoint = new Vector3();
        stages[0] = new Rectangle(34, 732, 79, 69);
        stages[1] = new Rectangle(246, 728, 72, 62);
        stages[2] = new Rectangle(420, 725, 72, 72);
        stages[3] = new Rectangle(529, 639, 72, 72);
        stages[4] = new Rectangle(777, 639, 72, 72);
        stages[5] = new Rectangle(994, 641, 72, 72);
        stages[6] = new Rectangle(1152, 708, 72, 72);
        stages[7] = new Rectangle(1377, 704, 72, 72);
        stages[8] = new Rectangle(1379, 534, 72, 72);
        stages[9] = new Rectangle(1329, 399, 72, 72);
        stages[10] = new Rectangle(1326, 181, 72, 72);
        stages[11] = new Rectangle(1041, 171, 72, 72);
        stages[12] = new Rectangle(728, 178, 72, 72);
        stages[13] = new Rectangle(426, 178, 72, 72);
        stages[14] = new Rectangle(90, 173, 72, 72);

    }
    @Override
    public void update(float delta) {
        input();
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.draw(stageSelect, 0, 0, (Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2), (Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2));
        sprite.end();
    }

    @Override
    public void dispose() {

    }

    public void input(){
        if(Gdx.input.justTouched()){
            manager.getCamera().unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            for(int i = 0; i < stages.length; i++){
//                System.out.println(stages[i].getX() + " - " +  stages[i].getY());
//                System.out.println(stages[i].getWidth() + " - " +  stages[i].getHeight());

                System.out.println("TOUCH - " + touchpoint.x + " - " + touchpoint.y);
                if(stages[i].contains(touchpoint.x, touchpoint.y)){
                    System.out.println("You clicked at stage " + i + "!!");
                }
            }
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
