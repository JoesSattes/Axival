package com.testgdx.game.button;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ButtonTest extends ApplicationAdapter{
    SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Texture img;
    private TextureRegionDrawable textureRegionDrawable;
    private ImageButton imageButton;
    private Stage stage;

    @Override
    public void create(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas("cardani/spritesheet/cardAni.atlas");
        textureRegionDrawable = new TextureRegionDrawable(textureAtlas.findRegion("001"));
        imageButton = new ImageButton(textureRegionDrawable);
        stage = new Stage(new StretchViewport(1280, 720));

        Image image = new Image(textureAtlas.findRegion("002"));
        stage.addActor(imageButton);
        imageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.print("Clicked "+1);
            }
        });
        stage.addActor(image);
        image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.print("Clicked "+2);
            }
        });
        //imageButton.setScale(.2f);
        //imageButton.getImage().setScale(.2f);
        Gdx.input.setInputProcessor(stage);

        /*imageButton.addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        //Actions.moveBy(0, 100, 2f),
                        //Actions.moveTo(stage.getWidth()/1.5f, stage.getHeight() / 12, 2f),
                        Actions.moveTo(0, 0, 2f),
                        Actions.scaleTo(.2f,.2f,2f))));*/
        imageButton.getImage().addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        //Actions.moveBy(0, 100, 2f),
                        //Actions.moveTo(stage.getWidth()/1.5f, stage.getHeight() / 12, 2f),
                        Actions.moveTo(1000, 0, 2f),
                        Actions.scaleTo(.2f,.2f,2f))));
        image.addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        //Actions.moveBy(0, 100, 2f),
                        //Actions.moveTo(stage.getWidth()/1.5f, stage.getHeight() / 12, 2f),
                        Actions.moveTo(500, 0, 2f),
                        Actions.scaleTo(.2f,.2f,2f))));
    }

    @Override
    public void dispose(){
        batch.dispose();
        textureAtlas.dispose();
    }
    @Override
    public void render(){
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}
