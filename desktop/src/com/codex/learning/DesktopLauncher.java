package com.codex.learning;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.codex.learning.Game;
import com.codex.learning.utility.Constants;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle(Constants.GAME_TITLE);
		config.setWindowedMode(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT / 2);
//		config.setWindowedMode(1600,900);
		config.setForegroundFPS(60);

		//config.setResizable(false);

		new Lwjgl3Application(new Game(), config);
	}
}
