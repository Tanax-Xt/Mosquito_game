package com.mygdx.game.utils;

public class GameSettings {

    public static int SCR_WIDTH = 1920;
    public static int SCR_HEIGHT = 1080;

    public static DifficultyLevel DEFAULT_DIFFICULTY = DifficultyLevel.MEDIUM;
    public static boolean DEFAULT_SOUND_STATE = true;

    public static final int BUTTERFLY_DAMAGE = 30;

    // Easy settings:
    public static final int COUNT_OF_ENEMIES_EASY = 15;
    public static final float ENEMY_SPEED_EASY = 10;
    public static final int MAX_HIT_POINTS_EASY = 100;

    // Medium settings:
    public static final int COUNT_OF_ENEMIES_MEDIUM = 30;
    public static final float ENEMY_SPEED_MEDIUM = 15;
    public static final int MAX_HIT_POINTS_MEDIUM = 50;

    // Hard settings:
    public static final int COUNT_OF_ENEMIES_HARD = 45;
    public static final float ENEMY_SPEED_HARD = 20;
    public static final int MAX_HIT_POINTS_HARD = 10;
}