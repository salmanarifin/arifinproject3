public class Player {
    private int x;
    private int y;
    private int lives;


    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.lives = 3;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getLives() {
        return lives;
    }


    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }


    public void loseLife() {
        lives--;
    }


    public boolean isAlive() {
        return lives > 0;
    }
}

