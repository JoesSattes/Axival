package com.testgdx.game.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgdx.game.screen.screenin.SplashScreen;

public class ActionTest extends Game{
    public static final String TITLE = "Slidez";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 480;
    public static final int V_HEIGHT = 420;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public BitmapFont bitmapFont;

    @Override
    public void create(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 720);
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        this.setScreen(new SplashScreen(this));
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
        this.getScreen().dispose();
    }
}
