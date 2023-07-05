package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.uiComponents.ImageView;
import com.mygdx.game.uiComponents.UiComponent;

import java.util.ArrayList;

public class AboutScreen implements Screen {

    private MyGdxGame myGdxGame;
    private ImageView returnButton;
    private ArrayList<UiComponent> componentsList;

    public AboutScreen (MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        componentsList = new ArrayList<>();

        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/aboutBG.jpg");
        TextView title = new TextView(myGdxGame.largeFont.bitmapFont, "About", -1, 950);
        returnButton = new ImageView(200, 950, 100, 100, "icons/backIcon.png");
        TextView information = new TextView(myGdxGame.commonFont.bitmapFont, "Учебный проект в рамках Школы программной инженерии", 100, 800);
        TextView author = new TextView(myGdxGame.commonFont.bitmapFont, "Преподаватель - Павлюк Глеб", 100, 700);
        TextView aboutGame = new TextView(myGdxGame.commonFont.bitmapFont, "Это игра про ПРИКЛЮЧЕНИЯ КОМАРИКОВ!", 100, 600);

        componentsList.add(background);
        componentsList.add(title);
        componentsList.add(returnButton);
        componentsList.add(information);
        componentsList.add(author);
        componentsList.add(aboutGame);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        returnButton.setOnClickListener(onReturnButtonClickListener);

        if (Gdx.input.justTouched()) {
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

    private UiComponent.OnClickListener onReturnButtonClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {

            Gdx.app.debug("onClicked", "onReturnButtonClicked");
            myGdxGame.setScreen(myGdxGame.menuScreen);

        }
    };
}