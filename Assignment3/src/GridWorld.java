import java.util.Random;

public class GridWorld {
    static final int GRID_SIZE_X = 50;
    static final int GRID_SIZE_Y = 50;

    public GridSpace[][] generateTiles() {
        String[] tiles = {"Blank", "Speeder", "Lava", "Mud"};
        Random rand = new Random();
        GridSpace[][] grid = new GridSpace[GRID_SIZE_X][GRID_SIZE_Y];
        for (int i = 0; i < GRID_SIZE_X; i++) {
            for (int k = 0; k < GRID_SIZE_Y; k++) {
                grid[i][k] = new GridSpace(tiles[rand.nextInt(tiles.length)]);
            }
        }
        return grid;
    }
}
