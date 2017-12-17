package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Gold {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int RADIUS = 20;

    private Vector2 position;
    private World world;
    public int number;
    public boolean collision;
    private Texture goldImg;
    private Texture transparentImg;
    private Circle circle;

    public Gold(int x, int y, int number, World world) {
        position = new Vector2(x, y);
        this.world = world;
        this.number = number;
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
        position.x -= world.speed;
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
}
