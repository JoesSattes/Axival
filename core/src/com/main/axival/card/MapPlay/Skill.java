package com.main.axival.card.MapPlay;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class Skill {
    private String name;
    private int frame;
    private  float elapsetime=0f;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion texture;
    private Animation<TextureRegion> animation;
    public Skill(String atlasPath, String name) {
        this.name = name;
        this.frame = frame;
        this.atlas = new TextureAtlas(atlasPath);
        this.frame = atlas.getRegions().size;
    }

    public void setAtlas(String path) {
        atlas = new TextureAtlas(path);
    }

    public Animation<TextureRegion> getSkillAction(float deltaTime) {
        Array<TextureAtlas.AtlasRegion> array = atlas.findRegions(name);
        animation = new Animation<TextureRegion>((float)deltaTime/(float)array.size, array);
        return animation;
    }

    public void update(float delta) {
        this.elapsetime += delta;
        if (elapsetime > 100) { elapsetime = 0;}
    }
}
