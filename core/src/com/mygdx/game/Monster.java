package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monster {
    private TextureRegion texture;
    private int cellX, cellY;
    private float rotation;
    private float speed;

    public Monster(TextureAtlas atlas){
        this.texture = atlas.findRegion("monster");
        cellX = 14;
        cellY = 7;
        speed = 100.0f;
        rotation = 180.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, cellX * 80, cellY * 80);
    }

    public void  update(float dt){

    }
}
