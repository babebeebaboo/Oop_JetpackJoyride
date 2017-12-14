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
    //private Gold gold;
    public LinkedList<Gold> gold = new LinkedList<Gold>();
    private int numberOfGold = 0;
    private boolean removeGold = false;

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

    public void createGold() {
        if (random(0, 100) < 2) {
            gold.add(new Gold(jetpackjoyrideGame.WIDTH, jetpackjoyrideGame.HEIGHT - 500, numberOfGold++, this));
            // System.out.println(gold.getLast().number);
        }
    }

    public void update(float delta) {
        removeGold = false;
        updatePacmanDirection();
        increaseScore();
        flyer.update();

        //GOLD
        createGold();
        for (Gold g : gold) {
            g.update();
            g.setSpeed(random(1,10));
            if (g.getPosition().x < 0) removeGold = true;
        }
        if (removeGold) gold.removeFirst();
    }

    public int getScore() {
        return (int) score;
    }

    public void increaseScore() {
        score += 0.1;
    }

    Flyer getFlyer() {
        return flyer;
    }
}
