package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class World {
    private Flyer flyer;
    private int score;
    private JetpackJoyrideGame jetpackjoyrideGame;
    private GameScreen gameScreen;
    World(JetpackJoyrideGame jetpackjoyrideGame) {
        flyer = new Flyer(100,100,this);
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        score = 0;
    }

    private void updatePacmanDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            flyer.setNextDirection(Flyer.DIRECTION_UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            flyer.setNextDirection(Flyer.DIRECTION_DOWN);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            flyer.setNextDirection(Flyer.DIRECTION_LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            flyer.setNextDirection(Flyer.DIRECTION_RIGHT);
        }
        if (!(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
            flyer.setNextDirection(Flyer.DIRECTION_STILL);
        }
    }

    public void update(float delta) {
        updatePacmanDirection();
        flyer.update();
    }

    public int getScore() {
        return score;
    }
    public void increaseScore() {
        score += 1;
    }

    Flyer getFlyer() {
        return flyer;
    }
}
