package me.palombo.gamecore.game;

public enum GameState {

    WAITING(true),
    STARTING(true),
    WARMUP(false),
    IN_GAME(true),
    ENDING(false),
    RESTARTING(false);

    private boolean joinable;

    GameState(boolean joinable) {
        this.joinable = joinable;
    }

    public boolean isJoinable() {
        return joinable;
    }
}
