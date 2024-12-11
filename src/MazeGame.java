import java.util.Scanner;


public class MazeGame {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
        int spikeCount;
        while (true) {
            System.out.print("Enter your difficulty (Easy, Medium, Hard): ");
            String difficulty = scanner.nextLine();
            if (difficulty.equalsIgnoreCase("easy")) {
                size = 3;
                spikeCount = 2;
                break;
            } else if (difficulty.equalsIgnoreCase("medium")) {
                size = 5;
                spikeCount = 6;
                break;
            } else if (difficulty.equalsIgnoreCase("hard")) {
                size = 7;
                spikeCount = 8;
                break;
            } else {
                System.out.println("Invalid difficulty");
            }
        }


        Maze maze = new Maze(size, spikeCount);
        Player player = new Player(0, 0);


        while (player.isAlive()) {


            maze.displayMaze(player);
            System.out.print("Enter your move (up, down, left, right): ");
            String direction = scanner.next();


            int newX = player.getX();
            int newY = player.getY();


            direction = direction.toLowerCase();
            if (direction.equalsIgnoreCase("up")) {
                newX--;
            } else if (direction.equalsIgnoreCase("down")) {
                newX++;
            } else if (direction.equalsIgnoreCase("left")) {
                newY--;
            } else if (direction.equalsIgnoreCase("right")) {
                newY++;
            } else {
                System.out.println("Invalid move! Use up, down, left, or right.");
            }




            if (newX < 0 || newX >= size || newY < 0 || newY >= size) {
                player.loseLife();
                if (player.getLives() == 2) {
                    System.out.println("You hit your head on a wall and you start to feel dizzy.");
                }
                if (player.getLives() == 1) {
                    System.out.println("You hit your head on a wall and now you're bleeding.");
                }
                if (player.getLives() == 0) {
                    System.out.print("You hit your head on a wall and busted it open.");
                }
            } else if (maze.getCell(newX, newY) == 'S') {
                player.loseLife();
                if (player.getLives() == 2) {
                    System.out.println("Ouch. You stepped on a spike!");
                }
                if (player.getLives() == 1) {
                    System.out.println("You stepped on a spike and now you have a big scar on your leg.");
                }
                if (player.getLives() == 0) {
                    System.out.print("You stepped on a spike and it pierced through your body.");
                }
            } else if (maze.getCell(newX, newY) == 'E') {
                System.out.println("Congratulations! You've reached the exit!");
                break;
            } else {
                maze.setCell(player.getX(), player.getY(), '.');
                player.move(newX, newY);
            }


            if (!player.isAlive()) {
                System.out.print(" Game over.");
            }
        }


        scanner.close();
    }
}




