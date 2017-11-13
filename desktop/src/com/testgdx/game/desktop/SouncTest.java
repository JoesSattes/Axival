package com.testgdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.main.axival.card.CardPlay;
import com.testgdx.game.sound.SoundDemoFx;

public class SouncTest extends ApplicationAdapter{
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.width = 1280;
        config.title = CardPlay.TITLE+" V."+CardPlay.VERSION;
        config.width  = CardPlay.V_WIDTH;
        //config.height = 720;
        config.height  = CardPlay.V_HEIGHT;
        config.backgroundFPS = 60;
        config.foregroundFPS = 60;
        config.resizable = true;
        config.fullscreen = false;
        new LwjglApplication(new SoundDemoFx(), config);
    }
}