package cenith.assignment3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

class GUI extends JPanel {
    Grid grid;
    Player player;
    static int gridPixelOffset = 40;
    Color blankColor = new Color(200, 200, 200);
    Color speederColor = new Color(51, 153, 51);
    Color lavaColor = new Color(255, 153, 51);
    Color mudColor = new Color(139, 69, 19);
    Color objectiveColor = new Color(0, 0, 255);

    GUI(Grid grid, Player player) {
        this.grid = grid;
        this.player = player;
        this.setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (player.handleMovement(e.getKeyCode(), grid)) {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / Grid.GRID_SIZE;

        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int k = 0; k < Grid.GRID_SIZE; k++) {
                GridSpace tile = grid.getGrid()[i][k];
                g.setColor(switch (tile.state) {
                    case "Blank" -> blankColor;
                    case "Speeder" -> speederColor;
                    case "Lava" -> lavaColor;
                    case "Mud" -> mudColor;
                    default -> Color.BLACK;
                });
                g.fillRect(i * cellSize, k * cellSize + gridPixelOffset, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(i * cellSize, k * cellSize + gridPixelOffset, cellSize, cellSize);
            }
        }

        g.setColor(objectiveColor);
        Polygon triangleA = new Polygon();
        triangleA.addPoint(grid.objectiveAX * cellSize + cellSize / 2, gridPixelOffset);
        triangleA.addPoint(grid.objectiveAX * cellSize, gridPixelOffset + cellSize);
        triangleA.addPoint(grid.objectiveAX * cellSize + cellSize, gridPixelOffset + cellSize);
        g.fillPolygon(triangleA);

        Polygon triangleB = new Polygon();
        triangleB.addPoint(grid.objectiveBX * cellSize + cellSize / 2,
                (Grid.GRID_SIZE - 1) * cellSize + gridPixelOffset);
        triangleB.addPoint(grid.objectiveBX * cellSize, (Grid.GRID_SIZE - 1) * cellSize + gridPixelOffset + cellSize);
        triangleB.addPoint(grid.objectiveBX * cellSize + cellSize,
                (Grid.GRID_SIZE - 1) * cellSize + gridPixelOffset + cellSize);
        g.fillPolygon(triangleB);

        g.setColor(Color.RED);
        g.fillOval(player.x * cellSize, player.y * cellSize + gridPixelOffset, cellSize, cellSize);

        int uiPixelOffsetX = 5;
        int uiPixelOffsetY = 20;
        g.setColor(Color.BLACK);
        g.drawString("Health: " + player.health, uiPixelOffsetX, uiPixelOffsetY);
        g.drawString("Moves Left: " + player.moves, uiPixelOffsetX, uiPixelOffsetY + 15);

        int legendPixelOffsetX = 140;
        int legendPixelOffsetY = 20;

        g.setColor(blankColor);
        g.fillRect(legendPixelOffsetX, legendPixelOffsetY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -1 move", legendPixelOffsetX + 15, legendPixelOffsetY);

        g.setColor(speederColor);
        g.fillRect(legendPixelOffsetX, legendPixelOffsetY + 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -5 health", legendPixelOffsetX + 15, legendPixelOffsetY + 15);

        g.setColor(lavaColor);
        g.fillRect(legendPixelOffsetX + 100, legendPixelOffsetY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -50 health, -10 moves", legendPixelOffsetX + 115, legendPixelOffsetY);

        g.setColor(mudColor);
        g.fillRect(legendPixelOffsetX + 100, legendPixelOffsetY + 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -10 health, -5 moves", legendPixelOffsetX + 115, legendPixelOffsetY + 15);

        g.setColor(objectiveColor);
        g.fillRect(legendPixelOffsetX + 270, legendPixelOffsetY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= Start and Endpoint", legendPixelOffsetX + 285, legendPixelOffsetY);
    }
}