package pong.gameObjects;//Created by Christopher Wolff

import pong.Game;
import pong.gameObjects.paddle.Paddle;

import java.awt.*;

import static pong.Game.*;

public class Ball extends GameObject {
	private double angle;

	public Ball(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.xDir = Math.cos(angle);
		this.yDir = Math.sin(angle);
		this.width = 10;
		this.height = 10;
		this.speed = 1;
		this.type = Type.Ball;
	}

	public int bounce(){
        if (y > Game.GAME_HEIGHT - (6 * width)){
            return 0;
        }
        if (y < 0){
            return 0;
        }

        if (x >= GAME_WIDTH -(4 * width)){
            return 1;
        }

        if (x <= 0) {
            return 2;
        }

        return -1;
    }

    @Override
    public void update(){
        ObjectManager objectManager = ObjectManager.getInstance();

        x += speed * xDir;
        y += speed * yDir;

        Paddle collider = (Paddle) collide(objectManager.findObjectsByType(Type.Paddle));

        if (collider  != null){
            changeX();
        }

        if(outOfScreen(false, true)){
            changeY();
        }

        if(outOfScreen(true, false)){
            Paddle p = null;
            System.out.println("Out of Screen!");
            if(x < GAME_WIDTH/2){
                p = (Paddle) objectManager.getObject(COMPUTER_PADDLE);
            }else{
                p = (Paddle) objectManager.getObject(PLAYER_PADDLE);
            }

            p.score();
            reset();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillOval((int) x, (int) y, (int) width, (int) height);
    }

    public void changeX(){
		xDir *= -1;
	}
	
	public void changeY(){
		yDir *= -1;
	}

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void reset(){
	    x = GAME_WIDTH/2;
	    y = GAME_HEIGHT/2;
	    angle += 3.141519;
        xDir = Math.cos(angle);
        yDir = Math.sin(angle);
    }
}
