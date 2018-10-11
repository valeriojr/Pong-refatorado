package pong.gameObjects.paddle;

import pong.Game;
import pong.gameObjects.Ball;
import pong.gameObjects.ObjectManager;

import java.util.Random;

public abstract class PaddleState {
    protected Ball ball;
    protected Paddle paddle;
    protected int stateTime;
    protected Random random;

    public PaddleState(int id){
        stateTime = 0;
        random = new Random();
        ball = (Ball) ObjectManager.getInstance().getObject(Game.BALL);
        paddle = (Paddle) ObjectManager.getInstance().getObject(id);
    }

    public abstract void update();
}
