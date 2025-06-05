# Assignment 3 – Take Home Exercise

A grid represents a 50x50 world made up of tiles with different effects which have been randomly generated positionally. The player must navigate from a randomly placed starting point (`objectiveA`) at the top of the grid to an endpoint (`objectiveB`) at the bottom.

## Features

- If possible, the level is automatically solved using Dijkstra’s algorithm.
- A visualized path appears if solvable.
- Press `R` to regenerate a new grid and restart if no solution presents itself.

## Tile Types

| Tile      | Health    | Moves    | Color      |
|-----------|-----------|----------|------------|
| Blank     |   0       |   -1     | Light gray |
| Speeder   |  -5       |    0     | Green      |
| Lava      | -50       |  -10     | Orange     |
| Mud       | -10       |   -5     | Brown      |

## Controls

- **Arrow keys** – Move the player.
- **R** – Restart the game with a new random grid.

## Requirements

- Java 17 or later

## Compile & Run

From the root directory:

```bash
# Compile
javac -d out src/main/java/*.java

# Run
java -cp out main.java.App
```

Or if using an IDE like IntelliJ or VS Code, just run App.java.