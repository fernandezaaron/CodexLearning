package com.codex.learning.entity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Response extends ApplicationAdapter {
    SpriteBatch batch;
    private String[] myStringText = {"I asked her to stay", "But she wouldn't listen",
            "And she left before I had the chance to say,", "oh",
            "The words that would mend",
            "The things that were broken",
            "But now it's far too late,", "she's gone away"};
    private String myText;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;
    int xpos, ypos;

    @Override
    public void create() {
        batch = new SpriteBatch();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("KGHAPPY.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 20;
        fontParameter.borderWidth = 5;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.WHITE;
        font = fontGenerator.generateFont(fontParameter);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 100);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(int i = 0; i < myStringText.length; i++) {
            myText = myStringText[0];
            for (int j = 0; j < 1000; j++) {
//                if (j % 1000 == 0) {
//                    xpos++;
//                    ypos++;
//                }
            }
        }

        batch.begin();
        font.draw(batch, myText, xpos+50, ypos+50);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
    }
}