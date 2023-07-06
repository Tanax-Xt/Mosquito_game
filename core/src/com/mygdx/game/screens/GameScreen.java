package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.Mosquito;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.uiComponents.Blackout;
import com.mygdx.game.uiComponents.ImageView;
import com.mygdx.game.uiComponents.TextButton;
import com.mygdx.game.uiComponents.UiComponent;
import com.mygdx.game.utils.DifficultyLevel;
import com.mygdx.game.utils.GameSession;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

import java.util.ArrayList;

public class        GameScreen implements Screen {

    ArrayList<UiComponent> componentsList;
    ArrayList<UiComponent> uiComponentsListEndOfGame;
    ArrayList<Texture> mosquitoTextureList;
    ArrayList<Mosquito> mosquitoList;
    MyGdxGame myGdxGame;

    int aliveMosquitoesCount;
    GameSession gameSession;

    TextView textViewAliveMosquitoesCount;
    TextButton returnButton;
    TextView textViewSessionTime;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        Gdx.app.debug("GameScreen", "constructor");

        gameSession = new GameSession();
        mosquitoTextureList = new ArrayList<>();
        componentsList = new ArrayList<>();
        uiComponentsListEndOfGame = new ArrayList<>();
        mosquitoList = new ArrayList<>();
        componentsList.add(new ImageView(0, 0, GameSettings.SCR_WIDTH,
                GameSettings.SCR_HEIGHT, "backgrounds/gameBG.jpg"));

        textViewAliveMosquitoesCount = new TextView(myGdxGame.commonFont.bitmapFont, "", 1620, 980);

        uiComponentsListEndOfGame.add(new Blackout());
        uiComponentsListEndOfGame.add(new TextView(myGdxGame.largeFont.bitmapFont,
                "Our congratulations", -1, 900));
        uiComponentsListEndOfGame.add(new TextView(myGdxGame.commonFont.bitmapFont,
                "Your time: ", 300, 700));
        textViewSessionTime = new TextView(myGdxGame.commonFont.bitmapFont, "", 700, 700);
        returnButton = new TextButton(myGdxGame.accentFont.bitmapFont, "Return home", 300, 500);
        componentsList.add(textViewAliveMosquitoesCount);
        uiComponentsListEndOfGame.add(textViewSessionTime);
        uiComponentsListEndOfGame.add(returnButton);
    }

    @Override
    public void show() {
        Gdx.app.debug("Show", "Show");
        loadMosquitoes(MemoryLoader.loadDifficultyLevel());
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            // add code
            Vector3 vector3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            vector3 = myGdxGame.camera.unproject(vector3);
            for (UiComponent component : componentsList) {
                if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
            }
        }

        for (Mosquito mosquito : mosquitoList) {
            if (mosquito.isAlive) mosquito.update();
        }

        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        for (UiComponent component : componentsList) {
            component.draw(myGdxGame.batch);
        }

        if (gameSession.gameState == GameSession.END_OF_GAME) {
            for (UiComponent component : uiComponentsListEndOfGame) {
                component.draw(myGdxGame.batch);
            }
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

    void loadMosquitoes(DifficultyLevel difficultyLevel) {
        mosquitoList = new ArrayList<>();
        aliveMosquitoesCount = difficultyLevel.getCountOfEnemies();

        textViewAliveMosquitoesCount.text = "Alive: " + aliveMosquitoesCount;

        for (int i = 0; i < 9; i++)
            mosquitoTextureList.add(new Texture("tiles/mosq" + i + ".png"));
        Texture deadMosquitoTexture = new Texture("tiles/mosq10.png");

        for (int i = 0; i < aliveMosquitoesCount; i++) {
            Mosquito mosquito = new Mosquito(mosquitoTextureList, deadMosquitoTexture,
                    difficultyLevel.getEnemySpeed(), onKillMosquitoListener, difficultyLevel);

            mosquito.actorImgView.width = mosquito.actorImgView.height *= difficultyLevel.getSizeChangeConst();
            mosquito.width = mosquito.height *= difficultyLevel.getSizeChangeConst();

            Gdx.app.debug("Show", String.valueOf(mosquito.actorImgView.x));
            mosquitoList.add(mosquito);
            componentsList.add(mosquito.actorImgView);
        }
    }

    Mosquito.OnKillMosquitoListener onKillMosquitoListener = new Mosquito.OnKillMosquitoListener() {
        @Override
        public void onKill() {
            Gdx.app.debug("onKill", "killed");
            aliveMosquitoesCount -= 1;
            textViewAliveMosquitoesCount.text = "Alive: " + aliveMosquitoesCount;
            if (aliveMosquitoesCount == 0){
                gameSession.gameState = GameSession.END_OF_GAME;
                textViewSessionTime.text = gameSession.getSessionTime();
            }
        }
    };

}