package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero extends TextureAtlas {
    public enum State {STANDING, WALKING, ATTACKING1, ATTACKING2, ALERT, HIT, DEAD, LEFT, RIGHT};
    public int col;
    public int row;
    public String action;
    public State facing;
    public State currentState;
    public State previousState;
    public int walk=10;
    private Texture img;
    private TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private Vector2 coordinates, des, goal;
    private float frameDuration;
    private float elapsedTime = 1f;
    private static int walking=0;
    private MapScreen screen;
    private Board board;

    public Hero(MapScreen screen, Board board, Vector2 vector) {
        this.screen = screen;
        this.board = board;
        this.row = (int) vector.y;
        this.col = (int) vector.x;
        this.goal = new Vector2(col, row);
        this.des = new Vector2();
        this.coordinates = new Vector2();
        this.coordinates.set(board.map[row][col].corX , board.map[row][col].corY);
        this.des.set(screen.board.map[row][col].corX, screen.board.map[row][col].corY);
        facing = State.RIGHT;
        currentState = State.STANDING;
        previousState = State.STANDING;
    }

    public void update(float dt) {
        this.elapsedTime += dt;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setImg(String path) {
        this.img = new Texture(path);
    }

    public void setWalking(int n) { this.walking = n; }

    public int getWalking() { return walking; }

    public void setCoordinates(float x, float y) {
        this.coordinates = coordinates.set(x, y);
    }
    public void setDes(float x, float y) {
        this.des.set(x, y);
    }
    public void setRowCol(int row, int col) {this.row = row; this.col = col; }
    public void setGoal(Vector2 goal) { this.goal = goal; }

    public Vector2 getCoordinates() {
        return this.coordinates;
    }
    public Vector2 getDes() { return  this.des; }
    public Vector2 getRowCol() {
        return new Vector2(this.col, this.row);
    }
    public Vector2 getGoal() { return this.goal;}

    public void setAtlas(String path) {
        this.atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> walkAction() {
        action = "stand1";
        float frameDuration=0.3f;
        if (this.currentState.compareTo(State.WALKING) == 0) {
            action = "walk1"; }
//        if (this.currentState.compareTo(State.ATTACKING1) == 0) {
//            action = "swingO3";
//            frameDuration = 0.2f;
//        }
//        if (this.currentState.compareTo(State.ATTACKING2) == 0) {
//            action = "swingOF";
//            frameDuration = 0.15f;
//        }
//        if (this.currentState.compareTo(State.ALERT) == 0) {
//            action = "alert";
//        }
//        if (this.currentState.compareTo(State.HIT) == 0) {
//            action = "hit";
//        }
//        if (this.currentState.compareTo(State.DEAD) == 0) {
//            action = "dead";
//        }
        return new Animation<TextureRegion>(frameDuration, this.atlas.findRegions(action));

    }

    public void setFacing(State facing) {
        this.facing = facing;
    }

    public void setCurrentState(State currentState) {
        this.previousState = this.currentState;
        this.currentState = currentState;
    }
}
