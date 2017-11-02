package com.testgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class TestCard extends ApplicationAdapter implements InputProcessor{
    SpriteBatch batch;
    TextureAtlas textureAtlas;
    Sprite sprite;
    TextureRegion textureRegion;
    int currentFrame = 1;
    int MAX_FRAMES = 7;
    boolean solveDown;
    boolean solveUp;
    boolean solveRight;
    boolean solveLeft;
    public OrthographicCamera camera, camera2;
    private Texture textureBg;

    @Override
    public void create(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("cardani/spritesheet/cardAni.atlas"));
        textureRegion = textureAtlas.findRegion("001");
        sprite = new Sprite(textureRegion);
        sprite.setScale(0.25f);
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        camera2 = new OrthographicCamera();
        camera2.setToOrtho(false, 1280, 720);
        textureBg = new Texture("bg1.jpg");
        Pixmap pm = new Pixmap(Gdx.files.internal("cursorImage2.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2));
        pm.dispose();
        sprite.setAlpha(0.5f);
    }

    @Override
    public void render(){
        if (solveUp) {
            currentFrame++;
            if (currentFrame > MAX_FRAMES) {
                currentFrame = 1;
            }
            sprite.setRegion(textureAtlas.findRegion(String.format("%03d", currentFrame)));
            sprite.translateY(1f);
        }
        if (solveDown){
            currentFrame--;
            if(currentFrame<1){
                currentFrame = MAX_FRAMES;
            }
            sprite.setRegion(textureAtlas.findRegion(String.format("%03d", currentFrame)));
            sprite.translateY(-1f);
        }
        if (solveRight){
            currentFrame--;
            if(currentFrame<1){
                currentFrame = MAX_FRAMES;
            }
            sprite.setRegion(textureAtlas.findRegion(String.format("%03d", currentFrame)));
            sprite.translateX(1f);
        }
        if (solveLeft){
            currentFrame--;
            if(currentFrame<1){
                currentFrame = MAX_FRAMES;
            }
            sprite.setRegion(textureAtlas.findRegion(String.format("%03d", currentFrame)));
            sprite.translateX(-1f);
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(textureBg, 0, 0);
        batch.draw(textureRegion, 0,0);
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = true;
        }
        if (keycode== Input.Keys.DOWN){
            solveDown = true;
        }
        if (keycode== Input.Keys.LEFT){
            solveLeft = true;
        }
        if (keycode== Input.Keys.RIGHT){
            solveRight = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = false;
        }
        if (keycode== Input.Keys.DOWN){
            solveDown = false;
        }
        if (keycode== Input.Keys.LEFT){
            solveLeft = false;
        }
        if (keycode== Input.Keys.RIGHT){
            solveRight = false;
        }
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
        /*if (Gdx.graphics.getWidth()/2-sprite.getWidth()/2 <= screenX && Gdx.graphics.getHeight()/2-sprite.getHeight()/2 <= screenY){
            sprite.setScale(2f);
        }
        else{
            sprite.setScale(1f);
        }
        if((Gdx.graphics.getWidth()/2 - sprite.getWidth()/2)<=screenX && screenX <= (Gdx.graphics.getWidth()/2 + sprite.getWidth()/2) && (Gdx.graphics.getHeight()/2 - sprite.getHeight()/2)<=screenY && screenY <= (Gdx.graphics.getHeight()/2 + sprite.getHeight()/2)){
            sprite.setScale(2f);
        }
        else{
            sprite.setScale(1f);
        }*/
        Vector3 mousePosition = new Vector3(screenX, screenY, 0);
        camera.unproject(mousePosition);
        //Vector3 spritePosition = new Vector3(sprite.getX(), sprite.getY(), 0);
        //camera2.unproject(spritePosition);
        /*
        if((sprite.getX()-sprite.getWidth()/2 <= mousePosition.x && mousePosition.x <= (sprite.getX()+sprite.getWidth()/2)) &&
                (sprite.getY()-sprite.getHeight()/2 <= mousePosition.y && mousePosition.y <= (sprite.getY()+sprite.getHeight()/2))){
    sprite.setScale(2f);
        }
        else{
            sprite.setScale(1f);
        }*/
        if(mousePosition.x >= sprite.getX() && mousePosition.y >= sprite.getY()){
            sprite.setAlpha(1f);
            sprite.setScale(0.5f);
        }
        else{
            sprite.setAlpha(0.5f);
            sprite.setScale(0.25f);
        }
        System.out.printf("ScreenXY: (%f, %f); PicOriginXY: (%f, %f)\n", mousePosition.x, mousePosition.y, sprite.getX(), sprite.getY());
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount>0)
            sprite.translateY(2f);
        if(amount<0)
            sprite.translateY(-2f);
        return false;
    }
}

