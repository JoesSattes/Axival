package com.main.axival.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.main.axival.card.screen.ScreenPlay;

public class WaitingScreen implements Screen{
    private CardPlay cardPlay;
    private Animation<TextureRegion> animationWaiting;
    private float timePlay;

    private boolean statusAlready;

    public WaitingScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
    }

    @Override
    public void show() {
        animationWaiting = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("waiting/loading2.gif").read());
    }

    @Override
    public void render(float delta) {
        timePlay += delta;
        Gdx.gl.glClearColor(1, 0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cardPlay.batch.begin();
        cardPlay.batch.draw(animationWaiting.getKeyFrame(timePlay, true), 0, 0, 1280, 720);
        cardPlay.batch.end();
        if (statusAlready || timePlay>10){
            cardPlay.setScreen(new ScreenPlay(cardPlay));
        }
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
        cardPlay.batch.dispose();
    }
}
