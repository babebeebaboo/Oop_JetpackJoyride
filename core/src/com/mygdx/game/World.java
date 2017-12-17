package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.LinkedList;

import static com.badlogic.gdx.math.MathUtils.random;

public class World {
    private Flyer flyer;
    private float score;
    private JetpackJoyrideGame jetpackjoyrideGame;
    private GameScreen gameScreen;

    //Gold
    public LinkedList<Gold> gold = new LinkedList<Gold>();
    private int numberOfGold = 0;
    private boolean removeGold = false;

    //Block
    public LinkedList<Block> block = new LinkedList<Block>();
    private boolean removeBlock = false;

    //inGame
    private int frame = 0;
    public int speed = 4;
    private boolean isGameRunning = true;

    public World(JetpackJoyrideGame jetpackjoyrideGame) {
        flyer = new Flyer(100, 100, this);
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        score = 0;
    }

    private void updatePacmanDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && isGameRunning) {
            flyer.jumpUp();
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE) && isGameRunning) {
            flyer.jumpDown();
        }
    }

    public int createBlock() {
        int top, down;
        block.add(new Block(jetpackjoyrideGame.WIDTH, jetpackjoyrideGame.HEIGHT - 40, random(1, maxBlocklength()), true, this));
        top = block.getLast().getLength();
        block.add(new Block(jetpackjoyrideGame.WIDTH, 0, random(1, maxBlocklength()), false, this));
        down = block.getLast().getLength();
        return random((down + 1) * block.getLast().HEIGHT, jetpackjoyrideGame.HEIGHT - (top + 1) * block.getLast().HEIGHT);
    }

    private int maxBlocklength() {
        if (score <= 10) {
            return 1;
        }
        if (score <= 20) {
            return 2;
        }
        if (score <= 30) {
            return 3;
        }
        if (score <= 40) {
            return 4;
        }
        if (score <= 50) {
            return 5;
        }
        if (score <= 60) {
            return 6;
        }
        if (score <= 70) {
            return 7;
        }
        return 7;
    }

    public void createMap() {
        if (isGameOver()) {
            return;
        }
        if (random(0, 100) < 20) {
            int posGold = createBlock();
            createGold(posGold);
        }
    }

    public void update(float delta) {
        removeGold = false;
        removeBlock = false;
        updatePacmanDirection();
        flyer.update();
        if (frame >= 20 * 1.5) {
            createMap();
            frame = 0;
        }
        for (Gold g : gold) {
            g.update();
            if (g.getPosition().x < -100) {
                removeGold = true;
            }
        }
        if (removeGold) gold.removeFirst();
        for (Block b : block) {
            b.update();
            if (b.getPosition().x < -100) {
                removeBlock = true;
            }
        }
        if (removeBlock) {
            block.removeFirst();
            block.removeFirst();
        }
        frame++;
        setSpeedbyScore();
        if (!isGameRunning) {
            speed = 0;
        }
    }

    public void setSpeedbyScore() {
        if (score < 0) {
            speed = 0;
        } else if (score < 10) {
            speed = 3;
        } else if (score < 20) {
            speed = 4;
        } else if (score < 30) {
            speed = 5;
        } else if (score < 40) {
            speed = 6;
        } else if (score < 50) {
            speed = 7;
        } else if (score < 60) {
            speed = 8;
        } else if (score < 70) {
            speed = 9;
        } else if (score < 80) {
            speed = 10;
        }
    }

    Flyer getFlyer() {
        return flyer;
    }

    public void createGold(int posGold) {
        gold.add(new Gold(jetpackjoyrideGame.WIDTH, posGold, numberOfGold++, this));
    }

    public void setGameOver() {
        isGameRunning = false;
    }

    public void increaseScore() {
        score += 1;
    }

    public boolean isSpacePress() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public boolean isGameOverAndExit() {
        return isGameOver() && Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    public boolean isGameOver() {
        return !isGameRunning;
    }

    public int getScore() {
        return (int) score;
    }
}
