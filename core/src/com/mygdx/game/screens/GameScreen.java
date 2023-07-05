//package com.mygdx.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.mygdx.game.GameSettings;
//import com.mygdx.game.MyGdxGame;
//import com.mygdx.game.uiComponents.*;
//import com.mygdx.game.uiComponents.ImageView;
//
//import java.util.ArrayList;
//
//public class GameScreen implements Screen {
//    MyGdxGame myGdxGame;
//    ArrayList<UiComponent> componentsList;
//    ArrayList<ImageView> mosquitoes = new ArrayList<>();
//    int vx = 10;
//    int vy = 10;
//
//    public GameScreen(MyGdxGame myGdxGame) {
//        this.myGdxGame = myGdxGame;
//        componentsList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            ImageView item = new ImageView(100, 100, 100, 100, "icons/mosquito.png");
//            mosquitoes.add(item);
//        }
//
//        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/gameBG.jpeg");
//        ImageView returnButton = new ImageView(200, 950, 100, 100, "icons/backIcon.png");
//        returnButton.setOnClickListener(onReturnButtonClickListener);
//
//        componentsList.add(background);
//        componentsList.add(returnButton);
//    }
//
//    UiComponent.OnClickListener onReturnButtonClickListener = new UiComponent.OnClickListener() {
//        @Override
//        public void onClick() {
//            Gdx.app.debug("onClicked", "onReturnButtonClicked");
//            myGdxGame.setScreen(myGdxGame.menuScreen);
//        }
//    };
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//
//        if (Gdx.input.justTouched()) {
//            Vector3 vector3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            vector3 = myGdxGame.camera.unproject(vector3);
//            for (UiComponent component : componentsList) {
//                if (component.isVisible) component.isHit((int) vector3.x, (int) vector3.y);
//            }
//        }
//
//        ScreenUtils.clear(0, 0, 0, 1);
//        myGdxGame.camera.update();
//        myGdxGame.batch.begin();
//        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
//
//        for (UiComponent component : componentsList)
//            component.draw(myGdxGame.batch);
//
//        for (ImageView mosquito : mosquitoes) {
//            mosquito.draw(myGdxGame.batch);
//            mosquito.setX(vx);
//            mosquito.setY(vy);
//        }
//
//        myGdxGame.batch.end();
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
