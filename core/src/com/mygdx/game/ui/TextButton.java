package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;

public class TextButton extends UiComponent {

    public String text;
    public BitmapFont font;

    public TextButton(BitmapFont font, String text, float x, float y) {
        super(x, y);
        this.text = text;
        this.font = font;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        font.draw(myGdxGame.batch, text, x, y);
    }

}
