package com.mygdx.game.uiComponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageView extends UiComponent {

    Texture imgTexture;

    public ImageView(int x, int y, int wight, int height, String imdSource) {
        super(x, y, wight, height);
        this.imgTexture = new Texture(imdSource);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(imgTexture, x, y, width, height);
    }

}
