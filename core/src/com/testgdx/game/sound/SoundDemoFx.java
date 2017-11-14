package com.testgdx.game.sound;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;

public class SoundDemoFx extends ApplicationAdapter implements InputProcessor{
    Music musicBg1, musicBg2, sfx;

    @Override
    public void create(){
        Gdx.input.setInputProcessor(this);
        musicBg1 = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/bgChase.ogg"));
        musicBg2 = Gdx.audio.newMusic(Gdx.files.internal("sound/bgm/bgFantasy.ogg"));
        sfx = Gdx.audio.newMusic(Gdx.files.internal("sound/fx/Draw.ogg"));

        musicBg1.play();

        musicBg1.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                musicBg2.play();
            }
        });

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(musicBg1.isPlaying()){
                    if(musicBg1.getPosition()>=10.0f){
                        musicBg1.setVolume(musicBg1.getVolume() - 0.2f);
                    }
                }
            }
        },10,1,4);
    }

    @Override
    public void render(){

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.UP)
            musicBg1.setVolume(musicBg1.getVolume()+1f);
        if(keycode==Input.Keys.S)
            musicBg2.play();
        if(keycode==Input.Keys.D)
            musicBg2.pause();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sfx.play();
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
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
