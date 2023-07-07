package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.uiComponents.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class Bee extends Character{
    OnHitBeeListener onHitButterflyListener;

    public Bee(ArrayList<Texture> texturesArray, float velocity,
               OnHitBeeListener onHitButterflyListener) {
        super(texturesArray);
        x = GameSettings.SCR_WIDTH / 2 - width / 2;
        y = GameSettings.SCR_HEIGHT / 2 - height / 2;
        velocityX = MathUtils.random(-velocity, velocity);
        velocityY = (float) ((MathUtils.random(0, 5) % 2 - 0.5) * 2 * Math.sqrt(velocity * velocity - velocityX * velocityX));
        this.onHitButterflyListener = onHitButterflyListener;
        actorImgView.setOnClickListener(beeOnClickListener);
    }

    UiComponent.OnClickListener beeOnClickListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            onHitButterflyListener.onHit();
        }
    };

    public interface OnHitBeeListener {
        void onHit();
    }

}
