package main.java;
public class GridSpace {
    public TileType type;

    public GridSpace(TileType type) {
        this.type = type;
    }

    public int getHealthEffect() {
        return type.healthEffect;
    }

    public int getMoveEffect() {
        return type.moveEffect;
    }
}
