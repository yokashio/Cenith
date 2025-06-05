package cenith.assignment3;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Grid grid = new Grid();
        int guiPixelScale = 15;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid World Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Grid.GRID_SIZE_X*guiPixelScale, Grid.GRID_SIZE_Y*guiPixelScale);
            frame.setContentPane(new GUI(grid));
            frame.setVisible(true);
        });

    }
}
