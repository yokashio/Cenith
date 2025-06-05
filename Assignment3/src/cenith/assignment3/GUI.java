package cenith.assignment3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

class GUI extends JPanel {
    Grid grid;
    int playerPosX = 0;
    int playerPosY = 0;

    GUI(Grid grid) {
        this.grid = grid;
        this.setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> playerPosX = Math.max(playerPosX - 1, 0);
                    case KeyEvent.VK_DOWN -> playerPosX = Math.min(playerPosX + 1, Grid.GRID_SIZE_X - 1);
                    case KeyEvent.VK_LEFT -> playerPosY = Math.max(playerPosY - 1, 0);
                    case KeyEvent.VK_RIGHT -> playerPosY = Math.min(playerPosY + 1, Grid.GRID_SIZE_Y - 1);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / ((Grid.GRID_SIZE_X+Grid.GRID_SIZE_Y)/2);
        for (int i = 0; i < Grid.GRID_SIZE_X; i++) {
            for (int j = 0; j < Grid.GRID_SIZE_Y; j++) {
                GridSpace tile = grid.getGrid()[i][j];
                g.setColor(switch (tile.state) {
                    case "Blank" -> Color.LIGHT_GRAY;
                    case "Speeder" -> new Color(51, 153, 51); //green
                    case "Lava" -> new Color(255, 153, 51); //orange
                    case "Mud" -> new Color(139, 69, 19); //brown
                    default -> Color.BLACK;
                });
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
        g.setColor(Color.RED);
        g.fillOval(playerPosY * cellSize, playerPosX * cellSize, cellSize, cellSize);
    }
}