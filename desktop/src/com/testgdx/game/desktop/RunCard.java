package com.testgdx.game.desktop;

import com.archive.edit.game.ArchiveAnimation;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgdx.game.TestCard;

public class RunCard extends ApplicationAdapter{
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        //config.resizable = false;
        config.fullscreen = false;
        new LwjglApplication(new TestCard(), config);
    }
}
