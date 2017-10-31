package com.main.axival.card.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.main.axival.card.CardPlay;
import com.main.axival.card.Menu;

public class LoadingComponent implements Screen{

    private final CardPlay cardPlay;
    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingComponent(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.shapeRenderer = new ShapeRenderer();
        this.progress=0f;
        queueAssets();
    }

    @Override
    public void show() {
        System.out.println("Loading");
    }

    private void update(float delta){
        //progress + (get-pro)*lerp
        progress = MathUtils.lerp(progress, cardPlay.assetManager.getProgress(), .1f);
        if(cardPlay.assetManager.update() && progress >= cardPlay.assetManager.getProgress() - .01f){
            //cardPlay.setScreen(new ScreenPlay(cardPlay));
            cardPlay.setScreen(new Menu(cardPlay));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.2f,.5f,.7f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, cardPlay.camera.viewportHeight/2-8, (cardPlay.camera.viewportWidth-64),16);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, cardPlay.camera.viewportHeight/2-8, progress*(cardPlay.camera.viewportWidth-64),16);
        shapeRenderer.end();

        cardPlay.batch.begin();
        cardPlay.bitmapFont.draw(cardPlay.batch, "Screen : Loading", 20, 20);
        cardPlay.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void queueAssets(){
        cardPlay.assetManager.load("card01.png", Texture.class);
        cardPlay.assetManager.load("cardani/001.png", Texture.class);
        cardPlay.assetManager.load("cardani/002.png", Texture.class);
        cardPlay.assetManager.load("cardani/spritesheet/cardAni.atlas", TextureAtlas.class);
        cardPlay.assetManager.load("cursorImage2.png", Texture.class);
        cardPlay.assetManager.load("bg1.jpg", Texture.class);
        cardPlay.assetManager.load("effect01.party", ParticleEffect.class);
    }
}
