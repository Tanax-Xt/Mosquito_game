package com.mygdx.game.utils;


import com.badlogic.gdx.Game;

public enum DifficultyLevel {

    EASY(GameSettings.COUNT_OF_ENEMIES_EASY,
            GameSettings.ENEMY_SPEED_EASY,
            GameSettings.MAX_HIT_POINTS_EASY,
            1),

    MEDIUM(GameSettings.COUNT_OF_ENEMIES_MEDIUM,
            GameSettings.ENEMY_SPEED_MEDIUM,
            GameSettings.MAX_HIT_POINTS_MEDIUM,
            2),

    HARD(GameSettings.COUNT_OF_ENEMIES_HARD,
            GameSettings.ENEMY_SPEED_HARD,
            GameSettings.MAX_HIT_POINTS_HARD,
            3);

    private final int difficultyLevelIdx;
    private final int countOfEnemies;
    private final int userHitPoints;
    private final float enemySpeed;
//    private final float sizeChangeConst;


    DifficultyLevel(int countOfEnemies, float enemySpeed, int userHitPoints, int difficultyLevelIdx) {
        this.difficultyLevelIdx = difficultyLevelIdx;
        this.countOfEnemies = countOfEnemies;
        this.userHitPoints = userHitPoints;
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

    public int getUserHitPoints() {
        return userHitPoints;
    }

//    public float getSizeChangeConst() {
//        return sizeChangeConst;
//    }
}