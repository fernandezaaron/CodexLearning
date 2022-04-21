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
//		Lwjgl3Window window = ((Lwjgl3Graphics)Gdx.graphics).getWindow();
//
//		window.setPosition(0, 0);
		config.setTitle("Codex Learning");
		config.setWindowedMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		config.setForegroundFPS(60);
		new Lwjgl3Application(new Game(), config);
	}
}
