package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.AboutScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SettingsScreen;
import com.mygdx.game.utils.MemoryLoader;
import com.mygdx.game.utils.MyCustomFont;
import com.mygdx.game.utils.SoundExecutor;

import java.util.Vector;

public class MyGdxGame extends Game {
    public OrthographicCamera camera;

    public MenuScreen menuScreen;
    public SettingsScreen settingsScreen;
    public GameScreen gameScreen;
    public AboutScreen aboutScreen;

    public SpriteBatch batch;
    public MyCustomFont commonFont;
    public MyCustomFont accentFont;
    public MyCustomFont largeFont;
    public Object secondaryFont;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();
        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false, 1920, 1080);
        if (MemoryLoader.loadMusicState()) SoundExecutor.playBackSound();

        commonFont = new MyCustomFont(40, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
        accentFont = new MyCustomFont(40, "fonts/arnamubi.ttf", new Color(1, 1, 1, 1));
        largeFont = new MyCustomFont(70, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));

        aboutScreen = new AboutScreen(this);
        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        settingsScreen = new SettingsScreen(this);

        setScreen(menuScreen);
    }
//
//	@Override
//	public void render () {
//		ScreenUtils.clear(0, 1, 1, 1);
//		camera.update();
//	}

//	@Override
//	public void dispose () {
//	}
}
