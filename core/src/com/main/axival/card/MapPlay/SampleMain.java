package com.main.axival.card.MapPlay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SampleMain extends Game{
    public static final int V_WIDTH = 1280;
    public  static final int V_HEIGHT = 720;
    public SpriteBatch batch;
    public MapScreen mapScreen;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //mapScreen = new MapScreen(this);
        setScreen(new MapScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
