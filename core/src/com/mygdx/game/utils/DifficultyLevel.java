package com.mygdx.game.utils;

public enum DifficultyLevel {

    EASY(15, 10, 1),
    MEDIUM(30, 15, 2),
    HARD(45, 20, 3);

    private final int difficultyLevelIdx;
    private final int countOfEnemies;
    private final float enemySpeed;

    DifficultyLevel(int countOfEnemies, float enemySpeed, int difficultyLevelIdx) {
        this.difficultyLevelIdx = difficultyLevelIdx;
        this.countOfEnemies = countOfEnemies;
        this.enemySpeed = enemySpeed;
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
