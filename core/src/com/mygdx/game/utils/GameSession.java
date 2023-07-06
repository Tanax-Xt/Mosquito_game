package com.mygdx.game.utils;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    public static final int PLAY_GAME = 0;
    public static final int END_OF_GAME = 1;
    public static final int GAME_OVER = 2;
    public static final int PAUSE_GAME = 3;
    private int gameState = -1;
    public long startTime;
    public long pauseStartTime;

    public GameSession() {
        gameState = PLAY_GAME;
        startTime = TimeUtils.millis();
    }

    public String getSessionTime() {
        long sessionTime = TimeUtils.millis() - startTime;
        String min = "" + sessionTime / 1000 / 60 / 10 + sessionTime / 1000 / 60 % 10;
        String sec = "" + sessionTime / 1000 % 60 / 10 + sessionTime / 1000 % 60 % 10;
        return min + ":" + sec;
    }

    public void setGameState(int newGameState) {
        if (newGameState == PAUSE_GAME) {
            pauseStartTime = TimeUtils.millis();
        } else if (newGameState == PLAY_GAME && this.gameState == PAUSE_GAME) {
            long res = TimeUtils.millis() - pauseStartTime;
            startTime -= res;
        }
        this.gameState = newGameState;
    }

    public int getGameState() {
        return gameState;
    }
}