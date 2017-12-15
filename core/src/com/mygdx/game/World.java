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
    public LinkedList<Gold> gold = new LinkedList<Gold>();
    private int numberOfGold = 0;
    private boolean removeGold = false;
    private boolean removeBlock = false;
    public LinkedList<Block> block = new LinkedList<Block>();
    private int frame = 0;

    World(JetpackJoyrideGame jetpackjoyrideGame) {
        flyer = new Flyer(100, 100, this);
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        score = 0;
    }

    private void updatePacmanDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            flyer.jumpUp();
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            flyer.jumpDown();
        }
    }

    public void createGold(int posGold) {
        gold.add(new Gold(jetpackjoyrideGame.WIDTH, posGold, numberOfGold++, this));
    }

    public int createBlock() {
        int top, down;
        block.add(new Block(jetpackjoyrideGame.WIDTH, jetpackjoyrideGame.HEIGHT - 40, random(1, 7),true, this));
        top = block.getLast().getLength();
        block.add(new Block(jetpackjoyrideGame.WIDTH, 0, random(1, 7), false,this));
        down = block.getLast().getLength();
        return random((down + 1) * block.getLast().HEIGHT, jetpackjoyrideGame.HEIGHT - (top + 1) * block.getLast().HEIGHT);
    }

    public void createMap() {
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
    }

    public int getScore() {
        return (int) score;
    }
    public void setScore(int a){
        score = a;
    }

    public void increaseScore() {
        score += 1;
    }

    Flyer getFlyer() {
        return flyer;
    }
}
