package com.mygdx.game.utils;

import static com.mygdx.game.utils.GameSettings.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MemoryLoader {

    private static final Preferences prefs = Gdx.app.getPreferences("User saves");

    public static void saveMusicState(boolean isPlaying) {
        prefs.putString("musicState", String.valueOf(isPlaying));
        prefs.flush();
    }

    public static boolean loadMusicState() {
        if (prefs.contains("musicState"))
            return Boolean.parseBoolean(prefs.getString("musicState"));
        saveMusicState(DEFAULT_SOUND_STATE);
        return true;
    }

    public static void saveDifficultyLevel(DifficultyLevel difficultyLevel) {
        prefs.putString("difficultyLevel", String.valueOf(difficultyLevel.getDifficultyLevelIdx()));
        prefs.flush();
    }

    public static DifficultyLevel loadDifficultyLevel() {
        if (prefs.contains("difficultyLevel")) {
            int difficultyLevelIdx = Integer.parseInt(prefs.getString("difficultyLevel"));
            for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
                if (difficultyLevel.getDifficultyLevelIdx() == difficultyLevelIdx)
                    return difficultyLevel;
            }
        }
        saveDifficultyLevel(DEFAULT_DIFFICULTY);
        return DEFAULT_DIFFICULTY;
    }

}
