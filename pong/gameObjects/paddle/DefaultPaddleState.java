package pong.gameObjects.paddle;

public class DefaultPaddleState extends PaddleState {

    public DefaultPaddleState(int id){
        super(id);
    }

    @Override
    public void update() {
        if (paddle.getY() < ball.getY()){
            paddle.moveDown();
        }else {
            paddle.moveUp();
        }
    }
}
