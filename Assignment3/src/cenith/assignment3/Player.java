package cenith.assignment3;

import java.awt.event.KeyEvent;

public class Player {
    int x = 0;
    int y = 0;
    int health = 200;
    int moves = 450;

    Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    boolean handleMovement(int keyCode, Grid grid) {
        int newX = this.x;
        int newY = this.y;
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> newX = Math.max(this.x - 1, 0);
            case KeyEvent.VK_RIGHT -> newX = Math.min(this.x + 1, Grid.GRID_SIZE - 1);
            case KeyEvent.VK_UP -> newY = Math.max(this.y - 1, 0);
            case KeyEvent.VK_DOWN -> newY = Math.min(this.y + 1, Grid.GRID_SIZE - 1);
            default -> {
                return false;
            }
        }
        if (newX != this.x || newY != this.y) {
            GridSpace tile = grid.getGrid()[newX][newY];
            this.health += tile.health;
            this.moves += tile.move;
            this.x = newX;
            this.y = newY;
            return true;
        }
        return false;
    }
}
