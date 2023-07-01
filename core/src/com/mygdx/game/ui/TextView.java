package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.GameSettings;

public class TextView extends UiComponent {

    public String text;
    public BitmapFont font;

    // send -1 as x to make text align center
    public TextView(BitmapFont font, String text, float x, float y) {
        super(x, y);
        this.font = font;
        this.text = text;

        GlyphLayout gl = new GlyphLayout(font, text);
        width = gl.width;
        height = gl.height;

        if ((int) x == -1) this.x = GameSettings.SCR_WIDTH / 2 - width / 2;
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        font.draw(myGdxGame.batch, text, x, y);
    }
}
