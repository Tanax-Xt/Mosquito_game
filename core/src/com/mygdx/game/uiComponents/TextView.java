package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.uiComponents.UiComponent;

public class TextView extends UiComponent {

    public String text;
    public BitmapFont font;

    // send -1 as x to make text align center
    public TextView(BitmapFont font, String text, int x, int y) {
        super(x, y);
        this.font = font;
        this.text = text;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;

        if ((int) x == -1) this.x = GameSettings.SCR_WIDTH / 2 - width / 2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        font.draw(spriteBatch, text, x, y);
    }
}