package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Flyer {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final double ACCELERATION = 0.13;
    public double speed = 0;
    private double acceleration = 0;


    private Vector2 position;
    private World world;


    public Flyer(int x, int y, World world) {
        position = new Vector2(x, y);
        this.world = world;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void jumpUp(){
        if(this.getRow() >= JetpackJoyrideGame.HEIGHT){
            acceleration = 0;
        }
        else{
            acceleration = ACCELERATION;
        }
    }
    public void jumpDown(){
        if(this.getRow() <= 0){
            acceleration = 0;
        }
        else{
            acceleration = -ACCELERATION;
        }
    }
    public void update() {
        speed += acceleration;
        //System.out.println(speed);
        position.y += speed;
        if (position.y <= 0){
            position.y = 0;
            speed = 0;
        }
        if(this.getRow() >= JetpackJoyrideGame.HEIGHT-HEIGHT){
            position.y = JetpackJoyrideGame.HEIGHT-HEIGHT;
            speed = 0;
        }
    }
    public int getRow() {
        return ((int)position.y) ;
    }
    private int getColumn() {
        return ((int)position.x) ;
    }
    public boolean isAtCenter() {
        return ((((int) position.x ) ) == 0) && ((((int) position.y ) ) == 0);
    }
    public boolean isAtRoof(){
        return position.y >= JetpackJoyrideGame.HEIGHT-HEIGHT;
    }
    public boolean onTheFloor(){
        return position.y == 0;
    }
}
