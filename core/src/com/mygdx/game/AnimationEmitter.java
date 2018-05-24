package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;

public class AnimationEmitter {
    public enum AnimationType {
        EXPLOSION("explosion", 0.02f), TEST("testAnimation", 0.02f);

        private float timePerFrame;
        private String regionName;

        AnimationType(String regionName, float timePerFrame){
            this.regionName = regionName;
            this.timePerFrame = timePerFrame;
        }

        public static AnimationType getRandomType(){
            //Random random = new Random();
            return values()[MathUtils.random((values().length) - 1)];
        }
    }


    private final int MAX_COUNT = 100;
    private Animation[] animations; //масив анимаций

    private HashMap<AnimationType, TextureRegion[]> regions;

    private TextureRegion[] explosionRegions; //область на текстуре в памяти
    private TextureRegion[] testRegions; //область на текстуре в памяти


    public AnimationEmitter(TextureAtlas atlas) {
        animations = new Animation[MAX_COUNT];
        for (int i = 0; i < animations.length; i++) {
            animations[i] = new Animation();
    }

    regions = new HashMap<AnimationType, TextureRegion[]>();
    for (int a = 0; a < AnimationType.values().length; a++){
        AnimationType current = AnimationType.values()[a];
        TextureRegion[][] tempRegions = atlas.findRegion(current.regionName).split(Rules.CELL_SIZE, Rules.CELL_SIZE);
        TextureRegion[] currentRegions = new TextureRegion[tempRegions.length * tempRegions[0].length];
        int n = 0;
        for (int i = 0; i < tempRegions.length; i++) {
            for (int j = 0; j < tempRegions[0].length; j++) {
                currentRegions[n] = tempRegions[i][j];
                n++;
                }
            }
            regions.put(current, currentRegions);
        }
    }

    public void createAnimation(float x, float y, float scale, AnimationType type){
        for (int i = 0; i < animations.length; i++) {
            if (!animations[i].isActive()){
                animations[i].activate(x, y, scale, regions.get(type), type.timePerFrame, false);
                break;
            }
        }
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < animations.length; i++) {
            if (animations[i].isActive()){
                animations[i].render(batch);
            }
        }
    }

    public void update(float dt){
        for (int i = 0; i < animations.length; i++) {
            if (animations[i].isActive()){
                animations[i].update(dt);
            }
        }
    }
}
