package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.main.axival.card.CardPlay;

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
    public boolean live=true;
    public boolean attacking=false;
    public int skillUsing=-1;
    public Skill[] ability;
    private int frame;
    private Texture img;
    private TextureAtlas atlas, skill;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion>[] skillAnimation = new Animation[4];
    private Vector2 coordinates, des, src;
    private float frameDuration;
    private float elapsedTime = 0f;
    private float startTime = 0f;
    private float widthErr = 0;
    private float heightErr = 0;
    private static int walking=0;
    private CardPlay game;
    private MapScreen screen;
    private Board board;

    public Hero(CardPlay game, MapScreen screen, Board board, Vector2 vector, int job) {
        this.job = job;
        ability = new Skill[4];
//        skillAnimation = new Animation[4];
        frameDuration = 0.5f;
        if (job == 1) {
            ability[0] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill0_Spritesheet/dt_skill0.atlas", "dt0");
            ability[1] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill1_Spritesheet/dt_skill1.atlas", "dt1");
            ability[2] = new Skill("skills/Cd_Skill_Spritesheet/CD_Skill3_Spritesheet/cd_skill3.atlas", "cd3");
            ability[3] = new Skill("skills/DT_Skill_Spritesheet/DT_Skill3_Spritesheet/dt_skill3.atlas", "dt3");
            skillAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingOF"));
            skillAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("fortify"));
            skillAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("stabOF"));
            skillAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO1"));
        }
        else if (job == 2) {
            ability[0] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill0_Spritesheet/wz_skill0.atlas", "wz0");
            ability[1] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill1_Spritesheet/wz_skill1.atlas", "wz1");
            //Wizard have no 2nd ability.
            ability[3] = new Skill("skills/WZ_Skill_Spritesheet/WZ_Skill3_Spritesheet/wz_skill3.atlas", "wz3");
            skillAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO1"));
            skillAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO2"));
            //Wizard have no 2nd ability.
            skillAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO3"));
        }
        else {
            ability[0] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill0_Spritesheet/pr_skill0.atlas", "pr0");
            ability[1] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill1_Spritesheet/pr_skill1.atlas", "pr1");
            ability[2] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill2_Spritesheet/pr_skill2.atlas", "pr2");
            ability[3] = new Skill("skills/PR_Skill_Spritesheet/PR_Skill3_Spritesheet/pr_skill3.atlas", "pr3");
            skillAnimation[0] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO1"));
            skillAnimation[1] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("heal"));
            skillAnimation[2] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("swingO2"));
            skillAnimation[3] = new Animation<TextureRegion>(frameDuration, this.atlas.findRegion("stabO1"));
        }
        this.game = game;
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

    public void update(float delta) {
        if (elapsedTime > 100) { elapsedTime = 0;}
        this.elapsedTime += delta;
        for (Skill skill: ability) {
            skill.update(delta);
        }
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
        float frameDuration=1/3f;
        if (this.currentState.compareTo(State.WALKING) == 0) {
            action = "walk1"; }
        return new Animation<TextureRegion>(frameDuration, this.atlas.findRegions(action));
    }

    public void renderWalking() {
        float errR = 0;
        float errL = 0;
        if (job == 1) {
            errL = -22f;
        }
        if (job == 2) {
            errR = 5f;
            errL = 10f;
        }
        if (job == 3) {
            errR = -10f;
            errL = -2f;
        }

        //Hero Action
        if (facing.compareTo(Hero.State.RIGHT) == 0) {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + (this.walkAction().getKeyFrame(elapsedTime,true).getRegionWidth()) + errR,
                    this.getCoordinates().y,
                    -(this.walkAction().getKeyFrame(elapsedTime, true).getRegionWidth()),
                    this.walkAction().getKeyFrame(elapsedTime, true).getRegionHeight());
        }
        else {
            game.batch.draw(this.walkAction().getKeyFrame(elapsedTime, true),
                    this.getCoordinates().x + errL, this.getCoordinates().y);
        }
    }

    public void useSkill() {
      //debugging
        float deltaTime = skillAnimation[skillUsing].getAnimationDuration();
        System.out.println("Elapsetime = " + elapsedTime);
        System.out.println("time = " + deltaTime);
        System.out.println("frame = " + skillAnimation[skillUsing].getKeyFrames().length);
        System.out.println("frameAniDur = " + skillAnimation[skillUsing].getAnimationDuration());
        System.out.println("frameDur = " + skillAnimation[skillUsing].getFrameDuration());
        deltaTime = frameDuration * frame;
        System.out.println("New delta time = " + deltaTime);

        if (elapsedTime < startTime + deltaTime && attacking == true && skillUsing > -1) {
            if (facing.compareTo(State.RIGHT) == 0) {
                game.batch.draw(skillAnimation[skillUsing].getKeyFrame(elapsedTime, true),
                        coordinates.x + skillAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionWidth(),
                        coordinates.y,
                        -(skillAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionWidth()),
                        skillAnimation[skillUsing].getKeyFrame(elapsedTime, true).getRegionHeight());
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime,true),
                        coordinates.x + ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionWidth() + widthErr,
                        coordinates.y - heightErr,
                        -(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime,true).getRegionWidth()),
                        ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime, true).getRegionHeight());
            }
            else {
                game.batch.draw(skillAnimation[skillUsing].getKeyFrame(elapsedTime, true), coordinates.x, coordinates.y);
                game.batch.draw(ability[skillUsing].getSkillAction(deltaTime).getKeyFrame(elapsedTime,
                        true), coordinates.x - widthErr, coordinates.y - heightErr);
            }


        }
        else {
            skillUsing = -1;
            attacking = false;
            this.renderWalking();
        }
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

    public void resetElapsedTime() {
        elapsedTime = 0;
    }

    public void setStartTime() {
        startTime = elapsedTime;
    }
}
