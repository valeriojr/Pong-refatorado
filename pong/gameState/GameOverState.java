package pong.gameState;

import pong.Game;
import pong.Input;

import java.awt.event.KeyEvent;

public class GameOverState extends GameState {

    public GameOverState(Game game) {
        super(game);
    }

    @Override
    public void update() {
        if(Input.getInstance().isKeyDown(KeyEvent.VK_ENTER)){
            game.setGameState(new RunningState(game));
        }
    }

    @Override
    public void draw() {

    }
}
