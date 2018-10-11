package pong.gameObjects;

import pong.Game;

import java.awt.*;
import java.util.Iterator;

public abstract class GameObject {
    public enum Type{
        Paddle,
        Ball
    }

    protected double x, y, speed, xDir, yDir, width, height;
    protected Type type;

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public boolean collide(GameObject other){
        return (Math.abs(x - other.x) < width/2 + other.width/2) && (Math.abs(y - other.y) < height/2 + other.height/2);
    }

    public GameObject collide(Iterator<GameObject> iterator){
        while(iterator.hasNext()){
            GameObject other = iterator.next();
            if(collide(other)){
                return other;
            }
        }
        return null;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean outOfScreen(boolean xAxis, boolean yAxis){
        boolean outOfScreenOnXAxis = xAxis && (x > Game.GAME_WIDTH - width/2 || x < width/2);
        boolean outOfScreenOnYAxis = yAxis && (y > Game.GAME_HEIGHT - height/2 || y < height/2);
        return outOfScreenOnXAxis || outOfScreenOnYAxis;
    }

    public Type getType() {
        return type;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setxDir(double xDir) {
        this.xDir = xDir;
    }

    public void setyDir(double yDir) {
        this.yDir = yDir;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
