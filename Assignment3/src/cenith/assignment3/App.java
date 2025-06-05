package cenith.assignment3;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Grid grid = new Grid();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid World Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setContentPane(new GridPanel(grid));
            frame.setVisible(true);
        });

    }
}
