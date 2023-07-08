package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.*;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.uiComponents.*;
import com.mygdx.game.utils.*;

import java.util.ArrayList;

public class GameScreen implements Screen {

    ArrayList<UiComponent> componentsList;
    ArrayList<UiComponent> uiComponentsListEndOfGame;
    ArrayList<UiComponent> uiComponentsListPauseOfGame;
    ArrayList<UiComponent> uiComponentsListGameOver;
    ArrayList<Texture> mosquitoTextureList;
    ArrayList<Texture> beeTextureList;
    ArrayList<Mosquito> mosquitoList;
    ArrayList<Bee> beeList;
    boolean isSoundPlay;
    MyGdxGame myGdxGame;
    int aliveMosquitoesCount;
    int mosquitoesQuantity;
    GameSession gameSession;
    ImageView pauseButton;
    TextView textViewAliveMosquitoesCount;
    TextButton returnButton;
    TextView textViewSessionTime;
    TextView pauseTextInfo;
    TextView pauseTextContinue;
    ProgressBar progressBar;

    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        Gdx.app.debug("GameScreen", "constructor");

        mosquitoTextureList = new ArrayList<>();
        beeTextureList = new ArrayList<>();
        componentsList = new ArrayList<>();
        uiComponentsListEndOfGame = new ArrayList<>();
        uiComponentsListPauseOfGame = new ArrayList<>();
        uiComponentsListGameOver = new ArrayList<>();
        mosquitoList = new ArrayList<>();
        beeList = new ArrayList<>();

        progressBar = new ProgressBar(700, 30,
                MemoryLoader.loadDifficultyLevel().getUserHitPoints(), 100, 30, "Hit points", myGdxGame.commonFont.bitmapFont);

        textViewAliveMosquitoesCount = new TextView(myGdxGame.commonFont.bitmapFont, "", 1420, 980);
        textViewSessionTime = new TextView(myGdxGame.commonFont.bitmapFont, "", 700, 700);
        returnButton = new TextButton(myGdxGame.accentFont.bitmapFont, "Return home", 300, 500);

        pauseButton = new ImageView(100, 900, 100, 100, "icons/pauseIcon.png");
        pauseButton.setOnClickListener(onPauseButtonClickListener);
        pauseTextInfo = new TextView(myGdxGame.commonFont.bitmapFont, "Game is stopped", 300, 800);

        pauseTextContinue = new TextView(myGdxGame.largeFont.bitmapFont, "Continue", 300, 650);
        pauseTextContinue.setOnClickListener(onContinueGameClickListener);


        componentsList.add(new ImageView(0, 0, GameSettings.SCR_WIDTH,
                GameSettings.SCR_HEIGHT, "backgrounds/gameBG" + MathUtils.random(0, 4) + ".jpg"));
        componentsList.add(progressBar);
        componentsList.add(textViewAliveMosquitoesCount);
        componentsList.add(pauseButton);

        uiComponentsListEndOfGame.add(new Blackout());
        uiComponentsListEndOfGame.add(new TextView(myGdxGame.largeFont.bitmapFont,
                "Our congratulations", -1, 900));
        uiComponentsListEndOfGame.add(new TextView(myGdxGame.commonFont.bitmapFont,
                "Your time: ", 300, 700));
        uiComponentsListEndOfGame.add(textViewSessionTime);
        uiComponentsListEndOfGame.add(returnButton);

        uiComponentsListPauseOfGame.add(new Blackout());
        uiComponentsListPauseOfGame.add(pauseTextInfo);
        uiComponentsListPauseOfGame.add(pauseTextContinue);
        uiComponentsListPauseOfGame.add(returnButton);

        uiComponentsListGameOver.add(new Blackout());
        uiComponentsListGameOver.add(new TextView(myGdxGame.largeFont.bitmapFont,
                "Game over", -1, 900));
        uiComponentsListGameOver.add(returnButton);

