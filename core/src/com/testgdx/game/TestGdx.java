package com.testgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestGdx extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	boolean solveRight=false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("core/assets/badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(
				Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - sprite.getHeight()/2
		);
		sprite.setRotation(180f);
		sprite.setScale(1f);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		if(solveRight)
			sprite.translateX(1f);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			sprite.translateX(-1f);
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
			sprite.translateX(1f);
		if(Gdx.input.isKeyPressed(Input.Keys.M))
			sprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//System.out.println(sprite.getX()+"Y"+sprite.getY()+"w"+sprite.getWidth()+"sX"+sprite.getScaleX());
		batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth()/2, sprite.getHeight()/2, sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Input.Keys.DOWN)
			sprite.translateX(-1f);
		if(keycode==Input.Keys.R)
			solveRight = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Input.Keys.R)
			solveRight = false;
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
		sprite.setPosition(screenX, Gdx.graphics.getHeight() - screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if(amount>0)
			sprite.translateY(1f);
		if(amount<0)
			sprite.translateY(-1f);
		return false;
	}
}
