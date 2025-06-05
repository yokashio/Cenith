package cenith.assignment3;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class GridPanel extends JPanel {
    Grid grid;

    GridPanel(Grid grid) {
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / Grid.GRID_SIZE_X;
        for (int i = 0; i < Grid.GRID_SIZE_X; i++) {
            for (int j = 0; j < Grid.GRID_SIZE_Y; j++) {
                GridSpace tile = grid.getGrid()[i][j];
                g.setColor(switch (tile.state) {
                    case "Blank" -> Color.LIGHT_GRAY;
                    case "Speeder" -> new Color(51, 153, 51);
                    case "Lava" -> new Color(255, 153, 51);
                    case "Mud" -> new Color(139, 69, 19);
                    default -> Color.BLACK;
                });
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}