package pong.gameObjects.paddle;

public class CPUControlledState extends PaddleState{

    public CPUControlledState(int id) {
        super(id);
    }

    @Override
    public void update() {
        if (stateTime++ < 100) {
            if (ball.getY() > paddle.getY()) {
                paddle.moveDown();
            } else if (ball.getY() < paddle.getY()) {
                paddle.moveUp();
            } else if (ball.getX() == paddle.getY()) {
                paddle.stop();
            }
        } else {
            stateTime = 0;
            int choice = random.nextInt(3);
            if (choice == 1) {
                paddle.moveDown();
            } else if (choice == 0) {
                paddle.moveUp();
            }
        }
    }
}
