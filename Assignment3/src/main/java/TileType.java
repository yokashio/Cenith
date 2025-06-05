package main.java;

public enum TileType {
    BLANK(0, -1),
    SPEEDER(-5, 0),
    LAVA(-50, -10),
    MUD(-10, -5);

    public final int healthEffect;
    public final int moveEffect;

    TileType(int healthEffect, int moveEffect) {
        this.healthEffect = healthEffect;
        this.moveEffect = moveEffect;
    }
}
