package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Gold {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int RADIUS = 20;
    public static final double ACCELERATION = 0.13;
    private double speed = 4;
    private double acceleration = 0;


    private Vector2 position;
    private World world;
    public int number;
    public boolean collision;
    private Texture goldImg;
    private Texture transparentImg;
    private Circle circle;


    public Gold(int x, int y, int number,World world ) {
        position = new Vector2(x, y);
        this.world = world;
        this.number = number;
        this.collision = false;
        goldImg = new Texture("jetpack.png");
        transparentImg = new Texture("transparent.png");
        circle = new Circle(position,WIDTH/2);
    }

    public Circle getCircle() {
        return circle;
    }

    public Texture getTexture(){
        if(collision)return transparentImg;
        else return goldImg;
    }
    public void setCollision(){
        collision=true;
    }
    public Vector2 getPosition(){
        return position;
    }
    public void setSpeed(double spd){
        this.speed= spd;

    }
    public void update() {
        position.x -= speed;
        circle.setPosition(position.x,position.y);
    }


}