        returnButton.setOnClickListener(onReturnButtonClickListener);
    }

    @Override
    public void show() {
        Gdx.app.debug("Show", "Show");
        gameSession = new GameSession();
        isSoundPlay = MemoryLoader.loadMusicState();
        Gdx.app.debug("Show", String.valueOf(gameSession.getGameState()));
        gameSession.setGameState(GameSession.PLAY_GAME);
        progressBar.setMaxValue(MemoryLoader.loadDifficultyLevel().getUserHitPoints());
        loadActors(MemoryLoader.loadDifficultyLevel());
    }

    @Override
    public void render(float delta) {
        Gdx.app.debug("Render", "Render");
        if (Gdx.input.justTouched()) {
            Vector3 vector3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            vector3 = myGdxGame.camera.unproject(vector3);
            if (gameSession.getGameState() == GameSession.PLAY_GAME) {
                for (UiComponent component : componentsList) {
                    if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
                }
            } else if (gameSession.getGameState() == GameSession.GAME_OVER) {
                for (UiComponent component : uiComponentsListGameOver) {
                    if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
                }
            } else if (gameSession.getGameState() == GameSession.END_OF_GAME) {
                for (UiComponent component : uiComponentsListEndOfGame) {
                    if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
                }
            }
        }

        if (gameSession.getGameState() != GameSession.PAUSE_GAME) {
            for (Mosquito mosquito : mosquitoList) {
                if (mosquito.isAlive) mosquito.update();
            }

            for (Bee bee : beeList) bee.update();
        }

        progressBar.setValue(gameSession.hitPointsLeft);


        ScreenUtils.clear(0, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        for (UiComponent component : componentsList) {
            component.draw(myGdxGame.batch);
        }
        if (gameSession.getGameState() == GameSession.END_OF_GAME) {
            for (UiComponent component : uiComponentsListEndOfGame) {
                component.draw(myGdxGame.batch);
            }
        }
        if (gameSession.getGameState() == GameSession.GAME_OVER) {
            for (UiComponent component : uiComponentsListGameOver) {
                component.draw(myGdxGame.batch);
            }
        }


        if (gameSession.getGameState() == GameSession.PAUSE_GAME) {
            if (Gdx.input.justTouched()) hitHandler(uiComponentsListPauseOfGame);
            for (UiComponent component : uiComponentsListPauseOfGame) {
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
        gameSession.setGameState(GameSession.PAUSE_GAME);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameSession.setGameState(GameSession.PAUSE_GAME);
    }

    @Override
    public void dispose() {

    }

    void loadActors(DifficultyLevel difficultyLevel) {
        mosquitoList.clear();
        beeList.clear();
        mosquitoTextureList.clear();
        beeTextureList.clear();
        aliveMosquitoesCount = difficultyLevel.getCountOfEnemies();
        mosquitoesQuantity = aliveMosquitoesCount;

//        int live = difficultyLevel.getCountOfEnemies() - aliveMosquitoesCount;

        textViewAliveMosquitoesCount.text = "Mosquitoes alive: " + aliveMosquitoesCount + " / " + mosquitoesQuantity;

        for (int i = 1; i <= 4; i++) beeTextureList.add(new Texture("tiles/bee" + i + ".png"));

        for (int i = 0; i <= 9; i++)
            mosquitoTextureList.add(new Texture("tiles/mosq" + i + ".png"));
        Texture deadMosquitoTexture = new Texture("tiles/mosq10.png");

        for (int i = 0; i < aliveMosquitoesCount; i++) {
            Mosquito mosquito = new Mosquito(mosquitoTextureList, deadMosquitoTexture, difficultyLevel.getEnemySpeed(), onKillMosquitoListener);
            mosquitoList.add(mosquito);

            if (i % 4 == 0) {
                Bee bee = new Bee(beeTextureList, difficultyLevel.getEnemySpeed(), onHitButterflyListener);
                beeList.add(bee);
                componentsList.add(bee.actorImgView);
            }

            componentsList.add(mosquito.actorImgView);
        }
    }

    private void hitHandler(ArrayList<UiComponent> uiComponents) {
        Vector3 vector3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        vector3 = myGdxGame.camera.unproject(vector3);
        for (UiComponent component : uiComponents) {
            if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
        }
    }

    Mosquito.OnKillMosquitoListener onKillMosquitoListener = new Mosquito.OnKillMosquitoListener() {
        @Override
        public void onKill() {
            Gdx.app.debug("onKill", "killed");
            if (isSoundPlay) SoundExecutor.playMosquitoSound();
            aliveMosquitoesCount -= 1;
            textViewAliveMosquitoesCount.text = "Mosquitoes alive: " + aliveMosquitoesCount + " / " + mosquitoesQuantity;
            if (aliveMosquitoesCount == 0) {
                gameSession.setGameState(GameSession.END_OF_GAME);
                textViewSessionTime.text = gameSession.getSessionTime();
            }
        }
    };

    private UiComponent.OnClickListener onReturnButtonClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onReturnButtonClicked");
            myGdxGame.gameScreen = new GameScreen(myGdxGame);
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };

    private UiComponent.OnClickListener onPauseButtonClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onPauseButtonClicked");
            if (gameSession.getGameState() != GameSession.END_OF_GAME) gameSession.setGameState(GameSession.PAUSE_GAME);
        }
    };

    private Bee.OnHitBeeListener onHitButterflyListener = new Bee.OnHitBeeListener() {
        @Override
        public void onHit() {
            if (gameSession.getGameState() == GameSession.PLAY_GAME) {
                gameSession.getDamage();
                SoundExecutor.playMosquitoSound();
            }
        }
    };

    private UiComponent.OnClickListener onContinueGameClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("onClicked", "onContinueButtonClicked");
            gameSession.setGameState(GameSession.PLAY_GAME);
        }
    };
}