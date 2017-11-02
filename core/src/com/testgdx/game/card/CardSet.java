package com.testgdx.game.card;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgdx.game.card.screen.CardScreen;
import com.testgdx.game.card.screen.LoadingScreen;

public class CardSet extends Game{
    public static final String TITLE = "Axivals";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 480;
    public static final int V_HEIGHT = 420;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public BitmapFont bitmapFont;

    public AssetManager assetManager;

    @Override
    public void create(){
        assetManager = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);

        this.setScreen(new LoadingScreen(this));
    }

    @Override
    public void render(){
        //Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose(){
        batch.dispose();
        bitmapFont.dispose();
        assetManager.dispose();
        this.getScreen().dispose();
    }
}
