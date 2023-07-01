package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class ImageView extends UiComponent{

    String imgSource;
    Texture imgTexture;

    public ImageView(float x, float y, float width, float height, String imgSource) {
        super(x, y, width, height);
        this.imgSource = imgSource;
        imgTexture = new Texture(imgSource);
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        if (onClickListener == null) myGdxGame.batch.draw(imgTexture, x, y, width, height);
        else myGdxGame.batch.draw(imgTexture, x, y, width, -height);
    }

}
