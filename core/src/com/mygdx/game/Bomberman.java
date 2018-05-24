package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bomberman{
    public enum State{
        IDLE(0), MOVE(1);

        private int animationIndex;

        State(int animationIndex) {
            this.animationIndex = animationIndex;
        }
    }

    private Map map;
    //private TextureRegion[] animationRegions;
    private Animation[] animations;
    private Vector2 position;
    private Vector2 velocity;
    private float pathcounter;
    private float speed;
    private State currentState;

    public int getCellX(){
        return (int)(position.x) / Rules.CELL_SIZE;
    }

    public int getCellY(){
        return (int)(position.y) / Rules.CELL_SIZE;
    }

    public Bomberman(Map map, TextureAtlas atlas){
        this.map = map;
        this.position = new Vector2(120.0f, 120.0f);
        this.pathcounter = -1;
        this.velocity = new Vector2(0.0f, 0.0f);
        this.speed = 200.0f;
        this.animations = new Animation[State.values().length];
//        this.animations[State.IDLE.animationIndex].activate(0, 0, 1, new TextureRegion(atlas.findRegion("bomberA")).split(Rules.CELL_SIZE, Rules.CELL_SIZE)[State.IDLE.animationIndex], 0.1f, true);
//        this.animations[State.MOVE.animationIndex].activate(0, 0, 1, new TextureRegion(atlas.findRegion("bomberA")).split(Rules.CELL_SIZE, Rules.CELL_SIZE)[State.MOVE.animationIndex], 0.1f, true);
        this.currentState = State.IDLE;
        for (int i = 0; i < State.values().length; i++) {
            this.animations[i] = new Animation();
            this.animations[State.values()[i].animationIndex].activate(0, 0, 1, new TextureRegion(atlas.findRegion("bomberA")).split(Rules.CELL_SIZE, Rules.CELL_SIZE)[State.values()[i].animationIndex], 0.1f, true);
        }
    }

    public void render(SpriteBatch batch){
        //batch.draw(texture, position.x - Rules.CELL_HALF_SIZE, position.y - Rules.CELL_HALF_SIZE);
        batch.draw(animations[currentState.animationIndex].getCurrentRegion(), position.x - Rules.CELL_HALF_SIZE, position.y - Rules.CELL_HALF_SIZE);

    }

    public void update(float dt){
        animations[currentState.animationIndex].update(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.D) && pathcounter < 0.0f && map.isCellEmpty(getCellX() + 1, getCellY())) {
                velocity.set(speed, 0.0f);
                pathcounter = 0.1f;
                currentState = State.MOVE;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && pathcounter < 0.0f && map.isCellEmpty(getCellX() - 1, getCellY())) {
            velocity.set(-speed, 0.0f);
            pathcounter = 0.1f;
            currentState = State.MOVE;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && pathcounter < 0.0f && map.isCellEmpty(getCellX(), getCellY() + 1)) {
            velocity.set(0.0f, speed);
            pathcounter = 0.1f;
            currentState = State.MOVE;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && pathcounter < 0.0f && map.isCellEmpty(getCellX(), getCellY() - 1)) {
            velocity.set(0.0f, -speed);
            pathcounter = 0.1f;
            currentState = State.MOVE;
        }

        if (pathcounter > 0.0f){

            position.mulAdd(velocity, dt);
            pathcounter += velocity.len() * dt;
            if (pathcounter > Rules.CELL_SIZE){
                position.x = getCellX() * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE;
                position.y = getCellY() * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE;
                pathcounter = -1.0f;
                currentState = State.IDLE;
            }
        }
    }
}
