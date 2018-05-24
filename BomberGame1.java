package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BomberGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	float x, y;
	float vx, vy;
	float ax, ay;
	float angle;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("car.png");
		x = 200;
		y = 200;
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0.48f, 0.07f, 0.47f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x - 40, y - 40, 40, 40, 80, 80, 1, 1, (float) Math.toDegrees(angle), 0, 0, 80, 80, false, false);
		batch.end();
	}

	public void update(float dt){
		ax = 0;
		ay = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			ax = (float) Math.cos(angle) * 300;
			ay = (float) Math.sin(angle) * 300;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			angle += 3.14f * dt;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			angle -= 3.14f * dt;
		}

		vx += ax * dt;
		vy += ay * dt;

		vx *= 0.985f;
		vy *= 0.985f;

		x += vx * dt;
		y += vy * dt;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
