import java.util.Random;

public class Maze {
    private char[][] grid;
    private final int size;
    private final int spikeCount;


    public Maze(int size, int spikeCount) {
        this.size = size;
        this.spikeCount = spikeCount;
        this.grid = new char[size][size];
        generateValidMaze();
    }


    private void generateValidMaze() {
        do {
            initializeMaze();
            placeExit();
            placeSpikes();
        } while (!isMazeSolvable());
    }


    private void initializeMaze() {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (rand.nextDouble() < 0.2) {
                    grid[i][j] = '#';
                } else {
                    grid[i][j] = '.';
                }
            }
        }
        grid[0][0] = '*';
    }


    private void placeExit() {
        grid[size - 1][size - 1] = 'E';
    }


    private void placeSpikes() {
        Random rand = new Random();
        int placedSpikes = 0;
        while (placedSpikes < spikeCount) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if (grid[x][y] == '.' && !(x == 0 && y == 0) && !(x == size - 1 && y == size - 1)) {
                grid[x][y] = 'S';
                placedSpikes++;
            }
        }
    }


    private boolean isMazeSolvable() {
        boolean[][] visited = new boolean[size][size];
        return dfs(0, 0, visited);
    }


    private boolean dfs(int x, int y, boolean[][] visited) {
        if (x < 0 || x >= size || y < 0 || visited[x][y]) {
            return false;
        }
        if (grid[x][y] == 'E') {
            return true;
        }
        visited[x][y] = true;
        return dfs(x + 1, y, visited) || dfs(x - 1, y, visited) || dfs(x, y + 1, visited) || dfs(x, y - 1, visited);
    }


    public char getCell(int x, int y) {
        return grid[x][y];
    }


    public void setCell(int x, int y, char value) {
        grid[x][y] = value;
    }




    public void displayMaze(Player player) {
        for (int i = 0; i < size; i++) {


            for (int j = 0; j < size; j++) {
                System.out.print("+---");
            }
            System.out.println("+");


            for (int j = 0; j < size; j++) {
                if (i == player.getX() && j == player.getY()) {
                    System.out.print("| * ");
                } else if (grid[i][j] == 'E') {
                    System.out.print("| E ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }




        for (int j = 0; j < size; j++) {
            System.out.print("+---");
        }
        System.out.println("+");


        if (player.getLives() == 3) {
            System.out.println("You have 3 lives. You're in perfect condition!");
        }
        if (player.getLives() == 2) {
            System.out.println("You have 2 lives left. Don't mess up.");
        }
        if (player.getLives() == 1) {
            System.out.println("You have 1 life left. Be very careful of where you go.");
        }
    }
}

