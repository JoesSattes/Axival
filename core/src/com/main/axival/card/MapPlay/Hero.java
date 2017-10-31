package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero extends TextureAtlas{
    public String action;
    public Hero.State facing;
    public Hero.State currentState;
    public Hero.State previousState;
    private Texture img;
    private Animation<TextureRegion> animation;
    private Vector2 coordinates = new Vector2();
    private float coX;
    private float coY;
    private TextureAtlas atlas;
    private float frameDuration;
    private float elapsedTime = 1.0F;

    public Hero(MapScreen screen) {
        this.facing = Hero.State.RIGHT;
        this.currentState = Hero.State.STANDING;
        this.previousState = Hero.State.STANDING;
    }

    public void update(float dt) {
        this.elapsedTime += dt;
    }

    public float getElapsedTime() {
        return this.elapsedTime;
    }

    public void setImg(String path) {
        this.img = new Texture(path);
    }

    public void setCoordinates(float x, float y) {
        this.coordinates = this.coordinates.set(x, y);
        this.coX = x;
        this.coY = y;
    }

    public Vector2 getCoordinates() {
        return this.coordinates;
    }

    public void setAtlas(String path) {
        this.atlas = new TextureAtlas(path);
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setX(float x) {
        this.coX = x;
        this.coordinates = this.coordinates.set(x, this.coordinates.y);
    }

    public void setY(float y) {
        this.coY = y;
        this.coordinates = this.coordinates.set(this.coordinates.x, y);
    }

    public Animation<TextureRegion> action() {
        this.action = "stand";
        float frameDuration = 0.3F;
        if (this.currentState.compareTo(Hero.State.WALKING) == 0) {
            this.action = "walk";
        }

        if (this.currentState.compareTo(Hero.State.ATTACKING1) == 0) {
            this.action = "swingO3";
            frameDuration = 0.2F;
        }

        if (this.currentState.compareTo(Hero.State.ATTACKING2) == 0) {
            this.action = "swingOF";
            frameDuration = 0.15F;
        }

        if (this.currentState.compareTo(Hero.State.ALERT) == 0) {
            this.action = "alert";
        }

        if (this.currentState.compareTo(Hero.State.HIT) == 0) {
            this.action = "hit";
        }

        if (this.currentState.compareTo(Hero.State.DEAD) == 0) {
            this.action = "dead";
        }

        return new Animation(frameDuration, this.atlas.findRegions(this.action));
    }

    public void setFacing(Hero.State facing) {
        this.facing = facing;
    }

    public void setCurrentState(Hero.State currentState) {
        this.previousState = this.currentState;
        this.currentState = currentState;
    }

    public static enum State {
        STANDING,
        WALKING,
        ATTACKING1,
        ATTACKING2,
        ALERT,
        HIT,
        DEAD,
        LEFT,
        RIGHT;

        private State() {
        }
    }
}
