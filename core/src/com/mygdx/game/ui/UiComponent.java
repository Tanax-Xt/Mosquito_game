package com.mygdx.game.ui;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.GameSettings;

public class UiComponent {

    float x;
    float y;
    float vx;
    float vy;
    float width;
    float height;
    public boolean isVisible;
    public OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    UiComponent(float x, float y, float width, float height) {
        this(x, y);
        this.width = width;
        this.height = height;
        isVisible = true;

        vx = (float) (20 + Math.random() * 10);
        vy = (float) (20 + Math.random() * 10);
    }

    UiComponent(float x, float y) {
        this.x = x;
        this.y = y;
        this.onClickListener = null;
        isVisible = true;
    }

    public void draw(MyGdxGame myGdxGame) {};

    public boolean isHit(float tx, float ty){
        boolean isTouchHitComponent = x < tx && tx < x + width && y > ty && ty > y - height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClicked();
        return isTouchHitComponent;
    }

    public interface OnClickListener {
        void onClicked();
    }

    public void setX() {
        this.x += vx;
        if (this.x < 0 || this.x + width > GameSettings.SCR_WIDTH) this.x = -vx;
    }

    public void setY() {
        this.y += vy;
        if (this.y < 0 || this.y + height > GameSettings.SCR_HEIGHT) this.y = -vy;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
