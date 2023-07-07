package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.uiComponents.UiComponent;
import com.mygdx.game.utils.DifficultyLevel;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class Mosquito extends Character {

    Texture deadMosquitoTexture;
    OnKillMosquitoListener onKillMosquitoListener;

    public Mosquito(ArrayList<Texture> texturesArray, Texture deadMosquitoTexture, float velocity,
                    OnKillMosquitoListener onKillMosquitoListener, DifficultyLevel difficultyLevel) {
        super(texturesArray);
        x = MathUtils.random(1, GameSettings.SCR_WIDTH - GameSettings.SCR_WIDTH / 5);
        y = MathUtils.random(1, GameSettings.SCR_HEIGHT - GameSettings.SCR_HEIGHT / 5);

        velocityX = MathUtils.random(-velocity, velocity);
        velocityY = (float) ((MathUtils.random(0, 1) - 0.5) * 2 * Math.sqrt(velocity * velocity - velocityX * velocityX));

        this.deadMosquitoTexture = deadMosquitoTexture;
        this.onKillMosquitoListener = onKillMosquitoListener;
        actorImgView.setOnClickListener(mosquitoOnClicked);
    }

    UiComponent.OnClickListener mosquitoOnClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            if (!isAlive) return;
            isAlive = false;
            actorImgView.setImgTexture(deadMosquitoTexture);
            onKillMosquitoListener.onKill();
        }
    };

    public interface OnKillMosquitoListener {
        void onKill();
    }

}