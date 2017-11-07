package com.testgdx.game.loading.libgdxloadingscreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
//import com.matsemann.libgdxloadingscreen.screen.LoadingScreen;
import com.testgdx.game.loading.libgdxloadingscreen.screen.LoadingScreen;

/**
 * @author Mats Svensson
 */
public class SomeCoolGame extends Game {

    /**
     * Holds all our assets
     */
    public AssetManager manager = new AssetManager();

    @Override
    public void create() {
        setScreen(new LoadingScreen(this));
    }
}
