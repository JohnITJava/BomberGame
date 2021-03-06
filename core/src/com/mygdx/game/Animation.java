package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animation {
    private Vector2 position;
    private Texture texture;
    private TextureRegion[] regions;
    private int framesCount;
    private float time;
    private float maxTime;
    private float timePerFrame;
    private boolean active;
    private float scale;
    private boolean infinity;

    public Animation(){
        this.position = new Vector2(0.0f, 0.0f);
        this.regions = null;
        this.framesCount = 0;
        this.time = 0.0f;
        this.maxTime = 0.0f;
        this.timePerFrame = 0.0f;
        this.active = false;
        this.scale = 1.0f;
        this.infinity = false;
    }

    public void activate(float x, float y, float scale, TextureRegion[] regions, float timePerFrame, boolean infinity){
        this.position.set(x, y);
        this.regions = regions;
        this.timePerFrame = timePerFrame;
        this.framesCount = regions.length;
        this.maxTime = timePerFrame * framesCount;
        this.time = 0.0f;
        this.active = true;
        this.scale = scale;
        this.infinity = infinity;
    }

    public void render (SpriteBatch batch){
        int frameIndex = (int)(time / timePerFrame);
        batch.draw(regions[frameIndex], position.x - Rules.CELL_HALF_SIZE, position.y - Rules.CELL_HALF_SIZE, Rules.CELL_HALF_SIZE, Rules.CELL_HALF_SIZE, Rules.CELL_SIZE, Rules.CELL_SIZE, scale, scale, 0.0f);
    }

    public TextureRegion getCurrentRegion(){
        int frameIndex = (int)(time / timePerFrame);
        return regions[frameIndex];
    }

    public void update(float dt) {
        time += dt;
        if (time >= maxTime) {
            if (!infinity) {
                active = false;
            } else time = 0.0f;
        }
    }

    public boolean isActive() {
        return active;
    }
}