package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Gold {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final double ACCELERATION = 0.13;
    private double speed = 4;
    private double acceleration = 0;


    private Vector2 position;
    private World world;
    public int number;


    public Gold(int x, int y, int number,World world ) {
        position = new Vector2(x, y);
        this.world = world;
        this.number = number;
    }

    public Vector2 getPosition() {
        return position;
    }
    public void setSpeed(double spd){
        this.speed= spd;
    }
    public void update() {
        position.x -= speed;
    }


}
