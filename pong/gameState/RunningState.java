package pong.gameState;

import pong.Game;
import pong.gameObjects.Ball;
import pong.gameObjects.GameObject;
import pong.gameObjects.ObjectManager;

import static pong.Game.BALL;

public class RunningState extends GameState {

    public RunningState(Game game) {
        super(game);
    }

    @Override
    public void update() {
        for(GameObject object : ObjectManager.getInstance().getGameObjects().values()){
            object.update();
        }
        if(((Ball) ObjectManager.getInstance().getObject(BALL)).bounce() == 0){
            game.playSound(Game.wallHit);
        }
    }

    @Override
    public void draw() {
        game.repaint();
    }
}
