package com.testgdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.testgdx.game.TestCard;
import com.testgdx.game.group.test.GroupDemo;

public class GroupTest {
    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        //config.resizable = false;
        config.fullscreen = false;
        new LwjglApplication(new GroupDemo(), config);
    }
}
