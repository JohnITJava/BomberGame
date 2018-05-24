package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {
    private static final int MAP__CELLS_WIDTH = 16;
    private static final int MAP__CELLS_HEIGHT = 9;

    public static final int CELL_WALL = 1;
    public static final int CELL_BOX = 2;
    private static final int CELL_EMPTY = 0;


    private byte[][] data;
    private TextureRegion textureGrass, textureWall, textureBox;


    public Map(TextureAtlas atlas){
        data = new byte[MAP__CELLS_WIDTH][MAP__CELLS_HEIGHT];
        textureBox = atlas.findRegion("box");
        textureGrass = atlas.findRegion("grass");
        textureWall = atlas.findRegion("wall");

        for (int i = 0; i < MAP__CELLS_WIDTH; i++) {
            data[i][0] = CELL_WALL;
            data[i][MAP__CELLS_HEIGHT - 1] = CELL_WALL;
        }

        for (int i = 0; i < MAP__CELLS_HEIGHT; i++) {
            data[0][i] = CELL_WALL;
            data[MAP__CELLS_WIDTH - 1][i] = CELL_WALL;
        }

        for (int i = 0; i < MAP__CELLS_WIDTH; i++) {
            for (int j = 0; j < MAP__CELLS_HEIGHT; j++) {
                if (i % 2 == 0 && j % 2 == 0 && data[i][j] == CELL_EMPTY) {
                    data[i][j] = CELL_BOX;
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < MAP__CELLS_WIDTH; i++) {
            for (int j = 0; j < MAP__CELLS_HEIGHT; j++) {
                batch.draw(textureGrass, i * Rules.CELL_SIZE, j * Rules.CELL_SIZE);
                if (data[i][j] == CELL_WALL){
                    batch.draw(textureWall, i * Rules.CELL_SIZE, j * Rules.CELL_SIZE);
                }
                if (data[i][j] == CELL_BOX){
                    batch.draw(textureBox, i * Rules.CELL_SIZE, j * Rules.CELL_SIZE);
                }
            }
        }
    }

    public boolean isCellEmpty(int cellX, int cellY){
        return data[cellX][cellY] == CELL_EMPTY;
    }

    public boolean isCellDestructable(int cellX, int cellY){
        return data[cellX][cellY] == CELL_BOX;
    }

    public void clearCell(int cellX, int cellY){
        data[cellX][cellY] = CELL_EMPTY;
    }

    public void update(float dt){

    }
}
