public class GridSpace {
    String type;
    int health;
    int move;

    GridSpace(String type) {
        this.type = type;
        switch (type) {
            case "Blank" -> {
                health = 0;
                move = -1;
            }
            case "Speeder" -> {
                health = -5;
                move = 0;
            }
            case "Lava" -> {
                health = -50;
                move = -10;
            }
            case "Mud" -> {
                health = -10;
                move = -5;
            }
            default -> throw new IllegalArgumentException("Invalid tile type :(");
        }
    }
}
