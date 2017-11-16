package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.g2d.*;

public class Skill {
    private String name;
    private int frame;
    private  float elapsetime=0f;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion texture;
    private Animation<TextureRegion> animation;
    public Skill(String atlasPath, String name) {
        this.name = name;
        this.atlas = new TextureAtlas(atlasPath);
        this.frame = atlas.getRegions().size;
    }

    public void setAtlas(String path) {
        atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> getSkillAction(float delta) {
        elapsetime += delta;
        if (elapsetime > 100) { elapsetime = 0; }
        System.out.println("elapsetime = " + elapsetime );
        animation = new Animation<TextureRegion>(0.2f, this.atlas.findRegions(name));
        return animation;
    }

    public float stateTime() {
        return elapsetime;
    }

    public int getFrame() {
        return frame;
    }
}
