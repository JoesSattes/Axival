package com.testgdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgdx.game.AnimationTest;
import com.testgdx.game.TestGdx;

public class AnimationRun {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        //config.resizable = false;
        config.fullscreen = false;
        new LwjglApplication(new AnimationTest(), config);
    }
}
