package cenith.assignment3;

import java.util.Random;

public class Grid {
    static final int GRID_SIZE = 50;
    private GridSpace[][] grid;
    int objectiveAX;
    int objectiveBX;

    Grid() {
        Random rand = new Random();
        //Set objective locations
        objectiveAX = rand.nextInt(GRID_SIZE);
        objectiveBX = rand.nextInt(GRID_SIZE);
        grid = generateTiles();
    }

    private GridSpace[][] generateTiles() {
        String[] tiles = { "Blank", "Speeder", "Lava", "Mud" };
        Random rand = new Random();
        GridSpace[][] populatedGrid = new GridSpace[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                populatedGrid[i][k] = new GridSpace(tiles[rand.nextInt(tiles.length)]);
            }
        }
        return populatedGrid;
    }

    public GridSpace[][] getGrid() {
        return grid;
    }
}
