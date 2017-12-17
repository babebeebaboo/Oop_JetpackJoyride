package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

class GameScreen extends ScreenAdapter {
    private final WorldRenderer worldRenderer;
    private final World world;

    public GameScreen(JetpackJoyrideGame jetpackjoyrideGame) {
        world = new World(jetpackjoyrideGame);
        worldRenderer = new WorldRenderer(jetpackjoyrideGame, world);
    }

    @Override
    public void render(float delta) {
        world.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render();
    }

}