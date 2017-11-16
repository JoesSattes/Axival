package com.main.axival.card;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.main.axival.card.screen.LoadingComponent;

public class CardPlay extends Game{
    public static final String TITLE = "Axivals";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 1280;
    public static final int V_HEIGHT = 720;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public BitmapFont bitmapFont;

    public AssetManager assetManager;

    public SoundManager soundManager;

    public Stage transitionStage;

    @Override
    public void create(){
        assetManager = new AssetManager();
        soundManager = new SoundManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        transitionStage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, camera));
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);

        //check memory
        javaFreeMem();

        this.setScreen(new LoadingComponent(this));
    }

    @Override
    public void render(){
        //Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
        assetManager.dispose();
        this.getScreen().dispose();
    }

    public void javaFreeMem() {
            // check memory
            System.out.println("Total Memory"+Runtime.getRuntime().totalMemory());
            // Java's garbage-collected clean Memory Not use
            System.out.println("Free Memory"+Runtime.getRuntime().freeMemory());
    }

    public static Texture getTexture(){

        Pixmap pixmap;
        try {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        }catch (GdxRuntimeException e)
        {
            pixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        }
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0,0,1,1);

        return new Texture(pixmap);
    }
}
