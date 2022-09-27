package com.codex.learning;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codex.learning.states.MenuState;
import com.codex.learning.states.PlayState;
import com.codex.learning.states.StageSelectState;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.awt.*;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Manager manager;

	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new Manager();

		manager.push(new MenuState(manager));
		Gdx.input.setInputProcessor(manager.getStage());
//		img = new Texture(Constants.BACKGROUND_PATH);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 0);

		float delta = Gdx.graphics.getDeltaTime();
		manager.update(delta);
		manager.render(batch);

//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}

	@Override
	public void resize(int width, int height){
//		manager.getCamera().setToOrtho(false, width, height);
		manager.getStage().getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
		manager.disposeAll();
	}
}
