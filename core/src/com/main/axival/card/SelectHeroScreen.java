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
import com.main.axival.card.screen.ScreenPlay;

public class SelectHeroScreen implements Screen{
    private CardPlay cardPlay;

    private Stage stage;

    private Image darkTempImg, blizzImg, priestImg, darkTempOn, blizzOn, priestOn;

    private int selectHero;

    public SelectHeroScreen(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        Gdx.input.setInputProcessor(stage);

        darkTempImg = new Image(new Texture("hero-select/DT.jpg"));
        darkTempOn = new Image(new Texture("hero-select/DTHover.jpg"));
        blizzImg = new Image(new Texture("hero-select/Mage.jpg"));
        blizzOn = new Image(new Texture("hero-select/Mage.jpg"));
        priestImg = new Image(new Texture("hero-select/Priest.jpg"));
        priestOn = new Image(new Texture("hero-select/PriestHover.jpg"));

        darkTempImg.setScale(.172f);
        darkTempImg.setPosition(0, 130);
        darkTempOn.setScale(.172f);
        darkTempOn.setPosition(0, 130);
        darkTempImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 0;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                darkTempOn.addAction(Actions.sequence(Actions.fadeIn(.7f)));
                stage.addActor(darkTempOn);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                darkTempImg.addAction(Actions.sequence(Actions.fadeIn(.7f)));
            }
        });
        darkTempOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectHero = 0;
                cardPlay.setScreen(new ScreenPlay(cardPlay));
                System.out.println("Click selected");
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                darkTempOn.addAction(Actions.sequence(Actions.fadeOut(.5f), Actions.removeActor()));
                stage.addActor(darkTempImg);
            }
        });

        blizzImg.setScale(.172f);
        blizzImg.setPosition(darkTempImg.getWidth()*.172f, 130);
        blizzOn.setScale(.172f);
        blizzOn.setPosition(darkTempImg.getWidth()*.172f, 130);
        blizzImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 1;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                blizzOn.addAction(Actions.sequence(Actions.fadeIn(.7f)));
                stage.addActor(blizzOn);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                blizzImg.addAction(Actions.sequence(Actions.fadeIn(.7f)));
            }
        });
        blizzOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 1;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                blizzOn.addAction(Actions.sequence(Actions.fadeOut(.5f), Actions.removeActor()));
                stage.addActor(blizzImg);
            }
        });

        priestImg.setScale(.172f);
        priestImg.setPosition(blizzImg.getWidth()*.172f + darkTempImg.getWidth()*.172f, 130);
        priestOn.setScale(.172f);
        priestOn.setPosition(blizzImg.getWidth()*.172f + darkTempImg.getWidth()*.172f, 130);
        priestImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 2;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                priestOn.addAction(Actions.sequence(Actions.fadeIn(.7f)));
                stage.addActor(priestOn);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                priestImg.addAction(Actions.sequence(Actions.fadeIn(.7f)));
            }
        });
        priestOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                selectHero = 2;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                priestOn.addAction(Actions.sequence(Actions.fadeOut(.5f), Actions.removeActor()));
                stage.addActor(priestImg);
            }
        });

        stage.addActor(priestImg);
        stage.addActor(blizzImg);
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
