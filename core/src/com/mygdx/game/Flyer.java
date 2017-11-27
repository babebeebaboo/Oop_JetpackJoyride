package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Flyer {
    private LinkedList<DotEattenListener> listeners;
    public double speed = 0;
    private double acceleration = 0;
    private int currentDirection;
    private int nextDirection;


    private Vector2 position;
    private World world;


    public Flyer(int x, int y, World world) {
        position = new Vector2(x, y);

        this.world = world;
        listeners = new LinkedList<DotEattenListener>();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setNextDirection(int dir) {
        nextDirection = dir;
    }
    public void jumpUp(){
        if(position.y >= JetpackJoyrideGame.HEIGHT){
            acceleration = 0;
        }
        else{
            acceleration = 0.1;
        }
    }
    public void jumpDown(){
        if(position.y <= 0){
            acceleration = 0;
        }
        else{
            acceleration = -0.1;
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
        if(position.y >= JetpackJoyrideGame.HEIGHT-40){
            position.y = JetpackJoyrideGame.HEIGHT-40;
            speed = 0;
        }
    }
    private int getRow() {
        return ((int)position.y) ;
    }

    private int getColumn() {
        return ((int)position.x) ;
    }

    public boolean isAtCenter() {
        return ((((int) position.x ) ) == 0)
                && ((((int) position.y ) ) == 0);
    }
    public interface DotEattenListener {
        void notifyDotEatten();
    }
    public void registerDotEattenListener(DotEattenListener l) {
        listeners.add(l);
    }

    private void notifyDotEattenListeners() {
        for(DotEattenListener l : listeners) {
            l.notifyDotEatten();
        }
    }
}
