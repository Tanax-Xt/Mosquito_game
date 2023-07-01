package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;
import com.sun.tools.sjavac.Log;

import java.util.ArrayList;

public class SettingsScreen implements Screen {

    ArrayList<UiComponent> uiComponentsList;
    MyGdxGame myGdxGame;
    TextButton difficultyButton;
    TextButton soundsButton;
    TextButton resetButton;

    public SettingsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        uiComponentsList = new ArrayList<>();

        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/settingsBG.jpg");
        TextView title = new TextView(myGdxGame.largeFont.bitmapFont, "Settings", -1, 950);
        ImageView returnButton = new ImageView(200, 950, 100, 100, "icons/backIcon.png");
        returnButton.setOnClickListener(onReturnButtonClickListener);

        difficultyButton = new TextButton(myGdxGame.commonFont.bitmapFont, "Change difficulty", 200, 700);
        soundsButton = new TextButton(myGdxGame.commonFont.bitmapFont, getSoundButtonText(), 200, 600);
        resetButton = new TextButton(myGdxGame.commonFont.bitmapFont, "Clear all saves", 200, 500);

        uiComponentsList.add(background);
        uiComponentsList.add(title);
        uiComponentsList.add(returnButton);
        uiComponentsList.add(difficultyButton);
        uiComponentsList.add(soundsButton);
        uiComponentsList.add(resetButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.touch = myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : uiComponentsList) {
                if(component.isVisible) component.isHit(myGdxGame.touch.x, myGdxGame.touch.y);
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        for (UiComponent component : uiComponentsList) {
            component.draw(myGdxGame);
        }

        myGdxGame.batch.end();
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

    private String getSoundButtonText() {
        boolean musicState = MemoryLoader.loadMusicState();
        if (musicState) return "Turn on music";
        else return "Turn of music";
    }

    UiComponent.OnClickListener onReturnButtonClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.debug("onClicked", "onReturnButtonClicked");
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };
}
