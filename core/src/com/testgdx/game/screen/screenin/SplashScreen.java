package com.testgdx.game.screen.screenin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.testgdx.game.screen.ActionTest;
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.awt.*;

public class SplashScreen implements Screen{

    private Stage stage;
    private Image splashImg;
    private final ActionTest actt;
    public SplashScreen(final ActionTest actt){
        this.actt = actt;
        this.stage = new Stage(new StretchViewport(ActionTest.V_WIDTH, ActionTest.V_HEIGHT, actt.camera));
        Gdx.input.setInputProcessor(stage);

        Texture splashTex = new Texture(Gdx.files.internal("card01.png"));
        splashImg = new Image(splashTex);
        splashImg.setScale(0.2f);
        //splashImg.setPosition(stage.getWidth()/2-32, stage.getHeight()/2-32);

        stage.addActor(splashImg);
    }

    @Override
    public void show() {
        System.out.println("show");
        splashImg.setPosition(stage.getWidth()/2-32, stage.getHeight()/2-32);
        /*
        splashImg.addAction(Actions.alpha(0f));
        splashImg.addAction(Actions.fadeIn(3f));*/
        splashImg.addAction(Actions.sequence(Actions.alpha(0f),
                Actions.parallel(Actions.moveBy(30,-20,2f), Actions.fadeIn(2f))));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();
        actt.batch.begin();
        actt.bitmapFont.draw(actt.batch, "SpalshScreen", 120, 120);
        actt.batch.end();

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
