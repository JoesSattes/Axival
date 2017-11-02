package com.testgdx.game.group.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.prism.Texture;

import java.applet.Applet;

public class GroupDemo extends ApplicationAdapter implements InputProcessor{
    public Stage stage;
    public OrthographicCamera camera;
    HorizontalGroup group = new HorizontalGroup();
    @Override
    public void create(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        stage = new Stage(new ScreenViewport());

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("cardani/spritesheet/cardAni.atlas"));
        Image[] card = new Image[7];
        card[0] = new Image(textureAtlas.findRegion("001"));
        card[1] = new Image(textureAtlas.findRegion("002"));
        card[2] = new Image(textureAtlas.findRegion("003"));
        card[3] = new Image(textureAtlas.findRegion("004"));
        card[4] = new Image(textureAtlas.findRegion("005"));
        card[5] = new Image(textureAtlas.findRegion("006"));
        card[6] = new Image(textureAtlas.findRegion("007"));

        group.addActor(card[0]);
        group.addActor(card[1]);
        group.addActor(card[2]);
        group.addActor(card[3]);
        group.addActor(card[4]);
        group.addActor(card[5]);
        group.addActor(card[6]);

        group.setScale(.2f);
        //card[0].addAction(Actions.parallel(Actions.moveTo(200,0,5),Actions.rotateBy(90,5)));
        stage.addActor(group);


        card[0].setPosition(300,150);
        card[1].setPosition(400,150);
        card[2].setPosition(500,150);
        card[3].setPosition(600,150);
        card[4].setPosition(700,150);
        card[5].setPosition(800,150);
        card[6].setPosition(900,150);
        //card[7].setPosition(1000,150);

        card[5].setName("5");
        Gdx.input.setInputProcessor(this);
        card[0].addAction(Actions.parallel(Actions.moveTo(200,0,5),Actions.rotateBy(90,5)));

        group.getChildren().get(3).addAction(Actions.parallel(Actions.moveTo(200,0,5),
                Actions.rotateBy(90,5)));
    }

    @Override
    public void render(){
        //HorizontalGroup group = (HorizontalGroup) stage.getActors().first();
        /*
        group.findActor("5").addAction(Actions.sequence(Actions.alpha(.5f), Actions.scaleTo(.1f, .1f),
                Actions.parallel(Actions.fadeIn(2f, Interpolation.pow2),
                        Actions.scaleTo(.4f, .4f, 2.5f, Interpolation.pow5),
                        Actions.moveTo(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 32, 2f, Interpolation.swing)),
                Actions.delay(1.5f), Actions.fadeOut(1.25f)));
        */

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        //HorizontalGroup group = (HorizontalGroup)stage.getActors().first();
        //Image cardH = (Image)group.getChildren().get(0);
        Image cardH2 = group.findActor("5");
        //cardH.addAction(Actions.fadeIn(3f));
        // cardH2.addAction(Actions.fadeIn(3f);

        if(keycode== Input.Keys.A)
            group.setRotation(group.getRotation()+1f);
        if(keycode== Input.Keys.D)
            group.setRotation(group.getRotation()-1f);
        if(keycode== Input.Keys.W) {
            //cardH.setRotation(cardH.getRotation() + 5f);
            group.findActor("5").setRotation(group.findActor("5").getRotation()-5f);
            //group.findActor("5").addAction(Actions.fadeIn(3f));

            //group.addAction(Actions.fadeIn(3f));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
