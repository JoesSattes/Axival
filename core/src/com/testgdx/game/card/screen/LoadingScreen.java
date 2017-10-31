package com.testgdx.game.card.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.testgdx.game.card.CardSet;

public class LoadingScreen implements Screen{

    private final CardSet cardSet;
    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(final CardSet cardSet){
        this.cardSet = cardSet;
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
        progress = MathUtils.lerp(progress, cardSet.assetManager.getProgress(), .1f);
        if(cardSet.assetManager.update() && progress >= cardSet.assetManager.getProgress() - .01f){
            cardSet.setScreen(new CardScreen(cardSet));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, cardSet.camera.viewportHeight/2-8, (cardSet.camera.viewportWidth-64),16);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, cardSet.camera.viewportHeight/2-8, progress*(cardSet.camera.viewportWidth-64),16);
        shapeRenderer.end();

        cardSet.batch.begin();
        cardSet.bitmapFont.draw(cardSet.batch, "Screen : Loading", 20, 20);
        cardSet.batch.end();
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
        cardSet.assetManager.load("card01.png", Texture.class);
    }
}
