package pong.gameObjects.paddle;

import pong.gameObjects.GameObject;

import java.awt.*;

//Created by Christopher Wolff
public class Paddle extends GameObject {

    private int score;
    private PaddleState state;

	public Paddle(int id) {
		this.x = 0;
		this.y = 0;
		this.width = 13;
		this.height = 70;
		this.speed = 1;
		this.type = Type.Paddle;
		this.score = 0;
	}

	@Override
	public void update() {
	    state.update();
    }

    @Override
	public void draw(Graphics2D g){
        g.fillRect((int) (x - width/2), (int) (y - height/2), (int) width, (int) height);
    }

    public static class Builder{
	    private Paddle paddle;

	    public Builder(int id){
	        paddle = new Paddle(id);
        }

        public Builder setX(double x){
	        paddle.setX(x);
	        return this;
        }

        public Builder setY(double y){
            paddle.setY(y);
            return this;
        }

        public Builder setWidth(int width){
            paddle.setWidth(width);
            return this;
        }

        public Builder setHeight(int height){
            paddle.setHeight(height);
            return this;
        }

        public Builder setSpeed(double speed){
            paddle.setSpeed(speed);
            return this;
        }

        public Builder setState(PaddleState state){
            paddle.setState(state);
            return this;
        }

        public Paddle getPaddle() {
	        return paddle;
        }
    }

	public void moveUp(){
		y -= speed;
	}

	public void moveDown(){
		y += speed;
	}

	public void stop(){
		speed = 0;
	}

	public double getSpeed() {
		return speed;
	}

    public void score(){
	    this.score++;
    }

    public int getScore() {
        return score;
    }

    public void resetScore(){
	    score = 0;
    }

    public void setState(PaddleState state) {
        this.state = state;
    }

    public void setScore(int score) {
        this.score = score;
    }
}