package Pong;//Created by Christopher Wolff

import Pong.GameObjects.GameObject;

public class Ball extends GameObject {
	private double angle;
	boolean destroyable;
		
	//When a ball is created, we can provide a default value
	//to the constructor
	public Ball(int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		//init the velocity 
		xDir = Math.cos(angle);
		yDir = Math.sin(angle);
		destroyable = false;
		//System.out.println(angle);
	
	}
	//update position
	public void updateBall(){
		x += speed * xDir;
		y += speed * yDir;
		//System.out.println(angle);
		//right bound checking
		if (x > Game.GAME_WIDTH -(4 * Game.BALL_RADIUS)){
			changeX();
			destroyable = true;
		}
		
		//left bound checking
		if (x < 0){
			changeX();
			destroyable = true;
		}
		
		//down bound checking
		if (y > Game.GAME_HEIGHT - (6 * Game.BALL_RADIUS)){
			changeY();
			
		}
		
		//upper bound checking
		if (y < 0){
			changeY();
		}

	}
	
	//for swapping directions
	//not encapsulated
	public void changeX(){
		xDir *= -1;
	}
	
	public void changeY(){
		yDir *= -1;
	}
	
	public boolean isDestroyable(){
		return destroyable;
	}
}
