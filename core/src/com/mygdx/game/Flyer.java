package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Gdx.audio;

class Flyer {
    public static final int HEIGHT = 80;
    private static final int WIDTH = 70;
    private static final double ACCELERATION = 0.13;
    private double speed = 0;
    private double acceleration = 0;
    private final Sound collectSound;

    private final Vector2 position;
    private final Rectangle rectangle;

    private final World world;

    public Flyer(int x, int y, World world) {
        position = new Vector2(x, y);
        this.world = world;
        rectangle = new Rectangle(x + 20, y, WIDTH - 20, HEIGHT);
        collectSound = audio.newSound(Gdx.files.internal("coin-sound-effect-trim2.mp3"));
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
                collectSound.stop();
                collectSound.play(0.2f);
                if (!g.isCollision()) {
                    world.increaseScore();
                }
                g.setCollision();
            }
        }
        for (Block b : world.block) {
            if (checkCollisionBlock(b)) {
                world.setGameOver();
            }
        }
        if (world.isGameOver()) {
            jumpDown();
        }
        rectangle.setPosition(position.x + 20, position.y);
    }

    private boolean checkCollisionBlock(Block b) {
        return Intersector.overlaps(b.getRectangle(), rectangle);
    }

    private boolean checkCollisionGold(Gold g) {
        return Intersector.overlaps(g.getCircle(), rectangle);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sound getCollectSound() {
        return collectSound;
    }
}
