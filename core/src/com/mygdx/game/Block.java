package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
    //TODO: Give Block a new Sprite

    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final double ACCELERATION = 0.13;
    private double speed = 4;
    private double acceleration = 0;

    private Vector2 position;
    private World world;
    public int number;
    private int length;
    private Texture blockImg;
    private Rectangle rectangle;
    private boolean isOnTop;

    public Block(int x, int y, int length, boolean isOnTop, World world) {
        position = new Vector2(x, y);
        this.world = world;
        this.length = length;
        blockImg = new Texture("wall.png");
        this.isOnTop = isOnTop;
        if (isOnTop) {
            rectangle = new Rectangle(x, y - HEIGHT * length, WIDTH, HEIGHT * length);
        } else {
            rectangle = new Rectangle(x, y, WIDTH, HEIGHT * length);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }

    public void update() {
        position.x -= speed;
        if (isOnTop) {
            rectangle.setPosition(position.x, position.y - HEIGHT * length + 40);

        } else {
            rectangle.setPosition(position.x, position.y);
        }
    }

    public Texture getTexture() {
        return blockImg;
    }
}
