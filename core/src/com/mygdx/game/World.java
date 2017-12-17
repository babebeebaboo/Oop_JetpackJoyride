package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

import java.util.LinkedList;

import static com.badlogic.gdx.math.MathUtils.random;

@SuppressWarnings("AccessStaticViaInstance")
class World {
    private final Flyer flyer;
    private float score;
    private final JetpackJoyrideGame jetpackjoyrideGame;

    //Gold
    public final LinkedList<Gold> gold = new LinkedList<Gold>();

    //Block
    public final LinkedList<Block> block = new LinkedList<Block>();

    //inGame
    private int frame = 0;
    private int speed = 4;
    private boolean isGameRunning = true;

    //HIGH SCORE
    private static Preferences prefs;

    public World(JetpackJoyrideGame jetpackjoyrideGame) {
        flyer = new Flyer(100, 100, this);
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        score = 0;

        //High Score
        prefs = Gdx.app.getPreferences("JetpackJoyride");
        if (!prefs.contains("highscore")) {
            prefs.putInteger("highscore", 0);
        }
    }

    private void updateJetPackDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && isGameRunning) {
            flyer.jumpUp();
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE) && isGameRunning) {
            flyer.jumpDown();
        }
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private int createBlock() {
        int top, down;
        block.add(new Block(JetpackJoyrideGame.WIDTH, JetpackJoyrideGame.HEIGHT - 40, random(1, maxBlocklength()), true, this));
        top = block.getLast().getLength();
        block.add(new Block(JetpackJoyrideGame.WIDTH, 0, random(1, maxBlocklength()), false, this));
        down = block.getLast().getLength();
        //noinspection AccessStaticViaInstance
        return random((down + 1) * block.getLast().HEIGHT, JetpackJoyrideGame.HEIGHT - (top + 1) * block.getLast().HEIGHT);
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

    private void createMap() {
        if (isGameOver()) {
            return;
        }
        if (random(0, 100) < 20) {
            int posGold = createBlock();
            createGold(posGold);
        }
    }

    public void update() {
        boolean removeGold = false;
        boolean removeBlock = false;
        updateJetPackDirection();
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
        //Garbadge Cleaner EXIT
        if (isGameOverAndExit()) {
            prefs.flush();
            jetpackjoyrideGame.getThemeSound().stop();
            jetpackjoyrideGame.getThemeSound().dispose();
            flyer.getCollectSound().stop();
            flyer.getCollectSound().dispose();
            System.exit(1);
        }
    }

    private void setSpeedbyScore() {
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

    private void createGold(int posGold) {
        gold.add(new Gold(JetpackJoyrideGame.WIDTH, posGold, this));
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

    private boolean isGameOverAndExit() {
        return isGameOver() && Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    public boolean isGameOver() {
        return !isGameRunning;
    }

    public int getScore() {
        return (int) score;
    }

    public static Preferences getPrefs() {
        return prefs;
    }

    public int getSpeed() {
        return speed;
    }
}
