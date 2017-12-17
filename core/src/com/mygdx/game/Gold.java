package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

class Gold {
    public static final int HEIGHT = 40;
    private static final int WIDTH = 40;
    public static final int RADIUS = 20;

    private final Vector2 position;
    private final World world;
    private boolean collision;
    private final Texture goldImg;
    private final Texture transparentImg;
    private final Circle circle;

    public Gold(int x, int y, World world) {
        position = new Vector2(x, y);
        this.world = world;
        this.collision = false;
        goldImg = new Texture("gold.png");

        transparentImg = new Texture("transparent.png");
        circle = new Circle(x, y, WIDTH / 2);
    }

    public Texture getTexture() {
        if (world.isGameOver()) {
            return transparentImg;
        }
        if (collision) {
            return transparentImg;
        } else {
            return goldImg;
        }
    }

    public void update() {
        position.x -= world.getSpeed();
        if (world.isGameOver()) {
            circle.setPosition(-100, -100);
        } else {
            circle.setPosition(position.x, position.y);
        }
    }

    public Circle getCircle() {
        return circle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setCollision() {
        collision = true;
    }

    public boolean isCollision() {
        return collision;
    }
}
