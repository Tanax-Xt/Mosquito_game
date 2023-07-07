package com.mygdx.game.utils;

import com.badlogic.gdx.Game;

public enum DifficultyLevel {

    EASY(GameSettings.COUNT_OF_ENEMIES_EASY, GameSettings.ENEMY_SPEED_EASY, 1, 1.2f),
    MEDIUM(GameSettings.COUNT_OF_ENEMIES_MEDIUM, GameSettings.ENEMY_SPEED_MEDIUM, 2, 0.8f),
    HARD(GameSettings.COUNT_OF_ENEMIES_HARD, GameSettings.ENEMY_SPEED_HARD, 3, 0.5f);

    private final int difficultyLevelIdx;
    private final int countOfEnemies;
    private final float enemySpeed;
    private final float sizeChangeConst;

    public float getSizeChangeConst() {
        return sizeChangeConst;
    }

    DifficultyLevel(int countOfEnemies, float enemySpeed, int difficultyLevelIdx, float sizeChangeConst) {
        this.difficultyLevelIdx = difficultyLevelIdx;
        this.countOfEnemies = countOfEnemies;
        this.enemySpeed = enemySpeed;
        this.sizeChangeConst = sizeChangeConst;
    }

    public int getCountOfEnemies() {
        return countOfEnemies;
    }

    public float getEnemySpeed() {
        return enemySpeed;
    }

    public int getDifficultyLevelIdx() {
        return difficultyLevelIdx;
    }
}
