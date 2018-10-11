package pong.gameObjects.paddle;

import pong.Input;

import java.awt.event.KeyEvent;

public class PlayerControlledState extends PaddleState {

    public PlayerControlledState(int id) {
        super(id);
    }

    @Override
    public void update() {
        Input input = Input.getInstance();

        if(input.isKeyDown(KeyEvent.VK_UP)){
            paddle.moveUp();
        }else if(input.isKeyDown(KeyEvent.VK_DOWN)){
            paddle.moveDown();
        }
    }
}
