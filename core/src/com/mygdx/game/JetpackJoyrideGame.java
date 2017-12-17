package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.audio;

public class JetpackJoyrideGame extends Game {
    public static final int HEIGHT = 768;
    public static final int WIDTH = 1366;

    public static SpriteBatch batch;

    //Sound
    private Sound themeSound;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //shapeRenderer = new ShapeRenderer();
        setScreen(new GameScreen(this));

        //SOUND
        themeSound = audio.newSound(Gdx.files.internal("ThemeSong.mp3"));
        themeSound.loop();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public Sound getThemeSound() {
        return themeSound;
    }
}
