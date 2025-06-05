package cenith.assignment3;

public class PlayerController {
    public static boolean handleMovement(int keyCode, Player player, Grid grid) {
        int newX = player.x;
        int newY = player.y;

        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_LEFT -> newX = Math.max(player.x - 1, 0);
            case java.awt.event.KeyEvent.VK_RIGHT -> newX = Math.min(player.x + 1, Grid.GRID_SIZE - 1);
            case java.awt.event.KeyEvent.VK_UP -> newY = Math.max(player.y - 1, 0);
            case java.awt.event.KeyEvent.VK_DOWN -> newY = Math.min(player.y + 1, Grid.GRID_SIZE - 1);
            default -> { return false; }
        }

        // Prevent movement if no change
        if (newX == player.x && newY == player.y) return false;

        GridSpace tile = grid.getGrid()[newX][newY];
        int newHealth = player.health + tile.health;
        int newMoves = player.moves + tile.move;

        // Enforce solvability rules
        if (newHealth < 0 || newMoves < 0) return false;

        player.health = newHealth;
        player.moves = newMoves;
        player.x = newX;
        player.y = newY;

        return true;
    }
}
