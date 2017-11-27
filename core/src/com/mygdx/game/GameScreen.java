package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    private Texture jetpackImg;
    private JetpackJoyrideGame jetpackjoyrideGame;

    public GameScreen(JetpackJoyrideGame jetpackjoyrideGame){
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        this.jetpackImg = new Texture("jetpack.png");
    }
}