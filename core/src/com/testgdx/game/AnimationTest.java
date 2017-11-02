package com.testgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.security.Key;

public class AnimationTest extends ApplicationAdapter{
    SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Texture img;
    Animation<TextureRegion> animation;
    private float elapsedTime=0f;


    @Override
    public void create(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas("spritesheet/jetSprite.atlas");
        animation = new Animation<TextureRegion>(1f/3f, textureAtlas.getRegions());
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void dispose(){
        batch.dispose();
        textureAtlas.dispose();
    }
    @Override
    public void render(){
        elapsedTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsedTime, true), 0 ,0);
        batch.end();
    }


}
