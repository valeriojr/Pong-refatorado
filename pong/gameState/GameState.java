package pong.gameState;

import pong.Game;

public abstract class GameState {
    protected Game game;

    public GameState(Game game){
        this.game = game;
    }

    public abstract void update();
    public abstract void draw();
}
