package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;

public class BomberGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Bomberman player;
	private Monster monster;
	private AnimationEmitter animationEmitter;
	private TextureAtlas atlas;
	private BitmapFont font32;

	private Map map;

	@Override
	public void create(){
		batch = new SpriteBatch();
		atlas = new TextureAtlas("game.pack");
		map = new Map(atlas);
		player = new Bomberman(map, atlas);
		monster = new Monster(atlas);
		animationEmitter = new AnimationEmitter(atlas);

		FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 32;
		fontParameter.color = Color.WHITE;
		fontParameter.borderWidth = 1;
		fontParameter.borderColor = Color.BLACK;
		fontParameter.shadowOffsetX = 3;
		fontParameter.shadowOffsetY = 3;
		fontParameter.shadowColor = Color.BLACK;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("gomarice.ttf"));
		font32 = generator.generateFont(fontParameter);
		generator.dispose();
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0.48f, 0.07f, 0.47f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch);
		player.render(batch);
		monster.render(batch);
		animationEmitter.render(batch);
		font32.draw(batch, "Bomber Game - 2018", 100, 700);
		batch.end();
	}

	public void update(float dt){
		map.update(dt);
		player.update(dt);
		monster.update(dt);
		animationEmitter.update(dt);
		if (Gdx.input.justTouched()){
			animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY(), MathUtils.random(1.0f, 5.0f), AnimationEmitter.AnimationType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY() - 80, AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY() - 160, AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY() + 80 , AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY() + 160 , AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX() - 80, 720 - Gdx.input.getY(), AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX() - 160, 720 - Gdx.input.getY(), AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX() + 80, 720 - Gdx.input.getY(), AnimationEmitter.AnimtionType.EXPLOSION);
//            animationEmitter.createAnimation(Gdx.input.getX() + 160, 720 - Gdx.input.getY(), AnimationEmitter.AnimtionType.EXPLOSION);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
