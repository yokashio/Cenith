package main.java;

import java.awt.event.KeyEvent;

public class PlayerController {
    public static boolean handleMovement(int keyCode, Player player, Grid grid) {
        int dx = 0, dy = 0;

        switch (keyCode) {
            case KeyEvent.VK_LEFT -> dx = -1;
            case KeyEvent.VK_RIGHT -> dx = 1;
            case KeyEvent.VK_UP -> dy = -1;
            case KeyEvent.VK_DOWN -> dy = 1;
            default -> { 
                return false; // Not a movement key
            } 
        }

        return move(player, grid, dx, dy);
    }

    public static boolean move(Player player, Grid grid, int dx, int dy) {
        int newX = Math.max(0, Math.min(Grid.GRID_SIZE - 1, player.x + dx));
        int newY = Math.max(0, Math.min(Grid.GRID_SIZE - 1, player.y + dy));

        GridSpace tile = grid.getGrid()[newX][newY];
        player.health += tile.getHealthEffect();
        player.moves += tile.getMoveEffect();
        player.x = newX;
        player.y = newY;

        return true;
    }
}
