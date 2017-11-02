package com.testgdx.game.card.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.testgdx.game.card.CardSet;

public class CardScreen implements Screen{
    private Stage stage;
    private Image cardImg;
    private final CardSet cardSet;
    public CardScreen(final CardSet cardSet){
        this.cardSet = cardSet;
        this.stage = new Stage(new StretchViewport(CardSet.V_WIDTH, CardSet.V_HEIGHT, cardSet.camera));
        Gdx.input.setInputProcessor(stage);

        //Texture splashTex = new Texture(Gdx.files.internal("card01.png"));
        Texture splashTex = cardSet.assetManager.get("card01.png", Texture.class);
        cardImg = new Image(splashTex);
        cardImg.setScale(0.2f);
        //splashImg.setPosition(stage.getWidth()/2-32, stage.getHeight()/2-32);

        cardImg.setOrigin(Gdx.graphics.getWidth()/2-cardImg.getWidth()/2, Gdx.graphics.getHeight()/2-cardImg.getHeight()/2);

        stage.addActor(cardImg);
    }

    @Override
    public void show() {
        System.out.println("screen");
        cardImg.setPosition(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 32);
        //cardImg.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        /*cardImg.addAction(Actions.sequence(Actions.alpha(0f),
                Actions.parallel(Actions.moveBy(30, -20, 2f), Actions.fadeIn(2f))));
        */cardImg.addAction(Actions.sequence(Actions.alpha(0), Actions.scaleTo(.1f, .1f),
                Actions.parallel(Actions.fadeIn(2f, Interpolation.pow2),
                        Actions.scaleTo(.4f, .4f, 2.5f, Interpolation.pow5),
                        Actions.moveTo(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 32, 2f, Interpolation.swing)),
                Actions.delay(1.5f), Actions.fadeOut(1.25f)));

        /*cardImg.addAction(Actions.delay(10f));
        cardImg.addAction(Actions.alpha(0f));
        cardImg.addAction(Actions.fadeIn(3f));*/

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardSet.batch.setProjectionMatrix(cardSet.camera.combined);
        stage.draw();
        cardSet.batch.begin();
        cardSet.bitmapFont.draw(cardSet.batch, "Screen: Spalsh", 120, 120);
        cardSet.batch.end();
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
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }
}
