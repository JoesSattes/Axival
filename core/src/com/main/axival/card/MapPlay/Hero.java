package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero extends TextureAtlas {
    public enum State {STANDING, WALKING, LEFT, RIGHT};
    public int job;
    public int col;
    public int row;
    public String action;
    public State facing;
    public State currentState;
    public State previousState;
    public int walk=4;
    public Skill[] ability;
    private int frameC=0;
    private Texture img;
    private TextureAtlas atlas, skill;
    private Animation<TextureRegion> animation;
    private Vector2 coordinates, des, src;
    private float frameDuration;
    private float elapsedTime = 0f;
    private static int walking=0;
    private MapScreen screen;
    private Board board;

    public Hero(MapScreen screen, Board board, Vector2 vector, int job) {
        this.job = job;
        ability = new Skill[4];
        if (job == 1) {
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[1] = new Skill("skills/CD_Skill_Spritesheet/CD_Skill4_Spritesheet/cd_skill4.atlas", "cd4");
//            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas", "dt3");
        }
        else if (job == 2) {
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[3] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas", "wz3");
        }
        else {
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[2] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
        }
        this.screen = screen;
        this.board = board;
        this.row = (int) vector.y;
        this.col = (int) vector.x;
        this.src = new Vector2(col, row);
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
        if (elapsedTime > 100) { elapsedTime = 0;}
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
    public void setSrc() { this.src = new Vector2(col, row); }

    public Vector2 getCoordinates() {
        return this.coordinates;
    }
    public Vector2 getDes() { return  this.des; }
    public Vector2 getRowCol() {
        return new Vector2(this.col, this.row);
    }
    public Vector2 getSrc() { return this.src;}

    public void setAtlas(String path) {
        this.atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> walkAction() {
        action = "stand1";
        float frameDuration=0.3f;
        if (this.currentState.compareTo(State.WALKING) == 0) {
            action = "walk1"; }
        return new Animation<TextureRegion>(frameDuration, this.atlas.findRegions(action));
    }

    public void setFacing(State facing) {
        this.facing = facing;
    }

    public void setCurrentState(State currentState) {
        this.previousState = this.currentState;
        this.currentState = currentState;
    }

    public void setSource(int col, int row) {
        this.src.set(col, row);
    }

    public Vector2 getSource() {
        return src;
    }
}
