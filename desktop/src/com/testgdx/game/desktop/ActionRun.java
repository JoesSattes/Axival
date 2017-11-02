package com.testgdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgdx.game.screen.ActionTest;

public class ActionRun extends ApplicationAdapter{
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.width = 1280;
        config.title = ActionTest.TITLE+" v."+ActionTest.VERSION;
        config.width  = ActionTest.V_WIDTH;
        //config.height = 720;
        config.height  = ActionTest.V_HEIGHT;
        config.backgroundFPS = 60;
        config.foregroundFPS = 60;
        config.resizable = true;
        config.fullscreen = false;
        new LwjglApplication(new ActionTest(), config);
    }
}
