package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    private Flyer flyer;
    private WorldRenderer worldRenderer;
    private Texture jetpackImg;
    private JetpackJoyrideGame jetpackjoyrideGame;
    World world;

    public GameScreen(JetpackJoyrideGame jetpackjoyrideGame){
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        jetpackImg = new Texture("jetpack.png");
        world = new World(jetpackjoyrideGame);
        worldRenderer = new WorldRenderer(jetpackjoyrideGame, world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render(delta);
    }
    public void update(float delta) {

        world.update(delta);
    }
}