package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {

    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;

    private Vector2 position;
    private World world;
    private int length;
    private Texture blockImg;
    private Texture transparentImg;

    private Rectangle rectangle;
    private boolean isOnTop;

    public Block(int x, int y, int length, boolean isOnTop, World world) {
        position = new Vector2(x - WIDTH / 2, y);
        this.world = world;
        this.length = length;
        blockImg = new Texture("wall2.png");
        transparentImg = new Texture("transparent.png");

        this.isOnTop = isOnTop;
        if (isOnTop) {
            rectangle = new Rectangle(x - WIDTH / 2, y - HEIGHT * length, WIDTH, HEIGHT * length);
        } else {
            rectangle = new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT * length);
        }
    }


    public void update() {
        position.x -= world.speed;
        if (world.isGameOver()) {
            rectangle.setPosition(-100, -100);
        } else if (isOnTop) {
            rectangle.setPosition(position.x, position.y - HEIGHT * length + 40);
        } else {
            rectangle.setPosition(position.x, position.y);
        }
    }

    public Texture getTexture() {
        return world.isGameOver() ? transparentImg : blockImg;
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
}
