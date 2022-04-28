package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class STAGE_1 extends Game {
    Stage stage_1;
    SpriteBatch STAGE;
    Texture Jedisaur, BACKGROUND;
    float SPEED = 100;
    float JEDISAUR_X = 200;
    float JEDISAUR_Y = 270;
    float JEDISAUR_W = 100;
    float JEDISAUR_H = 100;
    int WALK_COUNTER;
    Screen stage1;

    public class stage1 extends InputAdapter implements Screen {

        @Override
        public void show() {
            Jedisaur = new Texture("badlogic.jpg"); // Insert jedisaur sprite
            BACKGROUND = new Texture("house.png"); // Main background
            stage_1 = new Stage();
            Gdx.input.setInputProcessor(stage_1);
            STAGE = new SpriteBatch();
        }

        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(1, 1, 1, 0);
            ScreenUtils.clear(1, 1, 1, 0);
            STAGE.begin();
            stage_1.draw();
            STAGE.draw(BACKGROUND,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            STAGE.draw(Jedisaur, JEDISAUR_X, JEDISAUR_Y, JEDISAUR_W,JEDISAUR_H);
            // Jedisaur walking animation counter
            WALK_COUNTER ++;
            if (WALK_COUNTER > 30) {
                if (WALK_COUNTER == 1) {
                    WALK_COUNTER = 2;
                } else if (WALK_COUNTER == 2) {
                    WALK_COUNTER = 1;
                } else {
                    WALK_COUNTER = 0;
                }

            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {

                System.out.print("W");
                JEDISAUR_Y += Gdx.graphics.getDeltaTime() * SPEED;
                if (WALK_COUNTER == 1) {
                   // JEDISAUR_H = 50;		// Example for walking animation
                } else if (WALK_COUNTER == 2) {
                  //  JEDISAUR_H = 100;		// Example for walking animation
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                System.out.print("S");
                JEDISAUR_Y -= Gdx.graphics.getDeltaTime() * SPEED;
                if (WALK_COUNTER == 1) {
                  //  JEDISAUR_H = 30;		// Example for walking animation
                } else if (WALK_COUNTER == 2) {
                  //  JEDISAUR_H = 90;		// Example for walking animation
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                System.out.print("A");
                JEDISAUR_X -= Gdx.graphics.getDeltaTime() * SPEED;
                if (WALK_COUNTER == 1) {
                 //   JEDISAUR_W = 50;		// Example for walking animation
                } else if (WALK_COUNTER == 2) {
                 //   JEDISAUR_W = 100;		// Example for walking animation
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                System.out.print("D");
                JEDISAUR_X += Gdx.graphics.getDeltaTime() * SPEED;
                if (WALK_COUNTER == 1) {
                 //   JEDISAUR_W = 30;		// Example for walking animation
                } else if (WALK_COUNTER == 2) {
                 //   JEDISAUR_W = 90;		// Example for walking animation
                }
            }
            STAGE.end();
        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {

        }
    }
    @Override
    public void create () {
        Gdx.graphics.setWindowedMode(1600,920);
        stage1 = new stage1();
        setScreen(stage1);
    }


}
