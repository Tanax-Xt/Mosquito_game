package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class SoundExecutor {

    static Music backSound = Gdx.audio.newMusic(Gdx.files.internal("audio/background.mp3"));
    static Sound[] mosquitoesSounds = {
            Gdx.audio.newSound(Gdx.files.internal("audio/mosq0.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/mosq1.mp3"))
    };

    public static void playBackSound() {
        backSound.play();
        backSound.setLooping(true);
    }

    public static void stopPlaying() {
        backSound.stop();
    }

    public static void playMosquitoSound() {
        mosquitoesSounds[MathUtils.random(0, mosquitoesSounds.length - 1)].play();
    }


}

