package cenith.assignment3;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        Grid grid = new Grid();
        Player player = new Player(grid.objectiveAX, 0);
        int guiPixelScale = 15;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Assignment 3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Grid.GRID_SIZE * guiPixelScale, Grid.GRID_SIZE * guiPixelScale + GUI.gridPixelOffset);

            GUI gui = new GUI(grid, player);
            frame.setContentPane(gui);
            frame.setVisible(true);
            gui.requestFocusInWindow();
        });

    }
}
