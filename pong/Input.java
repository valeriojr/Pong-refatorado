package pong;//Created by Christopher Wolff

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input implements KeyListener{
	
	private boolean[] keys;
	private static Input instance;

    public static Input getInstance() {
        if(instance == null){
            instance = new Input();
        }
        return instance;
    }


    public void setComponentListener(Component c){
        c.addKeyListener(this);
    }

	private Input(){
		keys = new boolean[256]; 
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() > 0 && key.getKeyCode() < 256){
			keys[key.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() > 0 && key.getKeyCode() < 256){
			keys[key.getKeyCode()] = false;
		}
	}
	
	@Override 
	public void keyTyped(KeyEvent key){
		
	}

	 public boolean isKeyDown(int keyNum){
		 if (keyNum >0 && keyNum < 256){
			return keys[keyNum];
		 }
		 return false;
	 }
	
}