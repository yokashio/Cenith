package main.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

class GUI extends JPanel {
    Grid grid;
    Player player;
    PathSolver solver;
    JFrame frame;
    static int gridPixelOffset = 40;

    Color blankColor = new Color(200, 200, 200);
    Color speederColor = new Color(51, 153, 51);
    Color lavaColor = new Color(255, 153, 51);
    Color mudColor = new Color(139, 69, 19);
    Color objectiveColor = new Color(0, 0, 255);
    Color pathColor = new Color(255, 255, 0);

    GUI(Grid grid, Player player, PathSolver solver, JFrame frame) {
        this.grid = grid;
        this.player = player;
        this.solver = solver;
        this.frame = frame;
        this.setFocusable(true);

        // R to restart
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    App.startNewGame(frame);
                } else if (PlayerController.handleMovement(e.getKeyCode(), player, grid)) {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / Grid.GRID_SIZE;

        // Draw tiles
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int k = 0; k < Grid.GRID_SIZE; k++) {
                GridSpace tile = grid.getGrid()[i][k];
                g.setColor(switch (tile.type) {
                    case BLANK -> blankColor;
                    case SPEEDER -> speederColor;
                    case LAVA -> lavaColor;
                    case MUD -> mudColor;
                });
                g.fillRect(i * cellSize, k * cellSize + gridPixelOffset, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(i * cellSize, k * cellSize + gridPixelOffset, cellSize, cellSize);
            }
        }

        // Draw objective markers
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

                // Draw path overlays if path exists
        List<Point> path = solver.path;
        if (!path.isEmpty()) {
            g.setColor(pathColor);
            for (Point p : path) {
                g.fillRect(p.x * cellSize + cellSize / 4, p.y * cellSize + gridPixelOffset + cellSize / 4, cellSize / 2,
                        cellSize / 2);
            }
        }

        // Draw player
        g.setColor(Color.RED);
        g.fillOval(player.x * cellSize, player.y * cellSize + gridPixelOffset, cellSize, cellSize);

        // Player stats
        g.setColor(Color.BLACK);
        g.drawString("Health: " + player.health, 5, 20);
        g.drawString("Moves Left: " + player.moves, 5, 35);

        // Restart text
        g.setColor(Color.RED);
        g.drawString("Press 'R' to Restart", 600, 20);

        // "YOU DIED" if health or moves are negative or zero
        if (player.health <= 0 || player.moves <= 0) {
            g.setColor(Color.RED);
            g.drawString("YOU DIED", 75, 20);
        }

        // Legend
        int legendX = 150;
        int legendY = 20;

        g.setColor(blankColor);
        g.fillRect(legendX, legendY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -1 move", legendX + 15, legendY);

        g.setColor(speederColor);
        g.fillRect(legendX, legendY + 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -5 health", legendX + 15, legendY + 15);

        g.setColor(lavaColor);
        g.fillRect(legendX + 100, legendY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -50 health, -10 moves", legendX + 115, legendY);

        g.setColor(mudColor);
        g.fillRect(legendX + 100, legendY + 5, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= -10 health, -5 moves", legendX + 115, legendY + 15);

        g.setColor(objectiveColor);
        g.fillRect(legendX + 270, legendY - 10, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("= Start and Endpoint", legendX + 285, legendY);
    }
}