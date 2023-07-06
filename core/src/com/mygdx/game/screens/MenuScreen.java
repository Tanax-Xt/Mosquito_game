package com.mygdx.game.screens;

import static com.mygdx.game.utils.GameSettings.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.uiComponents.ImageView;
import com.mygdx.game.uiComponents.TextButton;
import com.mygdx.game.uiComponents.UiComponent;

import java.util.ArrayList;

public class MenuScreen implements Screen {
    private MyGdxGame myGdxGame;
    private ArrayList<UiComponent> componentsList;
    TextButton startButton;
    TextButton settingButton;
    TextButton aboutButton;
    TextButton exitButton;

    public MyGdxGame getMyGdxGame() {
        return myGdxGame;
    }

    public void setMyGdxGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
    }

    public ArrayList<UiComponent> getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(ArrayList<UiComponent> componentsList) {
        this.componentsList = componentsList;
    }

    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        componentsList = new ArrayList<>();
        componentsList.add(new ImageView(0, 0, SCR_WIDTH, SCR_HEIGHT, "backgrounds/homeBG.jpg"));

        startButton = new TextButton(myGdxGame.commonFont.bitmapFont, "Start", 300, 700);

        settingButton = new TextButton(myGdxGame.commonFont.bitmapFont, "Settings", 300, 600);

        aboutButton = new TextButton(myGdxGame.commonFont.bitmapFont, "About", 300, 500);

        exitButton = new TextButton(myGdxGame.commonFont.bitmapFont, "Exit", 300, 400);

        componentsList.add(new com.mygdx.game.ui.TextView(myGdxGame.largeFont.bitmapFont, "Welcome to Mosquito game", -1, 950));
        componentsList.add(startButton);
        componentsList.add(settingButton);
        componentsList.add(aboutButton);
        componentsList.add(exitButton);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        startButton.setOnClickListener(startButtonOnClickListener);
        settingButton.setOnClickListener(settingButtonOnClickListener);
        aboutButton.setOnClickListener(aboutButtonOnClickListener);
        exitButton.setOnClickListener(exitButtonOnClickListener);


        if (Gdx.input.justTouched()) {
            // add code
            Vector3 vector3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            vector3 = myGdxGame.camera.unproject(vector3);
            for (UiComponent component : componentsList) {
                if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        for (UiComponent component : componentsList)
            component.draw(myGdxGame.batch);

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

    private final UiComponent.OnClickListener startButtonOnClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClick", "start button clicked");
            myGdxGame.setScreen(myGdxGame.gameScreen);
        }
    };


    private final UiComponent.OnClickListener aboutButtonOnClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onButtonAboutClicked");
            myGdxGame.setScreen(myGdxGame.aboutScreen);
        }
    };

    private final UiComponent.OnClickListener settingButtonOnClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onButtonSettingsClicked");
            myGdxGame.setScreen(myGdxGame.settingsScreen);
        }
    };

    private final UiComponent.OnClickListener exitButtonOnClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onButtonExitClicked");
            Gdx.app.exit();
        }
    };

}