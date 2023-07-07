package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.uiComponents.UiComponent;
import com.mygdx.game.utils.DifficultyLevel;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class Butterfly extends Character {
    Texture deadMosquitoTexture;
    Mosquito.OnKillMosquitoListener onKillMosquitoListener;

    public Butterfly(ArrayList<Texture> texturesArray, Texture deadMosquitoTexture, float velocity,
                    Mosquito.OnKillMosquitoListener onKillMosquitoListener, DifficultyLevel difficultyLevel) {
        super(texturesArray);
//        actorImgView.width = actorImgView.height *= difficultyLevel.getSizeChangeConst();
//        width = height *= difficultyLevel.getSizeChangeConst();

        x = MathUtils.random(1, GameSettings.SCR_WIDTH - GameSettings.SCR_WIDTH / 2);
        y = MathUtils.random(1, GameSettings.SCR_HEIGHT - GameSettings.SCR_HEIGHT / 2);

        velocityX = MathUtils.random(-velocity, velocity);
        velocityY = (float) ((MathUtils.random(0, 1) - 0.5) * 2 * Math.sqrt(velocity * velocity - velocityX * velocityX));

        this.deadMosquitoTexture = deadMosquitoTexture;
        this.onKillMosquitoListener = onKillMosquitoListener;
        actorImgView.setOnClickListener(butterflyOnClicked);
    }

    UiComponent.OnClickListener butterflyOnClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            if (!isAlive) return;
            isAlive = false;
            actorImgView.setImgTexture(deadMosquitoTexture);
            onKillMosquitoListener.onKill();
        }
    };

    public interface OnHitButterflyListener {
        void onHit();
    }
}
