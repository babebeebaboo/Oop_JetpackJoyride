package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
    private BitmapFont font;
    World world;
    private Flyer flyer;
    private Texture jetpackImg;
    private JetpackJoyrideGame jetpackjoyrideGame;

    public WorldRenderer(JetpackJoyrideGame jetpackjoyrideGame, World world) {
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        SpriteBatch batch = jetpackjoyrideGame.batch;
        this.world = world;
        jetpackImg = new Texture("jetpack.png");
        font = new BitmapFont();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.update(delta);
        SpriteBatch batch = jetpackjoyrideGame.batch;

        Vector2 pos = world.getFlyer().getPosition();
        batch.begin();
        batch.draw(jetpackImg, pos.x ,pos.y);
        font.draw(batch, "" + world.getScore(), 700, 60);
        batch.end();
    }
}