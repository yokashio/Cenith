package cenith.assignment3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class PathSolver {
    private Grid grid;
    public List<Point> path = new ArrayList<>();

    public PathSolver(Grid grid) {
        this.grid = grid;
    }

    public void solve(int startX, int startY, int endX, int endY) {
        path.clear();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.priority));
        Map<String, Integer> visited = new HashMap<>();

        pq.add(new Node(startX, startY, new ArrayList<>(), 200, 450));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            String key = node.x + "," + node.y;

            // Track best (lowest priority) visit
            if (visited.containsKey(key) && visited.get(key) <= node.priority) continue;
            visited.put(key, node.priority);

            node.path.add(new Point(node.x, node.y));

            if (node.x == endX && node.y == endY && node.health > 0 && node.moves > 0) {
                path = node.path;
                return;
            }

            for (int[] dir : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
                int nx = node.x + dir[0], ny = node.y + dir[1];
                if (nx < 0 || ny < 0 || nx >= Grid.GRID_SIZE || ny >= Grid.GRID_SIZE) continue;

                GridSpace tile = grid.getGrid()[nx][ny];
                int newHealth = node.health + tile.health;
                int newMoves = node.moves + tile.move;

                if (newHealth < 0 || newMoves < 0) continue;

                int priority = -(newHealth + newMoves);
                List<Point> newPath = new ArrayList<>(node.path);
                pq.add(new Node(nx, ny, newPath, newHealth, newMoves, priority));
            }
        }
    }

    static class Node {
        int x, y, health, moves, priority;
        List<Point> path;

        Node(int x, int y, List<Point> path, int health, int moves) {
            this(x, y, path, health, moves, -(health + moves));
        }

        Node(int x, int y, List<Point> path, int health, int moves, int priority) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.health = health;
            this.moves = moves;
            this.priority = priority;
        }
    }
}