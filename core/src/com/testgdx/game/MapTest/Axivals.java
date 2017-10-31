package com.testgdx.game.MapTest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.testgdx.game.MapTest.Screens.PlayScreen;

public class Axivals extends Game {
	public static final int V_WIDTH = 1280;
	public  static final int V_HEIGHT = 720;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
