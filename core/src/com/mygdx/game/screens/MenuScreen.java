package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextButton;
import com.mygdx.game.ui.UiComponent;

import java.util.ArrayList;

public class MenuScreen implements Screen {

    public MyGdxGame myGdxGame;
    ArrayList<UiComponent> uiComponentsList;

    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        uiComponentsList = new ArrayList<>();

        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/homeBG.jpg");
        TextView title = new TextView(myGdxGame.largeFont.bitmapFont, "Welcome to Mosquito game", -1, 950);

        TextButton buttonStart = new TextButton(myGdxGame.commonFont.bitmapFont, "Start", 200, 700);
        buttonStart.setOnClickListener(onButtonStartClicked);

        TextButton buttonSettings = new TextButton(myGdxGame.commonFont.bitmapFont, "Settings", 200, 600);
        buttonSettings.setOnClickListener(onButtonSettingsClicked);

        TextButton buttonAbout = new TextButton(myGdxGame.commonFont.bitmapFont, "About", 200, 500);
        buttonAbout.setOnClickListener(onButtonAboutClicked);

        TextButton buttonExit = new TextButton(myGdxGame.commonFont.bitmapFont, "Exit", 200, 400);
        buttonExit.setOnClickListener(onButtonExitClicked);

        uiComponentsList.add(background);
        uiComponentsList.add(title);
        uiComponentsList.add(buttonStart);
        uiComponentsList.add(buttonSettings);
        uiComponentsList.add(buttonAbout);
        uiComponentsList.add(buttonExit);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // check is it useful
            myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : uiComponentsList) {
                if (component.isVisible) component.isHit(myGdxGame.touch.x, myGdxGame.touch.y);
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

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

    /*private final UiComponent.OnClickListener onButtonStartClicked = () -> {
        myGdxGame.setScreen(myGdxGame.gameScreen);
    };*/

    private final UiComponent.OnClickListener onButtonStartClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.debug("onClicked", "onButtonStartClicked");
            myGdxGame.setScreen(myGdxGame.gameScreen);
        }
    };

    private final UiComponent.OnClickListener onButtonAboutClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.debug("onClicked", "onButtonAboutClicked");
            myGdxGame.setScreen(myGdxGame.aboutScreen);
        }
    };

    private final UiComponent.OnClickListener onButtonSettingsClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.debug("onClicked", "onButtonSettingsClicked");
            myGdxGame.setScreen(myGdxGame.settingsScreen);
        }
    };

    private final UiComponent.OnClickListener onButtonExitClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClicked() {
            Gdx.app.debug("onClicked", "onButtonExitClicked");
            // myGdxGame.setScreen(myGdxGame.);
        }
    };
}
