package Pong;

import Pong.GameObjects.GameObject;

//Created by Christopher Wolff
public class Paddle extends GameObject {
	//constructor 
	public Paddle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//update the position of the paddles
	public void update(){
		//update the yPos
		y += speed;
		
		//top of the screen
		if (y < 0){
			y = 0;
		}
		
		//bottom of the screen
		if (y > Game.GAME_HEIGHT - 110){
			y = Game.GAME_HEIGHT - 110;
		}
	}
	
	public void moveUp(){
		speed = -2;
	}

	public void moveDown(){
		speed = 2;
	}

	public void stop(){
		speed = 0;
	}
	
	//getter
	public double getxPos() {
		return x;
	}
	
	//getter
	public double getyPos() {
		return y;
	}

	public double getSpeed() {
		return speed;
	}
}
