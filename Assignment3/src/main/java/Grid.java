package main.java;

import java.util.Random;

public class Grid {
    public static final int GRID_SIZE = 50;
    private GridSpace[][] grid;
    public int objectiveAX;
    public int objectiveBX;

    public Grid() {
        Random rand = new Random();
        objectiveAX = rand.nextInt(GRID_SIZE);
        objectiveBX = rand.nextInt(GRID_SIZE);
        grid = generateTiles();
    }

    private GridSpace[][] generateTiles() {
        TileType[] types = TileType.values();
        Random rand = new Random();
        GridSpace[][] populatedGrid = new GridSpace[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                populatedGrid[i][k] = new GridSpace(types[rand.nextInt(types.length)]);
            }
        }
        return populatedGrid;
    }

    public GridSpace[][] getGrid() {
        return grid;
    }
}
