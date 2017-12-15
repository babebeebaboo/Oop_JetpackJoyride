package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static javafx.application.Platform.exit;

public class Flyer {
    public static final int HEIGHT = 80;
    public static final int WIDTH = 70;
    public static final double ACCELERATION = 0.13;
    public double speed = 0;
    private double acceleration = 0;
    public Rectangle rectangle;

    private Vector2 position;
    private Vector2 gPosition;
    private Vector2 fPosition;

    private World world;

    public Flyer(int x, int y, World world) {
        position = new Vector2(x, y);
        this.world = world;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void jumpUp() {
        if (position.y >= JetpackJoyrideGame.HEIGHT) {
            acceleration = 0;
        } else {
            acceleration = ACCELERATION;
        }
    }

    public void jumpDown() {
        if (position.y <= 0) {
            acceleration = 0;
        } else {
            acceleration = -ACCELERATION;
        }
    }

    public void update() {
        speed += acceleration;
        position.y += speed;
        if (position.y <= 0) {
            position.y = 0;
            speed = 0;
        }
        if (position.y >= JetpackJoyrideGame.HEIGHT - HEIGHT) {
            position.y = JetpackJoyrideGame.HEIGHT - HEIGHT;
            speed = 0;
        }
        for (Gold g : world.gold) {
            if (checkCollisionGold(g)) {
                if (!g.collision) {
                    world.increaseScore();
                }
                g.setCollision();
            }
        }
        for (Block b : world.block) {
            if (checkCollisionBlock(b)) {
                world.setScore(-1);
            }
        }
        rectangle.setPosition(position.x, position.y);
    }

    public boolean checkCollisionBlock(Block b) {
        return Intersector.overlaps(b.getRectangle(), rectangle);
    }

    public boolean checkCollisionGold(Gold g) {
        return Intersector.overlaps(g.getCircle(), rectangle);
    }
}
