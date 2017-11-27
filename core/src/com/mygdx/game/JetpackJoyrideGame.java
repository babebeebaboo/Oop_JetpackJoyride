package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class JetpackJoyrideGame extends Game {

	//public SpriteBatch batch;
	public static SpriteBatch batch;
	private Texture jetpackImg;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
		jetpackImg = new Texture("jetpack.png");
	}

	@Override
	public void render() {
		SpriteBatch batch = JetpackJoyrideGame.batch;
		batch.begin();
		batch.draw(jetpackImg, 100, 100);
		batch.end();
	}

	@Override
	public void dispose (){
		batch.dispose();
	}
}
