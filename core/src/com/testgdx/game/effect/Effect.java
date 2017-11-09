package com.testgdx.game.effect;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Effect extends ApplicationAdapter {
    SpriteBatch batch;
    ParticleEffect pe;
    public void create(){
        batch = new SpriteBatch();
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("effect/fireball"),Gdx.files.internal("effect/fireball.png"));
        pe.getEmitters().first().setPosition(1200,700);

    }

    public void render (){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pe.update(Gdx.graphics.getDeltaTime());
        batch.begin();
        pe.draw(batch);
        batch.end();
    }
}
