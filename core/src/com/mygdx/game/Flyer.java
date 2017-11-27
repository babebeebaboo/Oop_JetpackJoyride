package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Flyer {
    private LinkedList<DotEattenListener> listeners;
    public static final int SPEED = 5;
    private int currentDirection;
    private int nextDirection;
    private static final int[][] DIR_OFFSETS = new int[][] {
            { 0, 0 },
            { 0, -1 },
            { 1, 0 },
            { 0, 1 },
            { -1, 0 }
    };
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    private Vector2 position;
    private World world;


    public Flyer(int x, int y, World world) {
        position = new Vector2(x, y);
        currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        this.world = world;
        listeners = new LinkedList<DotEattenListener>();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void move(int dir) {
        position.x += SPEED * DIR_OFFSETS[dir][0];
        position.y += SPEED * DIR_OFFSETS[dir][1];
    }
    public void setNextDirection(int dir) {
        nextDirection = dir;
    }
    public void update() {
        position.x += SPEED * DIR_OFFSETS[nextDirection][0];
        position.y += SPEED * DIR_OFFSETS[nextDirection][1];
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
