package main.java;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Assignment 3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            startNewGame(frame);
            frame.setVisible(true);
        });
    }

    static void startNewGame(JFrame frame) {
        Grid grid = new Grid();

        Player player = new Player(grid.objectiveAX, 0);
        PathSolver solver = new PathSolver(grid);
        solver.solve(grid.objectiveAX, 0, grid.objectiveBX, Grid.GRID_SIZE - 1);

        int guiPixelScale = 15;
        frame.setSize(Grid.GRID_SIZE * guiPixelScale, Grid.GRID_SIZE * guiPixelScale + GUI.gridPixelOffset);

        // Refresh the GUI
        GUI newGui = new GUI(grid, player, solver, frame);
        frame.setContentPane(newGui);
        frame.revalidate();
        frame.repaint();
        newGui.requestFocusInWindow();
    }
}
