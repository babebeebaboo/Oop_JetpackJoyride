package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.Gdx.audio;

public class JetpackJoyrideGame extends Game {
    public static final int HEIGHT = 768;
    public static final int WIDTH = 1366;
    public static SpriteBatch batch;
    public static ShapeRenderer shapeRenderer;

    //todo items
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
