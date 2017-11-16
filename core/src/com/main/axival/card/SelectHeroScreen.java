package com.main.axival.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SelectHeroScreen implements Screen{
    private CardPlay cardPlay;

    private Stage stage;

    private Image darkTempImg, blizzImg, priestImg, darkTempOn, blizzOn, priestOn;

    private int selectHero;

    public SelectHeroScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        Gdx.input.setInputProcessor(stage);

        darkTempImg = new Image(new Texture("hero-select/DT.jpg"));
        darkTempOn = new Image(new Texture("hero-select/DTHover.jpg"));
        blizzImg = new Image(new Texture("hero-select/Mage.jpg"));
        blizzOn = new Image(new Texture("hero-select/Mage.jpg"));
        priestImg = new Image(new Texture("hero-select/Priest.jpg"));
        priestOn = new Image(new Texture("hero-select/PriestHover.jpg"));

        darkTempImg.setScale(.1f);
        darkTempImg.setPosition(300, 200);
        darkTempOn.setScale(.1f);
        darkTempOn.setPosition(300, 200);
        darkTempImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 0;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //darkTempImg.addAction(Actions.sequence(Actions.scaleTo(.2f,.2f,.5f)));
                darkTempImg.addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(.5f), Actions.removeActor())));
                stage.addActor(darkTempOn);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //darkTempOn.addAction(Actions.sequence(Actions.fadeOut(.5f), Actions.removeActor()));
                //stage.addActor(darkTempImg);
                darkTempImg.addAction(Actions.sequence(Actions.fadeIn(.5f)));
            }
        });
        darkTempOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 0;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                //darkTempImg.addAction(Actions.sequence(Actions.scaleTo(.2f,.2f,.5f)));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                darkTempOn.addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(.5f), Actions.removeActor())));
                stage.addActor(darkTempImg);
            }
        });
        //stage.addActor(darkTempOn);
        stage.addActor(darkTempImg);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        stage.draw();
    }

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
        stage.dispose();
    }
}
